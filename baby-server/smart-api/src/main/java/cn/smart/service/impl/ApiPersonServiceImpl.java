package cn.smart.service.impl;

import cn.jiguang.common.utils.StringUtils;
import cn.smart.common.api.result.ApiLoginResult;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.DateUtil;
import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.Validator;
import cn.smart.bo.*;
import cn.smart.entity.ApiTokenDO;
import cn.smart.service.IApiPersonService;
import cn.smart.service.IApiTokenService;
import cn.smart.bo.CourseGameBO;
import cn.smart.bo.CourseReportBO;
import cn.smart.bo.ReportBO;
import cn.smart.bo.ReportDetailBO;
import cn.smart.condition.*;
import cn.smart.dao.*;
import cn.smart.entity.*;
import cn.smart.service.IClassInfoService;
import cn.smart.service.IReportService;
import cn.smart.support.jpush.PushComponent;
import cn.smart.support.qiniu.OSSFactory;
import cn.smart.support.talkcloud.business.RoomBusiness;
import cn.smart.support.wx.service.WeixinTemplateMsgService;
import cn.smart.utils.MD5Utils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.Oneway;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Ye
 */
@Service
public class ApiPersonServiceImpl implements IApiPersonService{

    private static final Logger logger = LogManager.getLogger(ApiPersonServiceImpl.class);

    /**
     * 积分类型 1 蛋糕 2 糖果
     */
    private static final int POINTS_TYPE_CAKE   = 1;
    private static final int POINTS_TYPE_CANDY  = 2;
    /**
     * 游戏类型 2 玩游戏 3 大声说
     */
    private static final int GAME_TYPE_PLAY_GAME    = 2;
    private static final int GAME_TYPE_SPEAK_UP     = 3;
    /**
     * 积分来源 1:玩游戏初始积分 2:玩游戏加分 3:玩游戏复习积分 4:大声说初始积分 5:大声说加分 6:大声说复习积分 7:打卡积分 8:视频观看时长积分
     */
    private static final int POINTS_SOURCE_PLAY_GAME_INIT   = 1;
    private static final int POINTS_SOURCE_PLAY_GAME_APPEND = 2;
    private static final int POINTS_SOURCE_PLAY_GAME_REVIEW = 3;
    private static final int POINTS_SOURCE_SPEAK_UP_INIT    = 4;
    private static final int POINTS_SOURCE_SPEAK_UP_APPEND  = 5;
    private static final int POINTS_SOURCE_SPEAK_UP_REVIEW  = 6;
    private static final int POINTS_SOURCE_CLOCK            = 7;
    private static final int POINT_SOURCE_WATCH_VIDEO       = 8;
    /**
     * 最大连续打卡次数 7
     */
    private static final int CLOCK_MAX_DAY = 7;

    @Autowired
    private IApiTokenService tokenService;

    @Autowired
    private IPersonMapper personMapper;

    @Autowired
    private IVerifyCodeMapper verifyCodeMapper;

    @Autowired
    private IPersonCourseStageMapper personCourseStageMapper;

    @Autowired
    private IPersonLearnRecordMapper personLearnRecordMapper;

    @Autowired
    private IRoomMapper roomMapper;

    @Autowired
    private RoomBusiness roomBusiness;

    @Autowired
    private IClockRecordMapper clockRecordMapper;

    @Autowired
    private IIntegralMapper integralMapper;

    @Autowired
    private ICourseInfoMapper courseInfoMapper;

    @Autowired
    private ICasualUserMapper casualUserMapper;

    @Autowired
    private IPushLogMapper pushLogMapper;

    @Autowired
    private PushComponent pushComponent;

    @Autowired
    private IReportService reportService;

    @Autowired
    private WeixinTemplateMsgService weixinTemplateMsgService;

    @Autowired
    private IClassInfoMapper classInfoMapper;

    @Autowired
    private IPersonCourseClockMapper personCourseClockMapper;

    @Autowired
    private IPersonCourseStageGroupMapper personCourseStageGroupMapper;

    @Autowired
    private IClassInfoService classInfoService;

    private static ExecutorService executorService = new ThreadPoolExecutor(2, 20, 5L,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(20), new ThreadPoolExecutor.DiscardOldestPolicy());


    @Override
    public ApiLoginResult register(PersonDO person, String verifyCode, MultipartFile image, String uploadDir) {
        // 校验手机号码
        PersonQueryCondition personQueryCondition = new PersonQueryCondition();
        personQueryCondition.setMobileNumber(person.getMobileNumber());
        personQueryCondition.setStatus(1);
        List<PersonDO> persons = personMapper.queryList(personQueryCondition);
        if (!Validator.isEmpty(persons)) {
            return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_REGISTERED, "当前手机号码已经注册, 请直接登录!");
        }
        // 校验验证码
        VerifyCodeQueryCondition verifyCodeQueryCondition = new VerifyCodeQueryCondition();
        verifyCodeQueryCondition.setTelephone(person.getMobileNumber());
        List<VerifyCodeDO> codes = verifyCodeMapper.queryList(verifyCodeQueryCondition);
        if (!Validator.isEmpty(codes)) {
            VerifyCodeDO verifyCodeDO = codes.get(0);
            if (verifyCodeDO.getExpireTime() < System.currentTimeMillis()) {
                return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_INVALID, "验证码已失效!");
            }

            // 验证通过 注册用户
            if (verifyCode.equalsIgnoreCase(verifyCodeDO.getCode())) {

                // 保存图片
                uploadAvatar(person, image);
                personMapper.save(person);
                // 登录成功 生成 Token 值
                ApiTokenDO token = tokenService.createToken(person.getPersonId(), person.getChannel());

                classInfoService.assignedPersonToDefaultClass(new Long[]{person.getPersonId()});

                return new ApiLoginResult("注册成功!", null, token.getToken());
            } else {
                return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_ERROR, "验证码错误!");
            }
        } else {
            return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_INVALID, "验证码已失效!");
        }
    }

    @Override
    public ApiLoginResult register(CasualUserDO casualUser, String verifyCode) {

        CasualUserQueryCondition casualQuery = new CasualUserQueryCondition();
        casualQuery.setMobilePhone(casualUser.getMobilePhone());
        List<CasualUserDO> userList = casualUserMapper.queryList(casualQuery);
        if (userList.size() > 0) {
            return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_REGISTERED, "手机号码已注册!");
        }

        if (casualUser.getSource() == 1) {
            CasualUserQueryCondition query = new CasualUserQueryCondition();
            query.setMobilePhone(casualUser.getMobilePhone());
            List<CasualUserDO> listCasualUser = casualUserMapper.queryList(query);
            if (!Validator.isEmpty(listCasualUser)) {
                return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_REGISTERED, "当前手机号码已经存在!");
            }
            // 校验验证码
            VerifyCodeQueryCondition verifyCodeQueryCondition = new VerifyCodeQueryCondition();
            verifyCodeQueryCondition.setTelephone(casualUser.getMobilePhone());
            List<VerifyCodeDO> codes = verifyCodeMapper.queryList(verifyCodeQueryCondition);
            if (!Validator.isEmpty(codes)) {
                VerifyCodeDO verifyCodeDO = codes.get(0);
                if (verifyCodeDO.getExpireTime() < System.currentTimeMillis()) {
                    return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_INVALID, "验证码已失效!");
                }

                // 验证通过 注册用户
                if (verifyCode.equalsIgnoreCase(verifyCodeDO.getCode())) {
                    casualUserMapper.save(casualUser);
                    return new ApiLoginResult(0,200,"操作成功!");
                } else {
                    return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_ERROR, "验证码错误!");
                }
            } else {
                return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_INVALID, "验证码已失效!");
            }
        } else {
            casualUserMapper.save(casualUser);
            return new ApiLoginResult(0,200,"操作成功!");
        }
    }

    @Override
    public ApiLoginResult login(String mobilePhone, String password, Integer type) {
        // 查询用户是否注册
        PersonQueryCondition personQueryCondition = new PersonQueryCondition();
        personQueryCondition.setMobileNumber(mobilePhone);
        personQueryCondition.setStatus(1);
        List<PersonDO> personList = personMapper.queryList(personQueryCondition);
        if (Validator.isEmpty(personList)) {
            return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_UNREGISTERED, "当前手机号码尚未注册!");
        }
        // 校验密码
        PersonDO person = personList.get(0);
        if (!MD5Utils.string2MD5(password).equals(person.getPassword())) {
            return new ApiLoginResult(-1, ApiStatus.SC_PASSWORD_ERROR, "密码错误,请重新输入!");
        }  else {
            // 已经登录且登录类型为APP登录 通知登出另一台设备
            if (person.getLogin() && type == 1) {
                if (StringUtils.isNotEmpty(person.getPushAlias())) {
                    executorService.submit(() -> {
                        Map<String, String> extras = new HashMap<>(16);
                        extras.put("type", MessageDO.MessageType.LOG_OUT);
                        pushComponent.pushMessageAndroidWithAlias("","", extras, person.getPushAlias());
                    });
                }
            }

            return loginSuccess(person, type);
        }
    }



    @Override
    public ApiLoginResult loginByVerifyCode(String mobileNumber, String code, Integer type) {

        // 查询用户是否注册
        PersonQueryCondition personQueryCondition = new PersonQueryCondition();
        personQueryCondition.setMobileNumber(mobileNumber);
        personQueryCondition.setStatus(1);
        List<PersonDO> personList = personMapper.queryList(personQueryCondition);
        if (Validator.isEmpty(personList)) {
            return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_UNREGISTERED, "当前手机号码尚未注册!");
        }
        // 查询验证码
        VerifyCodeQueryCondition verifyCodeQueryCondition = new VerifyCodeQueryCondition();
        verifyCodeQueryCondition.setTelephone(mobileNumber);
        List<VerifyCodeDO> codeList = verifyCodeMapper.queryList(verifyCodeQueryCondition);
        if (Validator.isEmpty(codeList)) {
            return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_INVALID, "验证码失效, 请重新获取验证码!");
        }
        // 校验验证码
        VerifyCodeDO verifyCode = codeList.get(0);
        if (verifyCode.getExpireTime() < System.currentTimeMillis()) {
            return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_INVALID, "验证码已失效,请重新获取!");
        }
        if (!code.equalsIgnoreCase(verifyCode.getCode())) {
            return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_ERROR, "验证码错误, 请重新输入!");
        } else {
           return loginSuccess(personList.get(0), type);
        }
    }

    private ApiLoginResult loginSuccess(PersonDO person, Integer type) {

        // 登录成功 生成 Token 值
        ApiTokenDO token = tokenService.createToken(person.getPersonId(), type);
        // 更新推送标记
        person.setLogin(true);
        person.setPushAlias("p_" + person.getPersonId() + "_" + person.getMobileNumber() + "_" + System.currentTimeMillis());
        personMapper.update(person);

        // 设置返回对象
        PersonDetailBO personDetail = new PersonDetailBO();
        personDetail.setPersonId(person.getPersonId());
        personDetail.setMobileNumber(person.getMobileNumber());
        personDetail.setNickName(person.getNickName());
        personDetail.setGender(person.getGender());
        personDetail.setBirthday(person.getBirthday());
        personDetail.setPhotoUrl(person.getPhotoUrl());
        personDetail.setCandyPoints(person.getCandyPoints());
        // 当天是否打卡
        String currentDate = DateUtils.format(new Date(), "yyyy-MM-dd");
        ClockRecordDO clockRecord = clockRecordMapper.queryByPersonId(person.getPersonId());
        if (clockRecord == null) {
            personDetail.setClockIn(false);
        } else {
            personDetail.setClockIn(clockRecord.getClockDate().equals(currentDate));
        }
        // 推送标签
        PersonQueryCondition personQuery = new PersonQueryCondition();
        personQuery.setPersonId(person.getPersonId());
        List<ClassInfoDO> listClassInfo = personMapper.queryPersonClass(personQuery);
        Set<String> pushTagsSet = new HashSet<>(listClassInfo.size());
        for(ClassInfoDO c : listClassInfo) {
            if (Validator.isNotEmpty(c.getPushTag())) {
                pushTagsSet.add(c.getPushTag());
            }
        }
        personDetail.setPushTags(pushTagsSet);
        personDetail.setPushAlias(person.getPushAlias());

        return new ApiLoginResult("登录成功!", personDetail, token.getToken());
    }

    @Override
    public ApiResult updatePassword(Long id, String password) {

        PersonDO person = personMapper.queryById(id);
        if (Validator.isEmpty(person)) {
            return new ApiResult(-1, ApiStatus.SC_USER_DOSE_NOT_EXIST,"当前用户不存在, 请联系管理员!");
        }
        person.setPersonId(id);
        person.setPassword(MD5Utils.string2MD5(password));
        int i = personMapper.update(person);

        if (i == 1) {
            return new ApiResult("修改密码成功!");
        } else {
            return new ApiResult(-1, ApiStatus.SC_UPDATE_PASSWORD_ERROR,"修改密码失败!");
        }
    }

    @Override
    public ApiResult updatePassword(String username, String code, String password) {

        PersonQueryCondition personQuery = new PersonQueryCondition();
        personQuery.setMobileNumber(username);
        List<PersonDO> personList = personMapper.queryList(personQuery);
        if (Validator.isEmpty(personList)) {
            return new ApiResult(-1, ApiStatus.SC_USER_DOSE_NOT_EXIST,"用户不存在");
        }
        VerifyCodeQueryCondition verifyQuery = new VerifyCodeQueryCondition();
        verifyQuery.setTelephone(username);
        verifyQuery.setCode(code);
        List<VerifyCodeDO> verifyCodeDOS = verifyCodeMapper.queryList(verifyQuery);
        if (Validator.isEmpty(verifyCodeDOS)) {
            return new ApiResult(-1, ApiStatus.SC_VERIFY_CODE_ERROR, "验证码错误");
        }

        VerifyCodeDO verify = verifyCodeDOS.get(0);
        if (verify.getExpireTime() < System.currentTimeMillis()) {
            return new ApiResult(-1, ApiStatus.SC_VERIFY_CODE_INVALID, "验证码已失效,请重新获取!");
        }
        if (!code.equalsIgnoreCase(verify.getCode())) {
            return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_ERROR, "验证码错误, 请重新输入!");
        }
        updatePassword(personList.get(0).getPersonId(), password);
        return new ApiResult("修改成功");
    }

    @Override
    public ApiResult updatePerson(PersonDO personDO, MultipartFile image, String uploadDir) {
        // 保存图片
        uploadAvatar(personDO, image);
        int result = personMapper.update(personDO);

        if (result == 1) {
            return new ApiResult("更新成功!");
        } else {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "更新失败!");
        }
    }

    private void uploadAvatar(PersonDO personDO, MultipartFile image) {
        String url = null;
        if (!Validator.isEmpty(image)) {
            try {
                if (Validator.isNotEmpty(personDO.getPhotoUrl())) {
                    OSSFactory.buildImage().removeFile(personDO.getPhotoUrl().substring(personDO.getPhotoUrl().lastIndexOf("/") + 1, personDO.getPhotoUrl().length()));
                }
                url = OSSFactory.buildImage().upload(image.getBytes(), image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
            personDO.setPhotoUrl(url);
        }
    }

    @Override
    public ApiResult validateMobilePhone(String mobilePhone) {
        // 验证手机号码是否已注册
        PersonQueryCondition personQueryCondition = new PersonQueryCondition();
        personQueryCondition.setMobileNumber(mobilePhone);
        List<PersonDO> personList = personMapper.queryList(personQueryCondition);
        if (personList != null && personList.size() > 0) {
            return new ApiResult(-1, ApiStatus.SC_MOBILE_PHONE_REGISTERED,"当前手机号码已经注册,请直接登录.");
        }
        return new ApiResult();
    }

    @Override
    public ApiResult logout(PersonDO person) {
        int result = tokenService.deleteById(person.getPersonId());
        person.setLogin(false);
        personMapper.update(person);
        if (1 == result) {
            return new ApiResult("退出成功!");
        } else {
            return new ApiResult(-1, ApiStatus.SC_LOG_OUT_ERROR,"退出失败!");
        }
    }

    @Override
    public ApiResult queryById(Long id) {
        PersonDO person = personMapper.queryById(id);
        person.setPassword(null);

        // 当天是否打卡
        String currentDate = DateUtils.format(new Date(), "yyyy-MM-dd");
        ClockRecordDO clockRecord = clockRecordMapper.queryByPersonId(id);
        if (clockRecord == null) {
            person.setClockIn(false);
        } else {
            person.setClockIn(clockRecord.getClockDate().equals(currentDate));
        }

        return new ApiResult(person);
    }

    @Override
    public ApiResult boughtCourseStage(Long id) {
        List<CourseStageDO> personCourseList = personCourseStageMapper.queryPersonBoughtCourse(id);
        return new ApiResult(personCourseList);
    }

    @Override
    public List<PersonLearnRecordDO> personLearnRecord(PersonLearnRecordQueryCondition personLearnRecordQueryCondition) {

        return personLearnRecordMapper.queryList(personLearnRecordQueryCondition);
    }

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public ApiResult savePersonLearnRecord(PersonDO person, Long learnId, Integer type, ReportBO report, Integer addNumber) {

        ApiResult apiResult = new ApiResult();
        Long personId = person.getPersonId();
        CourseInfoDO course = courseInfoMapper.queryById(learnId);

        if (null == course) {
            throw new CustomException("当前课程不存在");
        }

        // 查询用户历史学习记录
        PersonLearnRecordDO personLearnRecordDO = null;
        PersonLearnRecordQueryCondition query = new PersonLearnRecordQueryCondition();
        query.setCourseId(learnId);
        query.setPersonId(personId);
        query.setType(type);
        List<PersonLearnRecordDO> listPersonLearnRecord = personLearnRecordMapper.queryList(query);

        String jsonReport = null;
        if (Validator.isNotEmpty(report)) {
            jsonReport = JSONObject.toJSONString(report);
        }

        // 当前用户所学课程无学习记录
        if (listPersonLearnRecord.size() == 0) {
            personLearnRecordDO = new PersonLearnRecordDO();
            personLearnRecordDO.setCourseId(learnId);
            personLearnRecordDO.setPersonId(personId);
            personLearnRecordDO.setType(type);
            personLearnRecordDO.setCreateTime(new Date());
            personLearnRecordDO.setUpdateTime(new Date());
            personLearnRecordDO.setResultData(jsonReport);
            personLearnRecordDO.setScore(report == null ? 0 : report.getScore());
            personLearnRecordMapper.save(personLearnRecordDO);

            // 当前用户所学习课程存在学习记录, 再次学习获得积分数大于0, 则更新
        } else {
            // 重做游戏获得积分
            if (addNumber > 0) {
                personLearnRecordDO = listPersonLearnRecord.get(0);

                personLearnRecordDO.setResultData(jsonReport);
                personLearnRecordDO.setScore(report == null ? 0 : report.getScore());
                personLearnRecordDO.setUpdateTime(new Date());
                personLearnRecordMapper.update(personLearnRecordDO);
            }
        }

        // 计算课程获得总星星数
        if (Validator.isNotEmpty(report)) {
            PersonLearnRecordQueryCondition q = new PersonLearnRecordQueryCondition();
            q.setCourseId(learnId);
            q.setPersonId(personId);
            listPersonLearnRecord = personLearnRecordMapper.queryList(q);
            Integer playGameScore = 0, speakUpScore = 0;
            boolean speakUp = false;
            for (PersonLearnRecordDO p : listPersonLearnRecord) {
                if (p.getType() == 1) {
                    personLearnRecordDO = p;
                }
                // 单词辨析
                if (p.getType() == 2) {
                    ReportBO playGameReport = JSONObject.parseObject(p.getResultData(), ReportBO.class);
                    if (Validator.isNotEmpty(playGameReport)) {
                        playGameScore = playGameReport.getScore() == null ? 0 : playGameReport.getScore();
                    }

                    // 大声说
                } else if (p.getType() == 3) {
                    speakUp = true;
                    ReportBO speakUpReport = JSONObject.parseObject(p.getResultData(), ReportBO.class);
                    if (Validator.isNotEmpty(speakUpReport)) {
                        speakUpScore = speakUpReport.getScore() == null ? 0 : speakUpReport.getScore();
                    }
                }
            }
            Double result = (playGameScore.doubleValue() + speakUpScore.doubleValue()) / 2;
            boolean min = result < 1 && (playGameScore != 0 || speakUpScore != 0);
            if (personLearnRecordDO == null) {
                throw new CustomException("用户学习记录为空!");
            }
            if (min) {
                personLearnRecordDO.setScore(1);
            } else {
                // 课程总星星 均值 向下取整
                personLearnRecordDO.setScore((int)Math.floor(result));
            }
            personLearnRecordMapper.update(personLearnRecordDO);

            // 完成大声说 (必定完成玩游戏) 更新用户课阶单元积分
            if (speakUp) {

                Date date = new Date();
                IntegralDO integral = new IntegralDO();
                integral.setLearnRecordId(personLearnRecordDO.getRecordId());
                integral.setCourseId(course.getId());
                integral.setPersonId(personId);
                integral.setType(2);
                integral.setSource(2);
                integral.setCreateDate(DateUtils.format(date, "yyyy-MM-dd"));
                integral.setCreateTime(date);

                // 查询积分明细 当前课程是否已经获得过积分
                IntegralQueryCondition integralQuery = new IntegralQueryCondition();
                integralQuery.setCourseId(course.getId());
                integralQuery.setPersonId(personId);
                integralQuery.setSource(2);
                // 课程积分
                integralQuery.setType(2);
                List<IntegralDO> integralList = integralMapper.queryList(integralQuery);

                int integralNumber = 0;
                // 获得过积分
                if (integralList.size() > 0) {
                    int i = 0;
                    for (IntegralDO in : integralList) {
                        if (in.getType().equals(2)) {
                            i += in.getNumber();
                        }
                    }
                    // 未获得满分且课程得满分
                    if (i < 2 && personLearnRecordDO.getScore() == 3) {
                        integralNumber = 1;
                    }
                } else {
                    // 当前课程未获得过积分且课程游戏完成分数不为满分
                    if (personLearnRecordDO.getScore() != null && personLearnRecordDO.getScore() < 3) {
                        integralNumber = 1;
                    } else {
                        integralNumber = 2;
                    }
                }

                // 更新积分明细
                integral.setNumber(integralNumber);
                if (integralNumber != 0) {
                    integralMapper.save(integral);

                }

                // 对应课阶单元增加积分
                PersonCourseStageGroupQueryCondition pcsGroupQuery = new PersonCourseStageGroupQueryCondition();
                pcsGroupQuery.setStageId(course.getStageId());
                pcsGroupQuery.setGroupId(course.getGroupId());
                pcsGroupQuery.setPersonId(personId);
                List<PersonCourseStageGroupDO> pcsGroupList = personCourseStageGroupMapper.queryList(pcsGroupQuery);
                if (pcsGroupList.size() != 1) {
                    throw new CustomException("课程对应课程单元不存在, 积分增加失败");
                }
                PersonCourseStageGroupDO pcsGroup = pcsGroupList.get(0);
                Integer stageGroupPoints = pcsGroup.getCurrentPoints() == null ? integral.getNumber() : pcsGroup.getCurrentPoints() + integral.getNumber();
                pcsGroup.setCurrentPoints(stageGroupPoints);
                personCourseStageGroupMapper.update(pcsGroup);

                // 用户增加积分
                int point = person.getCandyPoints() == null ? integral.getNumber() : person.getCandyPoints() + integral.getNumber();
                person.setCandyPoints(point);
                personMapper.update(person);

                apiResult.setData(integralNumber);
            }

        }

        // 生成周报 推送微信消息
        executorService.submit(() -> {

            logger.info("学习记录生成周报, 微信公众号推送开始...");
            // 报告生成规则修改 完成一周五阶节课程即可生成报告
            CourseInfoQueryCondition courseQuery = new CourseInfoQueryCondition();
            courseQuery.setStageId(course.getStageId());
            courseQuery.setGroupId(course.getGroupId());
            courseQuery.setWeeks(course.getWeeks());
            List<CourseInfoDO> courseList = courseInfoMapper.queryList(courseQuery);
            Long[] ids = new Long[courseList.size()];
            int i = 0;
            for (CourseInfoDO c : courseList) {
                ids[i++] = c.getId();
            }
            if (ids.length >= 5) {
                PersonLearnRecordQueryCondition learnQuery = new PersonLearnRecordQueryCondition();
                // 5节课程 完成大声说
                learnQuery.setInCourseIds(ids);
                learnQuery.setType(3);
                learnQuery.setPersonId(personId);
                List<PersonLearnRecordDO> recordList = personLearnRecordMapper.queryList(learnQuery);
                if (recordList.size() >= 5) {
                    ClassPersonQueryCondition ccQuery = new ClassPersonQueryCondition();
                    ccQuery.setPersonId(personId);
                    ccQuery.setCourseId(learnId);
                    List<ClassPersonDO> ccp = classInfoMapper.queryClassCoursePerson(ccQuery);
                    WeekReportDO weekReport = null;
                    if (ccp.size() > 0) {
                        weekReport = reportService.generateWeekReport(ccp.get(0).getClassId(), ids, personId);
                    }
                    if (weekReport != null && StringUtils.isNotEmpty(person.getWxMpId())) {
                        weixinTemplateMsgService.sendWeekReportTemplateMsg(person);
                    } else {
                        logger.warn("/n微信推送周报失败, 用户未绑定微信.");
                    }
                } else {
                    logger.warn("周学习课程不满足条件, 不生成周报.");
                }

            } else {
                logger.warn("/n微信推送周报失败, 周课程不满足5节.");
            }
            logger.info("学习记录生成周报, 微信公众号推送结束.");
        });

        return apiResult;
    }

    /**
     * 保存积分信息
     * @param personId 用户id
     * @param type 积分类型 1 蛋糕 2 糖果
     * @param source 积分来源 1:玩游戏初始积分 2:玩游戏加分 3:玩游戏复习积分
*              4:大声说初始积分 5:大声说加分 6:大声说复习积分 7:打卡积分
     * @param points 积分数
     * @param courseId
     * @param recordId
     */
    private void savePersonIntegral(Long personId, Integer type, Integer source, int points, Long courseId, Long recordId) {
        // 用户总积分累加
        PersonDO person = personMapper.queryById(personId);
        if (Validator.isEmpty(person)) {
            throw new CustomException("用户不存在");
        }

        Date date = new Date();
        IntegralDO integral = new IntegralDO();
        integral.setPersonId(personId);
        integral.setNumber(points);
        integral.setType(type);
        integral.setSource(source);
        integral.setCreateDate(DateUtils.format(date, "yyyy-MM-dd"));
        integral.setCreateTime(date);
        if (null != courseId) {
            integral.setCourseId(courseId);
        }
        if (null != recordId) {
            integral.setLearnRecordId(recordId);
        }
        integralMapper.save(integral);

        int candyPoints = person.getCandyPoints() == null ? 0 : person.getCandyPoints();
        int cakePoints = person.getCakePoints() == null ? 0 : person.getCakePoints();
        // 蛋糕
        if (type == POINTS_TYPE_CAKE) {
            person.setCakePoints(cakePoints + points);
            // 糖果
        } else if (type == POINTS_TYPE_CANDY) {
            person.setCandyPoints(candyPoints + points);
        }
        person.setCandyPoints(candyPoints + points);
        personMapper.update(person);
        
    }

    @Override
    public ApiResult getRoomListByPersonId(Long id) {
        // 查询学生的所有教室(直播)
        List<RoomListBO> roomList = roomMapper.queryRoomByPersonId(id);

        List<RoomListBO> resultList = new ArrayList<>();

        // 获取各个房间的录制件
        for(RoomListBO room : roomList) {

            // 设置直播房间状态
            try {
                // 提前七分钟开课
                Date current = new Date();
                long currentTime = current.getTime();
                long previewTime = DateUtil.addMinutes(current, 7).getTime();
                long startTime = Objects.requireNonNull(DateUtils.parse(room.getStartTime(), "yyyy-MM-dd HH:mm:ss")).getTime();
                long endTime = Objects.requireNonNull(DateUtils.parse(room.getEndTime(), "yyyy-MM-dd HH:mm:ss")).getTime();

                if (endTime <= currentTime) {
                    // 直播完成 可查看录制件
                    room.setStatus(1);
                    Map result = roomBusiness.getRecordList(room.getId().intValue());
                    Integer resultCode = (Integer) result.get("result");
                    // 执行成功
                    if (resultCode == 0) {

                        JSONArray json = (JSONArray) result.get("recordlist");
                        // 设置录制件地址 录制件默认只有一条
                        if (json.size() > 0) {
                            room.setVideoUrl(((JSONObject)json.get(json.size()-1)).getString("recordpath"));
                        }

                    }
                } else if (startTime <= previewTime) {
                    // 正在直播
                    room.setStatus(2);
                } else {
                    // 未开播
                    room.setStatus(3);
                }
                resultList.add(room);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new CustomException("时间格式化错误!");
            }

        }

        return new ApiResult(resultList);
    }

    @Override
    public ClockRecordBO personClockRecord(Long personId) {

        ClockRecordBO clockRecordBO = new ClockRecordBO();
        String[] weekDays = DateUtil.getWeekDays(new Date(), DateUtil.DatePatten.YYYY_MM_DD);


        ClockRecordDO clockRecord = clockRecordMapper.queryByPersonId(personId);

        // 没有打卡记录 直接返回
        if (clockRecord == null) {
            clockRecordBO.setSum(0);
            clockRecordBO.setClockIn(false);
            List<ClockRecordDetailBO> listClockRecordDetail = new ArrayList<>(CLOCK_MAX_DAY);
            ClockRecordDetailBO clockRecordDetail;
            for (int i = 0; i < CLOCK_MAX_DAY; i++) {
                clockRecordDetail = new ClockRecordDetailBO();
                clockRecordDetail.setDate(weekDays[i]);
                clockRecordDetail.setClockIn(false);
                listClockRecordDetail.add(clockRecordDetail);
            }
            clockRecordBO.setRecordList(listClockRecordDetail);
            return clockRecordBO;
        } else {
            // 当天是否打卡
            String currentDate = DateUtils.format(new Date(), "yyyy-MM-dd");
            clockRecordBO.setClockIn(clockRecord.getClockDate().equals(currentDate));

            // 查询本周打卡记录
            IntegralQueryCondition integralQuery = new IntegralQueryCondition();
            integralQuery.setStartTime(weekDays[0]);
            integralQuery.setEndTime(weekDays[6]);
            integralQuery.setPersonId(personId);
            integralQuery.setSource(POINTS_SOURCE_CLOCK);
            List<IntegralDO> listIntegral = integralMapper.queryList(integralQuery);
            Map<String, Boolean> mapIntegral = new HashMap<>(listIntegral.size());
            for(IntegralDO integral : listIntegral) {
                mapIntegral.put(integral.getCreateDate(), true);
            }

            ClockRecordDetailBO clockRecordDetail;
            List<ClockRecordDetailBO> listClockRecordDetail = new ArrayList<>(CLOCK_MAX_DAY);
            for (int i = 0; i < CLOCK_MAX_DAY; i++) {
                clockRecordDetail = new ClockRecordDetailBO();
                String d = weekDays[i];
                clockRecordDetail.setDate(d);
                // 每天的打卡情况
                if (mapIntegral.get(d) == null || !mapIntegral.get(d)) {
                    clockRecordDetail.setClockIn(false);
                } else {
                    clockRecordDetail.setClockIn(true);
                }
                listClockRecordDetail.add(clockRecordDetail);
            }
            clockRecordBO.setRecordList(listClockRecordDetail);
        }
        return clockRecordBO;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = CustomException.class)
    @Override
    @Deprecated
    public Integer personClockIn(Long personId) {

        Date datetime = new Date();
        String day = DateUtils.format(datetime, "yyyy-MM-dd");
        ClockRecordDO clockRecord = clockRecordMapper.queryByPersonId(personId);
        if (Validator.isEmpty(clockRecord)) {
            clockRecord = new ClockRecordDO();
            clockRecord.setPersonId(personId);
            clockRecord.setClockDate(day);
            clockRecord.setClockTime(datetime);
            clockRecord.setSum(1);
            clockRecord.setMaxCount(1);
            clockRecordMapper.save(clockRecord);
        } else {
            String clockDate = clockRecord.getClockDate();
            if (clockRecord.getClockDate().equals(day)) {
                return null;
            }
            try {
                if (DateUtils.getNextDt(clockDate).equals(day)) {
                    int sum = clockRecord.getSum();
                    if ((sum + 1) > CLOCK_MAX_DAY) {
                        clockRecord.setSum(1);
                    } else {
                        clockRecord.setSum(sum + 1);
                    }
                } else {
                    clockRecord.setSum(1);
                }
            } catch (ParseException e) {
                logger.error("用户打卡失败, 日期格式化错误" + e.getMessage());
            }
            clockRecord.setMaxCount(clockRecord.getMaxCount() + 1);
            clockRecord.setClockDate(day);
            clockRecord.setClockTime(datetime);
            clockRecordMapper.update(clockRecord);
        }
        // 打卡天数
        int clockTimes = clockRecord.getSum();
        int day1 = 1, day2 = 2, day5 = 5;
        int addNumber = 0;
        if (clockTimes == day1) {
            addNumber = 2;
        } else if (clockTimes == day2) {
            addNumber = 3;
        } else if (clockTimes > day2 && clockTimes < day5) {
            addNumber = 5;
        } else if (clockTimes >= day5 && clockTimes < CLOCK_MAX_DAY ) {
            addNumber = 7;
        } else if (clockTimes == CLOCK_MAX_DAY) {
            addNumber = 9;
        }

        savePersonIntegral(personId, POINTS_TYPE_CANDY, POINTS_SOURCE_CLOCK, addNumber, null, null);

        return addNumber;
    }

    @Override
    @Deprecated
    public ApiResult gainReviewPoints(Long personId, Integer type, String reviewDate) {

        IntegralQueryCondition integralQuery = new IntegralQueryCondition();
        integralQuery.setPersonId(personId);
        integralQuery.setCreateDate(reviewDate);
        if (type == GAME_TYPE_PLAY_GAME) {
            integralQuery.setSource(POINTS_SOURCE_PLAY_GAME_REVIEW);
        } else if (type == GAME_TYPE_SPEAK_UP) {
            integralQuery.setSource(POINTS_SOURCE_SPEAK_UP_REVIEW);
        }
        List<IntegralDO> listIntegral = integralMapper.queryList(integralQuery);

        if (listIntegral.size() > 0) {
            return new ApiResult(-1, ApiStatus.REVIEW_POINTS_EXISTS, "今日复习积分已经给与");
        } else {
            savePersonIntegral(personId, POINTS_TYPE_CANDY, integralQuery.getSource(), 3, null, null);
        }
        return new ApiResult("获取积分成功");
    }

    @Override
    public ApiResult queryCourseReport(Long personId, Long courseId) {

        CourseReportBO courseReport = new CourseReportBO();

        PersonDO person = personMapper.queryById(personId);
        if (Validator.isEmpty(person)) {
            return new ApiResult(-1, ApiStatus.SC_USER_DOSE_NOT_EXIST, "当前用户不存在");
        }
        // 用户信息设置
        courseReport.setPersonId(personId);
        courseReport.setPersonName(person.getNickName());
        courseReport.setAvatar(person.getPhotoUrl());

        CourseInfoDO courseInfo = courseInfoMapper.queryById(courseId);
        if (Validator.isEmpty(courseInfo)) {
            return new ApiResult(-1, ApiStatus.SC_COURSE_IS_NULL, "当前课程不存在");
        }
        // 课程信息
        courseReport.setCourseId(courseId);
        courseReport.setCourseName(courseInfo.getCourseName());

        // 课程游戏
        GameInfoQueryCondition queryGame = new GameInfoQueryCondition();
        queryGame.setCourseId(courseId);
        List<CourseGameBO> listGamInfo = courseInfoMapper.queryCourseGame(queryGame);
        Map<Long, CourseGameBO> mapGameInfo = new HashMap<>(listGamInfo.size());
        for (CourseGameBO gi : listGamInfo) {
            mapGameInfo.put(gi.getGameId(), gi);
        }

        PersonLearnRecordQueryCondition queryLearnRecord = new PersonLearnRecordQueryCondition();
        queryLearnRecord.setPersonId(personId);
        queryLearnRecord.setCourseId(courseId);
        // 课程学习记录
        List<PersonLearnRecordDO> listPersonLearnRecord = personLearnRecordMapper.queryList(queryLearnRecord);
        PersonLearnRecordDO personLearnRecord = null;
        ReportBO report;
        for (PersonLearnRecordDO p : listPersonLearnRecord) {
            if (p.getType() == 1) {
                personLearnRecord = p;
            } else {
                report = JSONObject.parseObject(p.getResultData(), ReportBO.class);
                if (Validator.isNotEmpty(report)) {
                    List<ReportDetailBO> listReportDetail = new ArrayList<>(16);
                    for (ReportDetailBO rd : report.getReportDetail()) {
                        CourseGameBO gameInfo = mapGameInfo.get(rd.getId());
                        // 若题目不存在, 则不显示之前做题的记录
                        if (Validator.isNotEmpty(gameInfo)) {
                            rd.setName(gameInfo.getName());
                            listReportDetail.add(rd);
                        }
                    }
                    report.setReportDetail(listReportDetail);
                }
                // 单词辨析
                if (p.getType() == 2) {
                    courseReport.setPlayGameReport(report);
                    // 大声说
                } else if (p.getType() == 3) {
                    courseReport.setSpeakUpReport(report);
                }
            }
        }

        courseReport.setTotalScore(personLearnRecord != null ? personLearnRecord.getScore() != null ? personLearnRecord.getScore() : 0 : 0);

        return new ApiResult(courseReport);

    }

    @Override
    public PersonPushSettingBO queryPersonPushSettings(PersonDO person) {

        PersonQueryCondition personQuery = new PersonQueryCondition();
        personQuery.setPersonId(person.getPersonId());
        List<ClassInfoDO> listClassInfo = personMapper.queryPersonClass(personQuery);
        Set<String> listPushTag = new HashSet<>(listClassInfo.size());
        for(ClassInfoDO c : listClassInfo) {
            if (Validator.isNotEmpty(c.getPushTag())) {
                listPushTag.add(c.getPushTag());
            }
        }

        PersonPushSettingBO personPushSettingBO = new PersonPushSettingBO();
        personPushSettingBO.setPersonId(person.getPersonId());
        personPushSettingBO.setPushAlias(person.getPushAlias());
        personPushSettingBO.setPushTags(listPushTag);

        return personPushSettingBO;
    }

    @Override
    public ApiResult gainPoints(Long personId, Integer type, int points, String date, Long courseId) {

        IntegralQueryCondition integralQuery = new IntegralQueryCondition();
        integralQuery.setPersonId(personId);
        integralQuery.setCreateDate(date);
        integralQuery.setSource(type);

        if (type == POINT_SOURCE_WATCH_VIDEO) {
            points = 5;
        }
        if (type == POINTS_SOURCE_PLAY_GAME_REVIEW || type == POINTS_SOURCE_SPEAK_UP_REVIEW) {
            List<IntegralDO> listIntegral = integralMapper.queryList(integralQuery);
            if (listIntegral.size() > 0) {
                return new ApiResult(-1, ApiStatus.REVIEW_POINTS_EXISTS, "今日复习积分已经给与");
            }
        }
        //savePersonIntegral(personId, POINTS_TYPE_CANDY, integralQuery.getSource(), points, courseId, null);

        return new ApiResult("获取积分成功");
    }

    @Override
    public ApiResult validateCasualPhone(String mobilePhone) {

        // 验证手机号码是否已注册
        CasualUserQueryCondition query = new CasualUserQueryCondition();
        query.setMobilePhone(mobilePhone);
        List<CasualUserDO> listCasualUser = casualUserMapper.queryList(query);
        if (!Validator.isEmpty(listCasualUser)) {
            return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_REGISTERED, "当前手机号码已经存在!");
        }
        return new ApiResult();
    }

    @Override
    public List<IntegralDetailBO> getIntegralDetail(IntegralQueryCondition query) {
        return new ArrayList<>(16); // integralMapper.queryIntegralDetail(query);
    }

    @Override
    public int queryTotalIntegralDetail(IntegralQueryCondition query) {
        return 0; // integralMapper.queryTotalIntegralDetail(query);
    }

    @Override
    public List<PushBO> queryPersonMessage(PushLogQueryCondition query) {

        List<PushLogDO> pushLogList = pushLogMapper.queryPersonMessage(query);
        List<PushBO> messageList = new ArrayList<>(pushLogList.size());

        PushBO message;
        for(PushLogDO p : pushLogList) {
            message = new PushBO();
            message.setMsg(p.getPushMsg());
            message.setTitle(p.getPushTitle());
            if (p.getPushType().equals(PushLogDO.PushType.VOICE_MSG)) {
                message.setAudioUrl(p.getPushData());
            }
            messageList.add(message);
        }
        return messageList;
    }

    @Override
    public int queryTotalPersonMessage(PushLogQueryCondition query) {
        return pushLogMapper.queryTotalPersonMessage(query);
    }



    @Override
    public ApiResult queryUserDetail(Long personId) {

        PersonDO person = personMapper.queryById(personId);
        // 设置返回对象
        PersonDetailBO personDetail = new PersonDetailBO();
        personDetail.setPersonId(person.getPersonId());
        personDetail.setMobileNumber(person.getMobileNumber());
        personDetail.setNickName(person.getNickName());
        personDetail.setGender(person.getGender());
        personDetail.setBirthday(person.getBirthday());
        personDetail.setPhotoUrl(person.getPhotoUrl());
        personDetail.setCandyPoints(person.getCandyPoints());
        personDetail.setCompleteTheCourse(false);
        personDetail.setClockIn(false);

        String currentDate = DateUtils.format(new Date(), "yyyy-MM-dd");

        // 用户当天学习课阶课程开课情况
        ClassInfoQueryCondition classInfoQueryCondition = new ClassInfoQueryCondition();
        classInfoQueryCondition.setPersonId(personId);
        if (person.getLearningStage() != null) {
            classInfoQueryCondition.setCourseStageId(person.getLearningStage());
        }
        classInfoQueryCondition.setOpenTime(currentDate);
        classInfoQueryCondition.setOrderField("cc.update_time");
        classInfoQueryCondition.setOrder("desc");
        List<ClassInfoDO> classPersonList = classInfoMapper.queryAllClassPerson(classInfoQueryCondition);
        // 获取当天当前学习课阶开课课程
        ClassInfoDO temp = null;
        if (classPersonList.size() > 0) {
            temp = classPersonList.get(0);
        }
        // 没有开课课程. 默认签到和已学习
        if (temp == null || temp.getOpenCourseId() == null) {
            personDetail.setClockIn(true);
            personDetail.setCompleteTheCourse(true);
        } else {
            // 用户当天学习记录
            PersonLearnRecordQueryCondition personLearnRecordQueryCondition = new PersonLearnRecordQueryCondition();
            personLearnRecordQueryCondition.setPersonId(personId);
            personLearnRecordQueryCondition.setCourseId(temp.getOpenCourseId());
            personLearnRecordQueryCondition.setStartTime(currentDate + " 00:00:00");
            personLearnRecordQueryCondition.setEndTime(currentDate + " 23:59:59");
            personLearnRecordQueryCondition.setType(PersonLearnRecordQueryCondition.LEARN_TYPE.SPEAK_UP);
            List<PersonLearnRecordDO> personLearnRecordList = personLearnRecordMapper.queryList(personLearnRecordQueryCondition);
            if (personLearnRecordList.size() > 0) {
                personDetail.setCompleteTheCourse(true);
            }
            // 用户当天打卡情况
            PersonCourseClockQueryCondition personCourseClockQueryCondition = new PersonCourseClockQueryCondition();
            personCourseClockQueryCondition.setPersonId(personId);
            personCourseClockQueryCondition.setCourseId(temp.getOpenCourseId());
            personCourseClockQueryCondition.setClockDate(currentDate);
            List<PersonCourseClockDO> personCourseClockList = personCourseClockMapper.queryList(personCourseClockQueryCondition);
            if (personCourseClockList.size() > 0) {
                personDetail.setClockIn(true);
            }
        }

        // 推送标签
        PersonQueryCondition personQuery = new PersonQueryCondition();
        personQuery.setPersonId(person.getPersonId());
        List<ClassInfoDO> listClassInfo = personMapper.queryPersonClass(personQuery);
        Set<String> pushTagsSet = new HashSet<>(listClassInfo.size());
        for(ClassInfoDO c : listClassInfo) {
            if (Validator.isNotEmpty(c.getPushTag())) {
                pushTagsSet.add(c.getPushTag());
            }
        }
        personDetail.setPushTags(pushTagsSet);
        personDetail.setPushAlias(person.getPushAlias());

        return new ApiResult(personDetail);
    }

    @Override
    public ApiResult queryCourseStagePersonBought(Long personId, Integer pageIndex, Integer pageSize) {

        PersonCourseStageQueryCondition query = new PersonCourseStageQueryCondition();
        query.setPersonId(personId);
        query.setPage(pageIndex);
        query.setLimit(pageSize);

        List<CourseStageInfoBO> list = personCourseStageMapper.queryCourseStagePersonBought(query);

        int total = personCourseStageMapper.countCourseStagePersonBought(query);

        PageUtils pageUtils = new PageUtils(list, total, query.getLimit(), query.getLimit());

        return new ApiResult(pageUtils);
    }
}
