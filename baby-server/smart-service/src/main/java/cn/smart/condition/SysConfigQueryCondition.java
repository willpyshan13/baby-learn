package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

/**
* @author Ye
* @date 2018-5-17 10:14:25
*/

public class SysConfigQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Long id;
		/** 
		 * key
		 */
		private String key;
		/** 
		 * value
		 */
		private String value;
		/** 
		 * 状态   0：隐藏   1：显示
		 */
		private Byte status;
		/** 
		 * 备注
		 */
		private String remark;

		/** 
	     * 
		 */
		public Long getId() {
			return id;
		}
		
		/** 
	     * 
		 */
		public void setId(Long id) {
			this.id = id;
		}
		/** 
	     * key
		 */
		public String getKey() {
			return key;
		}
		
		/** 
	     * key
		 */
		public void setKey(String key) {
			this.key = key;
		}
		/** 
	     * value
		 */
		public String getValue() {
			return value;
		}
		
		/** 
	     * value
		 */
		public void setValue(String value) {
			this.value = value;
		}
		/** 
	     * 状态   0：隐藏   1：显示
		 */
		public Byte getStatus() {
			return status;
		}
		
		/** 
	     * 状态   0：隐藏   1：显示
		 */
		public void setStatus(Byte status) {
			this.status = status;
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

}
