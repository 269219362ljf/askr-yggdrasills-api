package askr.midgard.utils;

import askr.midgard.model.ApiRequest;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class APINetUtils {
    /**
     * @param apiRequest 请求结构体
     * @return  请求结果
     * @throws Exception
     */
    public static String net(ApiRequest apiRequest) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        String strUrl=apiRequest.getUrl();
        Map params=apiRequest.getParams();
        try {
            StringBuffer sb = new StringBuffer();
            if(strUrl==null || apiRequest.getMethod().equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(apiRequest.getMethod()==null || apiRequest.getMethod().equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", apiRequest.getUserAgent());
            conn.setUseCaches(false);
            conn.setConnectTimeout(apiRequest.getDefConnTimeout());
            conn.setReadTimeout(apiRequest.getDefReadTimeout());
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && apiRequest.getMethod().equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    throw e;
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, apiRequest.getDefChatset()));
            String strRead = null;
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

    //将map型转为请求参数型
    private static String urlencode(Map<String,Object>data) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                throw e;
            }
        }
        return sb.toString();
    }

}
