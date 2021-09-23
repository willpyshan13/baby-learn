package cn.smart.controller;


import cn.smart.entity.SysUserDO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;

/**
 * Controller公共组件
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
	protected Logger logger = LogManager.getLogger(getClass());
	
	protected SysUserDO getUser() {
		return (SysUserDO) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}

	protected Long getDeptId() {
		return getUser().getDeptId();
	}
}
