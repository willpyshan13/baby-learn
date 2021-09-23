package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-12 14:54:07
 */

public class CourseStageQueryCondition extends QueryCondition {

    /**
     * 课阶id
     */
    private Long id;

    /**
     * 班级id
     */
    private Long classId;

    /**
     * 用户id 已购买的课程
     */
    private Long existsPersonId;
    /**
     * 用户id 未买的课程
     */
    private Long notExistsPersonId;
    /**
     * 阶段名称
     */
    private String stageName;
    /**
     * 课阶等级
     */
    private Integer level;
    /**
     * 课阶描述
     */
    private String description;
    /**
     * 阶段课时
     */
    private Double spendTime;
    /**
     * 阶段课程数
     */
    private Integer courseCount;
    /**
     * 原价
     */
    private Double originPrice;
    /**
     * 优惠价
     */
    private Double preferentialPrice;
    /**
     * 已售数量
     */
    private Integer soldCount;
    /**
     * 课阶图片地址
     */
    private String imgUrl;
    /**
     * 阶段教师id
     */
    private Long teacherId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间(自动维护)
     */
    private Date updateTime;

    /**
     * 是否出售 0 否 1 是
     */
    private Integer selling;

    /**
     * 课阶id
     */
    public Long getId() {
        return id;
    }

    /**
     * 课阶id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 阶段名称
     */
    public String getStageName() {
        return stageName;
    }

    /**
     * 阶段名称
     */
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    /**
     * 课阶等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 课阶等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 课阶描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 课阶描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 阶段课时
     */
    public Double getSpendTime() {
        return spendTime;
    }

    /**
     * 阶段课时
     */
    public void setSpendTime(Double spendTime) {
        this.spendTime = spendTime;
    }

    /**
     * 阶段课程数
     */
    public Integer getCourseCount() {
        return courseCount;
    }

    /**
     * 阶段课程数
     */
    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    /**
     * 原价
     */
    public Double getOriginPrice() {
        return originPrice;
    }

    /**
     * 原价
     */
    public void setOriginPrice(Double originPrice) {
        this.originPrice = originPrice;
    }

    /**
     * 优惠价
     */
    public Double getPreferentialPrice() {
        return preferentialPrice;
    }

    /**
     * 优惠价
     */
    public void setPreferentialPrice(Double preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    /**
     * 已售数量
     */
    public Integer getSoldCount() {
        return soldCount;
    }

    /**
     * 已售数量
     */
    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }

    /**
     * 课阶图片地址
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 课阶图片地址
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 阶段教师id
     */
    public Long getTeacherId() {
        return teacherId;
    }

    /**
     * 阶段教师id
     */
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getExistsPersonId() {
        return existsPersonId;
    }

    public void setExistsPersonId(Long existsPersonId) {
        this.existsPersonId = existsPersonId;
    }

    public Long getNotExistsPersonId() {
        return notExistsPersonId;
    }

    public void setNotExistsPersonId(Long notExistsPersonId) {
        this.notExistsPersonId = notExistsPersonId;
    }

    public Integer getSelling() {
        return selling;
    }

    public void setSelling(Integer selling) {
        this.selling = selling;
    }
}
