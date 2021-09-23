package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-7-10 17:56:53
 */
public class PushLogDO extends BaseEntity {

	public String getPushTitle() {
		return pushTitle;
	}

	public void setPushTitle(String pushTitle) {
		this.pushTitle = pushTitle;
	}

	public interface PushType {
		/**
		 * 上课开始消息
		 */
		int CLASS_BEGIN 	= 1;
		/**
		 * 强制登出
		 */
		int LOG_OUT 		= 2;
		/**
		 * 班级推送标签更新
		 */
		int UPDATE_TAG 		= 3;
		/**
		 * 语音消息
		 */
		int VOICE_MSG		= 4;
	}

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

    // 非持久化对象
    private String mobileNumber;
    private String roomName;

	/**
     * Get 
	 */
	public Integer getId() {
		return id;
	}
	
	/** 
     * Set 
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
     * Get 推送标记
	 */
	public String getPushTag() {
		return pushTag;
	}
	
	/** 
     * Set 推送标记
	 */
	public void setPushTag(String pushTag) {
		this.pushTag = pushTag;
	}
	/**
     * Get 推送别名
	 */
	public String getPushAlias() {
		return pushAlias;
	}
	
	/** 
     * Set 推送别名
	 */
	public void setPushAlias(String pushAlias) {
		this.pushAlias = pushAlias;
	}
	/**
     * Get 推送类型
	 */
	public Integer getPushType() {
		return pushType;
	}
	
	/** 
     * Set 推送类型
	 */
	public void setPushType(Integer pushType) {
		this.pushType = pushType;
	}
	/**
     * Get 推送消息
	 */
	public String getPushMsg() {
		return pushMsg;
	}
	
	/** 
     * Set 推送消息
	 */
	public void setPushMsg(String pushMsg) {
		this.pushMsg = pushMsg;
	}
	/**
     * Get 推送结果
	 */
	public String getResult() {
		return result;
	}
	
	/** 
     * Set 推送结果
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
     * Get 推送时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 
     * Set 推送时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
     * Get 推送用户
	 */
	public Long getPersonId() {
		return personId;
	}
	
	/** 
     * Set 推送用户
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	/**
     * Get 推送班级
	 */
	public Long getRoomId() {
		return roomId;
	}
	
	/** 
     * Set 推送班级
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getPushData() {
		return pushData;
	}

	public void setPushData(String pushData) {
		this.pushData = pushData;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"id\":")
				.append(id);
		sb.append(",\"pushTag\":\"")
				.append(pushTag).append('\"');
		sb.append(",\"pushAlias\":\"")
				.append(pushAlias).append('\"');
		sb.append(",\"pushType\":")
				.append(pushType);
		sb.append(",\"pushTitle\":\"")
				.append(pushTitle).append('\"');
		sb.append(",\"pushMsg\":\"")
				.append(pushMsg).append('\"');
		sb.append(",\"pushData\":\"")
				.append(pushData).append('\"');
		sb.append(",\"result\":\"")
				.append(result).append('\"');
		sb.append(",\"createTime\":\"")
				.append(createTime).append('\"');
		sb.append(",\"personId\":")
				.append(personId);
		sb.append(",\"roomId\":")
				.append(roomId);
		sb.append(",\"mobileNumber\":\"")
				.append(mobileNumber).append('\"');
		sb.append(",\"roomName\":\"")
				.append(roomName).append('\"');
		sb.append('}');
		return sb.toString();
	}
}