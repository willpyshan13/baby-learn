package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * 
 * @author Ye
 * @date 2018-9-13 17:23:56
 */
public class CourseStageGroupGoodsDO extends BaseEntity {

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

	/**
	 * 非持久化属性
	 */
	private String goodsName;
	private String goodsPrice;
	private String goodsImageUrl;
	private Long[] goodsIds;
	private String goodsCardImageUrl;

	/**
     * Get 单元商品id
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 单元商品id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 商品id
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/** 
     * Set 商品id
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
     * Get 课阶id
	 */
	public Long getStageId() {
		return stageId;
	}
	
	/** 
     * Set 课阶id
	 */
	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}
	/**
     * Get 单元id
	 */
	public Long getGroupId() {
		return groupId;
	}
	
	/** 
     * Set 单元id
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	/**
     * Get 兑换所需积分
	 */
	public Integer getExchangePoints() {
		return exchangePoints;
	}
	
	/** 
     * Set 兑换所需积分
	 */
	public void setExchangePoints(Integer exchangePoints) {
		this.exchangePoints = exchangePoints;
	}
	/**
     * Get 创建人
	 */
	public Long getCreateBy() {
		return createBy;
	}
	
	/** 
     * Set 创建人
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
     * Get 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 
     * Set 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
     * Get 更新人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	
	/** 
     * Set 更新人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
     * Get 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 
     * Set 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsImageUrl() {
		return goodsImageUrl;
	}

	public void setGoodsImageUrl(String goodsImageUrl) {
		this.goodsImageUrl = goodsImageUrl;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"goodsId\":")
                .append(goodsId);
        sb.append("\"stageId\":")
                .append(stageId);
        sb.append("\"groupId\":")
                .append(groupId);
        sb.append("\"exchangePoints\":")
                .append(exchangePoints);
        sb.append("\"createBy\":")
                .append(createBy);
        sb.append("\"createTime\":")
                .append(createTime);
        sb.append("\"updateBy\":")
                .append(updateBy);
        sb.append("\"updateTime\":")
                .append(updateTime);
        sb.append('}');
        return sb.toString();
    }

	public Long[] getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(Long[] goodsIds) {
		this.goodsIds = goodsIds;
	}

	public String getGoodsCardImageUrl() {
		return goodsCardImageUrl;
	}

	public void setGoodsCardImageUrl(String goodsCardImageUrl) {
		this.goodsCardImageUrl = goodsCardImageUrl;
	}
}