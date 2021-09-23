package cn.smart.controller;

import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.TeacherQueryCondition;
import cn.smart.entity.TeacherDO;
import cn.smart.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/list")
    @ResponseBody
    public R list(TeacherQueryCondition teacherQueryCondition) {
        teacherQueryCondition.setOrderField("create_time");
        teacherQueryCondition.setOrder("desc");
        List<TeacherDO> teacherList = teacherService.queryList(teacherQueryCondition);
        int total = teacherService.queryTotal(teacherQueryCondition);
        PageUtils pageUtils = new PageUtils(teacherList, total, teacherQueryCondition.getLimit(), teacherQueryCondition.getPage());
        return R.ok().put("teacherList", pageUtils);
    }

    @PostMapping("/add")
    @ResponseBody
    @Logs(value = "新增教师")
    public R add(@RequestBody TeacherDO teacherDO) {
        teacherService.save(teacherDO);
        return R.ok();
    }

    @PostMapping("/update")
    @ResponseBody
    @Logs(value = "更新教师信息")
    public R update(@RequestBody TeacherDO teacherDO) {
        teacherService.update(teacherDO);
        return R.ok();
    }

    @PostMapping("/deleteById")
    @ResponseBody
    @Logs(value = "删除教师")
    public R deleteById(Long id) {
        teacherService.deleteById(id);
        return R.ok();
    }
}
