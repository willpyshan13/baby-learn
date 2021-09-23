package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-6-29 17:26:56
 */
public class ClockRecordDO extends BaseEntity {

    /**
    * 
    */
    private Integer id;


    /**
    * 用户id
    */
    private Long personId;


    /**
    * 首次打卡日期
    */
	@Deprecated
    private String initDate;


    /**
    * 打卡日期
    */
    private String clockDate;


    /**
    * 打卡时间
    */
    private Date clockTime;


    /**
    * 连续打卡次数
    */
    private Integer sum;


    /**
    * 连续打卡最高次数
    */
    private Integer maxCount;


    /**
    * 更新时间
    */
    private Date updateTime;

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
     * Get 首次打卡日期
	 */
	@Deprecated
	public String getInitDate() {
		return initDate;
	}
	
	/** 
     * Set 首次打卡日期
	 */
	@Deprecated
	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}
	/**
     * Get 打卡日期
	 */
	public String getClockDate() {
		return clockDate;
	}
	
	/** 
     * Set 打卡日期
	 */
	public void setClockDate(String clockDate) {
		this.clockDate = clockDate;
	}
	/**
     * Get 打卡时间
	 */
	public Date getClockTime() {
		return clockTime;
	}
	
	/** 
     * Set 打卡时间
	 */
	public void setClockTime(Date clockTime) {
		this.clockTime = clockTime;
	}
	/**
     * Get 连续打卡次数
	 */
	public Integer getSum() {
		return sum;
	}
	
	/** 
     * Set 连续打卡次数
	 */
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	/**
     * Get 连续打卡最高次数
	 */
	public Integer getMaxCount() {
		return maxCount;
	}
	
	/** 
     * Set 连续打卡最高次数
	 */
	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
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
	
}