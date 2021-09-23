package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-10-12 17:14:16
*/

public class PersonRecordingQueryCondition extends QueryCondition {

	/**
	 * 
	 */
	private Long id;
	/**
	 * 用户ID
	 */
	private Long personId;
	/**
	 * 教室ID
	 */
	private Long roomId;
	/**
	 * 录制件ID
	 */
	private Long recordingId;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 当天开课课程ID
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
	 * 用户ID
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * 用户ID
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
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
	 * 录制件ID
	 */
	public Long getRecordingId() {
		return recordingId;
	}

	/**
	 * 录制件ID
	 */
	public void setRecordingId(Long recordingId) {
		this.recordingId = recordingId;
	}
	/**
	 * 创建日期
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 创建日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	 * 当天开课课程ID
	 */
	public Long getCourseId() {
		return courseId;
	}

	/**
	 * 当天开课课程ID
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

}
