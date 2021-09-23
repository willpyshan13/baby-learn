package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-5-21 10:37:07
*/

public class SysRoleQueryCondition extends QueryCondition {

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
	     * 
		 */
		public Long getRoleId() {
			return roleId;
		}
		
		/** 
	     * 
		 */
		public void setRoleId(Long roleId) {
			this.roleId = roleId;
		}
		/** 
	     * 角色名称
		 */
		public String getRoleName() {
			return roleName;
		}
		
		/** 
	     * 角色名称
		 */
		public void setRoleName(String roleName) {
			this.roleName = roleName;
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

}
