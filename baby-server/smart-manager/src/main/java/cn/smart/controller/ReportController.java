package cn.smart.controller;

import cn.smart.bo.ClassInfoBO;
import cn.smart.common.utils.R;
import cn.smart.condition.ClassInfoQueryCondition;
import cn.smart.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ye
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IReportService reportService;

    @RequestMapping("/week")
    @ResponseBody
    public R generateWeekReport(@RequestBody ClassInfoBO classInfoBO) {
        return reportService.generateWeekReport(classInfoBO.getClassId(), classInfoBO.getCourseIds());
    }
}
