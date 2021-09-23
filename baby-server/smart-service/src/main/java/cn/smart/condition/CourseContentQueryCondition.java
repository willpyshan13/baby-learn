package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-4-10 14:34:32
*/

public class CourseContentQueryCondition extends QueryCondition {

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
	     * 课程内容id
		 */
		public Long getId() {
			return id;
		}
		
		/** 
	     * 课程内容id
		 */
		public void setId(Long id) {
			this.id = id;
		}
		/** 
	     * 内容类别
		 */
		public String getCategory() {
			return category;
		}
		
		/** 
	     * 内容类别
		 */
		public void setCategory(String category) {
			this.category = category;
		}
		/** 
	     * 视频地址
		 */
		public String getVideoUrl() {
			return videoUrl;
		}
		
		/** 
	     * 视频地址
		 */
		public void setVideoUrl(String videoUrl) {
			this.videoUrl = videoUrl;
		}
		/** 
	     * 图片地址
		 */
		public String getImageUrl() {
			return imageUrl;
		}
		
		/** 
	     * 图片地址
		 */
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		/** 
	     * 内容类型 0 : 视频 1 : 图片 2 : 文字
		 */
		public Integer getType() {
			return type;
		}
		
		/** 
	     * 内容类型 0 : 视频 1 : 图片 2 : 文字
		 */
		public void setType(Integer type) {
			this.type = type;
		}
		/** 
	     * 视频时长
		 */
		public Long getDuration() {
			return duration;
		}
		
		/** 
	     * 视频时长
		 */
		public void setDuration(Long duration) {
			this.duration = duration;
		}
		/** 
	     * tts文本
		 */
		public String getTtsText() {
			return ttsText;
		}
		
		/** 
	     * tts文本
		 */
		public void setTtsText(String ttsText) {
			this.ttsText = ttsText;
		}
		/** 
	     * 单词
		 */
		public String getWord() {
			return word;
		}
		
		/** 
	     * 单词
		 */
		public void setWord(String word) {
			this.word = word;
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

}
