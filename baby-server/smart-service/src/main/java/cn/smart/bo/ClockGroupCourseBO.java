package cn.smart.bo;

public class ClockGroupCourseBO {

    private Long courseId;

    private String stampImageUrl;

    private String cardImageUrl;

    private String courseName;

    private Boolean clockIn;

    private Integer star;

    private Boolean open;

    private String openDate;

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

    public String getStampImageUrl() {
        return stampImageUrl;
    }

    public void setStampImageUrl(String stampImageUrl) {
        this.stampImageUrl = stampImageUrl;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Boolean getClockIn() {
        return clockIn;
    }

    public void setClockIn(Boolean clockIn) {
        this.clockIn = clockIn;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }
}
