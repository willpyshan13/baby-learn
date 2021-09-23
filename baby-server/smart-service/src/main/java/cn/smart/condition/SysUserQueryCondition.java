package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-5-10 15:34:05
*/

public class SysUserQueryCondition extends QueryCondition {

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
	     * 
		 */
		public Long getUserId() {
			return userId;
		}
		
		/** 
	     * 
		 */
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		/** 
	     * 用户名
		 */
		public String getUserName() {
			return userName;
		}
		
		/** 
	     * 用户名
		 */
		public void setUserName(String userName) {
			this.userName = userName;
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
	     * 盐
		 */
		public String getSalt() {
			return salt;
		}
		
		/** 
	     * 盐
		 */
		public void setSalt(String salt) {
			this.salt = salt;
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
	     * 手机号
		 */
		public String getMobile() {
			return mobile;
		}
		
		/** 
	     * 手机号
		 */
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		/** 
	     * 状态  1 ：正常 0 ：禁用
		 */
		public Byte getStatus() {
			return status;
		}
		
		/** 
	     * 状态  1 ：正常 0 ：禁用
		 */
		public void setStatus(Byte status) {
			this.status = status;
		}
		/** 
	     * 头像
		 */
		public String getAvatar() {
			return avatar;
		}
		
		/** 
	     * 头像
		 */
		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}
		/** 
	     * 部门ID
		 */
		public Long getDeptId() {
			return deptId;
		}
		
		/** 
	     * 部门ID
		 */
		public void setDeptId(Long deptId) {
			this.deptId = deptId;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
