package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-24 14:28:59
 */

public class RoomQueryCondition extends QueryCondition {

    /**
     *
     */
    private Long id;
    /**
     * 教室名称
     */
    private String roomName;
    /**
     * 教室类型 1:一对一 2:一对四 3:一对多
     */
    private Integer roomType;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 老师密码
     */
    private String chairmanPwd;
    /**
     * 助教密码
     */
    private String assistantPwd;
    /**
     * 学员密码
     */
    private String confuserPwd;
    /**
     * 巡课密码
     */
    private String patrolPwd;
    /**
     * 是否密码 1:需要 0:不需要
     */
    private Integer passwordRequired;
    /**
     * 自动开启音视频 1:开启 0:未开启
     */
    private Integer autoOpenAv;
    /**
     * 视频分辨率 0:176x144 1:320x240
     */
    private Integer videoType;
    /**
     * 学员地址
     */
    private String confuserUrl;
    /**
     * 教师地址
     */
    private String teacherUrl;
    /**
     * 视频帧率
     */
    private Integer videoFrameRate;
    /**
     * 课堂点数
     */
    private Integer confuserNum;
    /**
     * 班级id
     */
    private Long classId;
    /**
     * 教室图片地址
     */
    private String logoUrl;

    /**
     * .h264硬件编码 0 : 否(默认) 2:是
     */
    private Integer vcodec;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间(自动维护)
     */
    private Date updateTime;

    /**
     *
     */
    public Long getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 教室名称
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * 教室名称
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * 教室类型 0:一对一 2: 3:一对多
     */
    public Integer getRoomType() {
        return roomType;
    }

    /**
     * 教室类型 0:一对一 2: 3:一对多
     */
    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    /**
     * 开始时间
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     */
    public void setStartTime(String startTime) {
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
     * 老师密码
     */
    public String getChairmanPwd() {
        return chairmanPwd;
    }

    /**
     * 老师密码
     */
    public void setChairmanPwd(String chairmanPwd) {
        this.chairmanPwd = chairmanPwd;
    }

    /**
     * 助教密码
     */
    public String getAssistantPwd() {
        return assistantPwd;
    }

    /**
     * 助教密码
     */
    public void setAssistantPwd(String assistantPwd) {
        this.assistantPwd = assistantPwd;
    }

    /**
     * 学员密码
     */
    public String getConfuserPwd() {
        return confuserPwd;
    }

    /**
     * 学员密码
     */
    public void setConfuserPwd(String confuserPwd) {
        this.confuserPwd = confuserPwd;
    }

    /**
     * 巡课密码
     */
    public String getPatrolPwd() {
        return patrolPwd;
    }

    /**
     * 巡课密码
     */
    public void setPatrolPwd(String patrolPwd) {
        this.patrolPwd = patrolPwd;
    }

    /**
     * 是否密码 1:需要 0:不需要
     */
    public Integer getPasswordRequired() {
        return passwordRequired;
    }

    /**
     * 是否密码 1:需要 0:不需要
     */
    public void setPasswordRequired(Integer passwordRequired) {
        this.passwordRequired = passwordRequired;
    }

    /**
     * 自动开启音视频 1:开启 0:未开启
     */
    public Integer getAutoOpenAv() {
        return autoOpenAv;
    }

    /**
     * 自动开启音视频 1:开启 0:未开启
     */
    public void setAutoOpenAv(Integer autoOpenAv) {
        this.autoOpenAv = autoOpenAv;
    }

    /**
     * 视频分辨率 0:176x144 1:320x240
     */
    public Integer getVideoType() {
        return videoType;
    }

    /**
     * 视频分辨率 0:176x144 1:320x240
     */
    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    /**
     * 学员地址
     */
    public String getConfuserUrl() {
        return confuserUrl;
    }

    /**
     * 学员地址
     */
    public void setConfuserUrl(String confuserUrl) {
        this.confuserUrl = confuserUrl;
    }

    /**
     * 教师地址
     */
    public String getTeacherUrl() {
        return teacherUrl;
    }

    /**
     * 教师地址
     */
    public void setTeacherUrl(String teacherUrl) {
        this.teacherUrl = teacherUrl;
    }

    /**
     * 视频帧率
     */
    public Integer getVideoFrameRate() {
        return videoFrameRate;
    }

    /**
     * 视频帧率
     */
    public void setVideoFrameRate(Integer videoFrameRate) {
        this.videoFrameRate = videoFrameRate;
    }

    /**
     * 课堂点数
     */
    public Integer getConfuserNum() {
        return confuserNum;
    }

    /**
     * 课堂点数
     */
    public void setConfuserNum(Integer confuserNum) {
        this.confuserNum = confuserNum;
    }

    /**
     * 班级id
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * 班级id
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * 教室图片地址
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * 教室图片地址
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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
     * 更新时间(自动维护)
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间(自动维护)
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVcodec() {
        return vcodec;
    }

    public void setVcodec(Integer vcodec) {
        this.vcodec = vcodec;
    }
}
