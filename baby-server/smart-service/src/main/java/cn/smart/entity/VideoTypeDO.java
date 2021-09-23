package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-6-4 10:33:51
 */
public class VideoTypeDO extends BaseEntity {

    /**
    * 
    */
    private Long typeId;


    /**
    * 描述
    */
    private String typeDesc;


    /**
    * 名称
    */
    private String typeName;


    /**
    * 父类
    */
    private Long typeParent;


	/**
	 * 父类名称
	 */
	private String parentName;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新时间
    */
    private Date updateTime;

	/**
     * Get 
	 */
	public Long getTypeId() {
		return typeId;
	}
	
	/** 
     * Set 
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	/**
     * Get 描述
	 */
	public String getTypeDesc() {
		return typeDesc;
	}
	
	/** 
     * Set 描述
	 */
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	/**
     * Get 名称
	 */
	public String getTypeName() {
		return typeName;
	}
	
	/** 
     * Set 名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
     * Get 父类
	 */
	public Long getTypeParent() {
		return typeParent;
	}
	
	/** 
     * Set 父类
	 */
	public void setTypeParent(Long typeParent) {
		this.typeParent = typeParent;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}