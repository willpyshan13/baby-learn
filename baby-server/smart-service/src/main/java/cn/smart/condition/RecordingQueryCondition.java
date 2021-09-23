package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-10-12 16:44:42
*/

public class RecordingQueryCondition extends QueryCondition {

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
	 * 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 教室ID
	 */
	public Long getRoomId() {
		return roomId;
	}

	/**
	 * 教室ID
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	/**
	 * 录制件标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 录制件标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 播放地址
	 */
	public String getPlayPath() {
		return playPath;
	}

	/**
	 * 播放地址
	 */
	public void setPlayPath(String playPath) {
		this.playPath = playPath;
	}
	/**
	 * 播放地址HTTPS
	 */
	public String getHttpsPlayPath() {
		return httpsPlayPath;
	}

	/**
	 * 播放地址HTTPS
	 */
	public void setHttpsPlayPath(String httpsPlayPath) {
		this.httpsPlayPath = httpsPlayPath;
	}
	/**
	 * 记录地址
	 */
	public String getRecordPath() {
		return recordPath;
	}

	/**
	 * 记录地址
	 */
	public void setRecordPath(String recordPath) {
		this.recordPath = recordPath;
	}
	/**
	 * 视频时长
	 */
	public Double getDuration() {
		return duration;
	}

	/**
	 * 视频时长
	 */
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	/**
	 * 视频大小
	 */
	public Double getSize() {
		return size;
	}

	/**
	 * 视频大小
	 */
	public void setSize(Double size) {
		this.size = size;
	}
	/**
	 * 记录日期
	 */
	public Date getRecordDate() {
		return recordDate;
	}

	/**
	 * 记录日期
	 */
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	/**
	 * 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 当日课程ID
	 */
	public Long getCourseId() {
		return courseId;
	}

	/**
	 * 当日课程ID
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

}
