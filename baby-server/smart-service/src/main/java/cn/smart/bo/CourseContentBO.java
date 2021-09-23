package cn.smart.bo;

import cn.smart.entity.CourseContentDO;

public class CourseContentBO {

    private CourseContentDO courseContent;

    public CourseContentBO(CourseContentDO courseContent) {
        this.courseContent = courseContent;
    }

    public CourseContentDO getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(CourseContentDO courseContent) {
        this.courseContent = courseContent;
    }
}
