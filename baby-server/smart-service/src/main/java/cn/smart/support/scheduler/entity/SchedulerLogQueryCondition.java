package cn.smart.support.scheduler.entity;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
 * @author Ye
 */
public class SchedulerLogQueryCondition extends QueryCondition {

    private Long id;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 状态
     */
    private String status;
    /**
     * hose
     */
    private String serverHost;
    /**
     * duid
     */
    private String serverDuid;
    /**
     * fireInfo
     */
    private String fireInfo;

    private Long targetId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getServerDuid() {
        return serverDuid;
    }

    public void setServerDuid(String serverDuid) {
        this.serverDuid = serverDuid;
    }

    public String getFireInfo() {
        return fireInfo;
    }

    public void setFireInfo(String fireInfo) {
        this.fireInfo = fireInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"groupName\":\"")
                .append(groupName).append('\"');
        sb.append(",\"taskName\":\"")
                .append(taskName).append('\"');
        sb.append(",\"startTime\":\"")
                .append(startTime).append('\"');
        sb.append(",\"endTime\":\"")
                .append(endTime).append('\"');
        sb.append(",\"status\":\"")
                .append(status).append('\"');
        sb.append(",\"serverHost\":\"")
                .append(serverHost).append('\"');
        sb.append(",\"serverDuid\":\"")
                .append(serverDuid).append('\"');
        sb.append(",\"fireInfo\":\"")
                .append(fireInfo).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}
