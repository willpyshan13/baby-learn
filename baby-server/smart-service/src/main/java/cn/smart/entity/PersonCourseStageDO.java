package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 用户购买课程关联表
 * @author Ye
 * @date 2018-4-4 17:22:04
 */
public class PersonCourseStageDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 用户id
    */
    private Long personId;


    /**
    * 课阶id
    */
    private Long courseStageId;


    /**
    * 订单id
    */
    private Long orderId;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新时间(自动维护)
    */
    private Date updateTime;

	/**
	 * 非持久化属性
	 */
	private Long createBy;
	private Long updateBy;

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
     * Get 课阶id
	 */
	public Long getCourseStageId() {
		return courseStageId;
	}
	
	/** 
     * Set 课阶id
	 */
	public void setCourseStageId(Long courseStageId) {
		this.courseStageId = courseStageId;
	}
	/**
     * Get 订单id
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/** 
     * Set 订单id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
}