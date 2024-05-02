package com.back.utils;


import com.alibaba.fastjson.JSON;
import com.back.response.HttpResult;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 封装HttpClient常用方法
 */
public class HttpClientUtil {

    private static HttpClientUtil httpClientUtil;
    //设置连接超时时间，单位毫秒。
    public final static int CONNECT_TIMEOUT = 30000;
    //设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
    public final static int CONNECT_REQUEST_TIMEOUT = 10000;
    //请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
    public final static int SOCKET_TIMEOUT = 30000;

    private HttpClientUtil() {
    }

    public static synchronized HttpClientUtil getHttpClientUtil() {
        if (httpClientUtil == null) {
            httpClientUtil = new HttpClientUtil();
        }
        return httpClientUtil;
    }

    /**
     * 不带参数的get 表单形式
     *
     * @param uri
     * @return
     * @throws Exception
     */
    public String doGet(String uri) throws Exception {
        return doGet(uri, null);
    }

    /**
     * 1.带参数的get请求 body形式
     * 2.添加请求头header参数token
     * 3.设置超时时长
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public String doGetAndHeader(String url, Map<String, Object> map, String tokenK, String tokenV) throws Exception {
        // 1.创建URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);

        // 2.设置请求参数
        if (map != null) {
            // 遍历请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // 封装请求参数
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        // 3.创建请求对象httpGet
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        /*
         * 添加请求头信息
         */
        // 浏览器表示
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
        // 传输的类型
        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
        //在请求头header中添加token参数
        httpGet.addHeader(tokenK, tokenV);

        // 3. 使用Httpclient发起请求
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)//设置连接超时时间，单位毫秒。
                .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)//设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
                .setSocketTimeout(SOCKET_TIMEOUT)//请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
                .build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpclient.execute(httpGet);

        // 4. 解析返回数据，封装HttpResult
        // 4.1状态码
        int code = response.getStatusLine().getStatusCode();
        // 4.2 响应体内容
        String body = null;
        if (response.getEntity() != null) {
            body = EntityUtils.toString(response.getEntity(), "UTF-8");
        }

//        HttpResult result = new HttpResult();
//        result.setCode(code);
//        result.setBody(body);
        return body;
    }

    /**
     * 带参数的get请求 表单形式
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public String doGet(String url, Map<String, Object> map) throws Exception {
        // 1.创建URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);

        // 2.设置请求参数
        if (map != null) {
            // 遍历请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // 封装请求参数
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 3.创建请求对象httpGet
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        // 4.使用httpClient发起请求
        CloseableHttpResponse response = HttpClients.createDefault().execute(httpGet);

        // 5.解析返回结果，封装返回对象httpResult
        // 5.1获取状态码
        int code = response.getStatusLine().getStatusCode();

        // 5.2 获取响应体
        // 使用EntityUtils.toString方法必须保证entity不为空
        String body = null;
        if (response.getEntity() != null) {
            body = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return body;
    }

    /**
     * 不带参数的post请求 表单形式
     *
     * @param url
     * @return
     */
    public HttpResult doPost(String url) throws Exception {
        return doPost(url, null);
    }

    /**
     * 带参数的post请求 表单形式
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 1. 声明httppost
        HttpPost httpPost = new HttpPost(url);

        // 2.封装请求参数，请求数据是表单
        // 声明封装表单数据的容器
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        if (map != null) {

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // 封装请求参数到容器中
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
        }
        // 创建表单的Entity类
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "UTF-8");

        // 3. 把封装好的表单实体对象设置到HttpPost中
        httpPost.setEntity(entity);

        // 4. 使用Httpclient发起请求
        CloseableHttpResponse response = HttpClients.createDefault().execute(httpPost);

        // 5. 解析返回数据，封装HttpResult
        // 5.1状态码
        int code = response.getStatusLine().getStatusCode();
        // 5.2 响应体内容
        String body = null;
        if (response.getEntity() != null) {
            body = EntityUtils.toString(response.getEntity(), "UTF-8");
        }

        HttpResult result = new HttpResult();
        result.setCode(code);
        result.setBody(body);
        return result;
    }

    /**
     * 1.带参数的post请求 body形式
     * 2.添加请求头header参数token
     * 3.设置超时时长
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPostAndHeader(String url, Map<String, Object> map, String tokenK, String tokenV) throws Exception {
        // 1. 声明httppost
        HttpPost httpPost = new HttpPost(url);

        //2. 把body参数设置到HttpPost中
        httpPost.setEntity(new StringEntity(JSON.toJSONString(map)));
        /*
         * 添加请求头信息
         */
        // 浏览器表示
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
        // 传输的类型
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        //在请求头header中添加token参数
        httpPost.addHeader(tokenK, tokenV);

        // 3. 使用Httpclient发起请求
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)//设置连接超时时间，单位毫秒。
                .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)//设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
                .setSocketTimeout(SOCKET_TIMEOUT)//请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
                .build();
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = httpclient.execute(httpPost);

        // 4. 解析返回数据，封装HttpResult
        // 4.1状态码
        int code = response.getStatusLine().getStatusCode();
        // 4.2 响应体内容
        String body = null;
        if (response.getEntity() != null) {
            body = EntityUtils.toString(response.getEntity(), "UTF-8");
        }

        HttpResult result = new HttpResult();
        result.setCode(code);
        result.setBody(body);
        return result;
    }

    /**
     * 不带参数的put请求 表单形式
     *
     * @param url
     * @return
     */
    public HttpResult doPut(String url) throws Exception {
        return doPut(url, null);
    }

    /**
     * 带参数的put请求 表单形式
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPut(String url, Map<String, Object> map) throws Exception {
        // 1. 声明httpput
        HttpPut httpPut = new HttpPut(url);

        // 2.封装请求参数，请求数据是表单
        if (map != null) {
            // 声明封装表单数据的容器
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // 封装请求参数到容器中
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            // 创建表单的Entity类
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "UTF-8");

            // 3. 把封装好的表单实体对象设置到HttpPost中
            httpPut.setEntity(entity);
        }
        // 4. 使用Httpclient发起请求
        CloseableHttpResponse response = HttpClients.createDefault().execute(httpPut);

        // 5. 解析返回数据，封装HttpResult
        // 5.1状态码
        int code = response.getStatusLine().getStatusCode();
        // 5.2 响应体内容
        String body = null;
        if (response.getEntity() != null) {
            body = EntityUtils.toString(response.getEntity(), "UTF-8");
        }

        HttpResult result = new HttpResult();
        result.setCode(code);
        result.setBody(body);
        return result;
    }

    /**
     * 不带参数的delete 表单形式
     *
     * @param uri
     * @return
     * @throws Exception
     */
    public HttpResult doDelete(String uri) throws Exception {
        return doDelete(uri, null);
    }

    /**
     * 带参数的delete 表单形式
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doDelete(String url, Map<String, Object> map) throws Exception {
        // 1.创建URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);

        // 2.设置请求参数
        if (map != null) {
            // 遍历请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // 封装请求参数
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 3.创建请求对象httpGet
        HttpDelete httpDelete = new HttpDelete(uriBuilder.build());

        // 4.使用httpClient发起请求
        CloseableHttpResponse response = HttpClients.createDefault().execute(httpDelete);

        // 5.解析返回结果，封装返回对象httpResult
        // 5.1获取状态码
        int code = response.getStatusLine().getStatusCode();

        // 5.2 获取响应体
        // 使用EntityUtils.toString方法必须保证entity不为空
        String body = null;
        if (response.getEntity() != null) {
            body = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        HttpResult result = new HttpResult();
        result.setCode(code);
        result.setBody(body);
        return result;
    }
}
