package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 *  视频表
 * @author Ye
 * @date 2018-5-2 9:49:45
 */
public class VideoUrlDO extends BaseEntity {

    /**
    * 视频id
    */
    private Long videoUrlId;


    /**
    * 内容id
    */
    private Long videoId;


    /**
    * 视频格式
    */
    private String videoFormat;


    /**
    * 视频名称
    */
    private String videoUrlDesc;


    /**
    * 预览图
    */
    private String videoPlayUrl;


    /**
    * 视频地址
    */
    private String videoWebUrl;


    /**
    * 排序
    */
    private Integer videoUrlIndex;


    /**
    * 状态 0:生效 1:删除 2:不可用
    */
    private Integer status;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新时间(自动维护)
    */
    private Date updateTime;

	/**
     * Get 视频id
	 */
	public Long getVideoUrlId() {
		return videoUrlId;
	}
	
	/** 
     * Set 视频id
	 */
	public void setVideoUrlId(Long videoUrlId) {
		this.videoUrlId = videoUrlId;
	}
	/**
     * Get 内容id
	 */
	public Long getVideoId() {
		return videoId;
	}
	
	/** 
     * Set 内容id
	 */
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	/**
     * Get 视频格式
	 */
	public String getVideoFormat() {
		return videoFormat;
	}
	
	/** 
     * Set 视频格式
	 */
	public void setVideoFormat(String videoFormat) {
		this.videoFormat = videoFormat;
	}
	/**
     * Get 视频名称
	 */
	public String getVideoUrlDesc() {
		return videoUrlDesc;
	}
	
	/** 
     * Set 视频名称
	 */
	public void setVideoUrlDesc(String videoUrlDesc) {
		this.videoUrlDesc = videoUrlDesc;
	}
	/**
     * Get 预览图
	 */
	public String getVideoPlayUrl() {
		return videoPlayUrl;
	}
	
	/** 
     * Set 预览图
	 */
	public void setVideoPlayUrl(String videoPlayUrl) {
		this.videoPlayUrl = videoPlayUrl;
	}
	/**
     * Get 视频地址
	 */
	public String getVideoWebUrl() {
		return videoWebUrl;
	}
	
	/** 
     * Set 视频地址
	 */
	public void setVideoWebUrl(String videoWebUrl) {
		this.videoWebUrl = videoWebUrl;
	}
	/**
     * Get 排序
	 */
	public Integer getVideoUrlIndex() {
		return videoUrlIndex;
	}
	
	/** 
     * Set 排序
	 */
	public void setVideoUrlIndex(Integer videoUrlIndex) {
		this.videoUrlIndex = videoUrlIndex;
	}
	/**
     * Get 状态 0:生效 1:删除 2:不可用
	 */
	public Integer getStatus() {
		return status;
	}
	
	/** 
     * Set 状态 0:生效 1:删除 2:不可用
	 */
	public void setStatus(Integer status) {
		this.status = status;
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