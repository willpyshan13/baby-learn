package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

/**
* @author Ye
* @date 2018-5-29 19:44:20
*/

public class PageviewsQueryCondition extends QueryCondition {

		/** 
		 * 编号
		 */
		private Integer id;
		/** 
		 * 页面地址
		 */
		private String url;
		/** 
		 * 类型1:首页 2:课程详情
		 */
		private Integer type;
		/** 
		 * 序列号
		 */
		private String serialNumber;
		/** 
		 * 渠道 1:pc 2:h5
		 */
		private Integer channel;
		/** 
		 * 用户id
		 */
		private Long personId;
		/**
		 * 访问时间(年)
		 */
		private String createYear;
		/**
		 * 访问时间(月)
		 */
		private String createMonth;
		/**
		 * 访问时间(日)
		 */
		private String createDay;
		/** 
		 * 访问时间(小时)
		 */
		private String createTime;

		private String startDay;

		private String endDay;


		/** 
	     * 编号
		 */
		public Integer getId() {
			return id;
		}
		
		/** 
	     * 编号
		 */
		public void setId(Integer id) {
			this.id = id;
		}
		/** 
	     * 页面地址
		 */
		public String getUrl() {
			return url;
		}
		
		/** 
	     * 页面地址
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		/** 
	     * 类型1:首页 2:课程详情
		 */
		public Integer getType() {
			return type;
		}
		
		/** 
	     * 类型1:首页 2:课程详情
		 */
		public void setType(Integer type) {
			this.type = type;
		}
		/** 
	     * 序列号
		 */
		public String getSerialNumber() {
			return serialNumber;
		}
		
		/** 
	     * 序列号
		 */
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}
		/** 
	     * 渠道 1:pc 2:h5
		 */
		public Integer getChannel() {
			return channel;
		}
		
		/** 
	     * 渠道 1:pc 2:h5
		 */
		public void setChannel(Integer channel) {
			this.channel = channel;
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
	     * 访问时间(年)
		 */
		public String getCreateYear() {
			return createYear;
		}

		/**
	     * 访问时间(年)
		 */
		public void setCreateYear(String createYear) {
			this.createYear = createYear;
		}
		/**
	     * 访问时间(月)
		 */
		public String getCreateMonth() {
			return createMonth;
		}

		/**
	     * 访问时间(月)
		 */
		public void setCreateMonth(String createMonth) {
			this.createMonth = createMonth;
		}
		/**
	     * 访问时间(日)
		 */
		public String getCreateDay() {
			return createDay;
		}

		/**
	     * 访问时间(日)
		 */
		public void setCreateDay(String createDay) {
			this.createDay = createDay;
		}
		/** 
	     * 访问时间(小时)
		 */
		public String getCreateTime() {
			return createTime;
		}
		
		/** 
	     * 访问时间(小时)
		 */
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
}
