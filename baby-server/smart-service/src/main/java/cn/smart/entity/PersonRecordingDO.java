package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * 
 * @author Ye
 * @date 2018-10-12 17:14:16
 */
public class PersonRecordingDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 用户ID
    */
    private Long personId;


    /**
    * 教室ID
    */
    private Long roomId;


    /**
    * 录制件ID
    */
    private Long recordingId;


    /**
    * 创建日期
    */
    private Date createDate;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 当天开课课程ID
    */
    private Long courseId;

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
     * Get 用户ID
	 */
	public Long getPersonId() {
		return personId;
	}
	
	/** 
     * Set 用户ID
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	/**
     * Get 教室ID
	 */
	public Long getRoomId() {
		return roomId;
	}
	
	/** 
     * Set 教室ID
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	/**
     * Get 录制件ID
	 */
	public Long getRecordingId() {
		return recordingId;
	}
	
	/** 
     * Set 录制件ID
	 */
	public void setRecordingId(Long recordingId) {
		this.recordingId = recordingId;
	}
	/**
     * Get 创建日期
	 */
	public Date getCreateDate() {
		return createDate;
	}
	
	/** 
     * Set 创建日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
     * Get 当天开课课程ID
	 */
	public Long getCourseId() {
		return courseId;
	}
	
	/** 
     * Set 当天开课课程ID
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"personId\":")
                .append(personId);
        sb.append("\"roomId\":")
                .append(roomId);
        sb.append("\"recordingId\":")
                .append(recordingId);
        sb.append("\"createDate\":")
                .append(createDate);
        sb.append("\"createTime\":")
                .append(createTime);
        sb.append("\"courseId\":")
                .append(courseId);
        sb.append('}');
        return sb.toString();
    }

}