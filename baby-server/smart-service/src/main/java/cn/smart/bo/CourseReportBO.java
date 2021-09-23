package cn.smart.bo;

/**
 * 课程报告
 */
public class CourseReportBO {

    /**
     * 用户id
     */
    private Long personId;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 用户名称
     */
    private String personName;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 总分
     */
    private Integer totalScore;

    /**
     * 单词辨析结果
     */
    private ReportBO playGameReport;

    /**
     * 大声说结果
     */
    private ReportBO speakUpReport;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public ReportBO getPlayGameReport() {
        return playGameReport;
    }

    public void setPlayGameReport(ReportBO playGameReport) {
        this.playGameReport = playGameReport;
    }

    public ReportBO getSpeakUpReport() {
        return speakUpReport;
    }

    public void setSpeakUpReport(ReportBO speakUpReport) {
        this.speakUpReport = speakUpReport;
    }

    @Override
    public String toString() {
        return "CourseReportBO{" +
                "personId=" + personId +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", personName='" + personName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", totalScore=" + totalScore +
                ", playGameReport=" + playGameReport +
                ", speakUpReport=" + speakUpReport +
                '}';
    }
}
