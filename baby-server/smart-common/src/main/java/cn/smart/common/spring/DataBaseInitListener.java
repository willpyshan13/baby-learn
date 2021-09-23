//package cn.smart.common.spring;
//
//
//import cn.smart.condition.AppQueryCondition;
//import cn.smart.entity.AppDO;
//import cn.smart.service.IAppService;
//import cn.smart.server.module.sys.condition.SysUserQueryCondition;
//import cn.smart.server.module.sys.entity.SysUserDO;
//import cn.smart.service.ISysUserService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.shiro.crypto.hash.Sha256Hash;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.support.XmlWebApplicationContext;
//
//import java.util.Date;
//
//@Component
//public class DataBaseInitListener implements ApplicationListener<ContextRefreshedEvent> {
//
//    private static final Logger LOG= LogManager.getLogger(DataBaseInitListener.class);
//
//    @Autowired
//    private ISysUserService sysUserService;
//
//    @Autowired
//    private IAppService appService;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent arg0) {
//        XmlWebApplicationContext app=(XmlWebApplicationContext)arg0.getSource();
//        if("dispatcher-servlet".equals(app.getNamespace())) {
//            LOG.info("初始化数据库数据");
//
//            if (sysUserService.queryTotal(new SysUserQueryCondition()) < 1) {
//                SysUserDO sysUserDO = new SysUserDO();
//                sysUserDO.setUserId(1L);
//                sysUserDO.setUserName("admin");
//                sysUserDO.setAvatar("http://ow3s4rpxd.bkt.clouddn.com/avatar.jpg");
//                sysUserDO.setSalt("smart");
//                sysUserDO.setPassword(new Sha256Hash("123456", sysUserDO.getSalt()).toHex());
//                sysUserDO.setStatus((byte) 1);
//                sysUserDO.setCreateTime(new Date());
//                sysUserService.save(sysUserDO);
//            }
//            if (appService.queryTotal(new AppQueryCondition()) < 1) {
//                AppDO appDO = new AppDO();
//                appDO.setVersionName("1.0");
//                appDO.setAppType(1);
//                appDO.setPath("");
//                appDO.setUpdateTime("2018-3-26");
//                appService.save(appDO);
//
//            }
//        }
//    }
//}
