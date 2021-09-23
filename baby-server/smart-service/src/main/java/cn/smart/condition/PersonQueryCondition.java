package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-8 16:14:27
 */

public class PersonQueryCondition extends QueryCondition {

    /**
     * 用户id
     */
    private Long personId;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 性别 1:男 0:女
     */
    private Boolean gender;
    /**
     * 手机号码
     */
    private String mobileNumber;

    private String searchKeys;
    /**
     * 序列号
     */
    private String mobileSerial;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 个人描述
     */
    private String personDesc;
    /**
     * 头像
     */
    private String photoUrl;
    /**
     * 用户状态 1 ：生效 0 ：失效
     */
    private Integer status;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 累计积分
     */
    private Long accumulatedPoints;
    /**
     * 渠道 1:app 2:h5 3:ios
     */
    private Integer channel;

    /**
     * 推送别名
     */
    private String pushAlias;

    /**
     * 班级id
     */
    private Long classId;

    /**
     * 用户id集合
     */
    private Long[] exitsPersonIds;

    /**
     * 当前学习课阶
     */
    private Long learningStage;

    /**
     * 最新周报id
     */
    private Long weekReportId;

    /**
     * 微信id
     */
    private String wxMpId;

    /**
     * 用户id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * 用户id
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * 生日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 性别 1:男 0:女
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * 性别 1:男 0:女
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * 手机号码
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * 手机号码
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * 序列号
     */
    public String getMobileSerial() {
        return mobileSerial;
    }

    /**
     * 序列号
     */
    public void setMobileSerial(String mobileSerial) {
        this.mobileSerial = mobileSerial;
    }

    /**
     * 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 个人描述
     */
    public String getPersonDesc() {
        return personDesc;
    }

    /**
     * 个人描述
     */
    public void setPersonDesc(String personDesc) {
        this.personDesc = personDesc;
    }

    /**
     * 头像
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * 头像
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * 用户状态 1 ：生效 0 ：失效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 用户状态 1 ：生效 0 ：失效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 累计积分
     */
    public Long getAccumulatedPoints() {
        return accumulatedPoints;
    }

    /**
     * 累计积分
     */
    public void setAccumulatedPoints(Long accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }

    /**
     * 渠道 1:app 2:h5 3:ios
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * 渠道 1:app 2:h5 3:ios
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getPushAlias() {
        return pushAlias;
    }

    public void setPushAlias(String pushAlias) {
        this.pushAlias = pushAlias;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long[] getExitsPersonIds() {
        return exitsPersonIds;
    }

    public void setExitsPersonIds(Long[] exitsPersonIds) {
        this.exitsPersonIds = exitsPersonIds;
    }

    public Long getLearningStage() {
        return learningStage;
    }

    public void setLearningStage(Long learningStage) {
        this.learningStage = learningStage;
    }

    public String getWxMpId() {
        return wxMpId;
    }

    public void setWxMpId(String wxMpId) {
        this.wxMpId = wxMpId;
    }

    public Long getWeekReportId() {
        return weekReportId;
    }

    public void setWeekReportId(Long weekReportId) {
        this.weekReportId = weekReportId;
    }

    public String getSearchKeys() {
        return searchKeys;
    }

    public void setSearchKeys(String searchKeys) {
        this.searchKeys = searchKeys;
    }
}
