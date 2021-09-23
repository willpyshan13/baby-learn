package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-7-16 19:28:06
 */
public class ArticleTypeDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 分类编号
    */
    private Integer code;


    /**
    * 分类名称
    */
    private String codeText;


    /**
    * 分类图片
    */
    private String imageUrl;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新时间(自动维护)
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
     * Get 分类编号
	 */
	public Integer getCode() {
		return code;
	}
	
	/** 
     * Set 分类编号
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
     * Get 分类名称
	 */
	public String getCodeText() {
		return codeText;
	}
	
	/** 
     * Set 分类名称
	 */
	public void setCodeText(String codeText) {
		this.codeText = codeText;
	}
	/**
     * Get 分类图片
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	
	/** 
     * Set 分类图片
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
     * Get 更新时间(自动维护)
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 
     * Set 更新时间(自动维护)
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}