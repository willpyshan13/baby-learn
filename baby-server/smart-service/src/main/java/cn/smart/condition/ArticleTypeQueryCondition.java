package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-7-16 19:28:06
*/

public class ArticleTypeQueryCondition extends QueryCondition {

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
	 * 分类编号
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 分类编号
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * 分类名称
	 */
	public String getCodeText() {
		return codeText;
	}

	/**
	 * 分类名称
	 */
	public void setCodeText(String codeText) {
		this.codeText = codeText;
	}
	/**
	 * 分类图片
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * 分类图片
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	 * 更新时间(自动维护)
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间(自动维护)
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
