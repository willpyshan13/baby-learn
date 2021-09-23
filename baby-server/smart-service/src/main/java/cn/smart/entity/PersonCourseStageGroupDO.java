package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * 课程阶段单元
 * @author Ye
 * @date 2018-9-14 15:58:29
 */
public class PersonCourseStageGroupDO extends BaseEntity {

    /**
    * 单元编号
    */
    private Long id;


    /**
    * 课阶编号
    */
    private Long stageId;


    /**
    * 单元id
    */
    private Long groupId;


    /**
    * 用户id
    */
    private Long personId;


    /**
    * 当前积分
    */
    private Integer currentPoints;


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
	 * 非持久化属性
	 */
	private Integer groupCode;

	/**
	 * Get 单元编号
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 单元编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 课阶编号
	 */
	public Long getStageId() {
		return stageId;
	}
	
	/** 
     * Set 课阶编号
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
     * Get 当前积分
	 */
	public Integer getCurrentPoints() {
		return currentPoints;
	}
	
	/** 
     * Set 当前积分
	 */
	public void setCurrentPoints(Integer currentPoints) {
		this.currentPoints = currentPoints;
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
        sb.append("\"personId\":")
                .append(personId);
        sb.append("\"currentPoints\":")
                .append(currentPoints);
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

	public Integer getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(Integer groupCode) {
		this.groupCode = groupCode;
	}
}