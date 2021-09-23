package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-5-28 11:13:05
 */
public class GameInfoDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 游戏名称
    */
    private String name;


    /**
    * 游戏信息
    */
    private String info;


    /**
    * 图片地址
    */
    private String imageUrl;


    /**
    * 资源地址
    */
    private String resourceUrl;


    /**
    * 状态 1：生效 0：失效
    */
    private Integer status;


    /**
    * 难度等级
    */
    private Integer level;


    /**
    * 类型 [0, "三选一"], [1, "大声说"]
    */
    private Integer type;


    /**
    * 序号
    */
    private Integer serialNumber;


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
     * Get 游戏名称
	 */
	public String getName() {
		return name;
	}
	
	/** 
     * Set 游戏名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
     * Get 游戏信息
	 */
	public String getInfo() {
		return info;
	}
	
	/** 
     * Set 游戏信息
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
     * Get 图片地址
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	
	/** 
     * Set 图片地址
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
     * Get 资源地址
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}
	
	/** 
     * Set 资源地址
	 */
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	/**
     * Get 状态 1：生效 0：失效
	 */
	public Integer getStatus() {
		return status;
	}
	
	/** 
     * Set 状态 1：生效 0：失效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
     * Get 难度等级
	 */
	public Integer getLevel() {
		return level;
	}
	
	/** 
     * Set 难度等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
     * Get 类型 [0, "三选一"], [1, "单词辨析"]
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 类型 [0, "三选一"], [1, "单词辨析"]
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * Get 序号
	 */
	public Integer getSerialNumber() {
		return serialNumber;
	}
	
	/** 
     * Set 序号
	 */
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
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