package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-6-15 17:55:05
 */

public class UsageLogQueryCondition extends QueryCondition {

    /**
     *
     */
    private Integer id;
    /**
     * 记录名称
     */
    private String name;
    /**
     * 记录类型
     */
    private Integer type;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 时长
     */
    private Long duration;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 事件类型
     */
    private Integer eventType;

    /**
     * 点击次数
     */
    private Long number;


    /**
     *
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 记录名称
     */
    public String getName() {
        return name;
    }

    /**
     * 记录名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 记录类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 记录类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 时长
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * 时长
     */
    public void setDuration(Long duration) {
        this.duration = duration;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "UsageLogQueryCondition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", userId=" + userId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", createTime=" + createTime +
                ", userName='" + userName + '\'' +
                ", eventType=" + eventType +
                ", number=" + number +
                '}';
    }
}
