package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-9-13 19:35:38
*/

public class IntegralQueryCondition extends QueryCondition {

	/**
	 * 积分明细id
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Long personId;
	/**
	 * 个数
	 */
	private Integer number;
	/**
	 * 类型 1:蛋糕 2:糖果
	 */
	private Integer type;
	/**
	 * 来源 1: 签到积分 2: 课程学习积分
	 */
	private Integer source;
	/**
	 * 获取日期
	 */
	private String createDate;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 课程id
	 */
	private Long courseId;
	/**
	 * 学习记录id
	 */
	private Long learnRecordId;
	/**
	 * 兑换记录id
	 */
	private Long exchangeDetailId;

	private String startTime;
	private String endTime;

	/**
	 * 积分明细id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 积分明细id
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
	 * 个数
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * 个数
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * 类型 1:蛋糕 2:糖果
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型 1:蛋糕 2:糖果
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 来源 1: 签到积分 2: 课程学习积分
	 */
	public Integer getSource() {
		return source;
	}

	/**
	 * 来源 1: 签到积分 2: 课程学习积分
	 */
	public void setSource(Integer source) {
		this.source = source;
	}
	/**
	 * 获取日期
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * 获取日期
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	 * 课程id
	 */
	public Long getCourseId() {
		return courseId;
	}

	/**
	 * 课程id
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	/**
	 * 学习记录id
	 */
	public Long getLearnRecordId() {
		return learnRecordId;
	}

	/**
	 * 学习记录id
	 */
	public void setLearnRecordId(Long learnRecordId) {
		this.learnRecordId = learnRecordId;
	}
	/**
	 * 兑换记录id
	 */
	public Long getExchangeDetailId() {
		return exchangeDetailId;
	}

	/**
	 * 兑换记录id
	 */
	public void setExchangeDetailId(Long exchangeDetailId) {
		this.exchangeDetailId = exchangeDetailId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
