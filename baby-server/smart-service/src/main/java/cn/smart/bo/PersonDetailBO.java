package cn.smart.bo;

import java.util.Set;

/**
 * 用户详细信息
 * @author Ye
 */
public class PersonDetailBO {

    /**
     * ID
     */
    private Long personId;
    /**
     * 手机号码
     */
    private String mobileNumber;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 生日
     */
    private String birthday;
    /**
     * 头像地址
     */
    private String photoUrl;
    /**
     * 性别 1:男 0:女
     */
    private Boolean gender;
    /**
     * 糖果积分数
     */
    private Integer candyPoints;

    /**
     * 当天是否打卡 根据当天班级是否开课 开课判断是否 未开课默认为true
     */
    private Boolean clockIn;

    /**
     * 当天是否学习 根据当天班级是否开课 开课判断是否 未开课默认为true
     */
    private Boolean completeTheCourse;

    /**
     * 用户标签
     */
    private Set<String> pushTags;

    /**
     * 用户别名
     */
    private String pushAlias;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getCandyPoints() {
        return candyPoints;
    }

    public void setCandyPoints(Integer candyPoints) {
        this.candyPoints = candyPoints;
    }

    public Boolean getClockIn() {
        return clockIn;
    }

    public void setClockIn(Boolean clockIn) {
        this.clockIn = clockIn;
    }

    public Set<String> getPushTags() {
        return pushTags;
    }

    public void setPushTags(Set<String> pushTags) {
        this.pushTags = pushTags;
    }

    public String getPushAlias() {
        return pushAlias;
    }

    public void setPushAlias(String pushAlias) {
        this.pushAlias = pushAlias;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"personId\":")
                .append(personId);
        sb.append(",\"mobileNumber\":\"")
                .append(mobileNumber).append('\"');
        sb.append(",\"nickName\":\"")
                .append(nickName).append('\"');
        sb.append(",\"birthday\":\"")
                .append(birthday).append('\"');
        sb.append(",\"photoUrl\":\"")
                .append(photoUrl).append('\"');
        sb.append(",\"gender\":")
                .append(gender);
        sb.append(",\"candyPoints\":")
                .append(candyPoints);
        sb.append(",\"clockIn\":")
                .append(clockIn);
        sb.append(",\"pushTags\":")
                .append(pushTags);
        sb.append(",\"pushAlias\":\"")
                .append(pushAlias).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public Boolean getCompleteTheCourse() {
        return completeTheCourse;
    }

    public void setCompleteTheCourse(Boolean completeTheCourse) {
        this.completeTheCourse = completeTheCourse;
    }
}
