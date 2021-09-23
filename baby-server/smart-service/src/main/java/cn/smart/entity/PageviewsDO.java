package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

/**
 * @Description 浏览量统计
 * @author Ye
 * @date 2018-5-29 19:44:19
 */
public class PageviewsDO extends BaseEntity {

    /**
    * 编号
    */
    private Integer id;


    /**
    * 页面地址
    */
    private String url;


    /**
    * 类型1:首页 2:课程详情
    */
    private Integer type;


    /**
    * 序列号
    */
    private String serialNumber;


    /**
    * 渠道 1:pc 2:h5
    */
    private Integer channel;


    /**
    * 用户id
    */
    private Long personId;

    /**
    * 访问时间
    */
    private String createTime;

	/**
     * Get 编号
	 */
	public Integer getId() {
		return id;
	}
	
	/** 
     * Set 编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
     * Get 页面地址
	 */
	public String getUrl() {
		return url;
	}
	
	/** 
     * Set 页面地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
     * Get 类型1:首页 2:课程详情
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 类型1:首页 2:课程详情
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * Get 序列号
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	
	/** 
     * Set 序列号
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
     * Get 渠道 1:pc 2:h5
	 */
	public Integer getChannel() {
		return channel;
	}
	
	/** 
     * Set 渠道 1:pc 2:h5
	 */
	public void setChannel(Integer channel) {
		this.channel = channel;
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
     * Get 访问时间
	 */
	public String getCreateTime() {
		return createTime;
	}
	
	/** 
     * Set 访问时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}