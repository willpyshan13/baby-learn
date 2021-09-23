package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-5-6 20:16:56
*/

public class GameDetailQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Long id;
		/** 
		 * 游戏id
		 */
		private Long gameId;

		private Long[] gameIds;
		/** 
		 * 图片地址
		 */
		private String imageUrl;
		/** 
		 * 资源地址
		 */
		private String resourceUrl;
		/** 
		 * tts文本
		 */
		private String ttsText;
		/** 
		 * 单词
		 */
		private String word;
		/** 
		 * 类型 [0, "文字"], [1, "图片"], [2,'视频']
		 */
		private Integer gameType;
		/** 
		 * 是否正确答案 0 否 1 是
		 */
		private Boolean rightAnswer;
		/** 
		 * 创建时间
		 */
		private Date createTime;
		/** 
		 * 更新时间(自动维护)
		 */
		private Date updateTime;

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
	     * 游戏id
		 */
		public Long getGameId() {
			return gameId;
		}
		
		/** 
	     * 游戏id
		 */
		public void setGameId(Long gameId) {
			this.gameId = gameId;
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
	     * 资源地址
		 */
		public String getResourceUrl() {
			return resourceUrl;
		}
		
		/** 
	     * 资源地址
		 */
		public void setResourceUrl(String resourceUrl) {
			this.resourceUrl = resourceUrl;
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
	     * 类型 [0, "文字"], [1, "图片"], [2,'视频']
		 */
		public Integer getGameType() {
			return gameType;
		}
		
		/** 
	     * 类型 [0, "文字"], [1, "图片"], [2,'视频']
		 */
		public void setGameType(Integer gameType) {
			this.gameType = gameType;
		}
		/** 
	     * 是否正确答案 0 否 1 是
		 */
		public Boolean getRightAnswer() {
			return rightAnswer;
		}
		
		/** 
	     * 是否正确答案 0 否 1 是
		 */
		public void setRightAnswer(Boolean rightAnswer) {
			this.rightAnswer = rightAnswer;
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

	public Long[] getGameIds() {
		return gameIds;
	}

	public void setGameIds(Long[] gameIds) {
		this.gameIds = gameIds;
	}
}
