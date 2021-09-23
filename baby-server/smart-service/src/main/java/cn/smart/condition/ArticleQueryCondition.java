package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-7-16 19:11:49
*/

public class ArticleQueryCondition extends QueryCondition {
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public interface articleStatus {
		String PUBLISH = "1";
		String DRAFT = "2";
	}
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
	 * 查询不在分类下
	 */
	private Integer unequalType;

	/**
	 * 文章编号
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 文章编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 文章标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 文章标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 文章类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 文章类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 文章描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 文章描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 标题图片
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * 标题图片
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * 发布时间
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * 发布时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * 作者
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 编辑
	 */
	public String getEditor() {
		return editor;
	}

	/**
	 * 编辑
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}
	/**
	 * Tag标签
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * Tag标签
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * 关键字
	 */
	public String getKeys() {
		return keys;
	}

	/**
	 * 关键字
	 */
	public void setKeys(String keys) {
		this.keys = keys;
	}
	/**
	 * 文章内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 文章内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 阅读次数
	 */
	public Integer getReaderTimes() {
		return readerTimes;
	}

	/**
	 * 阅读次数
	 */
	public void setReaderTimes(Integer readerTimes) {
		this.readerTimes = readerTimes;
	}
	/**
	 * 发布状态
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 发布状态
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * 是否生效
	 */
	public Byte getEnable() {
		return enable;
	}

	/**
	 * 是否生效
	 */
	public void setEnable(Byte enable) {
		this.enable = enable;
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
	 * 编辑人
	 */
	public Long getEditBy() {
		return editBy;
	}

	/**
	 * 编辑人
	 */
	public void setEditBy(Long editBy) {
		this.editBy = editBy;
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

	public Integer getUnequalType() {
		return unequalType;
	}

	public void setUnequalType(Integer unequalType) {
		this.unequalType = unequalType;
	}

	public Double getImportance() {
		return importance;
	}

	public void setImportance(Double importance) {
		this.importance = importance;
	}
}
