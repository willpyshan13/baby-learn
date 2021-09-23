package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 系统用户
 * @author Ye
 * @date 2018-5-10 15:34:05
 */
public class SysUserDO extends BaseEntity {

    /**
    * 
    */
    private Long userId;


    /**
    * 用户名
    */
    private String userName;


    /**
    * 密码
    */
    private String password;


    /**
    * 盐
    */
    private String salt;


    /**
    * 邮箱
    */
    private String email;


    /**
    * 手机号
    */
    private String mobile;


    /**
    * 状态  1 ：正常 0 ：禁用
    */
    private Byte status;


    /**
    * 头像
    */
    private String avatar;


    /**
    * 部门ID
    */
    private Long deptId;


    /**
    * 创建时间
    */
    private Date createTime;

    private Long roleId;

    private String roleName;

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
     * Get 用户名
	 */
	public String getUserName() {
		return userName;
	}
	
	/** 
     * Set 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
     * Get 密码
	 */
	public String getPassword() {
		return password;
	}
	
	/** 
     * Set 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
     * Get 盐
	 */
	public String getSalt() {
		return salt;
	}
	
	/** 
     * Set 盐
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
     * Get 邮箱
	 */
	public String getEmail() {
		return email;
	}
	
	/** 
     * Set 邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
     * Get 手机号
	 */
	public String getMobile() {
		return mobile;
	}
	
	/** 
     * Set 手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
     * Get 状态   1 ：正常 0 ：禁用
	 */
	public Byte getStatus() {
		return status;
	}
	
	/** 
     * Set 状态   1 ：正常 0 ：禁用
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}
	/**
     * Get 头像
	 */
	public String getAvatar() {
		return avatar;
	}
	
	/** 
     * Set 头像
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}