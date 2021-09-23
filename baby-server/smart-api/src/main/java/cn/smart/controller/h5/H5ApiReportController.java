package cn.smart.controller.h5;

import cn.smart.bo.WeekReportBO;
import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.api.result.ApiResult;
import cn.smart.entity.PersonDO;
import cn.smart.service.IPersonService;
import cn.smart.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/h5/api/report")
public class H5ApiReportController {


    @Autowired
    private IReportService reportService;

    @AuthIgnore
    @RequestMapping("/week")
    public ApiResult getWeekReport(@RequestParam(value = "id") Long id) {

        WeekReportBO  report = reportService.queryWeekReportById(id);

        return new ApiResult(report);
    }
}
