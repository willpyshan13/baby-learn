//package cn.smart.controller.mobile;
//
//import cn.smart.common.annotation.AuthIgnore;
//import me.chanjar.weixin.common.api.WxConsts;
//import me.chanjar.weixin.common.bean.menu.WxMenu;
//import me.chanjar.weixin.common.bean.menu.WxMenuButton;
//import me.chanjar.weixin.common.error.WxErrorException;
//import me.chanjar.weixin.mp.api.WxMpMenuService;
//import me.chanjar.weixin.mp.api.WxMpService;
//import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
//import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/mobile/wechat/menu")
//public class WxMenuController {
//
//    @Autowired
//    WxMpService wxMpService;
//
//    @AuthIgnore
//    @GetMapping("/getMenu")
//    @ResponseBody
//    public WxMpMenu getMenu() {
//        try {
//            return this.wxMpService.getMenuService().menuGet();
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @AuthIgnore
//    @GetMapping("/create")
//    @ResponseBody
//    public String menuCreateSample() throws WxErrorException {
//
//        WxMenu menu = new WxMenu();
//        WxMenuButton b1 = new WxMenuButton();
//        b1.setAppId("who_we_are_btn_01");
//        b1.setName("我们是谁");
//
//        WxMenuButton b11 = new WxMenuButton();
//        b11.setName("0元机器人课程");
//        b11.setAppId("buy_course_btn_01");
//        b11.setType(WxConsts.MenuButtonType.VIEW);
//        b11.setUrl("http://www.smart-dog.cn/test/html/newactivitysignup.html");
//
//        WxMenuButton b12 = new WxMenuButton();
//        b12.setName("关于我们");
//        b12.setAppId("about_us_btn_01");
//        b12.setType(WxConsts.MenuButtonType.VIEW);
//        b12.setUrl("https://mp.weixin.qq.com/s/p_Cwua8NcRY588Tx7X8Ntg");
//
//        WxMenuButton b13 = new WxMenuButton();
//        b13.setName("我们的特色");
//        b13.setAppId("special_of_us_btn_01");
//        b13.setType(WxConsts.MenuButtonType.VIEW);
//        b13.setUrl("https://mp.weixin.qq.com/s/2Ic7FkPR5s3h6pw6DwkEgg");
//
//        b1.getSubButtons().add(b11);
//        b1.getSubButtons().add(b12);
//        b1.getSubButtons().add(b13);
//        menu.getButtons().add(b1);
//
//        WxMenuButton b2 = new WxMenuButton();
//        b2.setAppId("play_smart_baby_btn_01");
//        b2.setName("玩转小宝");
//
//        WxMenuButton b21 = new WxMenuButton();
//        b21.setName("常见问题");
//        b21.setType(WxConsts.MenuButtonType.VIEW);
//        b21.setUrl("http://www.smart-dog.cn/m/html/handbook.html");
//        b21.setKey("common_problem_btn");
//
//        WxMenuButton b22 = new WxMenuButton();
//        b22.setName("教学视频");
//        b22.setType(WxConsts.MenuButtonType.VIEW);
//        b22.setUrl("http://www.smart-dog.cn/m/html/teaching_video.html");
//        b22.setKey("teaching_video_btn");
//
//        WxMenuButton b23 = new WxMenuButton();
//        b23.setName("课程购买");
//        b23.setType(WxConsts.MenuButtonType.VIEW);
//        b23.setUrl("http://sanya.weixin-service.net/app/index.php?i=126&c=entry&m=ewei_shopv2&do=mobile&r=goods.detail&id=309");
//        b23.setKey("course_purchase_btn");
//
//        WxMenuButton b24 = new WxMenuButton();
//        b24.setName("代理商入口");
//        b24.setType(WxConsts.MenuButtonType.VIEW);
//        b24.setUrl("http://sanya.weixin-service.net/app/index.php?i=126&c=entry&m=ewei_shopv2&do=mobile&r=goods.detail&id=309");
//        b24.setKey("agent_entry_btn");
//
//        WxMenuButton b25 = new WxMenuButton();
//        b25.setName("人工客服");
//        b25.setType(WxConsts.MenuButtonType.VIEW);
//        b25.setUrl("http://p.qiao.baidu.com/cps2/chatIndex?reqParam=%7B%22from%22%3A0%2C%22sessionid%22%3A%22%22%2C%22siteId%22%3A%2212492180%22%2C%22tid%22%3A%22-1%22%2C%22userId%22%3A%2223016574%22%2C%22ttype%22%3A1%2C%22siteConfig%22%3A%7B%22wsUrl%22%3A%22ws%3A%2F%2Fp.qiao.baidu.com%2Fwebsocket%22%2C%22eid%22%3A%2223016574%22%2C%22queuing%22%3A%22%22%2C%22siteId%22%3A%2212492180%22%2C%22online%22%3A%22true%22%2C%22webRoot%22%3A%22%2F%2Fp.qiao.baidu.com%2Fcps2%2F%22%2C%22bid%22%3A%22154149110742778771%22%2C%22userId%22%3A%2223016574%22%2C%22isGray%22%3A%22false%22%2C%22invited%22%3A0%7D%2C%22config%22%3A%7B%22themeColor%22%3A%224d74fa%22%7D%7D");
//        b25.setKey("artificial_service_btn");
//
//        b2.getSubButtons().add(b21);
//        b2.getSubButtons().add(b22);
//        b2.getSubButtons().add(b23);
//        b2.getSubButtons().add(b24);
//        b2.getSubButtons().add(b25);
//        menu.getButtons().add(b2);
//
//
//        WxMenuButton b3 = new WxMenuButton();
//        b3.setType(WxConsts.MenuButtonType.MINIPROGRAM);
//        b3.setAppId("wx893c969634a1b1e3");
//        b3.setName("应用中心");
//        b3.setUrl("http://wx.qlogo.cn");
//        b3.setPagePath("pages/denglu/denglu");
//
//        menu.getButtons().add(b3);
//
//        return this.wxMpService.getMenuService().menuCreate(menu);
//    }
//
//    /**
//     * <pre>
//     * 自定义菜单删除接口
//     * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015&token=&lang=zh_CN
//     * </pre>
//     */
//    @AuthIgnore
//    @GetMapping("/delete")
//    public void menuDelete() throws WxErrorException {
//        this.wxMpService.getMenuService().menuDelete();
//    }
//
//    /**
//     * <pre>
//     * 删除个性化菜单接口
//     * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
//     * </pre>
//     *
//     * @param menuId 个性化菜单的menuid
//     */
//    @AuthIgnore
//    @GetMapping("/delete/{menuId}")
//    public void menuDelete(@PathVariable String menuId) throws WxErrorException {
//        this.wxMpService.getMenuService().menuDelete(menuId);
//    }
//
//    /**
//     * <pre>
//     * 自定义菜单查询接口
//     * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014&token=&lang=zh_CN
//     * </pre>
//     */
//    @AuthIgnore
//    @GetMapping("/get")
//    public WxMpMenu menuGet() throws WxErrorException {
//        return this.wxMpService.getMenuService().menuGet();
//    }
//
//}
