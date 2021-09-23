package cn.smart.bo;

import java.util.List;

public class CourseInfoDetailBO {

    /**
     * 课程内容信息
     */
    private List<CourseContentBO> courseContentList;

    private CourseInfoBO courseInfo;

    public List<CourseContentBO> getCourseContentList() {
        return courseContentList;
    }

    public void setCourseContentList(List<CourseContentBO> courseContentList) {
        this.courseContentList = courseContentList;
    }

    public CourseInfoBO getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfoBO courseInfo) {
        this.courseInfo = courseInfo;
    }
}
