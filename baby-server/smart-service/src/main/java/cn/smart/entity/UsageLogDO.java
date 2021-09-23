package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-6-15 17:55:05
 */
public class UsageLogDO extends BaseEntity {

    /**
    * 
    */
    private Integer id;


    /**
    * 记录名称
    */
    private String name;

    /**
    * 记录类型 1:我的课堂 2: 播放器 3:一起玩-爱奇艺 4:一起玩-小企鹅
	 * 5、跳舞
	 * 6、音乐
	 * 7、故事
	 * 8、悬浮框
	 * 9、语音点击
	 * 10、长颈鹿点击
	 * 11、LiPa 动物园
	 * 12、LiPa 配对
	 * 13、LiPa 气球
	 * 14、LiPa 动物园
    */
    private Integer type;


    /**
    * 用户id
    */
    private Long userId;


    /**
    * 开始时间
    */
    private Date startTime;


    /**
    * 结束时间
    */
    private Date endTime;


    /**
    * 时长
    */
    private Long duration;


    /**
    * 创建时间
    */
    private Date createTime;

	/**
	 * 非持久化属性
	 * 用户姓名
	 */
	private String userName;

	/**
	 * 事件类型
	 */
	private Integer eventType;

	/**
	 * 点击次数
	 */
	private Long number;

	/**
     * Get 
	 */
	public Integer getId() {
		return id;
	}
	
	/** 
     * Set 
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
     * Get 记录名称
	 */
	public String getName() {
		return name;
	}
	
	/** 
     * Set 记录名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
     * Get 记录类型 1:我的课堂 2: 播放器 3:一起玩-爱奇艺 4:一起玩-小企鹅
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 记录类型 1:我的课堂 2: 播放器 3:一起玩-爱奇艺 4:一起玩-小企鹅
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * Get 用户id
	 */
	public Long getUserId() {
		return userId;
	}
	
	/** 
     * Set 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
     * Get 时长
	 */
	public Long getDuration() {
		return duration;
	}
	
	/** 
     * Set 时长
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "UsageLogDO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type=" + type +
				", userId=" + userId +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", duration=" + duration +
				", createTime=" + createTime +
				", userName='" + userName + '\'' +
				", eventType=" + eventType +
				", number=" + number +
				'}';
	}
}