//package cn.smart.support.third;
//
//import io.netty.handler.codec.http.HttpUtil;
//import org.apache.taglibs.standard.resources.Resources;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class WechatLogin {
//
//    public static final Map<String, String> getWxTokenAndOpenid(String code, String host) throws Exception {
//        Map<String, String> map = new HashMap();
//        String tokenUrl = Resources.THIRDPARTY.getString("accessTokenURL_wx");
//        tokenUrl = tokenUrl + "?appid=" + Resources.THIRDPARTY.getString("app_id_wx") + "&secret=" + Resources.THIRDPARTY.getString("app_key_wx") + "&code=" + code + "&grant_type=authorization_code";
//        String tokenRes = HttpUtil.get(tokenUrl);
//        if (tokenRes != null && tokenRes.indexOf("access_token") > -1) {
//            Map<String, String> tokenMap = toMap(tokenRes);
//            map.put("access_token", tokenMap.get("access_token"));
//            map.put("openId", tokenMap.get("openid"));
//            return map;
//        } else {
//            throw new IllegalArgumentException(Resources.getMessage("THIRDPARTY.LOGIN.NOTOKEN", new Object[]{"weixin"}));
//        }
//    }
//}
