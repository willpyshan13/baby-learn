package cn.smart.bo;

public class WeekReportWordBO {

    public WeekReportWordBO() {

    }

    public WeekReportWordBO(Long id, Integer type, String word, String audioUrl, String imageUrl) {
        this.id = id;
        this.type = type;
        this.word = word;
        this.audioUrl = audioUrl;
        this.imageUrl = imageUrl;
    }

    private Long id;

    private Integer type;

    private String word;

    private String audioUrl;

    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"type\":")
                .append(type);
        sb.append(",\"word\":\"")
                .append(word).append('\"');
        sb.append(",\"audioUrl\":\"")
                .append(audioUrl).append('\"');
        sb.append(",\"imageUrl\":\"")
                .append(imageUrl).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
