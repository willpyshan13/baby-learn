package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-8-13 10:23:47
 */
public class GoodsDO extends BaseEntity {

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
	 * 非持久化属性
	 */
	private Integer exchangePoints;
	private Long groupGoodsId;

	/**
	 * Get 编号
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 商品名称
	 */
	public String getName() {
		return name;
	}
	
	/** 
     * Set 商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
     * Get 虚拟物品 0 否 1 是
	 */
	public Integer getVirtual() {
		return virtual;
	}
	
	/** 
     * Set 虚拟物品 0 否 1 是
	 */
	public void setVirtual(Integer virtual) {
		this.virtual = virtual;
	}
	/**
     * Get 价格
	 */
	public Double getPrice() {
		return price;
	}
	
	/** 
     * Set 价格
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
     * Get 积分
	 */
	public Integer getPoints() {
		return points;
	}
	
	/** 
     * Set 积分
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	/**
     * Get 图片地址
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	
	/** 
     * Set 图片地址
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
     * Get 创建人
	 */
	public String getCreateBy() {
		return createBy;
	}
	
	/** 
     * Set 创建人
	 */
	public void setCreateBy(String createBy) {
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
     * Get 修改人
	 */
	public Date getUpdateBy() {
		return updateBy;
	}
	
	/** 
     * Set 修改人
	 */
	public void setUpdateBy(Date updateBy) {
		this.updateBy = updateBy;
	}

    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"name\":")
                .append(name);
        sb.append("\"virtual\":")
                .append(virtual);
        sb.append("\"price\":")
                .append(price);
        sb.append("\"points\":")
                .append(points);
        sb.append("\"imageUrl\":")
                .append(imageUrl);
        sb.append("\"createBy\":")
                .append(createBy);
        sb.append("\"createTime\":")
                .append(createTime);
        sb.append("\"updateBy\":")
                .append(updateBy);
        sb.append('}');
        return sb.toString();
    }

	public Integer getExchangePoints() {
		return exchangePoints;
	}

	public void setExchangePoints(Integer exchangePoints) {
		this.exchangePoints = exchangePoints;
	}

	public Long getGroupGoodsId() {
		return groupGoodsId;
	}

	public void setGroupGoodsId(Long groupGoodsId) {
		this.groupGoodsId = groupGoodsId;
	}

	public String getCardImageUrl() {
		return cardImageUrl;
	}

	public void setCardImageUrl(String cardImageUrl) {
		this.cardImageUrl = cardImageUrl;
	}
}