package cn.smart.controller;

import cn.smart.bo.ClassInfoBO;
import cn.smart.common.annotation.Logs;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.CourseInfoQueryCondition;
import cn.smart.condition.CourseStageQueryCondition;
import cn.smart.condition.GoodsQueryCondition;
import cn.smart.entity.*;
import cn.smart.service.ICourseInfoService;
import cn.smart.service.ICourseStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 课阶信息相关操作
 * @author Ye
 * @date 2018-4-8 09:58:06
 */
@Controller
@RequestMapping("/course/stage")
public class CourseStageController extends AbstractController{

    private final ICourseStageService courseStageService;

    @Autowired
    private ICourseInfoService courseInfoService;

    @Autowired
    public CourseStageController(ICourseStageService courseStageService) {
        this.courseStageService = courseStageService;
    }

    /**
     * 课阶列表
     * @param stageQueryCondition 查询条件
     * @return R
     */
    @RequestMapping("/list")
    @ResponseBody
    public R courseStageList(CourseStageQueryCondition stageQueryCondition) {

        stageQueryCondition.setOrderField("level");

        List<CourseStageDO> stageList = courseStageService.queryList(stageQueryCondition);

        int total = courseStageService.queryTotal(stageQueryCondition);

        PageUtils pageUtils = new PageUtils(stageList, total, stageQueryCondition.getLimit(), stageQueryCondition.getPage());

        return R.ok().put("courseStageList", pageUtils);
    }

    /**
     * 新增课阶信息
     * @param stage 课阶信息
     * @return R
     */
    @PostMapping("/add")
    @ResponseBody
    @Logs(value = "新增课阶")
    public R courseStageAdd(@RequestBody CourseStageDO stage) {
        try {
            courseStageService.save(stage);
        } catch (DuplicateKeyException e) {
            return R.error(-1, "当前课程阶段已经存在");
        } catch (Exception e) {
            return R.error(-1, "新增失败!");
        }
        return R.ok("新增成功!");
    }

    /**
     * 更新课阶
     * @param stage 课阶信息
     * @return R
     */
    @PostMapping("/update")
    @ResponseBody
    @Logs(value = "更新课阶")
    public R courseStageUpdate(@RequestBody CourseStageDO stage) {
        try {
            courseStageService.update(stage);
        } catch (Exception e) {
            return R.error(-1, "更新失败!");
        }
        return R.ok("更新成功!");
    }

    /**
     * 删除课阶
     * @param id 课阶id
     * @return R
     */
    @PostMapping("/deleteById")
    @ResponseBody
    @Logs(value = "删除课阶")
    public R deleteCourseStage(Long id) {
        try {
            courseStageService.deleteById(id);
        } catch (Exception e) {
            return R.error(-1, "删除失败!");
        }
        return R.ok("删除成功!");
    }

    @GetMapping("/queryById")
    @ResponseBody
    public R queryById(Long id) {

        CourseStageDO courseStage = courseStageService.queryById(id);

        return R.ok().put("courseStage", courseStage);
    }

    /**
     * 获取课阶单元信息
     * @param id 课阶id
     * @return
     */
    @GetMapping("/group")
    @ResponseBody
    public R queryStageGroup(Long id) {

        List<CourseStageGroupDO> courseStageGroupList = courseStageService.queryCourseStageGroupList(id);

        return R.ok().put("groupList", courseStageGroupList);
    }

    /**
     * 获取课阶单元信息
     * @param id 单元id
     * @return
     */
    @GetMapping("/group/getById")
    @ResponseBody
    public R getStageGroupById(Long id) {

        CourseStageGroupDO courseStageGroup = courseStageService.queryCourseStageGroupById(id);

        return R.ok().put("stageGroup", courseStageGroup);
    }

    @PostMapping("/group/create")
    @ResponseBody
    @Logs(value = "新增课阶单元")
    public R createStageGroup(@RequestBody CourseStageGroupDO group) {

        group.setCreateBy(getUser() != null ? getUserId() : null);
        group.setCreateTime(new Date());
        try {
            courseStageService.createStageGroup(group);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.error("新增失败");
        }

        return R.ok("操作成功");
    }

    @PostMapping("/group/update")
    @ResponseBody
    @Logs(value = "更新课阶单元")
    public R updateStageGroup(@RequestBody CourseStageGroupDO group) {

        group.setUpdateBy(getUser() != null ? getUserId() : null);
        group.setUpdateTime(new Date());
        courseStageService.updateStageGroup(group);

        return R.ok("操作成功");
    }

    /**
     * 删除课阶
     * @param id 课阶id
     * @return R
     */
    @PostMapping("/group/deleteById")
    @ResponseBody
    @Logs(value = "删除课阶单元")
    public R deleteStageGroup(Long id) {
        try {
            courseStageService.deleteStageGroup(id);
        } catch (Exception e) {
            return R.error(-1, "删除失败!");
        }
        return R.ok("删除成功!");
    }


    /**
     * 更新课阶下课程的单元
     * @param params Long[] courseIds Long groupId
     * @return
     */
    @PostMapping("/update/group/course")
    @ResponseBody
    @Logs(value = "更新课阶单元课程")
    public R queryGroupCourse(@RequestBody ClassInfoBO params) {

        try{
            courseInfoService.updateCourseGroup(params);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("单元分配课程失败");
        }

        return R.ok("操作成功");
    }


    /**
     * 获取课阶单元信息
     * @param query stageId 课阶id groupId 单元id
     * @return
     */
    @GetMapping("/group/goods")
    @ResponseBody
    public R queryStageGroupGoods(GoodsQueryCondition query) {

        List<GoodsDO> goodsList = courseStageService.queryCourseStageGroupGoodsList(query);

        int total = courseStageService.countCourseStageGroupGoods(query);

        PageUtils pageUtils = new PageUtils(goodsList, total, query.getLimit(), query.getPage());

        return R.ok().put("goods", pageUtils);
    }

    /**
     * 批量添加单元可兑换商品
     * @param groupGoods 单元商品
     * @return
     */
    @PostMapping("/group/goods/add/batch")
    @ResponseBody
    @Logs(value = "课阶单元设置可兑换商品")
    public R saveGroupGoodsBatch(@RequestBody List<CourseStageGroupGoodsDO> groupGoods) {

        try{
            courseStageService.saveStageGroupGoodsBatch(groupGoods);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("添加单元可兑换商品失败");
        }
        return R.ok("操作成功");
    }

    /**
     * 修改商品兑换积分值
     * @param groupGoods 商品信息
     * @return
     */
    @PostMapping("/group/goods/update")
    @ResponseBody
    @Logs(value = "更新商品兑换积分值")
    public R updateGroupGoods(@RequestBody CourseStageGroupGoodsDO groupGoods) {
        try {
            courseStageService.updateStageGroupGoods(groupGoods);
        } catch (Exception e) {
            return R.error("修改课阶单元商品兑换积分值失败");
        }
        return R.ok();
    }

    /**
     * 课阶单元商品删除
     * @param id
     * @return
     */
    @PostMapping("/group/goods/deleteById")
    @ResponseBody
    @Logs(value = "删除课阶单元可兑换商品")
    public R deleteGroupGoodsById(Long id) {
        courseStageService.deleteGroupGoodsById(id);
        return R.ok();
    }
}
