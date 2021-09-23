package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-8-3 14:23:32
*/

public class CasualUserQueryCondition extends QueryCondition {

	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private String mobilePhone;
	/**
	 * 
	 */
	private Integer age;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

	private Integer source;

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
	 * 
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * 
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * 
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
}
