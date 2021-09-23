package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.List;

/**
 * 菜单管理
 * @author Ye
 * @date 2018-4-12 13:33:25
 */
public class SysMenuDO extends BaseEntity {

    /**
    * 
    */
    private Long menuId;


    /**
    * 父菜单ID，一级菜单为0
    */
    private Long parentId;


    /**
    * 菜单名称
    */
    private String name;


    /**
    * 菜单URL
    */
    private String url;


    /**
    * 授权(多个用逗号分隔，如：user:list,user:create)
    */
    private String perms;


    /**
    * 类型   0：目录   1：菜单   2：按钮
    */
    private Integer type;


    /**
    * 菜单图标
    */
    private String icon;

	/**
	 * 父菜单名称
	 */
	private String parentName;

    /**
    * 排序
    */
    private Integer orderNum;

	private List<SysMenuDO> list;

	/**
     * Get 
	 */
	public Long getMenuId() {
		return menuId;
	}
	
	/** 
     * Set 
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	/**
     * Get 父菜单ID，一级菜单为0
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/** 
     * Set 父菜单ID，一级菜单为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
     * Get 菜单名称
	 */
	public String getName() {
		return name;
	}
	
	/** 
     * Set 菜单名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
     * Get 菜单URL
	 */
	public String getUrl() {
		return url;
	}
	
	/** 
     * Set 菜单URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
     * Get 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	public String getPerms() {
		return perms;
	}
	
	/** 
     * Set 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	public void setPerms(String perms) {
		this.perms = perms;
	}
	/**
     * Get 类型   0：目录   1：菜单   2：按钮
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 类型   0：目录   1：菜单   2：按钮
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * Get 菜单图标
	 */
	public String getIcon() {
		return icon;
	}
	
	/** 
     * Set 菜单图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
     * Get 排序
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	
	/** 
     * Set 排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public List<SysMenuDO> getList() {
		return list;
	}

	public void setList(List<SysMenuDO> list) {
		this.list = list;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}