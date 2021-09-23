package cn.smart.common;

/**
 * 配置信息
 */
public class ConfigConstant {

    public static final String USER_KEY = "userId";
    /**
     * 拓课云 Api 地址
     */
    public static final String BASE_URL = "http://global.talk-cloud.net/WebAPI/";

    /**
     * 拓课云 公司 key
     */
    public static final String KEY = "ACtb2LrFjgvQQdTz";

    /**
     * 云存储配置KEY
     */
    public final static String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";

    /**
     * 获取操作地址
     * @param m 操作方法
     * @return
     */
    public static String getBaseURL(String m) {
        StringBuilder s = new StringBuilder();
        s.append(BASE_URL);
        s.append(m);
        s.append("/");
        s.append("key");
        s.append("/");
        s.append(KEY);
        s.append("/");
        return s.toString();
    }

}
