package cn.smart.bo;

import java.util.List;

public class VideoDetailBO {

    private Long videoId;
    private String videoTitle;
    private String videoPreviewPicUrl;
    private String videoDirector;
    private String videoActor;
    private String videoRegion;
    private String videoYear;
    private String videoDesc;
    private String videoPlayUrl;
    private Boolean isCollect;
    private Boolean isPraised;
    private Integer praise;
    private List<VideoUrlBO> list;

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoPreviewPicUrl() {
        return videoPreviewPicUrl;
    }

    public void setVideoPreviewPicUrl(String videoPreviewPicUrl) {
        this.videoPreviewPicUrl = videoPreviewPicUrl;
    }

    public String getVideoDirector() {
        return videoDirector;
    }

    public void setVideoDirector(String videoDirector) {
        this.videoDirector = videoDirector;
    }

    public String getVideoActor() {
        return videoActor;
    }

    public void setVideoActor(String videoActor) {
        this.videoActor = videoActor;
    }

    public String getVideoRegion() {
        return videoRegion;
    }

    public void setVideoRegion(String videoRegion) {
        this.videoRegion = videoRegion;
    }

    public String getVideoYear() {
        return videoYear;
    }

    public void setVideoYear(String videoYear) {
        this.videoYear = videoYear;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoPlayUrl() {
        return videoPlayUrl;
    }

    public void setVideoPlayUrl(String videoPlayUrl) {
        this.videoPlayUrl = videoPlayUrl;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public boolean isPraised() {
        return isPraised;
    }

    public void setPraised(boolean praised) {
        isPraised = praised;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public List<VideoUrlBO> getList() {
        return list;
    }

    public void setList(List<VideoUrlBO> list) {
        this.list = list;
    }
}
