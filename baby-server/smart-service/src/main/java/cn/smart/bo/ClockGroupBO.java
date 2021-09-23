package cn.smart.bo;

import java.util.List;

public class ClockGroupBO {

    private Long groupId;

    private String groupName;

    private Integer groupCode;

    private Integer currentPoints;

    private Boolean currentGroup;

    private List<ClockGroupCourseBO> courseList;

    private List<ClockGroupGoodsBO> goodsList;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(Integer groupCode) {
        this.groupCode = groupCode;
    }

    public Integer getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(Integer currentPoints) {
        this.currentPoints = currentPoints;
    }

    public List<ClockGroupCourseBO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<ClockGroupCourseBO> courseList) {
        this.courseList = courseList;
    }

    public List<ClockGroupGoodsBO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ClockGroupGoodsBO> goodsList) {
        this.goodsList = goodsList;
    }

    public Boolean getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(Boolean currentGroup) {
        this.currentGroup = currentGroup;
    }
}
