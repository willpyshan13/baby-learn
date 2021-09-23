package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-4-25 20:12:55
*/

public class CoursewareQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Long id;
		/** 
		 * 文件名称
		 */
		private String name;
		/** 
		 * 文件类型1:视频 2:音频 3:文档
		 */
		private Integer type;
		/** 
		 * 课件类型 0:本地上传
		 */
		private Integer uploadType;
		/** 
		 * 课件地址
		 */
		private String resourceUrl;
		/** 
		 * 文件属性 0: 非动态 ppt 1: 动态 ppt
		 */
		private Integer dynamicPpt;
		/** 
		 * 上传者
		 */
		private String creator;
		/** 
		 * 状态
		 */
		private Integer status;
		/** 
		 * 文件大小
		 */
		private Double size;
		/** 
		 * 默认课件 0:不是  1：是
		 */
		private Integer isDefault;
		/** 
		 * 是否是公开文档 0：表示非公开文档 1：表示公开文档公开文档表示公司的其他房间都可以关联此文档，非公开文档表示只对本房间
		 */
		private Integer isOpen;
		/** 
		 * 创建时间
		 */
		private Date createTime;
		/** 
		 * 更新时间(自动维护)
		 */
		private Date updateTime;

		private Long roomId;

		private Long roomName;

		private Long coursewareId;

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
	     * 文件名称
		 */
		public String getName() {
			return name;
		}
		
		/** 
	     * 文件名称
		 */
		public void setName(String name) {
			this.name = name;
		}
		/** 
	     * 文件类型1:视频 2:音频 3:文档
		 */
		public Integer getType() {
			return type;
		}
		
		/** 
	     * 文件类型1:视频 2:音频 3:文档
		 */
		public void setType(Integer type) {
			this.type = type;
		}
		/** 
	     * 课件类型 0:本地上传
		 */
		public Integer getUploadType() {
			return uploadType;
		}
		
		/** 
	     * 课件类型 0:本地上传
		 */
		public void setUploadType(Integer uploadType) {
			this.uploadType = uploadType;
		}
		/** 
	     * 课件地址
		 */
		public String getResourceUrl() {
			return resourceUrl;
		}
		
		/** 
	     * 课件地址
		 */
		public void setResourceUrl(String resourceUrl) {
			this.resourceUrl = resourceUrl;
		}
		/** 
	     * 文件属性 0: 非动态 ppt 1: 动态 ppt
		 */
		public Integer getDynamicPpt() {
			return dynamicPpt;
		}
		
		/** 
	     * 文件属性 0: 非动态 ppt 1: 动态 ppt
		 */
		public void setDynamicPpt(Integer dynamicPpt) {
			this.dynamicPpt = dynamicPpt;
		}
		/** 
	     * 上传者
		 */
		public String getCreator() {
			return creator;
		}
		
		/** 
	     * 上传者
		 */
		public void setCreator(String creator) {
			this.creator = creator;
		}
		/** 
	     * 状态
		 */
		public Integer getStatus() {
			return status;
		}
		
		/** 
	     * 状态
		 */
		public void setStatus(Integer status) {
			this.status = status;
		}
		/** 
	     * 文件大小
		 */
		public Double getSize() {
			return size;
		}
		
		/** 
	     * 文件大小
		 */
		public void setSize(Double size) {
			this.size = size;
		}
		/** 
	     * 默认课件 0:不是  1：是
		 */
		public Integer getIsDefault() {
			return isDefault;
		}
		
		/** 
	     * 默认课件 0:不是  1：是
		 */
		public void setIsDefault(Integer isDefault) {
			this.isDefault = isDefault;
		}
		/** 
	     * 是否是公开文档 0：表示非公开文档 1：表示公开文档公开文档表示公司的其他房间都可以关联此文档，非公开文档表示只对本房间
		 */
		public Integer getIsOpen() {
			return isOpen;
		}
		
		/** 
	     * 是否是公开文档 0：表示非公开文档 1：表示公开文档公开文档表示公司的其他房间都可以关联此文档，非公开文档表示只对本房间
		 */
		public void setIsOpen(Integer isOpen) {
			this.isOpen = isOpen;
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
	     * 更新时间(自动维护)
		 */
		public Date getUpdateTime() {
			return updateTime;
		}
		
		/** 
	     * 更新时间(自动维护)
		 */
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

	public Long getRoomName() {
		return roomName;
	}

	public void setRoomName(Long roomName) {
		this.roomName = roomName;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getCoursewareId() {
		return coursewareId;
	}

	public void setCoursewareId(Long coursewareId) {
		this.coursewareId = coursewareId;
	}
}
