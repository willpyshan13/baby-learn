package cn.smart.bo;

import java.util.Date;

public class LearnRecordsBO {

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
     * 课程名称
     */
    private String courseName;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 是否开放 0:否 1:开放'
     */
    private Integer status;

    /**
     * 是否已学 0 否 1 是
     */
    private int learned;

    private Date createTime;

    /**
     * Get 课程id
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * Set 课程id
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    /**
     * Get 用户id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * Set 用户id
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    /**
     * Get 类型 1:课程 2:游戏
     */
    public Integer getType() {
        return type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getLearned() {
        return learned;
    }

    public void setLearned(int learned) {
        this.learned = learned;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
