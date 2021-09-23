package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-4-12 17:58:58
*/

public class SysUserTokenQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Long userId;
		/** 
		 * token
		 */
		private String token;
		/** 
		 * 过期时间
		 */
		private Date expireTime;
		/** 
		 * 更新时间
		 */
		private Date updateTime;

		/** 
	     * 
		 */
		public Long getUserId() {
			return userId;
		}
		
		/** 
	     * 
		 */
		public void setUserId(Long userId) {
			this.userId = userId;
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
	     * 过期时间
		 */
		public Date getExpireTime() {
			return expireTime;
		}
		
		/** 
	     * 过期时间
		 */
		public void setExpireTime(Date expireTime) {
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
