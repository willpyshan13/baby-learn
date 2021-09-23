package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-6-29 17:26:56
*/

public class ClockRecordQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Integer id;
		/** 
		 * 用户id
		 */
		private Long personId;
		/** 
		 * 首次打卡日期
		 */
		private String initDate;
		/** 
		 * 打卡日期
		 */
		private String clockDate;
		/** 
		 * 打卡时间
		 */
		private Date clockTime;
		/** 
		 * 连续打卡次数
		 */
		private Integer sum;
		/** 
		 * 连续打卡最高次数
		 */
		private Integer maxCount;
		/** 
		 * 更新时间
		 */
		private Date updateTime;

		/** 
	     * 
		 */
		public Integer getId() {
			return id;
		}
		
		/** 
	     * 
		 */
		public void setId(Integer id) {
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
	     * 首次打卡日期
		 */
		public String getInitDate() {
			return initDate;
		}
		
		/** 
	     * 首次打卡日期
		 */
		public void setInitDate(String initDate) {
			this.initDate = initDate;
		}
		/** 
	     * 打卡日期
		 */
		public String getClockDate() {
			return clockDate;
		}
		
		/** 
	     * 打卡日期
		 */
		public void setClockDate(String clockDate) {
			this.clockDate = clockDate;
		}
		/** 
	     * 打卡时间
		 */
		public Date getClockTime() {
			return clockTime;
		}
		
		/** 
	     * 打卡时间
		 */
		public void setClockTime(Date clockTime) {
			this.clockTime = clockTime;
		}
		/** 
	     * 连续打卡次数
		 */
		public Integer getSum() {
			return sum;
		}
		
		/** 
	     * 连续打卡次数
		 */
		public void setSum(Integer sum) {
			this.sum = sum;
		}
		/** 
	     * 连续打卡最高次数
		 */
		public Integer getMaxCount() {
			return maxCount;
		}
		
		/** 
	     * 连续打卡最高次数
		 */
		public void setMaxCount(Integer maxCount) {
			this.maxCount = maxCount;
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
