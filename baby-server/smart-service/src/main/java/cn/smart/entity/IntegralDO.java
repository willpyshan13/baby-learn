package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * 
 * @author Ye
 * @date 2018-9-13 19:35:38
 */
public class IntegralDO extends BaseEntity {

    /**
    * 积分明细id
    */
    private Long id;


    /**
    * 用户id
    */
    private Long personId;


    /**
    * 个数
    */
    private Integer number;


    /**
    * 类型 1:蛋糕 2:糖果
    */
    private Integer type;


    /**
    * 来源 1: 签到积分 2: 课程学习积分
    */
    private Integer source;


    /**
    * 获取日期
    */
    private String createDate;


    /**
    * 创建时间
    */
    private Date createTime;


    /**
    * 课程id
    */
    private Long courseId;


    /**
    * 学习记录id
    */
    private Long learnRecordId;


    /**
    * 兑换记录id
    */
    private Long exchangeDetailId;

	/**
     * Get 积分明细id
	 */
	public Long getId() {
		return id;
	}
	
	/** 
     * Set 积分明细id
	 */
	public void setId(Long id) {
		this.id = id;
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
     * Get 个数
	 */
	public Integer getNumber() {
		return number;
	}
	
	/** 
     * Set 个数
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
     * Get 类型 1:蛋糕 2:糖果
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 类型 1:蛋糕 2:糖果
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * Get 来源 1: 签到积分 2: 课程学习积分
	 */
	public Integer getSource() {
		return source;
	}
	
	/** 
     * Set 来源 1: 签到积分 2: 课程学习积分
	 */
	public void setSource(Integer source) {
		this.source = source;
	}
	/**
     * Get 获取日期
	 */
	public String getCreateDate() {
		return createDate;
	}
	
	/** 
     * Set 获取日期
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
     * Get 学习记录id
	 */
	public Long getLearnRecordId() {
		return learnRecordId;
	}
	
	/** 
     * Set 学习记录id
	 */
	public void setLearnRecordId(Long learnRecordId) {
		this.learnRecordId = learnRecordId;
	}
	/**
     * Get 兑换记录id
	 */
	public Long getExchangeDetailId() {
		return exchangeDetailId;
	}
	
	/** 
     * Set 兑换记录id
	 */
	public void setExchangeDetailId(Long exchangeDetailId) {
		this.exchangeDetailId = exchangeDetailId;
	}

    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"personId\":")
                .append(personId);
        sb.append("\"number\":")
                .append(number);
        sb.append("\"type\":")
                .append(type);
        sb.append("\"source\":")
                .append(source);
        sb.append("\"createDate\":")
                .append(createDate);
        sb.append("\"createTime\":")
                .append(createTime);
        sb.append("\"courseId\":")
                .append(courseId);
        sb.append("\"learnRecordId\":")
                .append(learnRecordId);
        sb.append("\"exchangeDetailId\":")
                .append(exchangeDetailId);
        sb.append('}');
        return sb.toString();
    }

}