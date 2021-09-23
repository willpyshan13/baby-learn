package cn.smart.common.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;

/**
 * 返回结果
 * @author Ye
 * @Date 2018-3-27 12:53:35
 */
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = 7308145825421379223L;

    public R() {
        put("code", 0);
    }

    public static R ok() {
        return new R();
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(HashMap map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员！");
    }

    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
