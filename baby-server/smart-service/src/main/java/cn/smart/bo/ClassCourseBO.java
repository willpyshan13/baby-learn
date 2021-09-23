package cn.smart.bo;

import java.util.Date;

public class ClassCourseBO {

    /**
     * 课程id
     */
    private Long id;

    /**
     * 班级-课程 id 主键
     */
    private Long classCourseId;

    /**
     * 课程名称
     */
    private String courseName;


    /**
     * 课程描述
     */
    private String description;


    /**
     * 课程阶段
     */
    private Long stageId;


    /**
     * 教师id
     */
    private Long teacherId;


    /**
     * 课程状态 1 ：生效 0 ：失效
     */
    private Integer status;


    /**
     * 课程类型 0 : 录屏 1 : 直播
     */
    private Integer type;


    /**
     * 开始时间
     */
    private Date startTime;


    /**
     * 结束时间
     */
    private Date endTime;


    /**
     * 课程时长
     */
    private Long duration;


    /**
     * 课程序号
     */
    private Integer serialNumber;


    /**
     * 观看权限 0 : 购买可看 1 : 登录可看 2 : 游客可看
     */
    private Integer viewingAuthority;


    /**
     * 更新时间(自动更新)
     */
    private Date updateTime;


    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 内容类别
     */
    private String category;


    /**
     * 视频地址
     */
    private String videoUrl;


    /**
     * 图片地址
     */
    private String imageUrl;


    /**
     * tts文本
     */
    private String ttsText;


    /**
     * 单词
     */
    private String word;

    /**
     * 课阶名称
     */
    private String stageName;

    /**
     * 是否开放 0:否 1:开放
     */
    private Integer opened;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getViewingAuthority() {
        return viewingAuthority;
    }

    public void setViewingAuthority(Integer viewingAuthority) {
        this.viewingAuthority = viewingAuthority;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTtsText() {
        return ttsText;
    }

    public void setTtsText(String ttsText) {
        this.ttsText = ttsText;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Integer getOpened() {
        return opened;
    }

    public void setOpened(Integer opened) {
        this.opened = opened;
    }

    public Long getClassCourseId() {
        return classCourseId;
    }

    public void setClassCourseId(Long classCourseId) {
        this.classCourseId = classCourseId;
    }
}
