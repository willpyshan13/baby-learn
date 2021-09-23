package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-4-25 19:38:40
*/

public class RoomCoursewareQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Long id;
		/** 
		 * 教室id
		 */
		private Long roomId;
		/** 
		 * 课件id
		 */
		private Long coursewareId;
		/** 
		 * 
		 */
		private Date createTime;
		/** 
		 * 
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
	     * 教室id
		 */
		public Long getRoomId() {
			return roomId;
		}
		
		/** 
	     * 教室id
		 */
		public void setRoomId(Long roomId) {
			this.roomId = roomId;
		}
		/** 
	     * 课件id
		 */
		public Long getCoursewareId() {
			return coursewareId;
		}
		
		/** 
	     * 课件id
		 */
		public void setCoursewareId(Long coursewareId) {
			this.coursewareId = coursewareId;
		}
		/** 
	     * 
		 */
		public Date getCreateTime() {
			return createTime;
		}
		
		/** 
	     * 
		 */
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		/** 
	     * 
		 */
		public Date getUpdateTime() {
			return updateTime;
		}
		
		/** 
	     * 
		 */
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

}
