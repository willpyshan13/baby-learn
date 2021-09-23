package cn.smart.support.scheduler.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 调度任务日志
 *
 * @author Admin
 */
public class SchedulerLog extends BaseEntity implements Serializable {

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

    /**
     * 执行对象id (roomId, classId)
     */
    private Long targetId;

    /**
     * Instantiates a new Scheduler log.
     */
    public SchedulerLog() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets group name.
     *
     * @return the group name
     */
    public String getGroupName() {
        return this.groupName;
    }

    /**
     * Sets group name.
     *
     * @param groupName the group name
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * Gets task name.
     *
     * @return the task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Sets task name.
     *
     * @param taskName the task name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * Gets server host.
     *
     * @return the server host
     */
    public String getServerHost() {
        return this.serverHost;
    }

    /**
     * Sets server host.
     *
     * @param serverHost the server host
     */
    public void setServerHost(String serverHost) {
        this.serverHost = serverHost == null ? null : serverHost.trim();
    }

    /**
     * Gets server duid.
     *
     * @return the server duid
     */
    public String getServerDuid() {
        return this.serverDuid;
    }

    /**
     * Sets server duid.
     *
     * @param serverDuid the server duid
     */
    public void setServerDuid(String serverDuid) {
        this.serverDuid = serverDuid == null ? null : serverDuid.trim();
    }

    /**
     * Gets fire info.
     *
     * @return the fire info
     */
    public String getFireInfo() {
        return this.fireInfo;
    }

    /**
     * Sets fire info.
     *
     * @param fireInfo the fire info
     */
    public void setFireInfo(String fireInfo) {
        this.fireInfo = fireInfo == null ? null : fireInfo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(this.hashCode());
        sb.append(", groupName=").append(this.groupName);
        sb.append(", taskName=").append(this.taskName);
        sb.append(", startTime=").append(this.startTime);
        sb.append(", endTime=").append(this.endTime);
        sb.append(", status=").append(this.status);
        sb.append(", serverHost=").append(this.serverHost);
        sb.append(", serverDuid=").append(this.serverDuid);
        sb.append(", fireInfo=").append(this.fireInfo);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that == null) {
            return false;
        } else if (this.getClass() != that.getClass()) {
            return false;
        } else {
            boolean var10000;
            label105: {
                label97: {
                    SchedulerLog other = (SchedulerLog)that;
                    if (this.getId() == null) {
                        if (other.getId() != null) {
                            break label97;
                        }
                    } else if (!this.getId().equals(other.getId())) {
                        break label97;
                    }

                    if (this.getGroupName() == null) {
                        if (other.getGroupName() != null) {
                            break label97;
                        }
                    } else if (!this.getGroupName().equals(other.getGroupName())) {
                        break label97;
                    }

                    if (this.getTaskName() == null) {
                        if (other.getTaskName() != null) {
                            break label97;
                        }
                    } else if (!this.getTaskName().equals(other.getTaskName())) {
                        break label97;
                    }

                    if (this.getStartTime() == null) {
                        if (other.getStartTime() != null) {
                            break label97;
                        }
                    } else if (!this.getStartTime().equals(other.getStartTime())) {
                        break label97;
                    }

                    if (this.getEndTime() == null) {
                        if (other.getEndTime() != null) {
                            break label97;
                        }
                    } else if (!this.getEndTime().equals(other.getEndTime())) {
                        break label97;
                    }

                    if (this.getStatus() == null) {
                        if (other.getStatus() != null) {
                            break label97;
                        }
                    } else if (!this.getStatus().equals(other.getStatus())) {
                        break label97;
                    }

                    if (this.getServerHost() == null) {
                        if (other.getServerHost() != null) {
                            break label97;
                        }
                    } else if (!this.getServerHost().equals(other.getServerHost())) {
                        break label97;
                    }

                    if (this.getServerDuid() == null) {
                        if (other.getServerDuid() != null) {
                            break label97;
                        }
                    } else if (!this.getServerDuid().equals(other.getServerDuid())) {
                        break label97;
                    }

                    if (this.getTargetId() == null) {
                        if (other.getTargetId() != null) {
                            break label97;
                        }
                    } else if (!this.getTargetId().equals(other.getTargetId())) {
                        break label97;
                    }

                    if (this.getFireInfo() == null) {
                        if (other.getFireInfo() == null) {
                            break label105;
                        }
                    } else if (this.getFireInfo().equals(other.getFireInfo())) {
                        break label105;
                    }

                }

                var10000 = false;
                return var10000;
            }

            var10000 = true;
            return var10000;
        }
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (this.getId() == null ? 0 : this.getId().hashCode());
        result = prime * result + (this.getGroupName() == null ? 0 : this.getGroupName().hashCode());
        result = prime * result + (this.getTaskName() == null ? 0 : this.getTaskName().hashCode());
        result = prime * result + (this.getStartTime() == null ? 0 : this.getStartTime().hashCode());
        result = prime * result + (this.getEndTime() == null ? 0 : this.getEndTime().hashCode());
        result = prime * result + (this.getStatus() == null ? 0 : this.getStatus().hashCode());
        result = prime * result + (this.getServerHost() == null ? 0 : this.getServerHost().hashCode());
        result = prime * result + (this.getServerDuid() == null ? 0 : this.getServerDuid().hashCode());
        result = prime * result + (this.getFireInfo() == null ? 0 : this.getFireInfo().hashCode());
        result = prime * result + (this.getTargetId() == null ? 0 : this.getTargetId().hashCode());
        return result;
    }

    /**
     * Gets target id.
     *
     * @return the target id
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * Sets target id.
     *
     * @param targetId the target id
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}