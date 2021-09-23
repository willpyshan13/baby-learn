package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 用户Token
 * @author Ye
 * @date 2018-4-2 18:06:10
 */
public class TbTokenDO extends BaseEntity {

    /**
    * 
    */
    private Long userId;


    /**
    * token
    */
    private String token;


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
	public Long getUserId() {
		return userId;
	}
	
	/** 
     * Set 
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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