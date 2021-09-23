package cn.smart.bo;

import java.util.List;

public class ClockCourseStageBO {

    /**
     * 课阶id
     */
    private Long stageId;

    /**
     * 阶段名称
     */
    private String stageName;

    /**
     * 当前开课课程
     */
    private Long currentOpenCourseId;

    /**
     * 当前课程是否签到
     */
    private Boolean clockIn;

    private List<ClockGroupBO> groupList;

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public List<ClockGroupBO> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<ClockGroupBO> groupList) {
        this.groupList = groupList;
    }

    public Long getCurrentOpenCourseId() {
        return currentOpenCourseId;
    }

    public void setCurrentOpenCourseId(Long currentOpenCourseId) {
        this.currentOpenCourseId = currentOpenCourseId;
    }

    public Boolean getClockIn() {
        return clockIn;
    }

    public void setClockIn(Boolean clockIn) {
        this.clockIn = clockIn;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }
}
