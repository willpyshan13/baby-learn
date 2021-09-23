package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 课程阶级
 * @author Ye
 * @date 2018-4-12 14:54:07
 */
public class CourseStageDO extends BaseEntity {

    /**
    * 课阶id
    */
    private Long id;


    /**
    * 阶段名称
    */
    private String stageName;


    /**
    * 课阶等级
    */
    private Integer level;


    /**
    * 课阶描述
    */
    private String description;


    /**
    * 阶段课时
    */
    private Double spendTime;


    /**
    * 阶段课程数
    */
    private Integer courseCount;


    /**
    * 原价
    */
    private Double originPrice;


    /**
    * 优惠价
    */
    private Double preferentialPrice;


    /**
    * 已售数量
    */
    private Integer soldCount;


    /**
    * 课阶图片地址
    */
    private String imgUrl;


    /**
    * 阶段教师id
    */
    private Long teacherId;

	/**
	 * 教师姓名
	 */
	private String teacherName;

	/**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新时间(自动维护)
    */
    private Date updateTime;

	/**
	 * 是否出售 0 否 1 是
	 */
	private Integer selling;

	/**
	 * Get 课阶id
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 课阶id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 阶段名称
	 */
	public String getStageName() {
		return stageName;
	}
	
	/** 
     * Set 阶段名称
	 */
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	/**
     * Get 课阶等级
	 */
	public Integer getLevel() {
		return level;
	}
	
	/** 
     * Set 课阶等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
     * Get 课阶描述
	 */
	public String getDescription() {
		return description;
	}
	
	/** 
     * Set 课阶描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
     * Get 阶段课时
	 */
	public Double getSpendTime() {
		return spendTime;
	}
	
	/** 
     * Set 阶段课时
	 */
	public void setSpendTime(Double spendTime) {
		this.spendTime = spendTime;
	}
	/**
     * Get 阶段课程数
	 */
	public Integer getCourseCount() {
		return courseCount;
	}
	
	/** 
     * Set 阶段课程数
	 */
	public void setCourseCount(Integer courseCount) {
		this.courseCount = courseCount;
	}
	/**
     * Get 原价
	 */
	public Double getOriginPrice() {
		return originPrice;
	}
	
	/** 
     * Set 原价
	 */
	public void setOriginPrice(Double originPrice) {
		this.originPrice = originPrice;
	}
	/**
     * Get 优惠价
	 */
	public Double getPreferentialPrice() {
		return preferentialPrice;
	}
	
	/** 
     * Set 优惠价
	 */
	public void setPreferentialPrice(Double preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}
	/**
     * Get 已售数量
	 */
	public Integer getSoldCount() {
		return soldCount;
	}
	
	/** 
     * Set 已售数量
	 */
	public void setSoldCount(Integer soldCount) {
		this.soldCount = soldCount;
	}
	/**
     * Get 课阶图片地址
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	
	/** 
     * Set 课阶图片地址
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
     * Get 阶段教师id
	 */
	public Long getTeacherId() {
		return teacherId;
	}
	
	/** 
     * Set 阶段教师id
	 */
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getSelling() {
		return selling;
	}

	public void setSelling(Integer selling) {
		this.selling = selling;
	}
}