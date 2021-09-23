package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-9-14 10:03:37
*/

public class PersonCourseClockQueryCondition extends QueryCondition {

	/**
	 * 课程签到id
	 */
	private Long id;
	/**
	 * 课阶id
	 */
	private Long stageId;
	/**
	 * 单元id
	 */
	private Long groupId;
	/**
	 * 课程id
	 */
	private Long courseId;
	/**
	 * 用户id
	 */
	private Long personId;
	/**
	 * 签到日期
	 */
	private String clockDate;
	/**
	 * 获得积分
	 */
	private Integer points;
	/**
	 * 创建人
	 */
	private Long createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新人
	 */
	private Long updateBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 课程签到id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 课程签到id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 课阶id
	 */
	public Long getStageId() {
		return stageId;
	}

	/**
	 * 课阶id
	 */
	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}
	/**
	 * 单元id
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * 单元id
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
	 * 签到日期
	 */
	public String getClockDate() {
		return clockDate;
	}

	/**
	 * 签到日期
	 */
	public void setClockDate(String clockDate) {
		this.clockDate = clockDate;
	}
	/**
	 * 获得积分
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * 获得积分
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	/**
	 * 创建人
	 */
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
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
	 * 更新人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 更新人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
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
