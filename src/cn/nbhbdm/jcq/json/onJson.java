package cn.nbhbdm.jcq.json;

import java.io.BufferedReader;
import java.util.Map;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;

/**
 * nbhbdm-jcq - 获取 API 数据类
 *
 * @author TheZihanGu
 * @date 2020/07/12
 */

public class onJson {
    public static void main(String[] args) {
        System.out.println(result("这是一个测试"));

    }
    public static String result(String keyword) {
        String requestUrl = "https://api.nbhbdm.cn/api/nbhbdm";
        Map params = new HashMap();
        params.put("keyword", keyword);
        String s = httpRequest(requestUrl, params);
        return s;
    }

    private static String httpRequest(String requestUrl, Map params) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl+"?"+urlencode(params));
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.connect();

            InputStream inputStream = httpUrlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader =  new BufferedReader(inputStreamReader);

            String str = null;
            while((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            httpUrlConnection.disconnect();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return buffer.toString();
    }

    public static String urlencode(Map<String, Object>data) {

        StringBuilder sb = new StringBuilder();
        for(Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();

    }
}