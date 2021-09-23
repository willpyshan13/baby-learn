package cn.smart.support;

import cn.smart.common.annotation.Logs;
import cn.smart.common.spring.SpringContextUtils;
import cn.smart.common.utils.WebUtil;
import cn.smart.entity.SysLogsDO;
import cn.smart.service.ISysLogsService;
import cn.smart.utils.ShiroUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
public class SysLogsAspectJ {

    private static final Logger log = LogManager.getLogger(SysLogsAspectJ.class);

    private final NamedThreadLocal<SysLogsDO> currentLogsThreadLocal = new NamedThreadLocal<>("ThreadLocalCurrentULogs");
    private ExecutorService executorService = Executors.newCachedThreadPool();
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

        if (signature.getMethod().getDeclaredAnnotation(Logs.class) != null && ShiroUtils.getUserEntity() != null) {
            Long uid = ShiroUtils.getUserId();
            String title = signature.getMethod().getDeclaredAnnotation(Logs.class).value();
            String requestPath = request.getServletPath();
            String methodName = signature.getMethod().getName();
            String parameters = null;
            try{
                parameters = JSONObject.toJSONString(point.getArgs());
            } catch (Exception e) {
                log.error("Set parameters error. {}", e.getMessage());                
            }
            String clientIp = WebUtil.getHost(request);
            String userAgent = request.getHeader("USER-AGENT");

            SysLogsDO logs = new SysLogsDO();
            logs.setTitle(title);
            logs.setRequestUrl(requestPath);
            logs.setMethod(methodName);
            logs.setParameters(parameters);
            logs.setClientHost(clientIp);
            logs.setUserAgent(userAgent);
            logs.setCreateBy(Long.valueOf(uid.toString()));
            logs.setUpdateBy(Long.valueOf(uid.toString()));
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
        synchronized(SysLogsAspectJ.class) {
            if (this.sysLogsService == null) {
                Map<String, ISysLogsService> map = SpringContextUtils.getBeansOfType(ISysLogsService.class);
                Iterator var4 = map.keySet().iterator();

                while(var4.hasNext()) {
                    String key = (String)var4.next();
                    if (key.toLowerCase().contains("syslogs")) {
                        this.sysLogsService = (ISysLogsService)map.get(key);
                    }
                }
            }
        }
        if (logs.getId() == null) {
            this.sysLogsService.save(logs);
        } else {
            this.sysLogsService.update(logs);
        }
    }
}