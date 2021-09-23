package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-9-14 10:18:34
*/

public class CourseStageGroupQueryCondition extends QueryCondition {

	/**
	 * 单元编号
	 */
	private Long id;
	/**
	 * 单元名称
	 */
	private String name;
	/**
	 * 单元编号
	 */
	private Integer code;
	/**
	 * 单元周数
	 */
	private Integer week;
	/**
	 * 每周课程数
	 */
	private Integer courseNumber;
	/**
	 * 课阶编号
	 */
	private Long stageId;
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
	 * 单元编号
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 单元编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 单元名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 单元名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 单元编号
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 单元编号
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * 课阶编号
	 */
	public Long getStageId() {
		return stageId;
	}

	/**
	 * 课阶编号
	 */
	public void setStageId(Long stageId) {
		this.stageId = stageId;
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

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(Integer courseNumber) {
		this.courseNumber = courseNumber;
	}
}
