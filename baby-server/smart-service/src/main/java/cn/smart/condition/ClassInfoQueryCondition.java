package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-11 14:41:27
 */

public class ClassInfoQueryCondition extends QueryCondition {

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public interface CLASS_TYPE {
        int ONE_TO_ONE  = 1;
        int ONE_TO_FOUR = 2;
        int ONE_TO_MANY = 3;
    }

    /**
     *
     */
    private Long id;
    /**
     * 班级介绍
     */
    private String classInfo;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 班级状态 1:生效 0:失效
     */
    private Integer status;
    /**
     * 班级人数
     */
    private Integer count;
    /**
     * 课阶id
     */
    private Long courseStageId;
    /**
     * 班级教师
     */
    private Long teacherId;
    /**
     * 班主任
     */
    private Long headerTeacher;

    /**
     * 更新时间(自动维护)
     */
    private Date updateTime;

    /**
     * 班级学生id集合 eg: 1,2,3,4
     */
    private String existPersonId;

    private Long personId;

    /**
     * 教室类型 1:一对一 2:一对四 3:一对多
     */
    private Integer type;

    /**
     * 是否默认一对多教室
     */
    private Boolean oneToMany;
    /**
     * 备注
     */
    private String remark;

    private String openTime;

    private Long courseId;

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
     * 班级介绍
     */
    public String getClassInfo() {
        return classInfo;
    }

    /**
     * 班级介绍
     */
    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }

    /**
     * 班级名称
     */
    public String getClassName() {
        return className;
    }

    /**
     * 班级名称
     */
    public void setClassName(String className) {
        this.className = className;
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
     * 班级状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 班级状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 班级人数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 班级人数
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 课阶id
     */
    public Long getCourseStageId() {
        return courseStageId;
    }

    /**
     * 课阶id
     */
    public void setCourseStageId(Long courseStageId) {
        this.courseStageId = courseStageId;
    }

    /**
     * 班级教师
     */
    public Long getTeacherId() {
        return teacherId;
    }

    /**
     * 班级教师
     */
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public Long getHeaderTeacher() {
        return headerTeacher;
    }

    public void setHeaderTeacher(Long headerTeacher) {
        this.headerTeacher = headerTeacher;
    }

    public String getExistPersonId() {
        return existPersonId;
    }

    public void setExistPersonId(String existPersonId) {
        this.existPersonId = existPersonId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getOneToMany() {
        return oneToMany;
    }

    public void setOneToMany(Boolean oneToMany) {
        this.oneToMany = oneToMany;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
