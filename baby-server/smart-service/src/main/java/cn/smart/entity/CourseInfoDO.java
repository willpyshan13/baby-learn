package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 *  课程信息
 * @author Ye
 * @date 2018-4-13 17:08:48
 */
public class CourseInfoDO extends BaseEntity {

    /**
    * 课程id
    */
    private Long id;


    /**
    * 课程名称
    */
    private String courseName;


    /**
    * 课程描述
    */
    private String description;


    /**
    * 课程阶段
    */
    private Long stageId;


    /**
    * 教师id
    */
    private Long teacherId;


    /**
    * 课程状态 1：生效 0：失效
    */
    private Integer status;


    /**
    * 课程类型 0 : 录屏 1 : 直播
    */
    private Integer type;


    /**
    * 开始时间
    */
    private Date startTime;


    /**
    * 结束时间
    */
    private Date endTime;


    /**
    * 课程时长
    */
    private Long duration;


    /**
    * 课程序号
    */
    private Integer serialNumber;


    /**
    * 观看权限 0 : 购买可看 1 : 登录可看 2 : 游客可看
    */
    private Integer viewingAuthority;


    /**
    * 更新时间(自动更新)
    */
    private Date updateTime;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 内容类别
    */
    private String category;


    /**
    * 视频地址
    */
    private String videoUrl;


    /**
    * 图片地址
    */
    private String imageUrl;


    /**
    * tts文本
    */
    private String ttsText;


    /**
    * 单词
    */
    private String word;

	/**
	 * 非持久化对象
	 * 课阶名称
	 */
	private String stageName;

	/**
	 * 资源地址
	 */
	private String resourceUrl;

	/**
	 * 周
	 */
	private Integer weeks;

	/**
	 * 单元ID
	 */
	private Long groupId;

	/**
	 * 签到印章图片地址
	 */
	private String stampImageUrl;
	/**
	 * 卡片图片地址
	 */
	private String cardImageUrl;
	/**
	 * 非持久化属性
	 */
	private Integer groupCode;
	private String groupName;

	/**
     * Get 课程id
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 课程id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 课程名称
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/** 
     * Set 课程名称
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
     * Get 课程描述
	 */
	public String getDescription() {
		return description;
	}
	
	/** 
     * Set 课程描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
     * Get 课程阶段
	 */
	public Long getStageId() {
		return stageId;
	}
	
	/** 
     * Set 课程阶段
	 */
	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}
	/**
     * Get 教师id
	 */
	public Long getTeacherId() {
		return teacherId;
	}
	
	/** 
     * Set 教师id
	 */
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	/**
     * Get 课程状态 1：生效 0：失效
	 */
	public Integer getStatus() {
		return status;
	}
	
	/** 
     * Set 课程状态 1：生效 0：失效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
     * Get 课程类型 0 : 录屏 1 : 直播
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 课程类型 0 : 录屏 1 : 直播
	 */
	public void setType(Integer type) {
		this.type = type;
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
     * Get 课程时长
	 */
	public Long getDuration() {
		return duration;
	}
	
	/** 
     * Set 课程时长
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	/**
     * Get 课程序号
	 */
	public Integer getSerialNumber() {
		return serialNumber;
	}
	
	/** 
     * Set 课程序号
	 */
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
     * Get 观看权限 0 : 购买可看 1 : 登录可看 2 : 游客可看
	 */
	public Integer getViewingAuthority() {
		return viewingAuthority;
	}
	
	/** 
     * Set 观看权限 0 : 购买可看 1 : 登录可看 2 : 游客可看
	 */
	public void setViewingAuthority(Integer viewingAuthority) {
		this.viewingAuthority = viewingAuthority;
	}
	/**
     * Get 更新时间(自动更新)
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 
     * Set 更新时间(自动更新)
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
     * Get 内容类别
	 */
	public String getCategory() {
		return category;
	}
	
	/** 
     * Set 内容类别
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
     * Get 视频地址
	 */
	public String getVideoUrl() {
		return videoUrl;
	}
	
	/** 
     * Set 视频地址
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	/**
     * Get 图片地址
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	
	/** 
     * Set 图片地址
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
     * Get tts文本
	 */
	public String getTtsText() {
		return ttsText;
	}
	
	/** 
     * Set tts文本
	 */
	public void setTtsText(String ttsText) {
		this.ttsText = ttsText;
	}
	/**
     * Get 单词
	 */
	public String getWord() {
		return word;
	}
	
	/** 
     * Set 单词
	 */
	public void setWord(String word) {
		this.word = word;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"id\":")
				.append(id);
		sb.append(",\"courseName\":\"")
				.append(courseName).append('\"');
		sb.append(",\"stageId\":")
				.append(stageId);
		sb.append(",\"teacherId\":")
				.append(teacherId);
		sb.append(",\"status\":")
				.append(status);
		sb.append(",\"type\":")
				.append(type);
		sb.append(",\"serialNumber\":")
				.append(serialNumber);
		sb.append(",\"viewingAuthority\":")
				.append(viewingAuthority);
		sb.append(",\"videoUrl\":\"")
				.append(videoUrl).append('\"');
		sb.append(",\"imageUrl\":\"")
				.append(imageUrl).append('\"');
		sb.append(",\"ttsText\":\"")
				.append(ttsText).append('\"');
		sb.append(",\"resourceUrl\":\"")
				.append(resourceUrl).append('\"');
		sb.append('}');
		return sb.toString();
	}

	public Integer getWeeks() {
		return weeks;
	}

	public void setWeeks(Integer weeks) {
		this.weeks = weeks;
	}

	public String getStampImageUrl() {
		return stampImageUrl;
	}

	public void setStampImageUrl(String stampImageUrl) {
		this.stampImageUrl = stampImageUrl;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Integer getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(Integer groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCardImageUrl() {
		return cardImageUrl;
	}

	public void setCardImageUrl(String cardImageUrl) {
		this.cardImageUrl = cardImageUrl;
	}
}