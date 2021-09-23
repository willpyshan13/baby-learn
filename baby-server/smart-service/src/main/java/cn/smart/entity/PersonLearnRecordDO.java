package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-6-22 16:34:32
 */
public class PersonLearnRecordDO extends BaseEntity {

    /**
    * 
    */
    private Long recordId;


    /**
    * 课程id
    */
    private Long courseId;


    /**
    * 用户id
    */
    private Long personId;


    /**
    * 类型 1:课程 2:游戏
    */
    private Integer type;


    /**
    * 完成结果
    */
    private String resultData;


    /**
    * 总分(星星数)
    */
    private Integer score;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 更新时间
    */
    private Date updateTime;

	/**
     * Get 
	 */
	public Long getRecordId() {
		return recordId;
	}
	
	/** 
     * Set 
	 */
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	/**
     * Get 课程id
	 */
	public Long getCourseId() {
		return courseId;
	}
	
	/** 
     * Set 课程id
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	/**
     * Get 用户id
	 */
	public Long getPersonId() {
		return personId;
	}
	
	/** 
     * Set 用户id
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	/**
     * Get 类型 1:课程 2:游戏
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 类型 1:课程 2:游戏
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * Get 完成结果
	 */
	public String getResultData() {
		return resultData;
	}
	
	/** 
     * Set 完成结果
	 */
	public void setResultData(String resultData) {
		this.resultData = resultData;
	}
	/**
     * Get 总分(星星数)
	 */
	public Integer getScore() {
		return score;
	}
	
	/** 
     * Set 总分(星星数)
	 */
	public void setScore(Integer score) {
		this.score = score;
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
     * Get 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 
     * Set 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}