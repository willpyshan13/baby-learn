package cn.smart.controller.mobile;

import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.annotation.Logs;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.Validator;
import cn.smart.entity.PersonDO;
import cn.smart.entity.UsageLogDO;
import cn.smart.service.IUsageLogService;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mobile/usage/")
public class ApiAppUsageLogController {

    private static final Logger logger = LogManager.getLogger(ApiAppUsageLogController.class);

    @Autowired
    private IUsageLogService usageLogService;


    @PostMapping("/log")
    @Logs(value = "记录用户使用日志")
    public ApiResult usageLog(@LoginUser PersonDO personDO,
                              @RequestParam("name") String name,
                              @RequestParam("type") Integer type,
                              @RequestParam("startTime") String startTime, // 开始时间
                              @RequestParam("endTime") String endTime,    // 结束时间
                              @RequestParam(value = "duration", required = false) Long duration) {

        if (Validator.isEmpty(personDO.getPersonId())) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 失效, 请重新登录");
        }
        if (Validator.isEmpty(name)) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "记录名称不能为空");
        }
        if (Validator.isEmpty(type)) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "记录类型不能为空");
        }
        if (Validator.isEmpty(startTime)) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "开始时间不能为空");
        }
        if (Validator.isEmpty(endTime)) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "结束时间不能为空");
        }

        UsageLogDO usageLogDO = new UsageLogDO();
        usageLogDO.setName(name);
        usageLogDO.setType(type);
        usageLogDO.setEventType(1);
        usageLogDO.setUserId(personDO.getPersonId());
        try {
            Date sd = DateUtils.parse(startTime, "yyyy-MM-dd HH:mm:ss");
            Date ed = DateUtils.parse(endTime, "yyyy-MM-dd HH:mm:ss");
            usageLogDO.setStartTime(sd);
            usageLogDO.setEndTime(ed);
            assert ed != null;
            assert sd != null;
            if (Validator.isEmpty(duration)) {
                usageLogDO.setDuration(ed.getTime() - sd.getTime());
            } else {
                usageLogDO.setDuration(duration);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        usageLogDO.setCreateTime(new Date());
        usageLogService.save(usageLogDO);

        return new ApiResult("操作成功");
    }

    /**
     * 保存用户使用记录
     * @param logs 使用记录集合 List
     * personId 用户id
     * name 记录名称
     * type 记录类型
     * eventType 事件类型 1 ： 停留事件 2 ： 点击事件
     * startTime 停留事件 开始时间
     * endTime 停留事件 结束时间
     * number 点击事件 点击次数
     * duration 停留事件 时长
     * @return result
     */
    @AuthIgnore
    @PostMapping("/v1/log")
    @Logs(value = "记录用户使用日志v1")
    public ApiResult useLog(@RequestParam("logs") String logs) {

        try {
            List listUsageLog = new ArrayList<UsageLogDO>(16);
            listUsageLog = JSONObject.parseObject(logs, listUsageLog.getClass());
            logger.info("批量插入用户操作记录： {}", listUsageLog);
            usageLogService.saveBatch(listUsageLog);
        } catch (Exception e) {
            logger.error("保存用户使用记录失败！ {}", e);
            return new ApiResult(-1, "记录保存失败", e.getMessage());
        }

        return new ApiResult("操作成功");
    }

}
