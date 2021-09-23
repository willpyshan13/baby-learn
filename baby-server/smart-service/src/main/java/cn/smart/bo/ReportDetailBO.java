package cn.smart.bo;

/**
 * 课程报告项目详情
 */
public class ReportDetailBO {

    /**
     * 大声说id / 单词辨析选项id
     */
    private Long id;

    /**
     * 课程项目名称
     */
    private String name;

    /**
     * 图片资源地址
     */
    private String imageUrl;

    /**
     * 音频资源地址
     */
    private String audioUrl;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 糖果数
     */
    private Integer number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ReportDetailBO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                ", score=" + score +
                ", number=" + number +
                '}';
    }
}
