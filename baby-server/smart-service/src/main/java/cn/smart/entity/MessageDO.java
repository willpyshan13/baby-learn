package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-8-11 15:27:45
 */
public class MessageDO extends BaseEntity {

	/**
	 * 1: 普通消息 2: 语音消息 3: 通知消息
	 * 4: 上课开始 5: 强制登出 6: 更新标签
	 */
	public interface MessageType {
		String COMMON = "1";
		String VOICE = "2";
		String NOTIFICATION = "3";
		String CLASS_BEGIN = "4";
		String LOG_OUT = "5";
		String UPDATE_TAGS = "6";
	}

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
     * Get 主键
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 消息标题
	 */
	public String getTitle() {
		return title;
	}
	
	/** 
     * Set 消息标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
     * Get 消息内容
	 */
	public String getContent() {
		return content;
	}
	
	/** 
     * Set 消息内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
     * Get 消息类型 1: 普通消息 2: 语音消息 3: 通知消息
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 消息类型 1: 普通消息 2: 语音消息 3: 通知消息
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * Get 消息数据 (JSON 格式)
	 */
	public String getData() {
		return data;
	}
	
	/** 
     * Set 消息数据 (JSON 格式)
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
     * Get 推送类型 1: 单个推送 2: 班级推送
	 */
	public Integer getObjectType() {
		return objectType;
	}
	
	/** 
     * Set 推送类型 1: 单个推送 2: 班级推送
	 */
	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	/**
     * Get 推送对象 主键
	 */
	public Long getObjectId() {
		return objectId;
	}
	
	/** 
     * Set 推送对象 主键
	 */
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
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

    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"title\":")
                .append(title);
        sb.append("\"content\":")
                .append(content);
        sb.append("\"type\":")
                .append(type);
        sb.append("\"data\":")
                .append(data);
        sb.append("\"objectType\":")
                .append(objectType);
        sb.append("\"objectId\":")
                .append(objectId);
        sb.append("\"createTime\":")
                .append(createTime);
        sb.append('}');
        return sb.toString();
    }

}