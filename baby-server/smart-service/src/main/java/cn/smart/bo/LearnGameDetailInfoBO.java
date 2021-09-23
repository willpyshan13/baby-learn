package cn.smart.bo;

import java.util.Date;

public class LearnGameDetailInfoBO {

    private Long detailId;
    private Long gameType;
    private String word;
    private String isTrue;
    private String ttsText;
    private Long gameId;
    private String imagePath;
    private Date createTime;
    private String updateTime;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getGameType() {
        return gameType;
    }

    public void setGameType(Long gameType) {
        this.gameType = gameType;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(String isTrue) {
        this.isTrue = isTrue;
    }

    public String getTtsText() {
        return ttsText;
    }

    public void setTtsText(String ttsText) {
        this.ttsText = ttsText;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
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
}
