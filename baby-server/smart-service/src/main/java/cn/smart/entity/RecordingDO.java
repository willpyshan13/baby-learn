package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * 
 * @author Ye
 * @date 2018-10-12 16:44:42
 */
public class RecordingDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 教室ID
    */
    private Long roomId;


    /**
    * 录制件标题
    */
    private String title;


    /**
    * 播放地址
    */
    private String playPath;


    /**
    * 播放地址HTTPS
    */
    private String httpsPlayPath;


    /**
    * 记录地址
    */
    private String recordPath;


    /**
    * 视频时长
    */
    private Double duration;


    /**
    * 视频大小
    */
    private Double size;


    /**
    * 记录日期
    */
    private Date recordDate;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 当日课程ID
    */
    private Long courseId;

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
     * Get 教室ID
	 */
	public Long getRoomId() {
		return roomId;
	}
	
	/** 
     * Set 教室ID
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	/**
     * Get 录制件标题
	 */
	public String getTitle() {
		return title;
	}
	
	/** 
     * Set 录制件标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
     * Get 播放地址
	 */
	public String getPlayPath() {
		return playPath;
	}
	
	/** 
     * Set 播放地址
	 */
	public void setPlayPath(String playPath) {
		this.playPath = playPath;
	}
	/**
     * Get 播放地址HTTPS
	 */
	public String getHttpsPlayPath() {
		return httpsPlayPath;
	}
	
	/** 
     * Set 播放地址HTTPS
	 */
	public void setHttpsPlayPath(String httpsPlayPath) {
		this.httpsPlayPath = httpsPlayPath;
	}
	/**
     * Get 记录地址
	 */
	public String getRecordPath() {
		return recordPath;
	}
	
	/** 
     * Set 记录地址
	 */
	public void setRecordPath(String recordPath) {
		this.recordPath = recordPath;
	}
	/**
     * Get 视频时长
	 */
	public Double getDuration() {
		return duration;
	}
	
	/** 
     * Set 视频时长
	 */
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	/**
     * Get 视频大小
	 */
	public Double getSize() {
		return size;
	}
	
	/** 
     * Set 视频大小
	 */
	public void setSize(Double size) {
		this.size = size;
	}
	/**
     * Get 记录日期
	 */
	public Date getRecordDate() {
		return recordDate;
	}
	
	/** 
     * Set 记录日期
	 */
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
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
     * Get 当日课程ID
	 */
	public Long getCourseId() {
		return courseId;
	}
	
	/** 
     * Set 当日课程ID
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"roomId\":")
                .append(roomId);
        sb.append("\"title\":")
                .append(title);
        sb.append("\"playPath\":")
                .append(playPath);
        sb.append("\"httpsPlayPath\":")
                .append(httpsPlayPath);
        sb.append("\"recordPath\":")
                .append(recordPath);
        sb.append("\"duration\":")
                .append(duration);
        sb.append("\"size\":")
                .append(size);
        sb.append("\"recordDate\":")
                .append(recordDate);
        sb.append("\"createTime\":")
                .append(createTime);
        sb.append("\"courseId\":")
                .append(courseId);
        sb.append('}');
        return sb.toString();
    }

}