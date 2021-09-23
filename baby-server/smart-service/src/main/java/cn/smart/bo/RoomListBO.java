package cn.smart.bo;

public class RoomListBO {

    /**
     * 教室id
     */
    private Long id;
    /**
     * 教室名称
     */
    private String roomName;

    /**
     * 开课时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 教室图片
     */
    private String logoUrl;

    /**
     * 教室状态 1: 录播 2: 正在直播 3: 未开播
     */
    private Integer status;

    /**
     * 录播视频地址
     */
    private String videoUrl;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
