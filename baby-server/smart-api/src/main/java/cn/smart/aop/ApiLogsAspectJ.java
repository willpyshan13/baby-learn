package cn.smart.aop;

import cn.smart.common.annotation.LoginUser;
import cn.smart.common.annotation.Logs;
import cn.smart.common.spring.SpringContextUtils;
import cn.smart.common.utils.WebUtil;
import cn.smart.entity.SysLogsDO;
import cn.smart.entity.PersonDO;
import cn.smart.service.ISysLogsService;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ye
 * @date 2017/11/19 下午10:10
 */
@Aspect
@Component
public class ApiLogsAspectJ {

    private static final Logger log = LogManager.getLogger(ApiLogsAspectJ.class);

    private final NamedThreadLocal<SysLogsDO> currentLogsThreadLocal = new NamedThreadLocal<>("ThreadLocalCurrentApiLogs");
    protected ExecutorService executorService = Executors.newCachedThreadPool();
    private ISysLogsService sysLogsService;


    @Pointcut("execution(* cn.smart.controller..*(..))")
    public void controller() { }

    @Pointcut("execution(* cn.smart.service..*(..))")
    public void service() { }

    @Before("controller()")
    public void controller(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        PersonDO person = new PersonDO();

        Map<String, Object> paramsMap = new HashMap<>(16);
        Parameter[] params = signature.getMethod().getParameters();
        int i = -1;
        for (Parameter p : params) {
            i += 1;
            if (p.getAnnotation(LoginUser.class) != null && i != -1) {
               person = (PersonDO) point.getArgs()[i];
            }
            if (i != -1 && p.getAnnotation(RequestParam.class) != null) {
                paramsMap.put(p.getAnnotation(RequestParam.class).value(), point.getArgs()[i]);
            }
        }

        if (signature.getMethod().getDeclaredAnnotation(Logs.class) != null && person.getPersonId() != null) {

            String title = signature.getMethod().getDeclaredAnnotation(Logs.class).value();
            String requestPath = request.getServletPath();
            String methodName = signature.getMethod().getName();
            String parameters = JSONObject.toJSONString(paramsMap);
            String clientIp = WebUtil.getHost(request);
            String userAgent = request.getHeader("USER-AGENT");

            SysLogsDO logs = new SysLogsDO();
            logs.setTitle(title);
            logs.setRequestUrl(requestPath);
            logs.setMethod(methodName);
            logs.setParameters(parameters);
            logs.setClientHost(clientIp);
            logs.setUserAgent(userAgent);
            logs.setCreateBy(person.getPersonId());
            logs.setUpdateBy(person.getPersonId());
            logs.setCreateTime(new Date());
            logs.setUpdateTime(new Date());
            this.currentLogsThreadLocal.set(logs);
        }
    }

    @AfterReturning(value = "controller()", returning = "returnValue")
    public void afterReturning(Object returnValue) {
        SysLogsDO logs = this.currentLogsThreadLocal.get();
        if (logs != null) {
            logs.setResult(JSONObject.toJSONString(returnValue));
            this.executorService.execute(() -> {
                try {
                    saveLogs(logs);
                } catch (Exception var2) {
                    log.error("Save event log cause error :", var2);
                }
            });
        }
        this.currentLogsThreadLocal.remove();
    }

    private void saveLogs(SysLogsDO logs) {
        synchronized(ApiLogsAspectJ.class) {
            if (this.sysLogsService == null) {
                Map<String, ISysLogsService> map = SpringContextUtils.getBeansOfType(ISysLogsService.class);
                Iterator var4 = map.keySet().iterator();

                while(var4.hasNext()) {
                    String key = (String)var4.next();
                    if (key.toLowerCase().contains("syslogs")) {
                        this.sysLogsService = map.get(key);
                    }
                }
            }
        }
        try {
            if (logs.getId() == null) {
                this.sysLogsService.saveApiLog(logs);
                log.info("保存操作日志.");
            } else {
                this.sysLogsService.updateApiLog(logs);
                log.info("更新操作日志.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}