package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-7-23 19:28:19
*/

public class MachineUpgradeQueryCondition extends QueryCondition {

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
	 * 主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 序列号
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * 序列号
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
	 * 当前版本号
	 */
	public String getCurrentVersion() {
		return currentVersion;
	}

	/**
	 * 当前版本号
	 */
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	/**
	 * 0 升级黑名单(不可升级) 
1 升级白名单(白名单开启 可升级)
2 默认可升级
	 */
	public Integer getUpgrade() {
		return upgrade;
	}

	/**
	 * 0 升级黑名单(不可升级) 
1 升级白名单(白名单开启 可升级)
2 默认可升级
	 */
	public void setUpgrade(Integer upgrade) {
		this.upgrade = upgrade;
	}
	/**
	 * 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
