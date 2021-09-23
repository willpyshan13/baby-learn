package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-8-29 14:01:21
*/

public class WeekReportQueryCondition extends QueryCondition {

	/**
	 * 
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Long personId;
	/**
	 * 评分等级
	 */
	private String level;
	/**
	 * 总得分
	 */
	private Double totalScore;
	/**
	 * 总学习天数
	 */
	private Integer totalDays;
	/**
	 * 总学习单词
	 */
	private Integer totalWords;

	/**
	 * 周学习单词个数
	 */
	private Integer wordsCount;

	/**
	 * 周学习天数
	 */
	private Integer days;
	/**
	 * 周学习单词
	 */
	private String words;
	/**
	 * 周学习句型
	 */
	private String sentence;
	/**
	 * 周报建议文字
	 */
	private String suggestion;
	/**
	 * 周报建议语音
	 */
	private String suggestionVoice;

	private Date createTime;

	private Date updateTime;

	private String courseIds;

	private Long classId;


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
	 * 用户id
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * 用户id
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	/**
	 * 评分等级
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * 评分等级
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 总得分
	 */
	public Double getTotalScore() {
		return totalScore;
	}

	/**
	 * 总得分
	 */
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	/**
	 * 总学习天数
	 */
	public Integer getTotalDays() {
		return totalDays;
	}

	/**
	 * 总学习天数
	 */
	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}
	/**
	 * 总学习单词
	 */
	public Integer getTotalWords() {
		return totalWords;
	}

	/**
	 * 总学习单词
	 */
	public void setTotalWords(Integer totalWords) {
		this.totalWords = totalWords;
	}
	/**
	 * 周学习天数
	 */
	public Integer getDays() {
		return days;
	}

	/**
	 * 周学习天数
	 */
	public void setDays(Integer days) {
		this.days = days;
	}
	/**
	 * 周学习单词
	 */
	public String getWords() {
		return words;
	}

	/**
	 * 周学习单词
	 */
	public void setWords(String words) {
		this.words = words;
	}
	/**
	 * 周学习句型
	 */
	public String getSentence() {
		return sentence;
	}

	/**
	 * 周学习句型
	 */
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	/**
	 * 周报建议文字
	 */
	public String getSuggestion() {
		return suggestion;
	}

	/**
	 * 周报建议文字
	 */
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	/**
	 * 周报建议语音
	 */
	public String getSuggestionVoice() {
		return suggestionVoice;
	}

	/**
	 * 周报建议语音
	 */
	public void setSuggestionVoice(String suggestionVoice) {
		this.suggestionVoice = suggestionVoice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(String courseIds) {
		this.courseIds = courseIds;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Integer getWordsCount() {
		return wordsCount;
	}

	public void setWordsCount(Integer wordsCount) {
		this.wordsCount = wordsCount;
	}
}
