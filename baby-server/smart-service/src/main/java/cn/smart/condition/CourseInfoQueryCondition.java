package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-13 17:08:48
 */

public class CourseInfoQueryCondition extends QueryCondition {

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
     * 班级id
     */
    private Long classId;

    /**
     * 用户id
     */
    private Long personId;

    /**
     * 手机号码
     */
    private String mobileNumber;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 推送标签
     */
    private String pushTag;

    /**
     * 资源地址
     */
    private String resourceUrl;

    private Long[] inCourseIds;

    /**
     * 周
     */
    private Integer weeks;

    private Long groupId;

    private Long unEqGroupId;

    /**
     * 签到印章图片地址
     */
    private String stampImageUrl;
    /**
     * 卡片图片地址
     */
    private String cardImageUrl;

    /**
     * 课程id
     */
    public Long getId() {
        return id;
    }

    /**
     * 课程id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 课程名称
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 课程名称
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * 课程描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 课程描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 课程阶段
     */
    public Long getStageId() {
        return stageId;
    }

    /**
     * 课程阶段
     */
    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    /**
     * 教师id
     */
    public Long getTeacherId() {
        return teacherId;
    }

    /**
     * 教师id
     */
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 课程状态 1 ：生效 0 ：失效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 课程状态 1 ：生效 0 ：失效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 课程类型 0 : 录屏 1 : 直播
     */
    public Integer getType() {
        return type;
    }

    /**
     * 课程类型 0 : 录屏 1 : 直播
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 课程时长
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * 课程时长
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * 课程序号
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * 课程序号
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 观看权限 0 : 购买可看 1 : 登录可看 2 : 游客可看
     */
    public Integer getViewingAuthority() {
        return viewingAuthority;
    }

    /**
     * 观看权限 0 : 购买可看 1 : 登录可看 2 : 游客可看
     */
    public void setViewingAuthority(Integer viewingAuthority) {
        this.viewingAuthority = viewingAuthority;
    }

    /**
     * 更新时间(自动更新)
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间(自动更新)
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 内容类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * 内容类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 视频地址
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 视频地址
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 图片地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * tts文本
     */
    public String getTtsText() {
        return ttsText;
    }

    /**
     * tts文本
     */
    public void setTtsText(String ttsText) {
        this.ttsText = ttsText;
    }

    /**
     * 单词
     */
    public String getWord() {
        return word;
    }

    /**
     * 单词
     */
    public void setWord(String word) {
        this.word = word;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPushTag() {
        return pushTag;
    }

    public void setPushTag(String pushTag) {
        this.pushTag = pushTag;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Long[] getInCourseIds() {
        return inCourseIds;
    }

    public void setInCourseIds(Long[] inCourseIds) {
        this.inCourseIds = inCourseIds;
    }

    public Integer getWeeks() {
        return weeks;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public String getStampImageUrl() {
        return stampImageUrl;
    }

    public void setStampImageUrl(String stampImageUrl) {
        this.stampImageUrl = stampImageUrl;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUnEqGroupId() {
        return unEqGroupId;
    }

    public void setUnEqGroupId(Long unEqGroupId) {
        this.unEqGroupId = unEqGroupId;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }
}
