package cn.smart.entity;

import cn.smart.common.base.entity.BaseEntity;
import java.util.Date;

/**
 * @Description 
 * @author Ye
 * @date 2018-8-30 18:33:12
 */
public class WeekReportSuggestionsDO extends BaseEntity {

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
     * Get 较好建议
	 */
	public String getBetter() {
		return better;
	}
	
	/** 
     * Set 较好建议
	 */
	public void setBetter(String better) {
		this.better = better;
	}
	/**
     * Get 较好建议语音
	 */
	public String getBetterVoice() {
		return betterVoice;
	}
	
	/** 
     * Set 较好建议语音
	 */
	public void setBetterVoice(String betterVoice) {
		this.betterVoice = betterVoice;
	}
	/**
     * Get 较差建议
	 */
	public String getPoor() {
		return poor;
	}
	
	/** 
     * Set 较差建议
	 */
	public void setPoor(String poor) {
		this.poor = poor;
	}
	/**
     * Get 较差建议语音
	 */
	public String getPoorVoice() {
		return poorVoice;
	}
	
	/** 
     * Set 较差建议语音
	 */
	public void setPoorVoice(String poorVoice) {
		this.poorVoice = poorVoice;
	}
	/**
     * Get 等级
	 */
	public Integer getLevel() {
		return level;
	}
	
	/** 
     * Set 等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
     * Get 类型 0 玩游戏 1 大声说
	 */
	public String getType() {
		return type;
	}
	
	/** 
     * Set 类型 0 玩游戏 1 大声说
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
     * Get 创建人
	 */
	public Long getCreateBy() {
		return createBy;
	}
	
	/** 
     * Set 创建人
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
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
     * Get 更新人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	
	/** 
     * Set 更新人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
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

    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"better\":")
                .append(better);
        sb.append("\"betterVoice\":")
                .append(betterVoice);
        sb.append("\"poor\":")
                .append(poor);
        sb.append("\"poorVoice\":")
                .append(poorVoice);
        sb.append("\"level\":")
                .append(level);
        sb.append("\"type\":")
                .append(type);
        sb.append("\"createBy\":")
                .append(createBy);
        sb.append("\"createTime\":")
                .append(createTime);
        sb.append("\"updateBy\":")
                .append(updateBy);
        sb.append("\"updateTime\":")
                .append(updateTime);
        sb.append('}');
        return sb.toString();
    }

}