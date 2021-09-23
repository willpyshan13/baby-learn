package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-4-18 16:01:45
*/

public class CourseGameQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Long id;
		/** 
		 * 课程id
		 */
		private Long courseInfoId;
		/** 
		 * 游戏id
		 */
		private Long gameInfoId;
		/** 
		 * 创建时间
		 */
		private Date createTime;

		private Long[] deleteGameIds;

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
	     * 课程id
		 */
		public Long getCourseInfoId() {
			return courseInfoId;
		}
		
		/** 
	     * 课程id
		 */
		public void setCourseInfoId(Long courseInfoId) {
			this.courseInfoId = courseInfoId;
		}
		/** 
	     * 游戏id
		 */
		public Long getGameInfoId() {
			return gameInfoId;
		}
		
		/** 
	     * 游戏id
		 */
		public void setGameInfoId(Long gameInfoId) {
			this.gameInfoId = gameInfoId;
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

	public Long[] getDeleteGameIds() {
		return deleteGameIds;
	}

	public void setDeleteGameIds(Long[] deleteGameIds) {
		this.deleteGameIds = deleteGameIds;
	}
}
