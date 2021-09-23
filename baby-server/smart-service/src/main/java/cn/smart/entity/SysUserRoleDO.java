package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

/**
 * @Description 
 * @author Ye
 * @date 2018-5-21 16:29:54
 */
public class SysUserRoleDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 用户ID
    */
    private Long userId;


    /**
    * 角色ID
    */
    private Long roleId;

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
     * Get 用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	
	/** 
     * Set 用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
     * Get 角色ID
	 */
	public Long getRoleId() {
		return roleId;
	}
	
	/** 
     * Set 角色ID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}