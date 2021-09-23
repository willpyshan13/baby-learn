package cn.smart.controller.mobile;

import cn.smart.bo.*;
import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.annotation.Logs;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.Validator;
import cn.smart.condition.IntegralQueryCondition;
import cn.smart.condition.PushLogQueryCondition;
import cn.smart.service.IApiPersonService;
import cn.smart.entity.PersonDO;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mobile/user")
public class ApiUserController {

    @Autowired
    private IApiPersonService personService;

    @PostMapping("/update")
    @Logs(value = "更新用户信息")
    public ApiResult updateUserInfo(@LoginUser PersonDO personDO,
                                    @RequestParam(value = "gender") boolean gender,
                                    @RequestParam(value = "nickname", required = false) String nickName,
                                    @RequestParam(value = "birthday", required = false) String birthday,
                                    @RequestParam(value = "desc", required = false) String desc,
                                    @RequestParam(value = "mobileSerial", required = false) String mobileSerial,
                                    @RequestParam(value = "headImage", required = false) MultipartFile image,
                                    HttpServletRequest request) {


        String uploadDir = request.getSession().getServletContext()
                .getRealPath("/")
                + "/";

//        if (Validator.isEmpty(personDO)) {
//            return new ApiResult(ApiStatus.SC_TOKEN_INVALID, "token无效, 请重新登录");
//        }

        personDO.setGender(gender);

        try {
            nickName = URLDecoder.decode(nickName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (Validator.isNotEmpty(nickName)) {
            personDO.setNickName(nickName);
        }
        if (Validator.isNotEmpty(birthday)) {
            personDO.setBirthday(birthday);
        }
        if (Validator.isNotEmpty(desc)) {
            personDO.setPersonDesc(desc);
        }
        if (Validator.isNotEmpty(mobileSerial)) {
            personDO.setMobileSerial(mobileSerial);
        }
        if (Validator.isNotEmpty(nickName)) {
            personDO.setNickName(nickName);
        }

        return personService.updatePerson(personDO, image, uploadDir);
    }

    @PostMapping("/detail")
    public ApiResult detail(@LoginUser PersonDO person) {
        if (person == null || Validator.isEmpty(person.getPersonId())) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }
        return personService.queryUserDetail(person.getPersonId());
    }

    @AuthIgnore
    @PostMapping("/forgetPassword")
    @Logs(value = "用户更新密码")
    public ApiResult updatePassword(@RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            HttpServletRequest request) {

        return personService.updatePassword(username, code, password);
    }


    @GetMapping("/v1/roomList")
    public ApiResult getUserRoomList(@LoginUser PersonDO personDO) {

        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }

        return personService.getRoomListByPersonId(personDO.getPersonId());
    }

    /**
     * 2018年9月14日 打卡业务逻辑更换 接口废弃
     *
     * @param personDO
     * @return
     */
    @GetMapping("/clock/record")
    public ApiResult getUserClockRecord(@LoginUser PersonDO personDO) {

        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }

        ClockRecordBO clockRecord = personService.personClockRecord(personDO.getPersonId());

        return new ApiResult(clockRecord);
    }

    /**
     * 2018年9月14日 打卡业务逻辑更换 接口废弃
     * 记录用户打卡数据
     * @param personDO 当前登陆用户
     * @param clockDate 打卡日期
     * @return Integer 打卡获得的蛋糕积分个数
     */
    @PostMapping("/clock/in")
    @Logs("记录用户打卡")
    @Deprecated
    public ApiResult personClockIn(@LoginUser PersonDO personDO,
                                   @RequestParam("clockDate") Long clockDate){
        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }
        if (Validator.isEmpty(clockDate)) {
            return new ApiResult(-1, ApiStatus.CLOCK_DATE_IS_NULL, "打卡时间不能为空!");
        }
        Date date = new Date(clockDate);
        if (!DateUtils.formatDate(date).equals(DateUtils.formatDate(new Date()))) {
            return new ApiResult(-1, ApiStatus.CLOCK_DATE_INCORRECT, "打卡日期错误");
        }

        // Integer addNumber = personService.personClockIn(personDO.getPersonId());
        Integer addNumber = 0;

        if (addNumber == null) {
            return new ApiResult(-1, ApiStatus.CLOCK_DATE_EXISTS, "今日已打卡, 请不要重复打卡");
        }
        return new ApiResult(addNumber);
    }

    /**
     * 2018年9月14日 打卡业务逻辑更换 接口废弃
     * 复习获得积分
     * @param personDO
     * @param type
     * @param reviewDate
     * @return
     */
    @PostMapping("/review/points")
    @Logs(value = "复习积分获取")
    @Deprecated
    public ApiResult getReviewPoints(@LoginUser PersonDO personDO,
                                     @RequestParam("type") Integer type,
                                     @RequestParam("reviewDate") Long reviewDate) {

        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }
        Date date = new Date(reviewDate);
        if (!DateUtils.formatDate(date).equals(DateUtils.formatDate(new Date()))) {
            return new ApiResult(-1, ApiStatus.CLOCK_DATE_INCORRECT, "复习日期错误");
        }

        // ApiResult result = personService.gainReviewPoints(personDO.getPersonId(), type, DateUtils.format(date, "yyyy-MM-dd"));

        return new ApiResult();
    }

    /**
     * 2018年9月14日 打卡业务逻辑更换 接口废弃
     * 获取积分
     * @param personDO 登录用户
     * @param type 积分类型
     * @param date 获取日期
     * @return
     */
    @PostMapping("/gain/points")
    @Logs(value = "用户获取积分")
    @Deprecated
    public ApiResult gainPoints(@LoginUser PersonDO personDO,
                                @RequestParam("type") Integer type,
                                @RequestParam(value = "courseId", required = false) Long courseId,
                                @RequestParam("date") Long date) {

        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }
        Date d = new Date(date);
        if (!DateUtils.formatDate(d).equals(DateUtils.formatDate(new Date()))) {
            return new ApiResult(-1, ApiStatus.CLOCK_DATE_INCORRECT, "获取日期错误");
        }

        return personService.gainPoints(personDO.getPersonId(), type, 3, DateUtils.format(d, "yyyy-MM-dd"), courseId);
    }


    /**
     * 获取用户课程报告
     * @param personDO 当前登录用户
     * @param courseId 课程id
     * @return
     */
    @GetMapping("/learn/report")
    @ResponseBody
    public ApiResult learnReport(@LoginUser PersonDO personDO, Long courseId) {

        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }
        return personService.queryCourseReport(personDO.getPersonId(), courseId);
    }

    /**
     * 废弃接口
     * 获取用户推送配置信息接口
     * @param personDO 当前登陆用户
     * @param pushAlias 推送别名 用户推送唯一标识
     * @return
     */
    @PostMapping("/update/pushAlias")
    @ResponseBody
    @Deprecated
    public ApiResult updatePersonPushSetting(@LoginUser PersonDO personDO,
                                             @RequestParam("pushAlias") String pushAlias) {
        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }
        personDO.setPushAlias(pushAlias);
        try {
            return personService.updatePerson(personDO, null, null);
        } catch (Exception e) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * 废弃接口
     * 用户班级推送标签更新
     * @param personDO 用户信息
     * @return 用户别名  用户标签(班级标签)
     */
    @GetMapping("/pushSetting")
    @ResponseBody
    @Deprecated
    public ApiResult getUserPushSetting(@LoginUser PersonDO personDO) {

        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }

        PersonPushSettingBO personPushSettingBO = personService.queryPersonPushSettings(personDO);

        return new ApiResult(personPushSettingBO);
    }

    /**
     * 2018年9月14日 积分逻辑更换 接口废弃
     * 积分兑换明细接口
     * @param personDO 用户信息
     * @param pageIndex 当前页
     * @param pageSize 分页大小
     * @return
     */
    @GetMapping("/integral/detail")
    @Deprecated
    public ApiResult getIntegralDetail(@LoginUser PersonDO personDO,
                                       @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                       @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        IntegralQueryCondition query = new IntegralQueryCondition();
        query.setPersonId(personDO.getPersonId());
        query.setPage(pageIndex);
        query.setLimit(pageSize);

        List<IntegralDetailBO> list = personService.getIntegralDetail(query);

        for (IntegralDetailBO i : list) {
            int s = i.getSource();
            switch (s) {
                case 1:
                    i.setName("学习" + i.getCourseName());
                    break;
                case 2:
                    i.setName("复习" + i.getCourseName());
                    break;
                case 3:
                    i.setName("签到");
                    break;
                case 4:
                    i.setName("观看视频奖励");
                    break;
                case 5:
                    i.setName("兑换" + i.getGoodsName());
                    break;
                default:
            }
        }

        int total = personService.queryTotalIntegralDetail(query);

        PageUtils pageUtils = new PageUtils(list, total, query.getLimit(), query.getPage());

        return new ApiResult(pageUtils);
    }

    /**
     * 消息中心列表接口
     * @param personDO 用户信息
     * @param pageIndex 当前页
     * @param pageSize 分页大小
     * @return list
     */
    @GetMapping("/message")
    public ApiResult getMessage(@LoginUser PersonDO personDO,
                                @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        PushLogQueryCondition query  = new PushLogQueryCondition();

        query.setPersonId(personDO.getPersonId());
        query.setPage(pageIndex);
        query.setLimit(pageSize);

        List<PushBO> list = personService.queryPersonMessage(query);

        int total = personService.queryTotalPersonMessage(query);

        PageUtils pageUtils = new PageUtils(list, total, query.getLimit(), query.getLimit());

        return new ApiResult(pageUtils);
    }

    @GetMapping("/course/stage")
    public ApiResult getUserBoughtCourses(@LoginUser PersonDO person, @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        if (Validator.isEmpty(person)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }

        return personService.queryCourseStagePersonBought(person.getPersonId(), pageIndex, pageSize);

    }
}
