package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-9-13 17:23:56
*/

public class CourseStageGroupGoodsQueryCondition extends QueryCondition {

	/**
	 * 单元商品id
	 */
	private Long id;
	/**
	 * 商品id
	 */
	private Long goodsId;
	/**
	 * 课阶id
	 */
	private Long stageId;
	/**
	 * 单元id
	 */
	private Long groupId;
	/**
	 * 兑换所需积分
	 */
	private Integer exchangePoints;
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

	private Long[] inGroupId;

	/**
	 * 单元商品id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 单元商品id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 商品id
	 */
	public Long getGoodsId() {
		return goodsId;
	}

	/**
	 * 商品id
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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
	 * 兑换所需积分
	 */
	public Integer getExchangePoints() {
		return exchangePoints;
	}

	/**
	 * 兑换所需积分
	 */
	public void setExchangePoints(Integer exchangePoints) {
		this.exchangePoints = exchangePoints;
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

	public Long[] getInGroupId() {
		return inGroupId;
	}

	public void setInGroupId(Long[] inGroupId) {
		this.inGroupId = inGroupId;
	}
}
