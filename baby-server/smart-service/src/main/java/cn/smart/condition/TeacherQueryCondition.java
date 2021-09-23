package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-4-19 13:34:59
*/

public class TeacherQueryCondition extends QueryCondition {

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
	     * 教师id
		 */
		public Long getTeacherId() {
			return teacherId;
		}
		
		/** 
	     * 教师id
		 */
		public void setTeacherId(Long teacherId) {
			this.teacherId = teacherId;
		}
		/** 
	     * 帐号
		 */
		public String getAccount() {
			return account;
		}
		
		/** 
	     * 帐号
		 */
		public void setAccount(String account) {
			this.account = account;
		}
		/** 
	     * 昵称
		 */
		public String getNickName() {
			return nickName;
		}
		
		/** 
	     * 昵称
		 */
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
		/** 
	     * 名字
		 */
		public String getFirstName() {
			return firstName;
		}
		
		/** 
	     * 名字
		 */
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		/** 
	     * 密码
		 */
		public String getPassword() {
			return password;
		}
		
		/** 
	     * 密码
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		/** 
	     * 性别 1:男 0:女
		 */
		public Integer getGender() {
			return gender;
		}
		
		/** 
	     * 性别 1:男 0:女
		 */
		public void setGender(Integer gender) {
			this.gender = gender;
		}
		/** 
	     * 手机号码
		 */
		public String getMobile() {
			return mobile;
		}
		
		/** 
	     * 手机号码
		 */
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		/** 
	     * 邮箱
		 */
		public String getEmail() {
			return email;
		}
		
		/** 
	     * 邮箱
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		/** 
	     * 介绍
		 */
		public String getDescription() {
			return description;
		}
		
		/** 
	     * 介绍
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/** 
	     * 教师类型 0:教师 1:助教
		 */
		public Integer getRole() {
			return role;
		}
		
		/** 
	     * 教师类型 0:教师 1:助教
		 */
		public void setRole(Integer role) {
			this.role = role;
		}
		/** 
	     * 头像地址
		 */
		public String getLogo() {
			return logo;
		}
		
		/** 
	     * 头像地址
		 */
		public void setLogo(String logo) {
			this.logo = logo;
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
	     * 更新时间(自动维护)
		 */
		public Date getUpdateTime() {
			return updateTime;
		}
		
		/** 
	     * 更新时间(自动维护)
		 */
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		/** 
	     * 
		 */
		public String getTeacherName() {
			return teacherName;
		}
		
		/** 
	     * 
		 */
		public void setTeacherName(String teacherName) {
			this.teacherName = teacherName;
		}
		/** 
	     * 
		 */
		public String getTeacherPassword() {
			return teacherPassword;
		}
		
		/** 
	     * 
		 */
		public void setTeacherPassword(String teacherPassword) {
			this.teacherPassword = teacherPassword;
		}

}
