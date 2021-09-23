package cn.smart.service;

import cn.smart.bo.*;
import cn.smart.common.api.result.ApiLoginResult;
import cn.smart.common.api.result.ApiResult;
import cn.smart.condition.IntegralQueryCondition;
import cn.smart.condition.PersonLearnRecordQueryCondition;
import cn.smart.condition.PushLogQueryCondition;
import cn.smart.entity.CasualUserDO;
import cn.smart.entity.PersonDO;
import cn.smart.entity.PersonLearnRecordDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IApiPersonService {

    /**
     * 用户注册
     * @param person 用户信息
     * @param verifyCode 验证码
     * @param image 头像图片
     * @param path 图片地址
     * @return ApiLoginResult
     */
    ApiLoginResult register(PersonDO person, String verifyCode, MultipartFile image, String path);

    ApiLoginResult register(CasualUserDO casualUser, String verifyCode);

    /**
     * 使用账户密码登录
     * @param mobilePhone 手机号码
     * @param password 密码
     * @param type 登录类型 1:Android 2:H5
     * @return ApiLoginResult
     */
    ApiLoginResult login(String mobilePhone, String password, Integer type);

    /**
     * 使用验证码登录
     * @param mobileNumber 手机号码
     * @param code 验证码
     * @param type 登录类型 1:Android 2:H5
     * @return ApiLoginResult
     */
    ApiLoginResult loginByVerifyCode(String mobileNumber, String code, Integer type);

    /**
     * 更新密码
     * @param id 用户id
     * @param password 密码
     * @return ApiResult
     */
    ApiResult updatePassword(Long id, String password);

    /**
     * 更新密码
     * @param username 手机号码
     * @param code 验证码
     * @param password 密码
     * @return
     */
    ApiResult updatePassword(String username, String code, String password);

    /**
     * 更新用户信息
     * @param personDO 用户信息
     * @return ApiResult
     */
    ApiResult updatePerson(PersonDO personDO, MultipartFile image, String uploadDir);

    /**
     * 校验手机号码是否注册
     * @param mobilePhone 手机号码
     * @return ApiResult
     */
    ApiResult validateMobilePhone(String mobilePhone);

    /**
     * 用户注销
     * @param person 用户
     * @return ApiResult
     */
    ApiResult logout(PersonDO person);

    /**
     * 根据id 查询用户信息
     * @param id 用户id
     * @return ApiResult
     */
    ApiResult queryById(Long id);

    /**
     * 用户购买课程
     * @param id 用户id
     * @return ApiResult
     */
    ApiResult boughtCourseStage(Long id);

    /**
     * 保存用户学习记录
     * @param person 用户id
     * @param learnId 课程id
     * @param type 类型 1:课程 2:游戏
     * @param report
     * @param addNumber
     */
    ApiResult savePersonLearnRecord(PersonDO person, Long learnId, Integer type, ReportBO report, Integer addNumber);


    /**
     * 用户学习记录
     * @param personLearnRecordQueryCondition
     * @return
     */
    List<PersonLearnRecordDO> personLearnRecord(PersonLearnRecordQueryCondition personLearnRecordQueryCondition);

    ApiResult getRoomListByPersonId(Long id);

    /**
     * 用户打卡记录
     * @param personId 用户id
     * @return
     */
    ClockRecordBO personClockRecord(Long personId);

    /**
     * 用户打卡
     * @param personId 用户id
     * @return
     */
    Integer personClockIn(Long personId);

    /**
     * 用户获取复习积分
     * @param personId 用户类型
     * @param type 复习类型 1 看视频 2 玩游戏 3 大声说
     * @param reviewDate 复习时间
     * @return
     */
    ApiResult gainReviewPoints(Long personId, Integer type, String reviewDate);

    /**
     * 获取用户课程报告
     * @param personId 用户id
     * @param courseId 课程id
     * @return
     */
    ApiResult queryCourseReport(Long personId, Long courseId);

    /**
     * 获取用户推送别名和标签
     * @param personId 用户id
     * @return
     */
    PersonPushSettingBO queryPersonPushSettings(PersonDO personId);

    /**
     * 用户获取积分
     * @param personId 用户id
     * @param type 积分类型
     * @param points
     * @param date 获取时间
     * @param courseId
     * @return
     */
    ApiResult gainPoints(Long personId, Integer type, int points, String date, Long courseId);

    /**
     * 验证临时用户电话号码是否存在
     * @param mobilePhone
     * @return
     */
    ApiResult validateCasualPhone(String mobilePhone);

    /**
     * 获取用户积分明细
     * @param query 用户id
     * @return
     */
    @Deprecated
    List<IntegralDetailBO> getIntegralDetail(IntegralQueryCondition query);

    /**
     * 获取用户积分明细总条数
     * @param query
     * @return
     */
    @Deprecated
    int queryTotalIntegralDetail(IntegralQueryCondition query);

    /**
     * 获取用户推送消息列表
     * @param query
     * @return
     */
    List<PushBO> queryPersonMessage(PushLogQueryCondition query);

    /**
     * 获取用户推送消息列表总条数
     * @param query
     * @return
     */
    int queryTotalPersonMessage(PushLogQueryCondition query);

    /**
     * 查询用户详细信息
     * @param personId 用户id
     * @return
     */
    ApiResult queryUserDetail(Long personId);

    /**
     * 查询用户购买的课阶信息
     * @param personId 用户id
     * @param pageIndex
     * @param pageSize
     * @return List<CourseStageInfo>
     */
    ApiResult queryCourseStagePersonBought(Long personId, Integer pageIndex, Integer pageSize);


}
