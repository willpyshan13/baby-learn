package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 系统用户Token
 * @author Ye
 * @date 2018-4-12 17:58:58
 */
public class SysUserTokenDO extends BaseEntity {

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
    private Date expireTime;


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
	public Date getExpireTime() {
		return expireTime;
	}
	
	/** 
     * Set 过期时间
	 */
	public void setExpireTime(Date expireTime) {
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