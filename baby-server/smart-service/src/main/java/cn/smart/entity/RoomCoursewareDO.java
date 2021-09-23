package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-25 19:38:39
 */
public class RoomCoursewareDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 教室id
    */
    private Long roomId;


    /**
    * 课件id
    */
    private Long coursewareId;


    /**
    * 
    */
    private Date createTime;


    /**
    * 
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
     * Get 教室id
	 */
	public Long getRoomId() {
		return roomId;
	}
	
	/** 
     * Set 教室id
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	/**
     * Get 课件id
	 */
	public Long getCoursewareId() {
		return coursewareId;
	}
	
	/** 
     * Set 课件id
	 */
	public void setCoursewareId(Long coursewareId) {
		this.coursewareId = coursewareId;
	}
	/**
     * Get 
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 
     * Set 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
     * Get 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 
     * Set 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}