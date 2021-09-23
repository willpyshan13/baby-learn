package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-8-3 14:23:32
 */
public class CasualUserDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 
    */
    private String userName;


    /**
    * 
    */
    private String mobilePhone;


    /**
    * 
    */
    private Integer age;


    /**
    * 
    */
    private Date createTime;


    /**
    * 
    */
    private Date updateTime;

	/**
	 * 来源 1 h5 2 微信小程序
	 */
	private Integer source;

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
     * Get 
	 */
	public String getUserName() {
		return userName;
	}
	
	/** 
     * Set 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
     * Get 
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	/** 
     * Set 
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
     * Get 
	 */
	public Integer getAge() {
		return age;
	}
	
	/** 
     * Set 
	 */
	public void setAge(Integer age) {
		this.age = age;
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
     * Get 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 
     * Set 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
}