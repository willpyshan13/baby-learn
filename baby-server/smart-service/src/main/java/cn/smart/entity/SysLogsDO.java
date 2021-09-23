package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-8-23 18:41:26
 */
public class SysLogsDO extends BaseEntity {

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
	 * 非持久化属性
	 */
	private String userName;
	private String mobilePhone;

	/**
	 * Get 编号
	 */
	public Integer getId() {
		return id;
	}
	
	/** 
     * Set 编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
     * Get 日志标题
	 */
	public String getTitle() {
		return title;
	}
	
	/** 
     * Set 日志标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
     * Get 请求地址
	 */
	public String getRequestUrl() {
		return requestUrl;
	}
	
	/** 
     * Set 请求地址
	 */
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	/**
     * Get 方法
	 */
	public String getMethod() {
		return method;
	}
	
	/** 
     * Set 方法
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
     * Get 参数
	 */
	public String getParameters() {
		return parameters;
	}
	
	/** 
     * Set 参数
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	/**
     * Get 来源
	 */
	public String getClientHost() {
		return clientHost;
	}
	
	/** 
     * Set 来源
	 */
	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}
	/**
     * Get 用户标识
	 */
	public String getUserAgent() {
		return userAgent;
	}
	
	/** 
     * Set 用户标识
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	/**
     * Get 结果
	 */
	public String getResult() {
		return result;
	}
	
	/** 
     * Set 结果
	 */
	public void setResult(String result) {
		this.result = result;
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
     * Get 操作用户
	 */
	public Long getCreateBy() {
		return createBy;
	}
	
	/** 
     * Set 操作用户
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
     * Get 更新用户
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	
	/** 
     * Set 更新用户
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
        sb.append("\"title\":")
                .append(title);
        sb.append("\"requestUrl\":")
                .append(requestUrl);
        sb.append("\"method\":")
                .append(method);
        sb.append("\"parameters\":")
                .append(parameters);
        sb.append("\"clientHost\":")
                .append(clientHost);
        sb.append("\"userAgent\":")
                .append(userAgent);
        sb.append("\"result\":")
                .append(result);
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

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}