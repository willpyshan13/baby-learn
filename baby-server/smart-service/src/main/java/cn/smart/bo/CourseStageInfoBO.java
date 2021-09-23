package cn.smart.bo;

/**
 * 用户已购课程对象
 * @author Ye
 */
public class CourseStageInfoBO {

    /**
     * 课阶id
     */
    private Long id;

    /**
     * 阶段名称
     */
    private String stageName;

    /**
     * 阶段课时
     */
    private Double spendTime;

    /**
     * 阶段课程数
     */
    private Integer courseCount;

    /**
     * 课阶图片地址
     */
    private String imgUrl;

    /**
     * 阶段教师id
     */
    private Long teacherId;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 班主任姓名
     */
    private String headerTeacherName;

    /**
     * 微信二维码地址
     */
    private String wxQrCode;

    /**
     * 课阶班级
     */
    private Long classId;

    /**
     * 班级名称
     */
    private String className;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Double getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Double spendTime) {
        this.spendTime = spendTime;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getWxQrCode() {
        return wxQrCode;
    }

    public void setWxQrCode(String wxQrCode) {
        this.wxQrCode = wxQrCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"stageName\":\"")
                .append(stageName).append('\"');
        sb.append(",\"spendTime\":")
                .append(spendTime);
        sb.append(",\"courseCount\":")
                .append(courseCount);
        sb.append(",\"imgUrl\":\"")
                .append(imgUrl).append('\"');
        sb.append(",\"teacherId\":")
                .append(teacherId);
        sb.append(",\"teacherName\":\"")
                .append(teacherName).append('\"');
        sb.append(",\"wxQrCode\":\"")
                .append(wxQrCode).append('\"');
        sb.append(",\"classId\":")
                .append(classId);
        sb.append(",\"className\":\"")
                .append(className).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getHeaderTeacherName() {
        return headerTeacherName;
    }

    public void setHeaderTeacherName(String headerTeacherName) {
        this.headerTeacherName = headerTeacherName;
    }
}
