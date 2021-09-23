package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * 课程阶段单元
 * @author Ye
 * @date 2018-9-14 10:18:33
 */
public class CourseStageGroupDO extends BaseEntity {

    /**
    * 单元编号
    */
    private Long id;


    /**
    * 单元名称
    */
    private String name;


    /**
    * 单元编号
    */
    private Integer code;


    /**
    * 课阶编号
    */
    private Long stageId;

	/**
	 * 单元周数
	 */
	private Integer week;


	/**
	 * 每周课程数
	 */
	private Integer courseNumber;

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
     * Get 单元名称
	 */
	public String getName() {
		return name;
	}
	
	/** 
     * Set 单元名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
     * Get 单元编号
	 */
	public Integer getCode() {
		return code;
	}
	
	/** 
     * Set 单元编号
	 */
	public void setCode(Integer code) {
		this.code = code;
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
        sb.append("\"name\":")
                .append(name);
        sb.append("\"code\":")
                .append(code);
        sb.append("\"stageId\":")
                .append(stageId);
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

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(Integer courseNumber) {
		this.courseNumber = courseNumber;
	}
}