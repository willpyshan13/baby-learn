package cn.smart.bo;

public class ClassInfoBO {

    private Long classId;

    private Long[] classCourseIds;

    private Long[] courseIds;

    private Long[] studentIds;

    private Long stageId;

    private Long groupId;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long[] getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(Long[] courseIds) {
        this.courseIds = courseIds;
    }

    public Long[] getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(Long[] studentIds) {
        this.studentIds = studentIds;
    }

    public Long[] getClassCourseIds() {
        return classCourseIds;
    }

    public void setClassCourseIds(Long[] classCourseIds) {
        this.classCourseIds = classCourseIds;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
