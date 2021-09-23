package cn.smart.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Ye
 */
public final class WebUtil {
    public static ThreadLocal<HttpServletRequest> REQUEST = new NamedThreadLocal("ThreadLocalRequest");
    private static Logger logger = LogManager.getLogger();

    private WebUtil() {
    }

    public static final String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        return cookie == null ? defaultValue : cookie.getValue();
    }

    public static final Object getCurrentUser(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (null != session) {
                return session.getAttribute("CURRENT_USER");
            }
        } catch (Exception var2) {
            logger.error(var2.getCause());
        }

        return null;
    }

    public static final void saveCurrentUser(HttpServletRequest request, Object user) {
        setSession(request, "CURRENT_USER", user);
    }

    public static final void setSession(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        if (null != session) {
            session.setAttribute(key, value);
        }

    }

    public static final Object getSession(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        return null != session ? session.getAttribute(key) : null;
    }

    public static final void removeCurrentUser(HttpServletRequest request) {
        request.getSession().removeAttribute("CURRENT_USER");
    }

    public static final String getApplicationResource(String key, HttpServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
        return resourceBundle.getString(key);
    }

    public static final Map<String, Object> getParameterMap(HttpServletRequest request) {
        return WebUtils.getParametersStartingWith(request, (String)null);
    }



    public static void setResponseFileName(HttpServletRequest request, HttpServletResponse response, String displayName) {
        String userAgent = request.getHeader("User-Agent");
        boolean isIE = false;
        if (userAgent != null && userAgent.toLowerCase().contains("msie")) {
            isIE = true;
        }

        try {
            String displayName2;
            if (isIE) {
                displayName2 = URLEncoder.encode(displayName, "UTF-8");
                displayName2 = displayName2.replaceAll("\\+", "%20");
                response.setHeader("Content-Disposition", "attachment;filename=" + displayName2);
            } else {
                displayName2 = new String(displayName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("Content-Disposition", "attachment;filename=\"" + displayName2 + "\"");
            }

            String extStr = displayName2.substring(displayName2.indexOf(".") + 1);
            if ("xls".equalsIgnoreCase(extStr)) {
                response.setContentType("application/vnd.ms-excel charset=UTF-8");
            } else {
                response.setContentType("application/octet-stream");
            }
        } catch (UnsupportedEncodingException var7) {
            logger.error("设置文件名发生错误", var7);
        }

    }

    public static final String getHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.indexOf(",") > 0) {
            logger.info(ip);
            String[] ips = ip.split(",");
            String[] var3 = ips;
            int var4 = ips.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String ip2 = var3[var5];
                if (!"unknown".equalsIgnoreCase(ip2)) {
                    ip = ip2;
                    break;
                }
            }
        }

        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            InetAddress inet = null;

            try {
                inet = InetAddress.getLocalHost();
            } catch (UnknownHostException var8) {
                logger.error("getCurrentIP", var8);
            }

            if (inet != null) {
                ip = inet.getHostAddress();
            }
        }

        logger.info("getRemoteAddr ip: " + ip);
        return ip;
    }

    public static boolean isWhiteRequest(String url, int size, List<String> whiteUrls) {
        if (url != null && !"".equals(url) && size != 0) {
            url = url.toLowerCase();
            Iterator var3 = whiteUrls.iterator();

            String urlTemp;
            do {
                if (!var3.hasNext()) {
                    return false;
                }

                urlTemp = (String)var3.next();
            } while(url.indexOf(urlTemp.toLowerCase()) <= -1);

            return true;
        } else {
            return true;
        }
    }

}
