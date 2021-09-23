package cn.smart.common.http;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;

/** 
 * http客户端工具类（支持短连接，非连接池）
 * @author yusheng
 * @since 2018-2-27
 * @version 1.0 
 */
public class HttpClientUtils {
	
	private static Logger logger = LogManager.getLogger(HttpClientUtils.class);
	
	/**
	 * 默认参数编码
	 */
	private static String              DEFAULT_CHARSET                     = "utf-8";
	
    /** 连接超时时间，由bean factory设置，缺省为8秒钟 */
    private int                        DEFAULT_CONNECTION_TIMEOUT            = 8000;

    /** 回应超时时间, 由bean factory设置，缺省为30秒钟 */
    private int                        DEFAULT_SO_TIMEOUT                    = 30000;
    
    /**
     * HTTP连接管理器，该连接管理器必须是线程安全的.
     */
    private HttpConnectionManager connectionManager;
    
    private static HttpClientUtils httpClientUtils = new HttpClientUtils();
    
    private HttpClientUtils() {
    	connectionManager = new SimpleHttpConnectionManager();
    }
    
    public static HttpClientUtils getInstance() {
    	return httpClientUtils;
    }

    /**
     * 执行Http请求
     * 
     * @param request 请求数据
     * @return null时表示请求不成功
     */
    public HttpResponse execute(HttpRequest request) {
    	HttpClient httpclient = new HttpClient();
    	
    	//连接超时时间
        int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
        if (request.getConnectionTimeout() > 0) {
            connectionTimeout = request.getConnectionTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
        
        // 设置回应超时
        int soTimeout = DEFAULT_SO_TIMEOUT;
        if (request.getTimeout() > 0) {
            soTimeout = request.getTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
    	
        String charset = request.getCharset();
        charset = charset == null ? DEFAULT_CHARSET : charset;
    	HttpMethod method;

        method = getHttpMethod(request, charset);

        // 设置Http Header中的User-Agent属性
    	if(StringUtils.isEmpty(request.getUserAgent())) {
    		method.addRequestHeader("User-Agent", "Mozilla/4.0");
    	} else {
    		method.addRequestHeader("User-Agent", request.getUserAgent());
    	}
    	
    	if(!StringUtils.isEmpty(request.getAcceptLanguage())) {
    		method.addRequestHeader("accept-language", request.getAcceptLanguage());
    	}
        HttpResponse response = new HttpResponse();

        if (execute(request, httpclient, method, response)) {
            return null;
        }
        return response;
    }
    
    /**
     * P2P理财执行Http请求
     * 
     * @param request 请求数据
     * @return null时表示请求不成功
     */
    public HttpResponse FinancExecute(HttpRequest request, String sessionId) {
    	HttpClient httpclient = new HttpClient();
    	
    	//连接超时时间
        int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
        if (request.getConnectionTimeout() > 0) {
            connectionTimeout = request.getConnectionTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
        
        // 设置回应超时
        int soTimeout = DEFAULT_SO_TIMEOUT;
        if (request.getTimeout() > 0) {
            soTimeout = request.getTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
    	
        String charset = request.getCharset();
        charset = charset == null ? DEFAULT_CHARSET : charset;
    	HttpMethod method;

        method = getHttpMethod(request, charset);

        // 设置Http Header中的User-Agent属性
    	if(StringUtils.isEmpty(request.getUserAgent())) {
    		method.addRequestHeader("User-Agent", "Mozilla/4.0");
    	} else {
    		method.addRequestHeader("User-Agent", request.getUserAgent());
    	}
    	
    	if(!StringUtils.isEmpty(request.getAcceptLanguage())) {
    		method.addRequestHeader("accept-language", request.getAcceptLanguage());
    	}
        //若sessionId不为空,则放入cookie中
    	if(StringUtils.isNotBlank(sessionId)){
    		method.addRequestHeader("Cookie", sessionId);
    	}
        HttpResponse response = new HttpResponse();

        if (execute(request, httpclient, method, response)) {
            return null;
        }
        return response;
    }

    private boolean execute(HttpRequest request, HttpClient httpclient, HttpMethod method, HttpResponse response) {
        try {
            httpclient.executeMethod(method);
            if (request.getResultType().equals(HttpResultType.STRING)) {
                response.setStringResult(method.getResponseBodyAsString());
            } else if (request.getResultType().equals(HttpResultType.BYTES)) {
                response.setByteResult(method.getResponseBody());
            }
            response.setResponseHeaders(method.getResponseHeaders());
        } catch (UnknownHostException ex) {
        	logger.error("http请求未知主机", ex);
            return true;
        } catch (IOException ex) {
        	logger.error("http请求读写IO异常", ex);
            return true;
        } catch (Exception ex) {
        	logger.error("http请求异常", ex);
            return true;
        } finally {
            method.releaseConnection();
        }
        return false;
    }

    private HttpMethod getHttpMethod(HttpRequest request, String charset) {
        HttpMethod method;
        if (request.getMethod().equals(HttpRequest.METHOD_GET)) {//GET
            method = new GetMethod(request.getUrl());
            method.getParams().setCredentialCharset(charset);

            // parseNotifyConfig会保证使用GET方法时，request一定使用QueryString
            method.setQueryString(request.getQueryString());
        } else {//POST
            method = new PostMethod(request.getUrl());
            ((PostMethod) method).addParameters(generatNameValuePair(request.getParameters()));
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);
        }
        return method;
    }

    /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }

    private static final String APPLICATION_JSON = "application/json;charset=uft-8";

    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    /**
     * 使用http Post 发送json
     * @param url url
     * @param json json
     * @throws Exception
     */
    public static void httpPostWithJSON(String url, String json) throws Exception {
        // 将JSON进行UTF-8编码,以便传输中文
        String encoderJson = URLEncoder.encode(json, "UTF-8");

        HttpClient httpclient = new HttpClient();
        PostMethod method = new PostMethod(url);
        RequestEntity requestEntity = new StringRequestEntity(encoderJson, null, null);
        method.setRequestEntity(requestEntity);
        method.addRequestHeader("Content-Type",APPLICATION_JSON);
        int result = httpclient.executeMethod(method);

        System.out.println( "Response status code: " + result);
        System.out.println( "Response body: " );
        System.out.println(method.getResponseBodyAsString());
        method.releaseConnection();
    }
    
    public static void main(String[] args) throws Exception{
		//Map map = new HashMap();
		//map.put("amount", 1d);
		//generatNameValuePair(map);

//        String url = "http://192.168.151.133:8089/tmdata/Dispatcher.do";
//        String json = "{" +
//                        "service: \"s_scenic_info\"," +
//                        "count: 10," +
//                        "batchNo: \"JQ201609011230590001\"," +
//                        "flag : \"11111\"," +
//                        "data: [" +
//                            "{" +
//                                "displayName: \"中文15980851200\"," +
//                                "authenTicket: \"8b11d343d766d4af88d6b8746ec4e786\"," +
//                                "authenUserId: \"1120d8d6ea4a4850b65d0faa40d6dffb\"," +
//                                "timestamp: 20151009195525\n" +
//                            "}," +
//                            "{" +
//                                "displayName: \"15980851200\"," +
//                                "authenTicket: \"8b11d343d766d4af88d6b8746ec4e786\"," +
//                                "authenUserId: \"1120d8d6ea4a4850b65d0faa40d6dffb\"," +
//                                "timestamp: 20151009195525" +
//                            "}" +
//                            "]" +
//                        "}";
//        String json2 = "{\"service\":\"s_weather_info\",\"count\":20,\"data\":[{\"countryNo\":\"156\",\"countryName\":\"中文\",\"provinceNo\":\"156410000\",\"provinceName\":\"中文\",\"cityNo\":\"156411000\",\"cityName\":\"中文\",\"countyNo\":\"156411023\",\"countyName\":\"中文\",\"scenicNo\":1,\"scenicName\":\"中文\",\"scenicGeoLng\":\"113.856255\",\"scenicGeoLat\":\"34.01066\",\"dataDt\":\"20161009\",\"publishTime\":\"20161009170910\",\"weather\":\"中文\",\"temperature\":\"16℃\",\"windForce\":\"中文\",\"aqi\":60,\"pm\":34,\"humidity\":\"50%\",\"weatherTomorrow\":\"中文\",\"temperatureTomorrow\":\"18中文\",\"windForceTomorrow\":\"中文\",\"aqiTomorrow\":\"\",\"humidityTomorrow\":\"\",\"weatherBackSecond\":\"中文\",\"weatherBackTrid\":\"21中文\",\"temperatureBackSecond\":\"中文\",\"temperatureBackTrid\":\"19中文\",\"remark\":\"中文\",\"cityInfo\":\"101180401-中文\"}]}";
//        httpPostWithJSON(url,json2);
	}

}
