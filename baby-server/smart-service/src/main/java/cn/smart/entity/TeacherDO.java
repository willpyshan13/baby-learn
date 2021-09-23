package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-19 13:34:59
 */
public class TeacherDO extends BaseEntity {

    /**
    * 教师id
    */
    private Long teacherId;


    /**
    * 帐号
    */
    private String account;


    /**
    * 昵称
    */
    private String nickName;


    /**
    * 名字
    */
    private String firstName;


    /**
    * 密码
    */
    private String password;


    /**
    * 性别 1:男 0:女
    */
    private Integer gender;


    /**
    * 手机号码
    */
    private String mobile;


    /**
    * 邮箱
    */
    private String email;


    /**
    * 介绍
    */
    private String description;


    /**
    * 教师类型 0:教师 1:助教
    */
    private Integer role;


    /**
    * 头像地址
    */
    private String logo;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新时间(自动维护)
    */
    private Date updateTime;


    /**
    * 
    */
    private String teacherName;


    /**
    * 
    */
    private String teacherPassword;

	/**
	 * 微信二维码地址
	 */
	private String wxQrCode;

	/**
     * Get 教师id
	 */
	public Long getTeacherId() {
		return teacherId;
	}
	
	/** 
     * Set 教师id
	 */
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	/**
     * Get 帐号
	 */
	public String getAccount() {
		return account;
	}
	
	/** 
     * Set 帐号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
     * Get 昵称
	 */
	public String getNickName() {
		return nickName;
	}
	
	/** 
     * Set 昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
     * Get 名字
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/** 
     * Set 名字
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
     * Get 性别 1:男 0:女
	 */
	public Integer getGender() {
		return gender;
	}
	
	/** 
     * Set 性别 1:男 0:女
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
     * Get 手机号码
	 */
	public String getMobile() {
		return mobile;
	}
	
	/** 
     * Set 手机号码
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
     * Get 介绍
	 */
	public String getDescription() {
		return description;
	}
	
	/** 
     * Set 介绍
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
     * Get 教师类型 0:教师 1:助教
	 */
	public Integer getRole() {
		return role;
	}
	
	/** 
     * Set 教师类型 0:教师 1:助教
	 */
	public void setRole(Integer role) {
		this.role = role;
	}
	/**
     * Get 头像地址
	 */
	public String getLogo() {
		return logo;
	}
	
	/** 
     * Set 头像地址
	 */
	public void setLogo(String logo) {
		this.logo = logo;
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
     * Get 更新时间(自动维护)
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 
     * Set 更新时间(自动维护)
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
     * Get 
	 */
	public String getTeacherName() {
		return teacherName;
	}
	
	/** 
     * Set 
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	/**
     * Get 
	 */
	public String getTeacherPassword() {
		return teacherPassword;
	}
	
	/** 
     * Set 
	 */
	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}

	public String getWxQrCode() {
		return wxQrCode;
	}

	public void setWxQrCode(String wxQrCode) {
		this.wxQrCode = wxQrCode;
	}
}