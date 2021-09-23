package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-11 14:41:27
 */
public class ClassInfoDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 班级介绍
    */
    private String classInfo;


    /**
    * 班级名称
    */
    private String className;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 班级状态 1:生效 0:失效
    */
    private Integer status;


    /**
    * 班级人数
    */
    private Integer count;


    /**
    * 课阶id
    */
    private Long courseStageId;

	/**
	 * 课阶名称
	 */
	private String stageName;

	/**
    * 班级教师
    */
    private Long teacherId;

	/**
	 * 班主任
	 */
	private Long headerTeacher;

	/**
	 * 推送标签
	 */
	private String pushTag;

	/**
	 * 教师姓名
	 */
	private String teacherName;

	/**
    * 更新时间(自动维护)
    */
    private Date updateTime;

	/**
	 * 当前班级打开课程
	 */
	private Long openCourseId;

	/**
	 * 当前班级学生id集合
	 */
	private String personIds;

	/**
	 * 班级类型 1:一对一 2:一对四 3:一对多
	 */
	private Integer type;

    /**
     * 是否默认一对多教室
     */
	private Boolean oneToMany;
    /**
     * 备注
     */
    private String remark;

	/**
	 * 非持久化属性
	 */
	private String courseName;
	private String headerTeacherName;
	private Long personId;
	// 开课时间
	private String openTime;

	/**
	 * Get
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Get 班级介绍
	 */
	public String getClassInfo() {
		return classInfo;
	}
	
	/** 
     * Set 班级介绍
	 */
	public void setClassInfo(String classInfo) {
		this.classInfo = classInfo;
	}
	/**
     * Get 班级名称
	 */
	public String getClassName() {
		return className;
	}
	
	/** 
     * Set 班级名称
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
     * Get 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 
     * Set 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
     * Get 班级状态
	 */
	public Integer getStatus() {
		return status;
	}
	
	/** 
     * Set 班级状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
     * Get 班级人数
	 */
	public Integer getCount() {
		return count;
	}
	
	/** 
     * Set 班级人数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
     * Get 课阶id
	 */
	public Long getCourseStageId() {
		return courseStageId;
	}
	
	/** 
     * Set 课阶id
	 */
	public void setCourseStageId(Long courseStageId) {
		this.courseStageId = courseStageId;
	}
	/**
     * Get 班级教师
	 */
	public Long getTeacherId() {
		return teacherId;
	}
	
	/** 
     * Set 班级教师
	 */
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	/**
     * Get 更新时间(自动维护)
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 
     * Set 更新时间(自动维护)
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getPushTag() {
		return pushTag;
	}

	public void setPushTag(String pushTag) {
		this.pushTag = pushTag;
	}

	public Long getHeaderTeacher() {
		return headerTeacher;
	}

	public void setHeaderTeacher(Long headerTeacher) {
		this.headerTeacher = headerTeacher;
	}

	public String getPersonIds() {
		return personIds;
	}

	public void setPersonIds(String personIds) {
		this.personIds = personIds;
	}

	public Long getOpenCourseId() {
		return openCourseId;
	}

	public void setOpenCourseId(Long openCourseId) {
		this.openCourseId = openCourseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getHeaderTeacherName() {
		return headerTeacherName;
	}

	public void setHeaderTeacherName(String headerTeacherName) {
		this.headerTeacherName = headerTeacherName;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

    public Boolean getOneToMany() {
        return oneToMany;
    }

    public void setOneToMany(Boolean oneToMany) {
        this.oneToMany = oneToMany;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}