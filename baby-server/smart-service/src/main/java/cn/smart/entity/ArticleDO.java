package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-7-16 19:11:49
 */
public class ArticleDO extends BaseEntity {

    /**
    * 文章编号
    */
    private Long id;


    /**
    * 文章标题
    */
    private String title;


    /**
    * 文章类型
    */
    private Integer type;


    /**
    * 文章描述
    */
    private String description;

	/**
	 * 重要性
	 */
	private Double importance;

	/**
    * 标题图片
    */
    private String imageUrl;


    /**
    * 发布时间
    */
    private Date sendTime;


    /**
    * 作者
    */
    private String author;


    /**
    * 编辑
    */
    private String editor;


    /**
    * Tag标签
    */
    private String tags;


    /**
    * 关键字
    */
    private String keys;


    /**
    * 文章内容
    */
    private String content;


    /**
    * 阅读次数
    */
    private Integer readerTimes;


    /**
    * 发布状态
    */
    private String status;


    /**
    * 备注
    */
    private String remark;


    /**
    * 是否生效
    */
    private Byte enable;


    /**
    * 创建人
    */
    private Long createBy;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 编辑人
    */
    private Long editBy;


    /**
    * 更新时间
    */
    private Date updateTime;

	/**
	 * 访问地址(后缀)
	 */
	private String url;

	/**
	 * 非持久化属性 文章类型名称
	 */
	private String codeText;
	/**
	 * 非持久化属性 文章类型图片
	 */
	private String typeImageUrl;

	/**
	 * Get 文章编号
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 文章编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 文章标题
	 */
	public String getTitle() {
		return title;
	}
	
	/** 
     * Set 文章标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
     * Get 文章类型
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 文章类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * Get 文章描述
	 */
	public String getDescription() {
		return description;
	}
	
	/** 
     * Set 文章描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
     * Get 标题图片
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	
	/** 
     * Set 标题图片
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
     * Get 发布时间
	 */
	public Date getSendTime() {
		return sendTime;
	}
	
	/** 
     * Set 发布时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	/**
     * Get 作者
	 */
	public String getAuthor() {
		return author;
	}
	
	/** 
     * Set 作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
     * Get 编辑
	 */
	public String getEditor() {
		return editor;
	}
	
	/** 
     * Set 编辑
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}
	/**
     * Get Tag标签
	 */
	public String getTags() {
		return tags;
	}
	
	/** 
     * Set Tag标签
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
     * Get 关键字
	 */
	public String getKeys() {
		return keys;
	}
	
	/** 
     * Set 关键字
	 */
	public void setKeys(String keys) {
		this.keys = keys;
	}
	/**
     * Get 文章内容
	 */
	public String getContent() {
		return content;
	}
	
	/** 
     * Set 文章内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
     * Get 阅读次数
	 */
	public Integer getReaderTimes() {
		return readerTimes;
	}
	
	/** 
     * Set 阅读次数
	 */
	public void setReaderTimes(Integer readerTimes) {
		this.readerTimes = readerTimes;
	}
	/**
     * Get 发布状态
	 */
	public String getStatus() {
		return status;
	}
	
	/** 
     * Set 发布状态
	 */
	public void setStatus(String status) {
		this.status = status;
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
     * Get 是否生效
	 */
	public Byte getEnable() {
		return enable;
	}
	
	/** 
     * Set 是否生效
	 */
	public void setEnable(Byte enable) {
		this.enable = enable;
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
     * Get 编辑人
	 */
	public Long getEditBy() {
		return editBy;
	}
	
	/** 
     * Set 编辑人
	 */
	public void setEditBy(Long editBy) {
		this.editBy = editBy;
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

	public String getTypeImageUrl() {
		return typeImageUrl;
	}

	public void setTypeImageUrl(String typeImageUrl) {
		this.typeImageUrl = typeImageUrl;
	}

	public String getCodeText() {
		return codeText;
	}

	public void setCodeText(String codeText) {
		this.codeText = codeText;
	}

	public Double getImportance() {
		return importance;
	}

	public void setImportance(Double importance) {
		this.importance = importance;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}