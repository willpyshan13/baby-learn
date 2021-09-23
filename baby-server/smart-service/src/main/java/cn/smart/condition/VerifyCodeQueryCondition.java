package cn.smart.condition;

import cn.smart.common.utils.QueryCondition;

import java.util.Date;

/**
* @author Ye
* @date 2018-4-10 19:04:58
*/

public class VerifyCodeQueryCondition extends QueryCondition {

		/** 
		 * 
		 */
		private Long verifyId;
		/** 
		 * 验证码
		 */
		private String code;
		/** 
		 * 过期时间
		 */
		private Long expireTime;
		/** 
		 * 手机号码
		 */
		private String telephone;
		/** 
		 * 更新时间
		 */
		private Date updateTime;
		/** 
		 * 
		 */
		private Long createTime;

		/** 
	     * 
		 */
		public Long getVerifyId() {
			return verifyId;
		}
		
		/** 
	     * 
		 */
		public void setVerifyId(Long verifyId) {
			this.verifyId = verifyId;
		}
		/** 
	     * 验证码
		 */
		public String getCode() {
			return code;
		}
		
		/** 
	     * 验证码
		 */
		public void setCode(String code) {
			this.code = code;
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
	     * 手机号码
		 */
		public String getTelephone() {
			return telephone;
		}
		
		/** 
	     * 手机号码
		 */
		public void setTelephone(String telephone) {
			this.telephone = telephone;
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
		/** 
	     * 
		 */
		public Long getCreateTime() {
			return createTime;
		}
		
		/** 
	     * 
		 */
		public void setCreateTime(Long createTime) {
			this.createTime = createTime;
		}

}
