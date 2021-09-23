package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-4 17:01:08
 */
public class CourseContentDO extends BaseEntity {

	/**
	 * 课程id
	 */
	private Long courseInfoId;

    /**
    * 课程内容id
    */
    private Long id;


    /**
    * 内容类别
    */
    private String category;


    /**
    * 视频地址
    */
    private String videoUrl;


    /**
    * 图片地址
    */
    private String imageUrl;


    /**
    * 内容类型 0 : 视频 1 : 图片 2 : 文字
    */
    private Integer type;


    /**
    * 视频时长
    */
    private Long duration;


    /**
    * tts文本
    */
    private String ttsText;


    /**
    * 单词
    */
    private String word;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新时间(自动维护)
    */
    private Date updateTime;

	/**
     * Get 课程内容id
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 课程内容id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 内容类别
	 */
	public String getCategory() {
		return category;
	}
	
	/** 
     * Set 内容类别
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
     * Get 视频地址
	 */
	public String getVideoUrl() {
		return videoUrl;
	}
	
	/** 
     * Set 视频地址
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
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
     * Get 内容类型 0 : 视频 1 : 图片 2 : 文字
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 内容类型 0 : 视频 1 : 图片 2 : 文字
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * Get 视频时长
	 */
	public Long getDuration() {
		return duration;
	}
	
	/** 
     * Set 视频时长
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
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

	public Long getCourseInfoId() {
		return courseInfoId;
	}

	public void setCourseInfoId(Long courseInfoId) {
		this.courseInfoId = courseInfoId;
	}
}