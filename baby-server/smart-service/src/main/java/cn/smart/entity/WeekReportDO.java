package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-8-29 14:01:21
 */
public class WeekReportDO extends BaseEntity {

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
    * 周学习天数
    */
    private Integer days;


    /**
    * 周学习单词
    */
    private String words;

	/**
	 * 周学习单词个数
	 */
	private Integer wordsCount;

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
     * Get 用户id
	 */
	public Long getPersonId() {
		return personId;
	}
	
	/** 
     * Set 用户id
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	/**
     * Get 评分等级
	 */
	public String getLevel() {
		return level;
	}
	
	/** 
     * Set 评分等级
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
     * Get 总得分
	 */
	public Double getTotalScore() {
		return totalScore;
	}
	
	/** 
     * Set 总得分
	 */
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	/**
     * Get 总学习天数
	 */
	public Integer getTotalDays() {
		return totalDays;
	}
	
	/** 
     * Set 总学习天数
	 */
	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}
	/**
     * Get 总学习单词
	 */
	public Integer getTotalWords() {
		return totalWords;
	}
	
	/** 
     * Set 总学习单词
	 */
	public void setTotalWords(Integer totalWords) {
		this.totalWords = totalWords;
	}
	/**
     * Get 周学习天数
	 */
	public Integer getDays() {
		return days;
	}
	
	/** 
     * Set 周学习天数
	 */
	public void setDays(Integer days) {
		this.days = days;
	}
	/**
     * Get 周学习单词
	 */
	public String getWords() {
		return words;
	}
	
	/** 
     * Set 周学习单词
	 */
	public void setWords(String words) {
		this.words = words;
	}
	/**
     * Get 周学习句型
	 */
	public String getSentence() {
		return sentence;
	}
	
	/** 
     * Set 周学习句型
	 */
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	/**
     * Get 周报建议文字
	 */
	public String getSuggestion() {
		return suggestion;
	}
	
	/** 
     * Set 周报建议文字
	 */
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	/**
     * Get 周报建议语音
	 */
	public String getSuggestionVoice() {
		return suggestionVoice;
	}
	
	/** 
     * Set 周报建议语音
	 */
	public void setSuggestionVoice(String suggestionVoice) {
		this.suggestionVoice = suggestionVoice;
	}

    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"personId\":")
                .append(personId);
        sb.append("\"level\":")
                .append(level);
        sb.append("\"totalScore\":")
                .append(totalScore);
        sb.append("\"totalDays\":")
                .append(totalDays);
        sb.append("\"totalWords\":")
                .append(totalWords);
        sb.append("\"days\":")
                .append(days);
        sb.append("\"words\":")
                .append(words);
        sb.append("\"sentence\":")
                .append(sentence);
        sb.append("\"suggestion\":")
                .append(suggestion);
        sb.append("\"suggestionVoice\":")
                .append(suggestionVoice);
        sb.append('}');
        return sb.toString();
    }

	public String getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(String courseIds) {
		this.courseIds = courseIds;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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