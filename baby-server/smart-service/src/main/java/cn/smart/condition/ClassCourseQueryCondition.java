package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-4-20 14:38:29
*/

public class ClassCourseQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Long id;
		/** 
		 * 
		 */
		private Long classId;
		/** 
		 * 
		 */
		private Long courseInfoId;
		/** 
		 * 开始时间
		 */
		private Date startTime;
		/** 
		 * 结束时间
		 */
		private Date endTime;
		/** 
		 * 是否开放 0:否 1:开放
		 */
		private Boolean status;
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
	     * 
		 */
		public Long getClassId() {
			return classId;
		}
		
		/** 
	     * 
		 */
		public void setClassId(Long classId) {
			this.classId = classId;
		}
		/** 
	     * 
		 */
		public Long getCourseInfoId() {
			return courseInfoId;
		}
		
		/** 
	     * 
		 */
		public void setCourseInfoId(Long courseInfoId) {
			this.courseInfoId = courseInfoId;
		}
		/** 
	     * 开始时间
		 */
		public Date getStartTime() {
			return startTime;
		}
		
		/** 
	     * 开始时间
		 */
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		/** 
	     * 结束时间
		 */
		public Date getEndTime() {
			return endTime;
		}
		
		/** 
	     * 结束时间
		 */
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		/** 
	     * 是否开放 0:否 1:开放
		 */
		public Boolean getStatus() {
			return status;
		}
		
		/** 
	     * 是否开放 0:否 1:开放
		 */
		public void setStatus(Boolean status) {
			this.status = status;
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
