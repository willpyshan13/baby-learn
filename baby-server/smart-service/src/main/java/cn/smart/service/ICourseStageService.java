package cn.smart.service;

import cn.smart.bo.CourseStageDetailBO;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.CourseStageQueryCondition;
import cn.smart.condition.GoodsQueryCondition;
import cn.smart.entity.*;

import java.util.List;

/**
* @author Ye
* @date 2018-4-3 15:41:07
*/
public interface ICourseStageService extends ICrudService<CourseStageDO, CourseStageQueryCondition> {

    /**
     * 查询课阶详细信息
     * @param person
     * @param id 课阶编号
     * @return 课阶详细信息
     */
    CourseStageDetailBO courseStageDetail(PersonDO person, Long id);

    /**
     *  * 查询用户课阶打卡课程信息
     * @param person 当前登录用户
     * @return ApiResult
     */
    ApiResult queryClockStageCourseList(PersonDO person);

    /**
     * 用户课程签到
     * @param person 当前登录用户
     * @param stageId 课阶id
     * @param groupId
     * @param courseId 课程id
     * @return api result
     */
    ApiResult clockStageGroupCourse(PersonDO person, Long stageId, Long groupId, Long courseId);

    /**
     * 获取用户课阶单元获取积分情况
     * @param person
     * @param groupId
     */
    ApiResult queryPersonGroupProgress(PersonDO person, Long groupId);

    /**
     * 获取课阶单元列表
     * @return
     * @param stageId
     */
    List<CourseStageGroupDO> queryCourseStageGroupList(Long stageId);

    /**
     * 新建课阶单元
     * @param group
     */
    void createStageGroup(CourseStageGroupDO group);

    /**
     * 更新课阶单元
     * @param group
     */
    void updateStageGroup(CourseStageGroupDO group);

    /**
     * 删除课阶返祖
     * @param id
     */
    void deleteStageGroup(Long id);

    /**
     * 查询课阶单元商品
     * @param query stageId 课阶id groupId 单元id
     * @return
     */
    List<GoodsDO> queryCourseStageGroupGoodsList(GoodsQueryCondition query);

    int countCourseStageGroupGoods(GoodsQueryCondition query);

    void saveStageGroupGoodsBatch(List<CourseStageGroupGoodsDO> groupGoods);

    void deleteGroupGoodsById(Long id);

    void updateStageGroupGoods(CourseStageGroupGoodsDO groupGoods);

    CourseStageGroupDO queryCourseStageGroupById(Long id);
}