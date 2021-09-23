package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-5-2 17:38:18
*/

public class VideoQueryCondition extends QueryCondition {

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

		/**
		 * 视频类型 主键
		 */
		private Long typeId;

		/** 
	     * 编号
		 */
		public Long getVideoId() {
			return videoId;
		}
		
		/** 
	     * 编号
		 */
		public void setVideoId(Long videoId) {
			this.videoId = videoId;
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
	     * 状态 0:生效 1:已删除 2:失效
		 */
		public Integer getStatus() {
			return status;
		}
		
		/** 
	     * 状态 0:生效 1:已删除 2:失效
		 */
		public void setStatus(Integer status) {
			this.status = status;
		}
		/** 
	     * 更新时间
		 */
		public Date getUpdateTime() {
			return updateTime;
		}
		
		/** 
	     * 更新时间
		 */
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		/** 
	     * 演员
		 */
		public String getVideoActor() {
			return videoActor;
		}
		
		/** 
	     * 演员
		 */
		public void setVideoActor(String videoActor) {
			this.videoActor = videoActor;
		}
		/** 
	     * 简介
		 */
		public String getVideoBrief() {
			return videoBrief;
		}
		
		/** 
	     * 简介
		 */
		public void setVideoBrief(String videoBrief) {
			this.videoBrief = videoBrief;
		}
		/** 
	     * 详情
		 */
		public String getVideoDesc() {
			return videoDesc;
		}
		
		/** 
	     * 详情
		 */
		public void setVideoDesc(String videoDesc) {
			this.videoDesc = videoDesc;
		}
		/** 
	     * 导演
		 */
		public String getVideoDirector() {
			return videoDirector;
		}
		
		/** 
	     * 导演
		 */
		public void setVideoDirector(String videoDirector) {
			this.videoDirector = videoDirector;
		}
		/** 
	     * 预览图
		 */
		public String getVideoPreviewPicUrl() {
			return videoPreviewPicUrl;
		}
		
		/** 
	     * 预览图
		 */
		public void setVideoPreviewPicUrl(String videoPreviewPicUrl) {
			this.videoPreviewPicUrl = videoPreviewPicUrl;
		}
		/** 
	     * 区域
		 */
		public Long getVideoRegion() {
			return videoRegion;
		}
		
		/** 
	     * 区域
		 */
		public void setVideoRegion(Long videoRegion) {
			this.videoRegion = videoRegion;
		}
		/** 
	     * 编剧
		 */
		public String getVideoScriptWriter() {
			return videoScriptWriter;
		}
		
		/** 
	     * 编剧
		 */
		public void setVideoScriptWriter(String videoScriptWriter) {
			this.videoScriptWriter = videoScriptWriter;
		}
		/** 
	     * 视频标题
		 */
		public String getVideoTitle() {
			return videoTitle;
		}
		
		/** 
	     * 视频标题
		 */
		public void setVideoTitle(String videoTitle) {
			this.videoTitle = videoTitle;
		}
		/** 
	     * 轮播图
		 */
		public String getVideoWheelPicUrl() {
			return videoWheelPicUrl;
		}
		
		/** 
	     * 轮播图
		 */
		public void setVideoWheelPicUrl(String videoWheelPicUrl) {
			this.videoWheelPicUrl = videoWheelPicUrl;
		}
		/** 
	     * 年份
		 */
		public Long getVideoYear() {
			return videoYear;
		}
		
		/** 
	     * 年份
		 */
		public void setVideoYear(Long videoYear) {
			this.videoYear = videoYear;
		}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
}
