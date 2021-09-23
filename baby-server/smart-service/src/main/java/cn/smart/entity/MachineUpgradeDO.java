package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-7-23 19:28:19
 */
public class MachineUpgradeDO extends BaseEntity {

    /**
    * 主键
    */
    private Long id;


    /**
    * 序列号
    */
    private String serialNumber;


    /**
    * 当前版本号
    */
    private String currentVersion;


    /**
    * 0 升级黑名单(不可升级) 
1 升级白名单(白名单开启 可升级)
2 默认可升级
    */
    private Integer upgrade;


    /**
    * 备注
    */
    private String remark;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新时间
    */
    private Date updateTime;

	/**
	 * 非持久化属性
	 * id 集合
	 */
	private Long[] machineIds;

	/**
     * Get 主键
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 主键
	 */
	public void setId(Long id) {
		this.id = id;
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
     * Get 当前版本号
	 */
	public String getCurrentVersion() {
		return currentVersion;
	}
	
	/** 
     * Set 当前版本号
	 */
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	/**
     * Get 0 升级黑名单(不可升级) 
1 升级白名单(白名单开启 可升级)
2 默认可升级
	 */
	public Integer getUpgrade() {
		return upgrade;
	}
	
	/** 
     * Set 0 升级黑名单(不可升级) 
1 升级白名单(白名单开启 可升级)
2 默认可升级
	 */
	public void setUpgrade(Integer upgrade) {
		this.upgrade = upgrade;
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

	public Long[] getMachineIds() {
		return machineIds;
	}

	public void setMachineIds(Long[] machineIds) {
		this.machineIds = machineIds;
	}
}