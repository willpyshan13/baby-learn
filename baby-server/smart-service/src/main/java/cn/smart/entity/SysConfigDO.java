package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

/**
 * @Description 系统配置信息表
 * @author Ye
 * @date 2018-5-17 10:14:25
 */
public class SysConfigDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * key
    */
    private String key;


    /**
    * value
    */
    private String value;


    /**
    * 状态   0：隐藏   1：显示
    */
    private Byte status;


    /**
    * 备注
    */
    private String remark;

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
     * Get key
	 */
	public String getKey() {
		return key;
	}
	
	/** 
     * Set key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
     * Get value
	 */
	public String getValue() {
		return value;
	}
	
	/** 
     * Set value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
     * Get 状态   0：隐藏   1：显示
	 */
	public Byte getStatus() {
		return status;
	}
	
	/** 
     * Set 状态   0：隐藏   1：显示
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}
	/**
     * Get 备注
	 */
	public String getRemark() {
		return remark;
	}
	
	/** 
     * Set 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}