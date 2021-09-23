package cn.smart.controller;

import cn.smart.bo.CourseInfoBO;
import cn.smart.bo.CourseReportBO;
import cn.smart.common.annotation.Logs;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.common.utils.Validator;
import cn.smart.condition.CourseInfoQueryCondition;
import cn.smart.condition.IntegralQueryCondition;
import cn.smart.condition.PersonQueryCondition;
import cn.smart.entity.IntegralDO;
import cn.smart.entity.PersonCourseStageDO;
import cn.smart.entity.PersonDO;
import cn.smart.service.IClassInfoService;
import cn.smart.service.ICourseInfoService;
import cn.smart.service.IIntegralService;
import cn.smart.service.IPersonService;
import cn.smart.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 用户操作
 * @author Ye
 * @date 2018-4-8 09:45:52
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private ICourseInfoService courseInfoService;

    @Autowired
    private IIntegralService integralService;

    @Autowired
    private IClassInfoService classInfoService;

    @GetMapping("/getPersonToClass")
    @ResponseBody
    public R getCourseToClass(PersonQueryCondition personQueryCondition) {
        personQueryCondition.setOrderField("create_time");
        personQueryCondition.setOrder("desc");
        PageUtils pageUtils = personService.getPersonToClass(personQueryCondition);
        return R.ok().put("personToClassList", pageUtils);
    }


    /**
     * 用户列表
     * @param personQueryCondition 查询条件
     * @return R
     */
    @GetMapping("/list")
    @ResponseBody
    public R list(PersonQueryCondition personQueryCondition) {
        PageUtils pageUtils = personService.queryPersonList(personQueryCondition);
        return R.ok().put("personList", pageUtils);
    }

    /**
     * 新增用户
     * @param person 用户信息
     * @return R
     */
    @PostMapping("/add")
    @ResponseBody
    @Logs(value = "新增用户信息")
    public R addUser(@RequestBody PersonDO person) {

        if (Validator.isEmpty(person.getMobileNumber())) {
            return R.error(-1, "手机号码不能为空!");
        }

        PersonQueryCondition personQueryCondition = new PersonQueryCondition();
        personQueryCondition.setMobileNumber(person.getMobileNumber());
        List<PersonDO> list = personService.queryList(personQueryCondition);
        if (list.size() > 0) {
            return R.error(-1, "手机号码已被注册!");
        }
        try {
            if (Validator.isEmpty(person.getPassword())) {
                // 将密码转换成md5值
                person.setPassword(MD5Utils.string2MD5("123456"));
            }
            person.setPushAlias(person.getMobileNumber());
            person.setCreateTime(new Date());
            personService.save(person);
            // 分配到默认一对多班级
            classInfoService.assignedPersonToDefaultClass(new Long[]{person.getPersonId()});
        } catch (Exception e) {
            logger.error("新增用户失败： " + e.getMessage());
            return R.error(-1, "新增失败!");
        }

        return R.ok("新增成功!");
    }

    /**
     * 更新用户
     * @param person 用户信息
     * @return R
     */
    @PostMapping("/update")
    @ResponseBody
    @Logs(value = "更新用户信息")
    public R updateUser(@RequestBody PersonDO person) {

        if (Validator.isEmpty(person.getPassword())) {
            // 将密码转换成md5值
            person.setPassword(MD5Utils.string2MD5("123456"));
        }

        personService.update(person);
        return R.ok("更新成功!");
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return R
     */
    @PostMapping("/deleteById")
    @ResponseBody
    @Logs(value = "删除用户信息")
    public R deleteById(Long id) {
        if (Validator.isEmpty(id)) {
            return R.error("id不能为空");
        }
        try {
            return personService.deletePerson(id);
        } catch (Exception e) {
            return R.error("删除用户信息失败");
        }
    }

    /**
     * 后台设置用户购买的课程
     * @param personCourseStageDO personId courseStageId
     * @return
     */
    @PostMapping("/buyCourse")
    @ResponseBody
    @Logs(value = "管理员设置用户购买课程")
    public R buyCourse(@RequestBody PersonCourseStageDO personCourseStageDO) {
        try{
            personCourseStageDO.setCreateTime(new Date());
            personCourseStageDO.setCreateBy(getUser() != null ? getUserId() : null);
            personService.buyCourse(personCourseStageDO);
        } catch (CustomException e) {
            return R.error("购买失败," + e.getMsg());
        }

        return R.ok("购买成功");
    }

    /**
     * 后台删除用户购买的课程
     * @param personId
     * @param stageId
     * @return
     */
    @PostMapping("/deleteBuyStage")
    @ResponseBody
    @Logs(value = "管理员删除用户购买课程")
    public R deleteBuyStage(Long personId, Long stageId) {
        try {
            personService.deleteBuyStage(personId, stageId);
        } catch (CustomException e) {
            return R.ok("删除失败");
        }
        return R.ok("删除成功");
    }

    /**
     * 用户学习记录
     * @param courseInfoQueryCondition
     * @return
     */
    @GetMapping("/learn/records")
    @ResponseBody
    public R learnRecords(CourseInfoQueryCondition courseInfoQueryCondition) {


        List<CourseInfoBO> learnRecordList = personService.queryLearnRecords(courseInfoQueryCondition);
        int total = courseInfoService.countPersonCourseList(courseInfoQueryCondition);
        PageUtils pageUtils = new PageUtils(learnRecordList, total, courseInfoQueryCondition.getLimit(), courseInfoQueryCondition.getPage());
        return R.ok().put("learnRecords", pageUtils);
    }

    /**
     * 课程报告
     * @param personId
     * @param courseId
     * @return
     */
    @GetMapping("/learn/report")
    @ResponseBody
    public R learnReport(Long personId, Long courseId) {

        CourseReportBO report = personService.queryCourseReport(personId, courseId);

        return R.ok().put("report", report);
    }

    /**
     * 获取用户积分明细
     * @param personId 用户id
     * @param type 积分类型 1:蛋糕 2:糖果
     * @param startTime 获取时间 起始
     * @param endTime 获取时间 结束
     * @return
     */
    @GetMapping("/integral/list")
    @ResponseBody
    public R getUserIntegralDetail(Long personId, @RequestParam(value = "type", required = false) Integer type, String startTime, String endTime, Integer source) {

        IntegralQueryCondition integralQuery = new IntegralQueryCondition();
        integralQuery.setPersonId(personId);
        if (null != type) {
            integralQuery.setType(type);
        }
        integralQuery.setStartTime(startTime);
        integralQuery.setEndTime(endTime);
        integralQuery.setSource(source);
        List<IntegralDO> listIntegral = integralService.queryList(integralQuery);

        int total = integralService.queryTotal(integralQuery);
        PageUtils pageUtils = new PageUtils(listIntegral, total, integralQuery.getLimit(), integralQuery.getPage());



        return R.ok().put("integral", pageUtils);
    }

}
