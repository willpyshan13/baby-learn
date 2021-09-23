package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-4-4 17:22:04
*/

public class PersonCourseStageQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Long id;
		/** 
		 * 用户id
		 */
		private Long personId;
		/** 
		 * 课阶id
		 */
		private Long courseStageId;
		/** 
		 * 订单id
		 */
		private Long orderId;
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
	     * 用户id
		 */
		public Long getPersonId() {
			return personId;
		}
		
		/** 
	     * 用户id
		 */
		public void setPersonId(Long personId) {
			this.personId = personId;
		}
		/** 
	     * 课阶id
		 */
		public Long getCourseStageId() {
			return courseStageId;
		}
		
		/** 
	     * 课阶id
		 */
		public void setCourseStageId(Long courseStageId) {
			this.courseStageId = courseStageId;
		}
		/** 
	     * 订单id
		 */
		public Long getOrderId() {
			return orderId;
		}
		
		/** 
	     * 订单id
		 */
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
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

}
