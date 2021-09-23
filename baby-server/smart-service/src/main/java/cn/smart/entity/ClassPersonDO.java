package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-11 14:41:37
 */
public class ClassPersonDO extends BaseEntity {

    /**
    * 
    */
    private Integer id;


    /**
    * 班级id
    */
    private Long classId;


    /**
    * 用户id
    */
    private Long personId;


    /**
    * 
    */
    private Date createTime;


    /**
    * 更新时间(自动维护)
    */
    private Date updateTime;

	/**
	 * 非持久化属性
	 */
	private Long courseId;
	private String className;
	private Long courseStageId;

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

	@Override
	public String toString() {
		return "ClassPersonDO{" +
				"id=" + id +
				", classId=" + classId +
				", personId=" + personId +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Long getCourseStageId() {
		return courseStageId;
	}

	public void setCourseStageId(Long courseStageId) {
		this.courseStageId = courseStageId;
	}
}