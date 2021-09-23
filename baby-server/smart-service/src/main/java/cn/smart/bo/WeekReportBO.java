package cn.smart.bo;

public class WeekReportBO {

    /**
     *
     */
    private Long id;


    /**
     * 用户id
     */
    private Long personId;

    private String nickName;

    private String photoUrl;

    private Integer age;

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


    private WeekReportDetailBO playGameReport;

    private WeekReportDetailBO speakUpReport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Integer getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(Integer totalWords) {
        this.totalWords = totalWords;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getSuggestionVoice() {
        return suggestionVoice;
    }

    public void setSuggestionVoice(String suggestionVoice) {
        this.suggestionVoice = suggestionVoice;
    }

    public WeekReportDetailBO getPlayGameReport() {
        return playGameReport;
    }

    public void setPlayGameReport(WeekReportDetailBO playGameReport) {
        this.playGameReport = playGameReport;
    }

    public WeekReportDetailBO getSpeakUpReport() {
        return speakUpReport;
    }

    public void setSpeakUpReport(WeekReportDetailBO speakUpReport) {
        this.speakUpReport = speakUpReport;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(Integer wordsCount) {
        this.wordsCount = wordsCount;
    }
}
