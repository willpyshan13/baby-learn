package cn.smart.bo;

import cn.smart.entity.CourseStageDO;
import cn.smart.entity.TeacherDO;

import java.util.List;

public class CourseStageDetailBO {

    /**
     * 课阶信息
     */
    private CourseStageDO courseStage;

    /**
     * 教师信息
     */
    private TeacherDO teacher;

    /**
     * 课程信息
     */
    private List<CourseInfoDetailBO> courseInfoList;

    /**
     * 是否购买课阶
     */
    private Boolean boughtCourseStage;

    public CourseStageDO getCourseStage() {
        return courseStage;
    }

    public void setCourseStage(CourseStageDO courseStage) {
        this.courseStage = courseStage;
    }

    public TeacherDO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDO teacher) {
        this.teacher = teacher;
    }

    public List<CourseInfoDetailBO> getCourseInfoList() {
        return courseInfoList;
    }

    public void setCourseInfoList(List<CourseInfoDetailBO> courseInfoList) {
        this.courseInfoList = courseInfoList;
    }

    public Boolean getBoughtCourseStage() {
        return boughtCourseStage;
    }

    public void setBoughtCourseStage(Boolean boughtCourseStage) {
        this.boughtCourseStage = boughtCourseStage;
    }
}
