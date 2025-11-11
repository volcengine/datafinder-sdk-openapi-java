package com.datarangers.sdk.requests;

import com.datarangers.sdk.common.Constants;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/***
 * 一些http请求方法
 */
public class Requests {
    private static final Object LOCK = new Object();
    private static final long DEFAULT_TIMEOUT = 60L;
    private static volatile OkHttpClient okHttpClient;

    public static void init(OkHttpClient okHttpClient) {
        if (Requests.okHttpClient != null) {
            throw new RuntimeException("ok httpclient has init");
        }
        Requests.okHttpClient = okHttpClient;
    }

    /**
     * 设置超时时间，必须选设置
     * @param timeout
     */
    public static void initTimeout(long timeout) {
        // 初始化
        getHttpClient(timeout);
    }

    private static OkHttpClient getHttpClient(){
        return getHttpClient(DEFAULT_TIMEOUT);
    }

    private static OkHttpClient getHttpClient(long timeout) {
        if (okHttpClient == null) {
            synchronized (LOCK) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS)
                            .readTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS)
                            .writeTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS)
                            .build();

                }
            }
        }
        return okHttpClient;
    }

    public static String post(String url, Map<String, String> headers, Map<String, String> params, String body) {
        try {
            if (params != null) {
                url += formatParams(params);
            }
            return requestMethod("POST", url, body, headers);
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public static String get(String url) {
        try {
            return requestGet(url, new HashMap<>());
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public static String get(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            if (params != null) {
                url += formatParams(params);
            }
            return requestGet(url, headers);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    private static String formatParams(Map<String, String> params) {
        String param = "?";
        for (String key : params.keySet()) {
            param += key + "=" + params.get(key) + "&";
        }
        return param.substring(0, param.length() - 1);
    }


    public static String requests(String method, String url, Map<String, String> headers, String body, Map<String, String> params) {
        if (Constants.POST.equals(method)) {
            return post(url, headers, params, body);
        } else if (Constants.GET.equals(method)) {
            return get(url, headers, params);
        }
        return call(url, headers, params, body, method);
    }

    /**
     * <LI>GET
     * <LI>POST
     * <LI>HEAD
     * <LI>OPTIONS
     * <LI>PUT
     * <LI>DELETE
     * <LI>TRACE
     */
    private static String call(String url, Map<String, String> headers, Map<String, String> params, String body, String method) {
        try {
            if (params != null) {
                url += formatParams(params);
            }
            return requestMethod(method, url, body, headers);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    private static String requestGet(String url, Map<String, String> headers) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .build();
        return getResponse(request);
    }

    private static String requestMethod(String method, String url, String bodyContent,
                                        Map<String, String> headers) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("charset=utf-8"), bodyContent);
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .method(method, body)
                .build();
        return getResponse(request);
    }

    private static String getResponse(Request request) throws IOException {
        OkHttpClient client = getHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (response == null) {
                return null;
            }
            return response.body() == null ? response.toString() : response.body().string();
        }
    }

    public static InputStream requestsStream(String method, String url, Map<String, String> headers, String body, Map<String, String> params) throws IOException {
        if (params != null) {
            url += formatParams(params);
        }
        if (Constants.GET.equals(method)) {
            return requestGetStream(url, headers);
        }
        return requestMethodStream(method, url, headers, body);
    }

    private static InputStream requestGetStream(String url, Map<String, String> headers) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .build();
        return getRequestStream(request);
    }

    private static InputStream requestMethodStream(String method, String url, Map<String, String> headers, String bodyContent) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("charset=utf-8"), bodyContent);
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .method(method, body)
                .build();
        return getRequestStream(request);
    }

    private static InputStream getRequestStream(Request request) throws IOException {
        OkHttpClient client = getHttpClient();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            response.close(); 
            throw new IOException("Unexpected code " + response);
        }

        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            return responseBody.byteStream();  
        } else {
            response.close();
            return null;
        }
    }
}
