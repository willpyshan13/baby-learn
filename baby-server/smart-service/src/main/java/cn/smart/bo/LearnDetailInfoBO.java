package cn.smart.bo;

import java.util.Date;

public class LearnDetailInfoBO {

    private Long Id;
    private String path;
    private String type;
    private String word;
    private String category;
    private String isLesson;
    private String ttsText;
    private Long learnId;
    private String imagePath;
    private Date createTime;
    private String updateTime;
    private Boolean isLearn;
    private Boolean played;
    private Integer playGameScore;
    private Integer speakUpScore;
    private String resourceUrl;
    private Boolean spoke;
    private Long groupId;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsLesson() {
        return isLesson;
    }

    public void setIsLesson(String isLesson) {
        this.isLesson = isLesson;
    }

    public String getTtsText() {
        return ttsText;
    }

    public void setTtsText(String ttsText) {
        this.ttsText = ttsText;
    }

    public Long getLearnId() {
        return learnId;
    }

    public void setLearnId(Long learnId) {
        this.learnId = learnId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public boolean getIsLearn() {
        return isLearn;
    }

    public void setLearn(Boolean learn) {
        isLearn = learn;
    }

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public Integer getSpeakUpScore() {
        return speakUpScore;
    }

    public void setSpeakUpScore(Integer speakUpScore) {
        this.speakUpScore = speakUpScore;
    }

    public Integer getPlayGameScore() {
        return playGameScore;
    }

    public void setPlayGameScore(Integer playGameScore) {
        this.playGameScore = playGameScore;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setSpoke(boolean spoke) {
        this.spoke = spoke;
    }

    public boolean isSpoke() {
        return spoke;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
