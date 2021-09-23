package cn.smart.controller.h5;

import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.Validator;
import cn.smart.condition.PageviewsQueryCondition;
import cn.smart.entity.PageviewsDO;
import cn.smart.service.IPageviewsService;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/h5/api")
public class H5ApiPageviewsController {

    private Logger logger = LogManager.getLogger(H5ApiPageviewsController.class);

    @Autowired
    private IPageviewsService pageviewsService;

    @AuthIgnore
    @PostMapping("/record")
    public ApiResult record(@RequestParam("type") Integer type,
                            @RequestParam("url") String url,
                            @RequestParam("serialNumber") String serialNumber,
                            @RequestParam("channel") Integer channel,
                            @RequestParam(value = "personId", required = false) Long personId) {

        if (Validator.isEmpty(type)) {
            return new ApiResult(-1, ApiStatus.SC_PAGEVIEWS_TYPE_NULL, "访问页面类型不能为空");
        }
        if (Validator.isEmpty(url)) {
            return new ApiResult(-1, ApiStatus.SC_PAGEVIEWS_URL_NULL, "访问页面地址不能为空");
        }
        if (Validator.isEmpty(serialNumber)) {
            return new ApiResult(-1, ApiStatus.SC_PAGEVIEWS_SERIAL_NUMBER_NULL, "访问设备序列号不能为空");
        }
        if (Validator.isEmpty(channel)) {
            return new ApiResult(-1, ApiStatus.SC_PAGEVIEWS_CHANNEL_NULL, "访问渠道不能为空");
        }

        PageviewsQueryCondition query = new PageviewsQueryCondition();
        Date currentTime = new Date();
        query.setCreateDay(DateUtils.format(currentTime, "yyyy-MM-dd"));
        query.setSerialNumber(serialNumber);
        query.setChannel(channel);
        query.setType(type);
        int total = pageviewsService.queryTotal(query);

        if (total == 0) {
            PageviewsDO pageviewsDO = new PageviewsDO();
            if (Validator.isNotEmpty(personId)) {
                pageviewsDO.setPersonId(personId);
            }
            pageviewsDO.setType(type);
            pageviewsDO.setChannel(channel);
            pageviewsDO.setUrl(url);
            pageviewsDO.setSerialNumber(serialNumber);
            pageviewsDO.setCreateTime(DateUtils.format(currentTime, "yyyy-MM-dd HH:mm:ss"));

            try {
                pageviewsService.save(pageviewsDO);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "操作失败");
            }
        } else {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR,"访问记录已存在");
        }
        return new ApiResult("操作成功");
    }
}
