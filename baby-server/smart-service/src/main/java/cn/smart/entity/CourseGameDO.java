package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Ye
 * @date 2018-4-18 16:01:45
 */
public class CourseGameDO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 课程id
    */
    private Long courseInfoId;


    /**
    * 游戏id
    */
    private Long gameInfoId;


    /**
    * 创建时间
    */
    private Date createTime;

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
     * Get 课程id
	 */
	public Long getCourseInfoId() {
		return courseInfoId;
	}
	
	/** 
     * Set 课程id
	 */
	public void setCourseInfoId(Long courseInfoId) {
		this.courseInfoId = courseInfoId;
	}
	/**
     * Get 游戏id
	 */
	public Long getGameInfoId() {
		return gameInfoId;
	}
	
	/** 
     * Set 游戏id
	 */
	public void setGameInfoId(Long gameInfoId) {
		this.gameInfoId = gameInfoId;
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
	
}