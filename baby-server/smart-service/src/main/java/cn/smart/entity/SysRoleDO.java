package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-5-21 10:37:07
 */
public class SysRoleDO extends BaseEntity {

    /**
    * 
    */
    private Long roleId;


    /**
    * 角色名称
    */
    private String roleName;


    /**
    * 备注
    */
    private String remark;


    /**
    * 部门ID
    */
    private Long deptId;


    /**
    * 创建时间
    */
    private Date createTime;

	/**
     * Get 
	 */
	public Long getRoleId() {
		return roleId;
	}
	
	/** 
     * Set 
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
     * Get 角色名称
	 */
	public String getRoleName() {
		return roleName;
	}
	
	/** 
     * Set 角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
     * Get 部门ID
	 */
	public Long getDeptId() {
		return deptId;
	}
	
	/** 
     * Set 部门ID
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
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
	
}