package cn.smart.bo;

import cn.smart.entity.VideoDO;

public class VideoBO {

    private Long id;

    private String url;

    private String name;

    private Long[] videoUrlIds;

    private Long videoId;

    private VideoDO video;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long[] getVideoUrlIds() {
        return videoUrlIds;
    }

    public void setVideoUrlIds(Long[] videoUrlIds) {
        this.videoUrlIds = videoUrlIds;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public VideoDO getVideo() {
        return video;
    }

    public void setVideo(VideoDO video) {
        this.video = video;
    }

}
