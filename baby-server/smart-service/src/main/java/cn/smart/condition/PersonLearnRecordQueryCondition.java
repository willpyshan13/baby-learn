package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-6-22 16:34:33
 */

public class PersonLearnRecordQueryCondition extends QueryCondition {

    public interface LEARN_TYPE {
        int WATCH_VIDEO       = 1;
        int PLAY_GAME   = 2;
        int SPEAK_UP    = 3;
    }

    /**
     *
     */
    private Long recordId;
    /**
     * 课程id
     */
    private Long courseId;
    /**
     * 用户id
     */
    private Long personId;
    /**
     * 类型 1:课程 2:游戏
     */
    private Integer type;
    /**
     * 完成结果
     */
    private String resultData;
    /**
     * 总分(星星数)
     */
    private Integer score;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 学习记录-结束时间
     */
    private String startTime;
    /**
     * 学习记录-开始时间
     */
    private String endTime;

    private Long[] inCourseIds;

    /**
     *
     */
    public Long getRecordId() {
        return recordId;
    }

    /**
     *
     */
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    /**
     * 课程id
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * 课程id
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * 用户id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * 用户id
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * 类型 1:课程 2:游戏
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型 1:课程 2:游戏
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 完成结果
     */
    public String getResultData() {
        return resultData;
    }

    /**
     * 完成结果
     */
    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    /**
     * 总分(星星数)
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 总分(星星数)
     */
    public void setScore(Integer score) {
        this.score = score;
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
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Long[] getInCourseIds() {
        return inCourseIds;
    }

    public void setInCourseIds(Long[] inCourseIds) {
        this.inCourseIds = inCourseIds;
    }
}
