package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-5-2 17:38:18
 */
public class VideoDO extends BaseEntity {

    /**
    * 编号
    */
    private Long videoId;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 状态 0:生效 1:已删除 2:失效
    */
    private Integer status;


    /**
    * 更新时间
    */
    private Date updateTime;


    /**
    * 演员
    */
    private String videoActor;


    /**
    * 简介
    */
    private String videoBrief;


    /**
    * 详情
    */
    private String videoDesc;


    /**
    * 导演
    */
    private String videoDirector;


    /**
    * 预览图
    */
    private String videoPreviewPicUrl;


    /**
    * 区域
    */
    private Long videoRegion;


    /**
    * 编剧
    */
    private String videoScriptWriter;


    /**
    * 视频标题
    */
    private String videoTitle;


    /**
    * 轮播图
    */
    private String videoWheelPicUrl;


    /**
    * 年份
    */
    private Long videoYear;

    private Long typeRecordId;

    private Long typeId;

	private String typeDesc;

	/**
     * Get 编号
	 */
	public Long getVideoId() {
		return videoId;
	}
	
	/** 
     * Set 编号
	 */
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
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
     * Get 状态 0:生效 1:已删除 2:失效
	 */
	public Integer getStatus() {
		return status;
	}
	
	/** 
     * Set 状态 0:生效 1:已删除 2:失效
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	/**
     * Get 演员
	 */
	public String getVideoActor() {
		return videoActor;
	}
	
	/** 
     * Set 演员
	 */
	public void setVideoActor(String videoActor) {
		this.videoActor = videoActor;
	}
	/**
     * Get 简介
	 */
	public String getVideoBrief() {
		return videoBrief;
	}
	
	/** 
     * Set 简介
	 */
	public void setVideoBrief(String videoBrief) {
		this.videoBrief = videoBrief;
	}
	/**
     * Get 详情
	 */
	public String getVideoDesc() {
		return videoDesc;
	}
	
	/** 
     * Set 详情
	 */
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	/**
     * Get 导演
	 */
	public String getVideoDirector() {
		return videoDirector;
	}
	
	/** 
     * Set 导演
	 */
	public void setVideoDirector(String videoDirector) {
		this.videoDirector = videoDirector;
	}
	/**
     * Get 预览图
	 */
	public String getVideoPreviewPicUrl() {
		return videoPreviewPicUrl;
	}
	
	/** 
     * Set 预览图
	 */
	public void setVideoPreviewPicUrl(String videoPreviewPicUrl) {
		this.videoPreviewPicUrl = videoPreviewPicUrl;
	}
	/**
     * Get 区域
	 */
	public Long getVideoRegion() {
		return videoRegion;
	}
	
	/** 
     * Set 区域
	 */
	public void setVideoRegion(Long videoRegion) {
		this.videoRegion = videoRegion;
	}
	/**
     * Get 编剧
	 */
	public String getVideoScriptWriter() {
		return videoScriptWriter;
	}
	
	/** 
     * Set 编剧
	 */
	public void setVideoScriptWriter(String videoScriptWriter) {
		this.videoScriptWriter = videoScriptWriter;
	}
	/**
     * Get 视频标题
	 */
	public String getVideoTitle() {
		return videoTitle;
	}
	
	/** 
     * Set 视频标题
	 */
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	/**
     * Get 轮播图
	 */
	public String getVideoWheelPicUrl() {
		return videoWheelPicUrl;
	}
	
	/** 
     * Set 轮播图
	 */
	public void setVideoWheelPicUrl(String videoWheelPicUrl) {
		this.videoWheelPicUrl = videoWheelPicUrl;
	}
	/**
     * Get 年份
	 */
	public Long getVideoYear() {
		return videoYear;
	}
	
	/** 
     * Set 年份
	 */
	public void setVideoYear(Long videoYear) {
		this.videoYear = videoYear;
	}

	public Long getTypeRecordId() {
		return typeRecordId;
	}

	public void setTypeRecordId(Long typeRecordId) {
		this.typeRecordId = typeRecordId;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
}