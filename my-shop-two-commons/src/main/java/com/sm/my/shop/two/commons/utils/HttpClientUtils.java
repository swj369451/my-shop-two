package com.sm.my.shop.two.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HttpClientUtils {
//    请求方式
    public static final String REQUEST_GET = "get";
    public static final String REQUEST_POST = "post";

//    cookie值
    public static final String HEADER_CONNECTION = "keep-alive";
    public static final String HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36";

    /**
     * get请求
     * @param url url路径
     * @return
     */
    public static String get(String url) {
        return getHttpClient(REQUEST_GET, url, null);
    }

    /**
     * 带cookie的get请求
     * @param url url路径
     * @param cookie cookie
     * @return
     */
    public static String get(String url, String cookie) {
        return getHttpClient(REQUEST_GET, url, cookie);
    }

    /**
     * post请求
     * @param url
     * @param paramList
     * @return
     */
    public static String post(String url, BasicNameValuePair... paramList) {
        return getHttpClient(REQUEST_POST, url, null, paramList);
    }

    /**
     * 带cookie的post请求
     * @param url
     * @param paramList
     * @return
     */
    public static String post(String url, String cookie, BasicNameValuePair... paramList) {
        return getHttpClient(REQUEST_POST, url, cookie, paramList);
    }

    /**
     * 创建并发送http请求
     * @param requestMethod 请求方式
     * @param url 请求地址
     * @param cookie 是否要带cookie
     * @param paramList 如果是post就有参数
     * @return 响应体
     */
    private static String getHttpClient(String requestMethod, String url, String cookie, BasicNameValuePair... paramList) {
//      创建http客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        请求方式
        HttpGet httpGet = null;
        HttpPost httpPost = null;
//        响应结果
        CloseableHttpResponse response = null;
        String result = null;

        try {
//      判断是get还是post请求
            if (REQUEST_GET.equals(requestMethod)) {
                httpGet = new HttpGet(url);
                //        设置header
                httpGet.setHeader("Connection", HEADER_CONNECTION);
                httpGet.setHeader("Cookie", cookie);
                httpGet.setHeader("User-Agent", HEADER_USER_AGENT);
                //          发起请求
                response = httpClient.execute(httpGet);
            } else if (REQUEST_POST.equals(requestMethod)) {
                httpPost = new HttpPost(url);
                //          设置header
                httpPost.setHeader("Connection", HEADER_CONNECTION);
                httpPost.setHeader("Cookie", cookie);
                httpPost.setHeader("User-Agent", HEADER_USER_AGENT);
                if (paramList != null && paramList.length > 0) {
                    //              创建请求参数
                    List<BasicNameValuePair> params = Arrays.asList(paramList);
//                    for (BasicNameValuePair param : params) {
//                        params.add(param);
//                    }
                    HttpEntity httpEntity = null;
                    httpEntity = new UrlEncodedFormEntity(params, "UTF-8");
                    httpPost.setEntity(httpEntity);
                    //              发起请求
                    response = httpClient.execute(httpPost);
                }
            }

//      获取内容
            HttpEntity entity = response.getEntity();
//      展示内容
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}

