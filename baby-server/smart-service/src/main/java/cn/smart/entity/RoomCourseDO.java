package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-10 14:46:38
 */
public class RoomCourseDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 
    */
    private Long roomId;


    /**
    * 
    */
    private Long courseInfoId;


    /**
    * 是否开放 0:否 1:开放
    */
    private Boolean status;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新时间(自动维护)
    */
    private Date updateTime;

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
     * Get 
	 */
	public Long getRoomId() {
		return roomId;
	}
	
	/** 
     * Set 
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	/**
     * Get 
	 */
	public Long getCourseInfoId() {
		return courseInfoId;
	}
	
	/** 
     * Set 
	 */
	public void setCourseInfoId(Long courseInfoId) {
		this.courseInfoId = courseInfoId;
	}
	/**
     * Get 是否开放 0:否 1:开放
	 */
	public Boolean getStatus() {
		return status;
	}
	
	/** 
     * Set 是否开放 0:否 1:开放
	 */
	public void setStatus(Boolean status) {
		this.status = status;
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
	
}