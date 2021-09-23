package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-5-28 11:13:05
*/

public class GameInfoQueryCondition extends QueryCondition {


        private Long courseId;

        private Long[] courseIds;

        private Long[] inCourseIds;

        private Long[] inGameIds;
 		/**
		 *  编号
		 */
		private Long id;
		/** 
		 * 游戏名称
		 */
		private String name;
		/** 
		 * 游戏信息
		 */
		private String info;
		/** 
		 * 图片地址
		 */
		private String imageUrl;
		/** 
		 * 资源地址
		 */
		private String resourceUrl;
		/** 
		 * 状态 1 ：生效 0 ：失效
		 */
		private Integer status;
		/** 
		 * 难度等级
		 */
		private Integer level;
		/** 
		 * 类型 [0, "三选一"], [1, "大声说"]
		 */
		private Integer type;
		/** 
		 * 序号
		 */
		private Integer serialNumber;
		/** 
		 * 创建时间
		 */
		private Date createTime;
		/** 
		 * 更新时间(自动维护)
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
	     * 游戏名称
		 */
		public String getName() {
			return name;
		}
		
		/** 
	     * 游戏名称
		 */
		public void setName(String name) {
			this.name = name;
		}
		/** 
	     * 游戏信息
		 */
		public String getInfo() {
			return info;
		}
		
		/** 
	     * 游戏信息
		 */
		public void setInfo(String info) {
			this.info = info;
		}
		/** 
	     * 图片地址
		 */
		public String getImageUrl() {
			return imageUrl;
		}
		
		/** 
	     * 图片地址
		 */
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		/** 
	     * 资源地址
		 */
		public String getResourceUrl() {
			return resourceUrl;
		}
		
		/** 
	     * 资源地址
		 */
		public void setResourceUrl(String resourceUrl) {
			this.resourceUrl = resourceUrl;
		}
		/** 
	     * 状态 1 ：生效 0 ：失效
		 */
		public Integer getStatus() {
			return status;
		}
		
		/** 
	     * 状态 1 ：生效 0 ：失效
		 */
		public void setStatus(Integer status) {
			this.status = status;
		}
		/** 
	     * 难度等级
		 */
		public Integer getLevel() {
			return level;
		}
		
		/** 
	     * 难度等级
		 */
		public void setLevel(Integer level) {
			this.level = level;
		}
		/** 
	     * 类型 [0, "三选一"], [1, "单词辨析"]
		 */
		public Integer getType() {
			return type;
		}
		
		/** 
	     * 类型 [0, "三选一"], [1, "单词辨析"]
		 */
		public void setType(Integer type) {
			this.type = type;
		}
		/** 
	     * 序号
		 */
		public Integer getSerialNumber() {
			return serialNumber;
		}
		
		/** 
	     * 序号
		 */
		public void setSerialNumber(Integer serialNumber) {
			this.serialNumber = serialNumber;
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
	     * 更新时间(自动维护)
		 */
		public Date getUpdateTime() {
			return updateTime;
		}
		
		/** 
	     * 更新时间(自动维护)
		 */
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

	public Long[] getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(Long[] courseIds) {
		this.courseIds = courseIds;
	}

	public Long[] getInGameIds() {
		return inGameIds;
	}

	public void setInGameIds(Long[] inGameIds) {
		this.inGameIds = inGameIds;
	}

    public Long[] getInCourseIds() {
        return inCourseIds;
    }

    public void setInCourseIds(Long[] inCourseIds) {
        this.inCourseIds = inCourseIds;
    }
}
