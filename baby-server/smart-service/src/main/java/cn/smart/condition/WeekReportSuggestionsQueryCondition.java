package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-8-30 18:33:12
*/

public class WeekReportSuggestionsQueryCondition extends QueryCondition {

	/**
	 * 
	 */
	private Long id;
	/**
	 * 较好建议
	 */
	private String better;
	/**
	 * 较好建议语音
	 */
	private String betterVoice;
	/**
	 * 较差建议
	 */
	private String poor;
	/**
	 * 较差建议语音
	 */
	private String poorVoice;
	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 类型 0 玩游戏 1 大声说
	 */
	private String type;
	/**
	 * 创建人
	 */
	private Long createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新人
	 */
	private Long updateBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;

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
	 * 较好建议
	 */
	public String getBetter() {
		return better;
	}

	/**
	 * 较好建议
	 */
	public void setBetter(String better) {
		this.better = better;
	}
	/**
	 * 较好建议语音
	 */
	public String getBetterVoice() {
		return betterVoice;
	}

	/**
	 * 较好建议语音
	 */
	public void setBetterVoice(String betterVoice) {
		this.betterVoice = betterVoice;
	}
	/**
	 * 较差建议
	 */
	public String getPoor() {
		return poor;
	}

	/**
	 * 较差建议
	 */
	public void setPoor(String poor) {
		this.poor = poor;
	}
	/**
	 * 较差建议语音
	 */
	public String getPoorVoice() {
		return poorVoice;
	}

	/**
	 * 较差建议语音
	 */
	public void setPoorVoice(String poorVoice) {
		this.poorVoice = poorVoice;
	}
	/**
	 * 等级
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * 等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 类型 0 玩游戏 1 大声说
	 */
	public String getType() {
		return type;
	}

	/**
	 * 类型 0 玩游戏 1 大声说
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 创建人
	 */
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
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
	/**
	 * 更新人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 更新人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
