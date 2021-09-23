package cn.smart.bo;

import java.util.Date;

public class ScheduledBO {

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
    private String openCourseName;
    private String className;
    private String roomName;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskCron() {
        return taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public Date getPreviousFireTime() {
        return previousFireTime;
    }

    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTargetSystem() {
        return targetSystem;
    }

    public void setTargetSystem(String targetSystem) {
        this.targetSystem = targetSystem;
    }

    public String getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getOpenCourseName() {
        return openCourseName;
    }

    public void setOpenCourseName(String openCourseName) {
        this.openCourseName = openCourseName;
    }
}
