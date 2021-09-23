package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-8-8 20:22:08
*/

public class ExchangeDetailQueryCondition extends QueryCondition {

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
	 * 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
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
	 * 积分类型
	 */
	public Integer getIntegralType() {
		return integralType;
	}

	/**
	 * 积分类型
	 */
	public void setIntegralType(Integer integralType) {
		this.integralType = integralType;
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
	 * 兑换数量
	 */
	public Long getNumbers() {
		return numbers;
	}

	/**
	 * 兑换数量
	 */
	public void setNumbers(Long numbers) {
		this.numbers = numbers;
	}
	/**
	 * 消耗积分
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * 消耗积分
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	/**
	 * 地区编号
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * 地区编号
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * 详细地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 电话号码
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 电话号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 快递类型
	 */
	public Integer getExpress() {
		return express;
	}

	/**
	 * 快递类型
	 */
	public void setExpress(Integer express) {
		this.express = express;
	}
	/**
	 * 快递名称
	 */
	public String getExpressCode() {
		return expressCode;
	}

	/**
	 * 快递名称
	 */
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	/**
	 * 发货时间
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * 发货时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * 状态 0 未收货 1 发货中 2 已送达
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态 0 未收货 1 发货中 2 已送达
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 送达时间
	 */
	public Date getReceiveTime() {
		return receiveTime;
	}

	/**
	 * 送达时间
	 */
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	/**
	 * 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
