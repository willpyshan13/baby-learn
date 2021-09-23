package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-8-23 18:41:26
*/

public class SysLogsQueryCondition extends QueryCondition {

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 日志标题
	 */
	private String title;
	/**
	 * 请求地址
	 */
	private String requestUrl;
	/**
	 * 方法
	 */
	private String method;
	/**
	 * 参数
	 */
	private String parameters;
	/**
	 * 来源
	 */
	private String clientHost;
	/**
	 * 用户标识
	 */
	private String userAgent;
	/**
	 * 结果
	 */
	private String result;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 操作用户
	 */
	private Long createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新用户
	 */
	private Long updateBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 查询关键字
	 */
	private String searchKeys;

	/**
	 * 编号
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 日志标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 日志标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 请求地址
	 */
	public String getRequestUrl() {
		return requestUrl;
	}

	/**
	 * 请求地址
	 */
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	/**
	 * 方法
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * 方法
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * 参数
	 */
	public String getParameters() {
		return parameters;
	}

	/**
	 * 参数
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	/**
	 * 来源
	 */
	public String getClientHost() {
		return clientHost;
	}

	/**
	 * 来源
	 */
	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}
	/**
	 * 用户标识
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * 用户标识
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	/**
	 * 结果
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 结果
	 */
	public void setResult(String result) {
		this.result = result;
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
	 * 操作用户
	 */
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 操作用户
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
	 * 更新用户
	 */
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 更新用户
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

	public String getSearchKeys() {
		return searchKeys;
	}

	public void setSearchKeys(String searchKeys) {
		this.searchKeys = searchKeys;
	}
}
