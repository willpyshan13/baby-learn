package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

/**
* @author Ye
* @date 2018-4-12 13:33:26
*/

public class SysMenuQueryCondition extends QueryCondition {

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
		 * 排序
		 */
		private Integer orderNum;

		/** 
	     * 
		 */
		public Long getMenuId() {
			return menuId;
		}
		
		/** 
	     * 
		 */
		public void setMenuId(Long menuId) {
			this.menuId = menuId;
		}
		/** 
	     * 父菜单ID，一级菜单为0
		 */
		public Long getParentId() {
			return parentId;
		}
		
		/** 
	     * 父菜单ID，一级菜单为0
		 */
		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}
		/** 
	     * 菜单名称
		 */
		public String getName() {
			return name;
		}
		
		/** 
	     * 菜单名称
		 */
		public void setName(String name) {
			this.name = name;
		}
		/** 
	     * 菜单URL
		 */
		public String getUrl() {
			return url;
		}
		
		/** 
	     * 菜单URL
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		/** 
	     * 授权(多个用逗号分隔，如：user:list,user:create)
		 */
		public String getPerms() {
			return perms;
		}
		
		/** 
	     * 授权(多个用逗号分隔，如：user:list,user:create)
		 */
		public void setPerms(String perms) {
			this.perms = perms;
		}
		/** 
	     * 类型   0：目录   1：菜单   2：按钮
		 */
		public Integer getType() {
			return type;
		}
		
		/** 
	     * 类型   0：目录   1：菜单   2：按钮
		 */
		public void setType(Integer type) {
			this.type = type;
		}
		/** 
	     * 菜单图标
		 */
		public String getIcon() {
			return icon;
		}
		
		/** 
	     * 菜单图标
		 */
		public void setIcon(String icon) {
			this.icon = icon;
		}
		/** 
	     * 排序
		 */
		public Integer getOrderNum() {
			return orderNum;
		}
		
		/** 
	     * 排序
		 */
		public void setOrderNum(Integer orderNum) {
			this.orderNum = orderNum;
		}

}
