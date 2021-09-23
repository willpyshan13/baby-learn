package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-10 19:04:58
 */
public class VerifyCodeDO extends BaseEntity {

    /**
    * 
    */
    private Long verifyId;


    /**
    * 验证码
    */
    private String code;


    /**
    * 过期时间
    */
    private Long expireTime;


    /**
    * 手机号码
    */
    private String telephone;


    /**
    * 更新时间
    */
    private Date updateTime;


    /**
    * 
    */
    private Long createTime;

	/**
     * Get 
	 */
	public Long getVerifyId() {
		return verifyId;
	}
	
	/** 
     * Set 
	 */
	public void setVerifyId(Long verifyId) {
		this.verifyId = verifyId;
	}
	/**
     * Get 验证码
	 */
	public String getCode() {
		return code;
	}
	
	/** 
     * Set 验证码
	 */
	public void setCode(String code) {
		this.code = code;
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
     * Get 手机号码
	 */
	public String getTelephone() {
		return telephone;
	}
	
	/** 
     * Set 手机号码
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	/**
     * Get 
	 */
	public Long getCreateTime() {
		return createTime;
	}
	
	/** 
     * Set 
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
}