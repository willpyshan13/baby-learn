package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-8-11 15:27:46
*/

public class MessageQueryCondition extends QueryCondition {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 消息标题
	 */
	private String title;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 消息类型 1: 普通消息 2: 语音消息 3: 通知消息
	 */
	private Integer type;
	/**
	 * 消息数据 (JSON 格式)
	 */
	private String data;
	/**
	 * 推送类型 1: 单个推送 2: 班级推送
	 */
	private Integer objectType;
	/**
	 * 推送对象 主键
	 */
	private Long objectId;
	/**
	 * 推送时间
	 */
	private Date createTime;

	/**
	 * 主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 消息标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 消息标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 消息内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 消息内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 消息类型 1: 普通消息 2: 语音消息 3: 通知消息
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 消息类型 1: 普通消息 2: 语音消息 3: 通知消息
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 消息数据 (JSON 格式)
	 */
	public String getData() {
		return data;
	}

	/**
	 * 消息数据 (JSON 格式)
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * 推送类型 1: 单个推送 2: 班级推送
	 */
	public Integer getObjectType() {
		return objectType;
	}

	/**
	 * 推送类型 1: 单个推送 2: 班级推送
	 */
	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	/**
	 * 推送对象 主键
	 */
	public Long getObjectId() {
		return objectId;
	}

	/**
	 * 推送对象 主键
	 */
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	/**
	 * 推送时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 推送时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
