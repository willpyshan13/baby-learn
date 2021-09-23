package cn.smart.bo;

public class VideoUrlBO {

    private String videoThumbmailUrl;
    private Integer videoUrlIndex;
    private String videoTitle;
    private String videoPlayUrl;

    public String getVideoThumbmailUrl() {
        return videoThumbmailUrl;
    }

    public void setVideoThumbmailUrl(String videoThumbmailUrl) {
        this.videoThumbmailUrl = videoThumbmailUrl;
    }

    public Integer getVideoUrlIndex() {
        return videoUrlIndex;
    }

    public void setVideoUrlIndex(Integer videoUrlIndex) {
        this.videoUrlIndex = videoUrlIndex;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoPlayUrl() {
        return videoPlayUrl;
    }

    public void setVideoPlayUrl(String videoPlayUrl) {
        this.videoPlayUrl = videoPlayUrl;
    }
}
