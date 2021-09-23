package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-9-14 15:58:29
*/

public class PersonCourseStageGroupQueryCondition extends QueryCondition {

	/**
	 * 单元编号
	 */
	private Long id;
	/**
	 * 课阶编号
	 */
	private Long stageId;
	/**
	 * 单元id
	 */
	private Long groupId;
	/**
	 * 用户id
	 */
	private Long personId;
	/**
	 * 当前积分
	 */
	private Integer currentPoints;
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

	private Integer groupCode;

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
	 * 当前积分
	 */
	public Integer getCurrentPoints() {
		return currentPoints;
	}

	/**
	 * 当前积分
	 */
	public void setCurrentPoints(Integer currentPoints) {
		this.currentPoints = currentPoints;
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

	public Integer getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(Integer groupCode) {
		this.groupCode = groupCode;
	}
}
