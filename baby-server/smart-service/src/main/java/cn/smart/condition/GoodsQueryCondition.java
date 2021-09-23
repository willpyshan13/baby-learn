package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-8-13 10:23:48
*/

public class GoodsQueryCondition extends QueryCondition {

	/**
	 * 编号
	 */
	private Long id;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 虚拟物品 0 否 1 是
	 */
	private Integer virtual;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 积分
	 */
	private Integer points;
	/**
	 * 图片地址
	 */
	private String imageUrl;

    /**
     * 弹窗卡片地址
     */
    private String cardImageUrl;

	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人
	 */
	private Date updateBy;

	/**
	 * 课阶id
	 */
	private Long stageId;

	/**
	 * 单元id
	 */
	private Long groupId;

	private Long[] outGoodsId;

	/**
	 * 编号
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 商品名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 虚拟物品 0 否 1 是
	 */
	public Integer getVirtual() {
		return virtual;
	}

	/**
	 * 虚拟物品 0 否 1 是
	 */
	public void setVirtual(Integer virtual) {
		this.virtual = virtual;
	}
	/**
	 * 价格
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 价格
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 积分
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * 积分
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	/**
	 * 图片地址
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * 图片地址
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * 创建人
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人
	 */
	public void setCreateBy(String createBy) {
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
	 * 修改人
	 */
	public Date getUpdateBy() {
		return updateBy;
	}

	/**
	 * 修改人
	 */
	public void setUpdateBy(Date updateBy) {
		this.updateBy = updateBy;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getStageId() {
		return stageId;
	}

	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}

	public Long[] getOutGoodsId() {
		return outGoodsId;
	}

	public void setOutGoodsId(Long[] outGoodsId) {
		this.outGoodsId = outGoodsId;
	}

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }
}
