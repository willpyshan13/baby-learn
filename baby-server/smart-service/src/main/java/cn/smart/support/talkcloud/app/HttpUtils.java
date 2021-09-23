package cn.smart.support.talkcloud.app;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HttpUtils {

    private static final Logger logger = LogManager.getLogger(HttpUtils.class);

    public static String  httpSend(String url, List<ParamsBean> params) {

        logger.info("talk-cloud request url: {}", url);
        logger.info("talk-cloud request params: {}", params);

        String result = null;
        try {
            URL mUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDefaultUseCaches(false);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(getValue(params));
            out.flush();
            InputStream is = connection.getInputStream();
            result = StreamUtils.getStringFromStream(is);
            is.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("talk-cloud request failed. " + e.getMessage());
            System.out.println("异常");
        }
        return result;
    }

    public static void httpSends(String url, RequestCallback rcb) {
        try {
            URL mUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDefaultUseCaches(false);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            InputStream is = connection.getInputStream();
            String result = StreamUtils.getStringFromStream(is);
            rcb.callBack(result);
            is.close();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常");
        }

    }


    /**
     * @param urlStr urlStr
     * @param textMap textMap
     * @param fileMap fileMap
     * @return String
     */
    public static String formUpload(String urlStr, Map<String, String> textMap,
                                    Map<String, String> fileMap) {
        logger.info("talk-cloud request url: {}", urlStr);
        logger.info("talk-cloud request file: {}", fileMap);
        logger.info("talk-cloud request params: {}", textMap);
        String res = "";
        HttpURLConnection conn = null;
        String BOUNDARY =  UUID.randomUUID().toString().replace("-", "");;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();  //建立连接
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(60000);
            conn.setDoOutput(true);  //设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在  http正文内，因此需要设为true, 默认情况下是false;
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");//制定相应方式
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // text
            if (textMap != null) {
                StringBuilder strBuf = new StringBuilder();
                for (Object o : textMap.entrySet()) {
                    Map.Entry entry = (Map.Entry) o;
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(
                            "\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"").append(inputName).append("\"\r\n\r\n");
                    strBuf.append(inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }

            // file
            if (fileMap != null) {
                for (Object o : fileMap.entrySet()) {
                    Map.Entry entry = (Map.Entry) o;
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();
                    String contentType;
                    contentType = "application/octet-stream";
                    StringBuilder strBuf = new StringBuilder();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"").append(inputName).append("\"; filename=\"").append(filename).append("\"\r\n");
                    strBuf.append("Content-Type:").append(contentType).append("\r\n\r\n");
                    out.write(strBuf.toString().getBytes());
                    //使用UTF-8编码
                    out.write(EncodingUtil.getBytes(strBuf.toString(), "UTF-8")); //为了解决带中文的文件上传之后不显示名字
                    DataInputStream in = new DataInputStream(new FileInputStream(file));
                    int bytes;
                    byte[] bufferOut = new byte[1024];
                    while (-1 != (bytes = in.read(bufferOut))) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();


            StringBuilder strBuf = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
        } catch (Exception e) {
            logger.error("文档上传失败: {}", e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        logger.info("talk-cloud result: {}", res);
        return res;
    }

    private static String getValue(List<ParamsBean> params) {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            ParamsBean bean = params.get(i);
            value.append(bean.getKey()).append("=").append(bean.getValues());
            if (i + 1 != params.size()) {
                value.append("&");
            }
        }

        return value.toString();
    }

}
