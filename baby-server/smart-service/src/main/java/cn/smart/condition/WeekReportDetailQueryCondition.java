package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-8-29 14:06:25
*/

public class WeekReportDetailQueryCondition extends QueryCondition {

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
	private String chartData;
	/**
	 * 数据类型
	 */
	private Integer type;
	/**
	 * 较好数据
	 */
	private String better;
	/**
	 * 较差数据
	 */
	private String poor;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 周报id
	 */
	public Long getWeekReportId() {
		return weekReportId;
	}

	/**
	 * 周报id
	 */
	public void setWeekReportId(Long weekReportId) {
		this.weekReportId = weekReportId;
	}
	/**
	 * 图表数据
	 */
	public String getChartData() {
		return chartData;
	}

	/**
	 * 图表数据
	 */
	public void setChartData(String chartData) {
		this.chartData = chartData;
	}
	/**
	 * 数据类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 数据类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 较好数据
	 */
	public String getBetter() {
		return better;
	}

	/**
	 * 较好数据
	 */
	public void setBetter(String better) {
		this.better = better;
	}
	/**
	 * 较差数据
	 */
	public String getPoor() {
		return poor;
	}

	/**
	 * 较差数据
	 */
	public void setPoor(String poor) {
		this.poor = poor;
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

}
