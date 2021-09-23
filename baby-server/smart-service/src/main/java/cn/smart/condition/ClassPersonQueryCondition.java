package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-11 14:41:37
 */

public class ClassPersonQueryCondition extends QueryCondition {

    /**
     *
     */
    private Integer id;
    /**
     * 班级id
     */
    private Long classId;
    /**
     * 用户id
     */
    private Long personId;
    /**
     *
     */
    private Date createTime;
    /**
     * 更新时间(自动维护)
     */
    private Date updateTime;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 存在的学生id
     */
    private Long[] existsPersonId;

    /**
     * 班级类型 1:一对一 2:一对四 3:一对多
     */
    private Integer type;

    /**
     *
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Integer id) {
        this.id = id;
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
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
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

    @Override
    public String toString() {
        return "ClassPersonQueryCondition{" +
                "id=" + id +
                ", classId=" + classId +
                ", personId=" + personId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long[] getExistsPersonId() {
        return existsPersonId;
    }

    public void setExistsPersonId(Long[] existsPersonId) {
        this.existsPersonId = existsPersonId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
