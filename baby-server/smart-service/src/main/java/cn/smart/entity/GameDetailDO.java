package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-5-6 20:16:56
 */
public class GameDetailDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 游戏id
    */
    private Long gameId;


    /**
    * 图片地址
    */
    private String imageUrl;


    /**
    * 资源地址
    */
    private String resourceUrl;


    /**
    * tts文本
    */
    private String ttsText;


    /**
    * 单词
    */
    private String word;


    /**
    * 类型 [0, "文字"], [1, "图片"], [2,'视频']
    */
    private Integer gameType;


    /**
    * 是否正确答案 0 否 1 是
    */
    private Boolean rightAnswer;


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
     * Get 游戏id
	 */
	public Long getGameId() {
		return gameId;
	}
	
	/** 
     * Set 游戏id
	 */
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	/**
     * Get 图片地址
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	
	/** 
     * Set 图片地址
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
     * Get 资源地址
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}
	
	/** 
     * Set 资源地址
	 */
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	/**
     * Get tts文本
	 */
	public String getTtsText() {
		return ttsText;
	}
	
	/** 
     * Set tts文本
	 */
	public void setTtsText(String ttsText) {
		this.ttsText = ttsText;
	}
	/**
     * Get 单词
	 */
	public String getWord() {
		return word;
	}
	
	/** 
     * Set 单词
	 */
	public void setWord(String word) {
		this.word = word;
	}
	/**
     * Get 类型 [0, "文字"], [1, "图片"], [2,'视频']
	 */
	public Integer getGameType() {
		return gameType;
	}
	
	/** 
     * Set 类型 [0, "文字"], [1, "图片"], [2,'视频']
	 */
	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}
	/**
     * Get 是否正确答案 0 否 1 是
	 */
	public Boolean getRightAnswer() {
		return rightAnswer;
	}
	
	/** 
     * Set 是否正确答案 0 否 1 是
	 */
	public void setRightAnswer(Boolean rightAnswer) {
		this.rightAnswer = rightAnswer;
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