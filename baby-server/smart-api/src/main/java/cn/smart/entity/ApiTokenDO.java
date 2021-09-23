package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * 接口token
 * @author Ye
 * @date 2018-4-8 14:45:10
 */
public class ApiTokenDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 用户id
    */
    private Long personId;


    /**
    * token
    */
    private String token;


    /**
    * 类型 1:h5 2:Android app
    */
    private Integer type;


    /**
    * 过期时间
    */
    private Long expireTime;


    /**
    * 更新时间
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
     * Get token
	 */
	public String getToken() {
		return token;
	}
	
	/** 
     * Set token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
     * Get 类型 1:h5 2:Android app
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 类型 1:h5 2:Android app
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * Get 过期时间
	 */
	public Long getExpireTime() {
		return expireTime;
	}
	
	/** 
     * Set 过期时间
	 */
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
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
	
}