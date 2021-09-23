package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

/**
 * @Description 
 * @author Ye
 * @date 2018-5-22 16:11:13
 */
public class SysRoleMenuDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 角色ID
    */
    private Long roleId;


    /**
    * 菜单ID
    */
    private Long menuId;

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
	/**
     * Get 菜单ID
	 */
	public Long getMenuId() {
		return menuId;
	}
	
	/** 
     * Set 菜单ID
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
}