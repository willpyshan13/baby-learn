package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-8-8 20:22:08
 */
public class ExchangeDetailDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 用户id
    */
    private Long personId;


    /**
    * 积分类型
    */
    private Integer integralType;


    /**
    * 商品id
    */
    private Long goodsId;


    /**
    * 兑换数量
    */
    private Long numbers;


    /**
    * 消耗积分
    */
    private Integer points;


    /**
    * 地区编号
    */
    private String areaCode;


    /**
    * 详细地址
    */
    private String address;


    /**
    * 电话号码
    */
    private String phone;


    /**
    * 快递类型
    */
    private Integer express;


    /**
    * 快递名称
    */
    private String expressCode;


    /**
    * 发货时间
    */
    private Date sendTime;


    /**
    * 状态 0 未收货 1 发货中 2 已送达
    */
    private Integer status;


    /**
    * 送达时间
    */
    private Date receiveTime;


    /**
    * 备注
    */
    private String remark;


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
     * Get 
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 用户id
	 */
	public Long getPersonId() {
		return personId;
	}
	
	/** 
     * Set 用户id
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	/**
     * Get 积分类型
	 */
	public Integer getIntegralType() {
		return integralType;
	}
	
	/** 
     * Set 积分类型
	 */
	public void setIntegralType(Integer integralType) {
		this.integralType = integralType;
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
     * Get 兑换数量
	 */
	public Long getNumbers() {
		return numbers;
	}
	
	/** 
     * Set 兑换数量
	 */
	public void setNumbers(Long numbers) {
		this.numbers = numbers;
	}
	/**
     * Get 消耗积分
	 */
	public Integer getPoints() {
		return points;
	}
	
	/** 
     * Set 消耗积分
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	/**
     * Get 地区编号
	 */
	public String getAreaCode() {
		return areaCode;
	}
	
	/** 
     * Set 地区编号
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
     * Get 详细地址
	 */
	public String getAddress() {
		return address;
	}
	
	/** 
     * Set 详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
     * Get 电话号码
	 */
	public String getPhone() {
		return phone;
	}
	
	/** 
     * Set 电话号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
     * Get 快递类型
	 */
	public Integer getExpress() {
		return express;
	}
	
	/** 
     * Set 快递类型
	 */
	public void setExpress(Integer express) {
		this.express = express;
	}
	/**
     * Get 快递名称
	 */
	public String getExpressCode() {
		return expressCode;
	}
	
	/** 
     * Set 快递名称
	 */
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	/**
     * Get 发货时间
	 */
	public Date getSendTime() {
		return sendTime;
	}
	
	/** 
     * Set 发货时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	/**
     * Get 状态 0 未收货 1 发货中 2 已送达
	 */
	public Integer getStatus() {
		return status;
	}
	
	/** 
     * Set 状态 0 未收货 1 发货中 2 已送达
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
     * Get 送达时间
	 */
	public Date getReceiveTime() {
		return receiveTime;
	}
	
	/** 
     * Set 送达时间
	 */
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	/**
     * Get 备注
	 */
	public String getRemark() {
		return remark;
	}
	
	/** 
     * Set 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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

    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"personId\":")
                .append(personId);
        sb.append("\"integralType\":")
                .append(integralType);
        sb.append("\"goodsId\":")
                .append(goodsId);
        sb.append("\"numbers\":")
                .append(numbers);
        sb.append("\"points\":")
                .append(points);
        sb.append("\"areaCode\":")
                .append(areaCode);
        sb.append("\"address\":")
                .append(address);
        sb.append("\"phone\":")
                .append(phone);
        sb.append("\"express\":")
                .append(express);
        sb.append("\"expressCode\":")
                .append(expressCode);
        sb.append("\"sendTime\":")
                .append(sendTime);
        sb.append("\"status\":")
                .append(status);
        sb.append("\"receiveTime\":")
                .append(receiveTime);
        sb.append("\"remark\":")
                .append(remark);
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

}