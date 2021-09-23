package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-6-4 10:33:51
*/

public class VideoTypeQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Long typeId;
		/** 
		 * 描述
		 */
		private String typeDesc;
		/** 
		 * 名称
		 */
		private String typeName;
		/** 
		 * 父类
		 */
		private Long typeParent;
		/** 
		 * 创建时间
		 */
		private Date createTime;
		/** 
		 * 更新时间
		 */
		private Date updateTime;

		/** 
	     * 
		 */
		public Long getTypeId() {
			return typeId;
		}
		
		/** 
	     * 
		 */
		public void setTypeId(Long typeId) {
			this.typeId = typeId;
		}
		/** 
	     * 描述
		 */
		public String getTypeDesc() {
			return typeDesc;
		}
		
		/** 
	     * 描述
		 */
		public void setTypeDesc(String typeDesc) {
			this.typeDesc = typeDesc;
		}
		/** 
	     * 名称
		 */
		public String getTypeName() {
			return typeName;
		}
		
		/** 
	     * 名称
		 */
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		/** 
	     * 父类
		 */
		public Long getTypeParent() {
			return typeParent;
		}
		
		/** 
	     * 父类
		 */
		public void setTypeParent(Long typeParent) {
			this.typeParent = typeParent;
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
	     * 更新时间
		 */
		public Date getUpdateTime() {
			return updateTime;
		}
		
		/** 
	     * 更新时间
		 */
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

}
