package cn.smart.support.scheduler;

import java.util.Date;

/**
 * @author Admin
 */
public class TaskScheduled {

    public interface JobType {
        String JOB = "job";
        String STATEFUL_JOB = "statefulJob";
    }

    public interface TaskType {
        String LOCAL = "local";
        String DUBBO = "dubbo";
    }
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务分组
     */
    private String taskGroup;
    /**
     * 状态
     */
    private String status;
    /**
     * Cron 表达式
     */
    private String taskCron;
    private Date previousFireTime;
    private Date nextFireTime;
    /**
     * 描述
     */
    private String taskDesc;
    /**
     * job 类型 job statefulJob 过时
     */
    private String jobType;
    /**
     * 任务类型 Local Dubbo
     */
    private String taskType;
    private String targetSystem;
    private String targetObject;
    private String targetMethod;
    /**
     * 通知邮箱地址
     */
    private String contactName;
    private String contactEmail;

    /**
     * 参数列表
     */
    private Long id;

    public TaskScheduled() {
    }

    public TaskScheduled(String taskGroup, String taskName) {
        this.taskGroup = taskGroup;
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskGroup() {
        return this.taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskCron() {
        return this.taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public Date getPreviousFireTime() {
        return this.previousFireTime;
    }

    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    public Date getNextFireTime() {
        return this.nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public String getTaskDesc() {
        return this.taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getJobType() {
        return this.jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTargetSystem() {
        return this.targetSystem;
    }

    public void setTargetSystem(String targetSystem) {
        this.targetSystem = targetSystem;
    }

    public String getTargetObject() {
        return this.targetObject;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public String getTargetMethod() {
        return this.targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"taskName\":\"")
                .append(taskName).append('\"');
        sb.append(",\"taskGroup\":\"")
                .append(taskGroup).append('\"');
        sb.append(",\"status\":\"")
                .append(status).append('\"');
        sb.append(",\"taskCron\":\"")
                .append(taskCron).append('\"');
        sb.append(",\"previousFireTime\":\"")
                .append(previousFireTime).append('\"');
        sb.append(",\"nextFireTime\":\"")
                .append(nextFireTime).append('\"');
        sb.append(",\"taskDesc\":\"")
                .append(taskDesc).append('\"');
        sb.append(",\"jobType\":\"")
                .append(jobType).append('\"');
        sb.append(",\"taskType\":\"")
                .append(taskType).append('\"');
        sb.append(",\"targetSystem\":\"")
                .append(targetSystem).append('\"');
        sb.append(",\"targetObject\":\"")
                .append(targetObject).append('\"');
        sb.append(",\"targetMethod\":\"")
                .append(targetMethod).append('\"');
        sb.append(",\"contactName\":\"")
                .append(contactName).append('\"');
        sb.append(",\"contactEmail\":\"")
                .append(contactEmail).append('\"');
        sb.append(",\"id\":")
                .append(id);
        sb.append('}');
        return sb.toString();
    }
}
