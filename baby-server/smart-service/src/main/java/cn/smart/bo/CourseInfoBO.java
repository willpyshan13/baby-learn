package cn.smart.bo;

import java.util.Date;

public class CourseInfoBO {



    /**
     * 课程id
     */
    private Long id;


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
     * 是否开放
     */
    private String opened;

    /**
     * 是否已学 0 否 1 是
     */
    private int learned;

    /**
     * 游戏是否已玩 0 否 1 是
     */
    private int played;

    private Date learnedTime;

    private String resourceUrl;

    private Long groupId;

    /**
     * Get 课程id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set 课程id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Get 课程名称
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set 课程名称
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    /**
     * Get 课程描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set 课程描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Get 课程阶段
     */
    public Long getStageId() {
        return stageId;
    }

    /**
     * Set 课程阶段
     */
    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }
    /**
     * Get 教师id
     */
    public Long getTeacherId() {
        return teacherId;
    }

    /**
     * Set 教师id
     */
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
    /**
     * Get 课程状态 1 ：生效 0 ：失效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Set 课程状态 1 ：生效 0 ：失效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * Get 课程类型 0 : 录屏 1 : 直播
     */
    public Integer getType() {
        return type;
    }

    /**
     * Set 课程类型 0 : 录屏 1 : 直播
     */
    public void setType(Integer type) {
        this.type = type;
    }
    /**
     * Get 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Set 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    /**
     * Get 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Set 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    /**
     * Get 课程时长
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * Set 课程时长
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }
    /**
     * Get 课程序号
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * Set 课程序号
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    /**
     * Get 观看权限 0 : 购买可看 1 : 登录可看 2 : 游客可看
     */
    public Integer getViewingAuthority() {
        return viewingAuthority;
    }

    /**
     * Set 观看权限 0 : 购买可看 1 : 登录可看 2 : 游客可看
     */
    public void setViewingAuthority(Integer viewingAuthority) {
        this.viewingAuthority = viewingAuthority;
    }
    /**
     * Get 更新时间(自动更新)
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Set 更新时间(自动更新)
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * Get 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Set 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * Get 内容类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set 内容类别
     */
    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * Get 视频地址
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * Set 视频地址
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    /**
     * Get 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Set 图片地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    /**
     * Get tts文本
     */
    public String getTtsText() {
        return ttsText;
    }

    /**
     * Set tts文本
     */
    public void setTtsText(String ttsText) {
        this.ttsText = ttsText;
    }
    /**
     * Get 单词
     */
    public String getWord() {
        return word;
    }

    /**
     * Set 单词
     */
    public void setWord(String word) {
        this.word = word;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getOpened() {
        return opened;
    }

    public void setOpened(String opened) {
        this.opened = opened;
    }

    public int getLearned() {
        return learned;
    }

    public void setLearned(int learned) {
        this.learned = learned;
    }

    public Date getLearnedTime() {
        return learnedTime;
    }

    public void setLearnedTime(Date learnedTime) {
        this.learnedTime = learnedTime;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
