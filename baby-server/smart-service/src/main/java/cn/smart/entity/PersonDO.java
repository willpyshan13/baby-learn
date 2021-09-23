package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 *
 * @author Ye
 * @date 2018-4-8 16:14:27
 */
public class PersonDO extends BaseEntity {

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
    * 用户状态 1：生效 0：失效
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
	 * 糖果积分
	 */
	private Integer candyPoints;

	/**
	 * 蛋糕积分
	 */
	private Integer cakePoints;

	/**
	 * 当前学习课阶
	 */
	private Long learningStage;

	/**
	 * 当前是否登录
	 */
	private Boolean login;

	/**
	 * 非持久化字段
	 * 当天是否打卡
	 */

	private String className;
	private Boolean clockIn;

	private Long weekReportId;

	private String wxMpId;

	/**
     * Get 用户id
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
     * Set 用户id
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	/**
     * Get 生日
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
     * Set 生日
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
     * Get 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
     * Set 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
     * Get 性别 1:男 0:女
	 */
	public Boolean getGender() {
		return gender;
	}

	/**
     * Set 性别 1:男 0:女
	 */
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	/**
     * Get 手机号码
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
     * Set 手机号码
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
     * Get 序列号
	 */
	public String getMobileSerial() {
		return mobileSerial;
	}

	/**
     * Set 序列号
	 */
	public void setMobileSerial(String mobileSerial) {
		this.mobileSerial = mobileSerial;
	}
	/**
     * Get 昵称
	 */
	public String getNickName() {
		return nickName;
	}

	/**
     * Set 昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
     * Get 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
     * Set 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
     * Get 个人描述
	 */
	public String getPersonDesc() {
		return personDesc;
	}

	/**
     * Set 个人描述
	 */
	public void setPersonDesc(String personDesc) {
		this.personDesc = personDesc;
	}
	/**
     * Get 头像
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
     * Set 头像
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	/**
     * Get 用户状态 1：生效 0：失效
	 */
	public Integer getStatus() {
		return status;
	}

	/**
     * Set 用户状态 1：生效 0：失效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
     * Get 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
     * Set 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
     * Get 用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
     * Set 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
     * Get 累计积分
	 */
	public Long getAccumulatedPoints() {
		return accumulatedPoints;
	}

	/**
     * Set 累计积分
	 */
	public void setAccumulatedPoints(Long accumulatedPoints) {
		this.accumulatedPoints = accumulatedPoints;
	}
	/**
     * Get 渠道 1:app 2:h5 3:ios
	 */
	public Integer getChannel() {
		return channel;
	}

	/**
     * Set 渠道 1:app 2:h5 3:ios
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

	public Integer getCandyPoints() {
		return candyPoints;
	}

	public void setCandyPoints(Integer candyPoints) {
		this.candyPoints = candyPoints;
	}

	public Integer getCakePoints() {
		return cakePoints;
	}

	public void setCakePoints(Integer cakePoints) {
		this.cakePoints = cakePoints;
	}

	public Long getLearningStage() {
		return learningStage;
	}

	public void setLearningStage(Long learningStage) {
		this.learningStage = learningStage;
	}

	public Boolean getClockIn() {
		return clockIn;
	}

	public void setClockIn(Boolean clockIn) {
		this.clockIn = clockIn;
	}

	public Boolean getLogin() {
		return login;
	}

	public void setLogin(Boolean login) {
		this.login = login;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"personId\":")
				.append(personId);
		sb.append(",\"birthday\":\"")
				.append(birthday).append('\"');
		sb.append(",\"createTime\":\"")
				.append(createTime).append('\"');
		sb.append(",\"gender\":")
				.append(gender);
		sb.append(",\"mobileNumber\":\"")
				.append(mobileNumber).append('\"');
		sb.append(",\"mobileSerial\":\"")
				.append(mobileSerial).append('\"');
		sb.append(",\"nickName\":\"")
				.append(nickName).append('\"');
		sb.append(",\"password\":\"")
				.append(password).append('\"');
		sb.append(",\"personDesc\":\"")
				.append(personDesc).append('\"');
		sb.append(",\"photoUrl\":\"")
				.append(photoUrl).append('\"');
		sb.append(",\"status\":")
				.append(status);
		sb.append(",\"userName\":\"")
				.append(userName).append('\"');
		sb.append(",\"accumulatedPoints\":")
				.append(accumulatedPoints);
		sb.append(",\"channel\":")
				.append(channel);
		sb.append(",\"pushAlias\":\"")
				.append(pushAlias).append('\"');
		sb.append(",\"candyPoints\":")
				.append(candyPoints);
		sb.append(",\"learningStage\":")
				.append(learningStage);
		sb.append(",\"login\":")
				.append(login);
		sb.append(",\"clockIn\":")
				.append(clockIn);
		sb.append('}');
		return sb.toString();
	}

	public Long getWeekReportId() {
		return weekReportId;
	}

	public void setWeekReportId(Long weekReportId) {
		this.weekReportId = weekReportId;
	}

	public String getWxMpId() {
		return wxMpId;
	}

	public void setWxMpId(String wxMpId) {
		this.wxMpId = wxMpId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}