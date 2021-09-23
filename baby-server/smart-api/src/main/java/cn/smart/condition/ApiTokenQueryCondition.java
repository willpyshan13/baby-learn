package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;
import java.util.Date;

/**
* @author Ye
* @date 2018-4-8 14:45:10
*/

public class ApiTokenQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Long id;
		/** 
		 * 用户id
		 */
		private Long personId;
		/** 
		 * token
		 */
		private String token;
		/** 
		 * 类型 1:h5 2:Android app
		 */
		private Integer type;
		/** 
		 * 过期时间
		 */
		private Long expireTime;
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
	     * 用户id
		 */
		public Long getPersonId() {
			return personId;
		}
		
		/** 
	     * 用户id
		 */
		public void setPersonId(Long personId) {
			this.personId = personId;
		}
		/** 
	     * token
		 */
		public String getToken() {
			return token;
		}
		
		/** 
	     * token
		 */
		public void setToken(String token) {
			this.token = token;
		}
		/** 
	     * 类型 1:h5 2:Android app
		 */
		public Integer getType() {
			return type;
		}
		
		/** 
	     * 类型 1:h5 2:Android app
		 */
		public void setType(Integer type) {
			this.type = type;
		}
		/** 
	     * 过期时间
		 */
		public Long getExpireTime() {
			return expireTime;
		}
		
		/** 
	     * 过期时间
		 */
		public void setExpireTime(Long expireTime) {
			this.expireTime = expireTime;
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
