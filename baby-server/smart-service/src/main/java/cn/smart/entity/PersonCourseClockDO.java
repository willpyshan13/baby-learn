package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * 
 * @author Ye
 * @date 2018-9-14 10:03:36
 */
public class PersonCourseClockDO extends BaseEntity {

    /**
    * 课程签到id
    */
    private Long id;


    /**
    * 课阶id
    */
    private Long stageId;


    /**
    * 单元id
    */
    private Long groupId;


    /**
    * 课程id
    */
    private Long courseId;


    /**
    * 用户id
    */
    private Long personId;


    /**
    * 签到日期
    */
    private String clockDate;


    /**
    * 获得积分
    */
    private Integer points;


    /**
    * 创建人
    */
    private Long createBy;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新人
    */
    private Long updateBy;


    /**
    * 更新时间
    */
    private Date updateTime;

	/**
     * Get 课程签到id
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 课程签到id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 课阶id
	 */
	public Long getStageId() {
		return stageId;
	}
	
	/** 
     * Set 课阶id
	 */
	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}
	/**
     * Get 单元id
	 */
	public Long getGroupId() {
		return groupId;
	}
	
	/** 
     * Set 单元id
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	/**
     * Get 课程id
	 */
	public Long getCourseId() {
		return courseId;
	}
	
	/** 
     * Set 课程id
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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
     * Get 签到日期
	 */
	public String getClockDate() {
		return clockDate;
	}
	
	/** 
     * Set 签到日期
	 */
	public void setClockDate(String clockDate) {
		this.clockDate = clockDate;
	}
	/**
     * Get 获得积分
	 */
	public Integer getPoints() {
		return points;
	}
	
	/** 
     * Set 获得积分
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	/**
     * Get 创建人
	 */
	public Long getCreateBy() {
		return createBy;
	}
	
	/** 
     * Set 创建人
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
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
     * Get 更新人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	
	/** 
     * Set 更新人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
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

    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"stageId\":")
                .append(stageId);
        sb.append("\"groupId\":")
                .append(groupId);
        sb.append("\"courseId\":")
                .append(courseId);
        sb.append("\"personId\":")
                .append(personId);
        sb.append("\"clockDate\":")
                .append(clockDate);
        sb.append("\"points\":")
                .append(points);
        sb.append("\"createBy\":")
                .append(createBy);
        sb.append("\"createTime\":")
                .append(createTime);
        sb.append("\"updateBy\":")
                .append(updateBy);
        sb.append("\"updateTime\":")
                .append(updateTime);
        sb.append('}');
        return sb.toString();
    }

}