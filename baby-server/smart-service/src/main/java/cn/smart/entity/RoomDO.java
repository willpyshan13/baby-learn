package cn.smart.entity;

import cn.smart.common.annotation.MyCheck;
import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-24 14:28:59
 */
public class RoomDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 教室名称
    */
    @MyCheck(common = "教室名称")
    private String roomName;


    /**
    * 教室类型 1:一对一 2:一对四 3:一对多
    */
    private Integer roomType;


    /**
    * 开始时间
    */
    @MyCheck(common = "开始时间")
    private Date startTime;


    /**
    * 结束时间
    */
    @MyCheck(common = "结束时间")
    private Date endTime;


    /**
    * 老师密码
    */
    @MyCheck(common = "老师密码")
    private String chairmanPwd;


    /**
    * 助教密码
    */
	@MyCheck(common = "助教密码")
	private String assistantPwd;


    /**
    * 学员密码
    */
    private String confuserPwd;


    /**
    * 巡课密码
    */
	@MyCheck(common = "巡课密码")
    private String patrolPwd;


    /**
    * 是否密码 1:需要 0:不需要
    */
    private Integer passwordRequired;


    /**
    * 自动开启音视频 1:开启 0:未开启
    */
    private Integer autoOpenAv;


    /**
    * 视频分辨率 0:176x144 1:320x240
    */
    private Integer videoType;


    /**
    * 学员地址
    */
    private String confuserUrl;


    /**
    * 教师地址
    */
    private String teacherUrl;


    /**
    * 视频帧率
    */
    private Integer videoFrameRate;


    /**
    * 课堂点数
    */
    private Integer confuserNum;


    /**
    * 班级id
    */
    private Long classId;


    /**
    * 教室图片地址
    */
    private String logoUrl;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新时间(自动维护)
    */
    private Date updateTime;

	/**
	 * .h264硬件编码 0 : 否(默认) 2:是
	 */
	private Integer vcodec;

	/**
	 * 非持久化属性
	 */
	private String className;

	/**
	 * Get
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 教室名称
	 */
	public String getRoomName() {
		return roomName;
	}
	
	/** 
     * Set 教室名称
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	/**
     * Get 教室类型 0:一对一 2: 3:一对多
	 */
	public Integer getRoomType() {
		return roomType;
	}
	
	/** 
     * Set 教室类型 0:一对一 2: 3:一对多
	 */
	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}
	/**
     * Get 开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/** 
     * Set 开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
     * Get 结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/** 
     * Set 结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
     * Get 老师密码
	 */
	public String getChairmanPwd() {
		return chairmanPwd;
	}
	
	/** 
     * Set 老师密码
	 */
	public void setChairmanPwd(String chairmanPwd) {
		this.chairmanPwd = chairmanPwd;
	}
	/**
     * Get 助教密码
	 */
	public String getAssistantPwd() {
		return assistantPwd;
	}
	
	/** 
     * Set 助教密码
	 */
	public void setAssistantPwd(String assistantPwd) {
		this.assistantPwd = assistantPwd;
	}
	/**
     * Get 学员密码
	 */
	public String getConfuserPwd() {
		return confuserPwd;
	}
	
	/** 
     * Set 学员密码
	 */
	public void setConfuserPwd(String confuserPwd) {
		this.confuserPwd = confuserPwd;
	}
	/**
     * Get 巡课密码
	 */
	public String getPatrolPwd() {
		return patrolPwd;
	}
	
	/** 
     * Set 巡课密码
	 */
	public void setPatrolPwd(String patrolPwd) {
		this.patrolPwd = patrolPwd;
	}
	/**
     * Get 是否密码 1:需要 0:不需要
	 */
	public Integer getPasswordRequired() {
		return passwordRequired;
	}
	
	/** 
     * Set 是否密码 1:需要 0:不需要
	 */
	public void setPasswordRequired(Integer passwordRequired) {
		this.passwordRequired = passwordRequired;
	}
	/**
     * Get 自动开启音视频 1:开启 0:未开启
	 */
	public Integer getAutoOpenAv() {
		return autoOpenAv;
	}
	
	/** 
     * Set 自动开启音视频 1:开启 0:未开启
	 */
	public void setAutoOpenAv(Integer autoOpenAv) {
		this.autoOpenAv = autoOpenAv;
	}
	/**
     * Get 视频分辨率 0:176x144 1:320x240
	 */
	public Integer getVideoType() {
		return videoType;
	}
	
	/** 
     * Set 视频分辨率 0:176x144 1:320x240
	 */
	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
	}
	/**
     * Get 学员地址
	 */
	public String getConfuserUrl() {
		return confuserUrl;
	}
	
	/** 
     * Set 学员地址
	 */
	public void setConfuserUrl(String confuserUrl) {
		this.confuserUrl = confuserUrl;
	}
	/**
     * Get 教师地址
	 */
	public String getTeacherUrl() {
		return teacherUrl;
	}
	
	/** 
     * Set 教师地址
	 */
	public void setTeacherUrl(String teacherUrl) {
		this.teacherUrl = teacherUrl;
	}
	/**
     * Get 视频帧率
	 */
	public Integer getVideoFrameRate() {
		return videoFrameRate;
	}
	
	/** 
     * Set 视频帧率
	 */
	public void setVideoFrameRate(Integer videoFrameRate) {
		this.videoFrameRate = videoFrameRate;
	}
	/**
     * Get 课堂点数
	 */
	public Integer getConfuserNum() {
		return confuserNum;
	}
	
	/** 
     * Set 课堂点数
	 */
	public void setConfuserNum(Integer confuserNum) {
		this.confuserNum = confuserNum;
	}
	/**
     * Get 班级id
	 */
	public Long getClassId() {
		return classId;
	}
	
	/** 
     * Set 班级id
	 */
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	/**
     * Get 教室图片地址
	 */
	public String getLogoUrl() {
		return logoUrl;
	}
	
	/** 
     * Set 教室图片地址
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
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
     * Get 更新时间(自动维护)
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 
     * Set 更新时间(自动维护)
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getVcodec() {
		return vcodec;
	}

	public void setVcodec(Integer vcodec) {
		this.vcodec = vcodec;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}