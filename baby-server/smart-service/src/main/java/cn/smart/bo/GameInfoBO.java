package cn.smart.bo;

import java.util.Date;
import java.util.List;

/**
 * @author Ye
 */
public class GameInfoBO {

    private Long infoId;
    private Long type;
    private String gameInfo = "";
    private String gameName = "";
    private String imagePath = "";
    private String mp3Path = "";
    private Date createTime;
    private String updateTime;
    private Integer score;
    private Integer number; // 糖果数
    /**
     * 状态
     */
    private int status;
    private List<LearnGameDetailInfoBO> learnGameDetailEntities;

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(String gameInfo) {
        this.gameInfo = gameInfo;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMp3Path() {
        return mp3Path;
    }

    public void setMp3Path(String mp3Path) {
        this.mp3Path = mp3Path;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<LearnGameDetailInfoBO> getLearnGameDetailEntities() {
        return learnGameDetailEntities;
    }

    public void setLearnGameDetailEntities(List<LearnGameDetailInfoBO> learnGameDetailEntities) {
        this.learnGameDetailEntities = learnGameDetailEntities;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
