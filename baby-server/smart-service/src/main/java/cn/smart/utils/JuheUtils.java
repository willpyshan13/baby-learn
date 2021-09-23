package cn.smart.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * author: yushan.peng
 * Create Time: 2017/6/21 10:07.
 */
public class JuheUtils {

    private static Logger logger = LogManager.getLogger(JuheUtils.class);

    private static final String DEF_CHATSET = "UTF-8";
    private static final int DEF_CONN_TIMEOUT = 30000;
    private static final int DEF_READ_TIMEOUT = 30000;
    private static final String ERROR_CODE = "error_code";
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    /**
     * 配置您申请的KEY
     */
    private static final String APPKEY ="0d4c81ae58bf25792a43c51a1891dfc4";

    /**
     * 1.屏蔽词检查测
     */
    public static void getRequest1(){
        //请求接口地址
        String url ="http://v.juhe.cn/sms/black";
        //请求参数
        Map<String, Object> params = new HashMap<>(16);
        //需要检测的短信内容，需要UTF8 URLENCODE
        params.put("word","");
        //应用APPKEY(应用详细页查询)
        params.put("key",APPKEY);

        processResult(url, params);
    }

    private static void processResult(String url, Map<String, Object> params) {
        String result;
        try {
            result =net(url, params, "GET");
            JSONObject object = new JSONObject(result);
            if(object.getInt(ERROR_CODE)==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送验证码
     * @param tel 手机号码
     * @param code 验证码
     * @return Boolean
     * @throws Exception e
     */
    public static Boolean sendVerifyCode(String tel, String code) throws Exception {
        String result;
        //请求接口地址
        String url ="http://v.juhe.cn/sms/send";
        //请求参数
        Map<String, Object> params = new HashMap<>(16);
        //接收短信的手机号码
        params.put("mobile",tel);
        //短信模板ID，请参考个人中心短信模板设置
        params.put("tpl_id","95576");
        //变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行 urlencode 编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
        params.put("tpl_value",URLEncoder.encode("&#code#="+code, "UTF-8"));
        //应用APPKEY(应用详细页查询)
        params.put("key",APPKEY);
        //返回数据的格式,xml或json，默认json
        params.put("dtype","json");

        result =net(url, params, "GET");
        JSONObject object = new JSONObject(result);
        logger.info("-------> Send message result : " + object);

        return object.getInt(ERROR_CODE) == 0;


    }

    /**
     * 2.发送短信
     * @param tel 接收短信的手机号码
     * @param code 内容
     * @throws Exception e
     */
    public static void getRequest2(String tel,String code) throws Exception{
        //请求接口地址
        String url ="http://v.juhe.cn/sms/send";
        //请求参数
        Map<String, Object> params = new HashMap<>(16);
        //接收短信的手机号码
        params.put("mobile",tel);
        //短信模板ID，请参考个人中心短信模板设置
        params.put("tpl_id","37733");
        //变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
        params.put("tpl_value",URLEncoder.encode("&#code#="+code, "UTF-8"));
        //应用APPKEY(应用详细页查询)
        params.put("key",APPKEY);
        //返回数据的格式,xml或json，默认json
        params.put("dtype","json");

        processResult(url, params);
    }

    /**
     * 发起请求
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception e
     */
    private static String net(String strUrl, Map<String, Object> params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuilder sb = new StringBuilder();
            if(method==null || GET_METHOD.equals(method)){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || GET_METHOD.equals(method)){
                conn.setRequestMethod(GET_METHOD);
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && POST_METHOD.equals(method)) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // ignore
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    /**
     * 将map型转为请求参数型
     * @param data data
     * @return string
     */
    private static String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
