package cn.smart.support.talkcloud.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ParamsUtil {

    /**
     * 组装参数
     * @param stringBuilder 字符串
     * @param map 参数
     * @return stringBuilder
     */
    public static String getParams(StringBuilder stringBuilder, Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                stringBuilder.append(key);
                stringBuilder.append("/");
                try {
                    stringBuilder.append(URLEncoder.encode(value.toString(), "UTF8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                stringBuilder.append("/");
            }
        }

        return stringBuilder.substring(0, stringBuilder.lastIndexOf("/"));
    }
}
