package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

/**
 * @Description 
 * @author Ye
 * @date 2018-5-25 10:17:28
 */
public class VideoTypeRecordDO extends BaseEntity {

    /**
    * 
    */
    private Long typeRecordId;


    /**
    * 
    */
    private Long typeId;


    /**
    * 
    */
    private Long videoId;

	/**
     * Get 
	 */
	public Long getTypeRecordId() {
		return typeRecordId;
	}
	
	/** 
     * Set 
	 */
	public void setTypeRecordId(Long typeRecordId) {
		this.typeRecordId = typeRecordId;
	}
	/**
     * Get 
	 */
	public Long getTypeId() {
		return typeId;
	}
	
	/** 
     * Set 
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	/**
     * Get 
	 */
	public Long getVideoId() {
		return videoId;
	}
	
	/** 
     * Set 
	 */
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	
}