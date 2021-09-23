package cn.smart.bo;

import cn.smart.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * @Description 周报详细数据表
 * @author Ye
 * @date 2018-8-29 14:06:25
 */
public class WeekReportDetailBO extends BaseEntity {

    /**
    * 
    */
    private Long id;


    /**
    * 周报id
    */
    private Long weekReportId;


    /**
    * 图表数据
    */
    private WeekReportChartBO chartData;


    /**
    * 数据类型
    */
    private Integer type;


    /**
    * 较好数据
    */
    private WeekReportGameBO better;


    /**
    * 较差数据
    */
    private WeekReportGameBO poor;


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
     * Get 周报id
	 */
	public Long getWeekReportId() {
		return weekReportId;
	}
	
	/** 
     * Set 周报id
	 */
	public void setWeekReportId(Long weekReportId) {
		this.weekReportId = weekReportId;
	}
	/**
     * Get 图表数据
	 */
	public WeekReportChartBO getChartData() {
		return chartData;
	}
	
	/** 
     * Set 图表数据
	 */
	public void setChartData(WeekReportChartBO chartData) {
		this.chartData = chartData;
	}
	/**
     * Get 数据类型
	 */
	public Integer getType() {
		return type;
	}
	
	/** 
     * Set 数据类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
     * Get 较好数据
	 */
	public WeekReportGameBO getBetter() {
		return better;
	}
	
	/** 
     * Set 较好数据
	 */
	public void setBetter(WeekReportGameBO better) {
		this.better = better;
	}
	/**
     * Get 较差数据
	 */
	public WeekReportGameBO getPoor() {
		return poor;
	}
	
	/** 
     * Set 较差数据
	 */
	public void setPoor(WeekReportGameBO poor) {
		this.poor = poor;
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

    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"weekReportId\":")
                .append(weekReportId);
        sb.append("\"chartData\":")
                .append(chartData);
        sb.append("\"type\":")
                .append(type);
        sb.append("\"better\":")
                .append(better);
        sb.append("\"poor\":")
                .append(poor);
        sb.append("\"createTime\":")
                .append(createTime);
        sb.append('}');
        return sb.toString();
    }

}