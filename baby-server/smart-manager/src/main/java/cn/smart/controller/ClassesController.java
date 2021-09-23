package cn.smart.controller;

import cn.smart.bo.ClassCourseBO;
import cn.smart.bo.ClassInfoBO;
import cn.smart.common.annotation.Logs;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.common.utils.Validator;
import cn.smart.condition.ClassInfoQueryCondition;
import cn.smart.condition.CourseInfoQueryCondition;
import cn.smart.condition.PersonQueryCondition;
import cn.smart.entity.ClassCourseDO;
import cn.smart.entity.ClassInfoDO;
import cn.smart.entity.PersonDO;
import cn.smart.service.IClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/classes")
public class ClassesController {
    
    @Autowired
    IClassInfoService classInfoService;

    @GetMapping("/list")
    @ResponseBody
    public R list(ClassInfoQueryCondition classInfoQueryCondition) {

        List<ClassInfoDO> classList = classInfoService.queryList(classInfoQueryCondition);

        int total = classInfoService.queryTotal(classInfoQueryCondition);

        PageUtils pageUtils = new PageUtils(classList, total, classInfoQueryCondition.getLimit(), classInfoQueryCondition.getPage());

        return R.ok().put("classInfoList", pageUtils);
    }

    /**
     * 班级分配人员
     * @param classInfoBO 班级学生信息
     * @return
     */
    @PostMapping("/assigned/person")
    @ResponseBody
    @Logs(value = "班级分配学生")
    public R assignedPerson(@RequestBody ClassInfoBO classInfoBO) {

        try {
            classInfoService.assignedPerson(classInfoBO);
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
        return R.ok();
    }

    /**
     * 班级分配课程
     * @param classInfoBO 班级课程信息
     * @return
     */
    @PostMapping("/assigned/course")
    @ResponseBody
    @Logs(value = "班级分配课程")
    public R assignedCourse(@RequestBody ClassInfoBO classInfoBO) {

        classInfoService.assignedCourse(classInfoBO);

        return R.ok();
    }

    @PostMapping("/add")
    @ResponseBody
    @Logs(value ="新增班级")
    public R add(@RequestBody ClassInfoDO classInfo) {

        classInfoService.saveClassInfo(classInfo);
        return R.ok();
    }

    @PostMapping("/update")
    @ResponseBody
    @Logs(value = "更新班级")
    public R update(@RequestBody ClassInfoDO classInfo) {

        classInfoService.updateClassInfo(classInfo);
        return R.ok();
    }


    @PostMapping("/deleteById")
    @ResponseBody
    @Logs(value = "删除班级")
    public R delete(@RequestParam("id") Long id) {
        classInfoService.deleteById(id);
        return R.ok();
    }

    @GetMapping("/getClassCourse")
    @ResponseBody
    public R queryClassCourseByClassId(CourseInfoQueryCondition courseInfoQueryCondition) {

        courseInfoQueryCondition.setOrderField("serial_number");
        List<ClassCourseBO> classInfoDOList = classInfoService.queryClassCourseByClassId(courseInfoQueryCondition);
        Integer total = classInfoService.countClassCourse(courseInfoQueryCondition);

        PageUtils pageUtils = new PageUtils(classInfoDOList, total, courseInfoQueryCondition.getLimit(), courseInfoQueryCondition.getPage());
        return R.ok().put("classCourseList", pageUtils);
    }

    @GetMapping("/getClassPerson")
    @ResponseBody
    public R queryClassPersonByClassId(PersonQueryCondition personQueryCondition) {
        List<PersonDO> personList = classInfoService.queryClassPersonByClassId(personQueryCondition);
        Integer total = classInfoService.countClassPerson(personQueryCondition);
        PageUtils pageUtils = new PageUtils(personList, total, personQueryCondition.getLimit(), personQueryCondition.getPage());

        return R.ok().put("classPersonList", pageUtils);
    }

    @PostMapping("/cancel/course")
    @ResponseBody
    @Logs(value = "删除班级课程")
    public R cancelClassCourse(@RequestBody ClassInfoBO classInfoBO) {

        classInfoService.cancelClassCourse(classInfoBO);

        return R.ok();
    }

    @PostMapping("/cancel/student")
    @ResponseBody
    @Logs(value = "删除班级学生")
    public R cancelClassStudent(@RequestBody ClassInfoBO classInfoBO) {

        if(Validator.isEmpty(classInfoBO.getClassId())) {
            return R.error("班级id不能为空!");
        }
        if (classInfoBO.getStudentIds().length <= 0) {
            return R.error("请选择需要删除的学生!");
        }
        try {
            classInfoService.cancelClassStudent(classInfoBO);
        } catch (CustomException e) {
            return R.error("删除班级学生失败");
        }

        return R.ok("操作成功");
    }

    /**
     * 更新班级课程是否开放状态
     * @param classCourseDO id 班级-课程表主键 status 开放状态 0 开放 1 关闭
     * @return R
     */
    @PostMapping("/change/status")
    @ResponseBody
    @Logs(value = "更新班级课程状态")
    public R updateClassCourseStatus(@RequestBody ClassCourseDO classCourseDO) {
        classInfoService.updateClassCourseStatus(classCourseDO);
        return R.ok();
    }
}
