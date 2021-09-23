package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-7-10 17:56:54
*/

public class PushLogQueryCondition extends QueryCondition {

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 推送标记
	 */
	private String pushTag;
	/**
	 * 推送别名
	 */
	private String pushAlias;
	/**
	 * 推送类型
	 */
	private Integer pushType;
	/**
	 * 推送标题
	 */
	private String pushTitle;
	/**
	 * 推送消息
	 */
	private String pushMsg;
	/**
	 * 推送数据(JSON)
	 */
	private String pushData;
	/**
	 * 推送结果
	 */
	private String result;
	/**
	 * 推送时间
	 */
	private Date createTime;
	/**
	 * 推送用户
	 */
	private Long personId;
	/**
	 * 推送班级
	 */
	private Long roomId;

	private String keyWord;

	/**
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 推送标记
	 */
	public String getPushTag() {
		return pushTag;
	}

	/**
	 * 推送标记
	 */
	public void setPushTag(String pushTag) {
		this.pushTag = pushTag;
	}
	/**
	 * 推送别名
	 */
	public String getPushAlias() {
		return pushAlias;
	}

	/**
	 * 推送别名
	 */
	public void setPushAlias(String pushAlias) {
		this.pushAlias = pushAlias;
	}
	/**
	 * 推送类型
	 */
	public Integer getPushType() {
		return pushType;
	}

	/**
	 * 推送类型
	 */
	public void setPushType(Integer pushType) {
		this.pushType = pushType;
	}
	/**
	 * 推送消息
	 */
	public String getPushMsg() {
		return pushMsg;
	}

	/**
	 * 推送消息
	 */
	public void setPushMsg(String pushMsg) {
		this.pushMsg = pushMsg;
	}
	/**
	 * 推送结果
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 推送结果
	 */
	public void setResult(String result) {
		this.result = result;
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
	/**
	 * 推送用户
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * 推送用户
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	/**
	 * 推送班级
	 */
	public Long getRoomId() {
		return roomId;
	}

	/**
	 * 推送班级
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getPushData() {
		return pushData;
	}

	public void setPushData(String pushData) {
		this.pushData = pushData;
	}

	public String getPushTitle() {
		return pushTitle;
	}
	public void setPushTitle(String pushTitle) {
		this.pushTitle = pushTitle;
	}
}
