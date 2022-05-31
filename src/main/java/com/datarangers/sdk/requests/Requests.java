package com.datarangers.sdk.requests;

import com.datarangers.sdk.common.Constants;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/***
 * 一些http请求方法
 */
public class Requests {
    public static String post(String url, Map<String, String> headers, Map<String, String> params, String body) {
        HttpURLConnection connection = null;
        try {
            if (params != null) {
                url += formatParams(params);
            }
            URL urls = new URL(url);
            connection = (HttpURLConnection) urls.openConnection();
            //设置请求方式为POST
            connection.setRequestMethod("POST");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            //允许写出
            connection.setDoOutput(true);
            //允许读入
            connection.setDoInput(true);
            //不使用缓存
            connection.setUseCaches(false);
            connection.connect();//连接
            if (body != null) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(body);
                writer.close();
            }
            connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            //将流转换为字符串
            String result = convertStreamToString(inputStream);
            return result;
        } catch (ProtocolException e) {
            e.printStackTrace();
            return e.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static String get(String url) {
        HttpURLConnection connection = null;
        try {
            URL urls = new URL(url);
            connection = (HttpURLConnection) urls.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            //将流转换为字符串
            String result = convertStreamToString(inputStream);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static String get(String url, Map<String, String> headers, Map<String, String> params) {
        HttpURLConnection connection = null;
        try {
            if (params != null) {
                url += formatParams(params);
            }
            URL urls = new URL(url);
            connection = (HttpURLConnection) urls.openConnection();
            connection.setRequestMethod("GET");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            connection.connect();
            connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            //将流转换为字符串
            String result = convertStreamToString(inputStream);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String formatParams(Map<String, String> params) {
        String param = "?";
        for (String key : params.keySet()) {
            param += key + "=" + params.get(key) + "&";
        }
        return param.substring(0, param.length() - 1);
    }


    public static String convertStreamToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }
        String res = sb.toString();
        return res;
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
        HttpURLConnection connection = null;
        try {
            if (params != null) {
                url += formatParams(params);
            }
            URL urls = new URL(url);
            connection = (HttpURLConnection) urls.openConnection();
            //设置请求方式为POST
            connection.setRequestMethod(method);
            headers.forEach(connection::setRequestProperty);
            //允许写出
            connection.setDoOutput(true);
            //允许读入
            connection.setDoInput(true);
            //不使用缓存
            connection.setUseCaches(false);
            //连接
            connection.connect();
            if (body != null) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(body);
                writer.close();
            }
            connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            //将流转换为字符串。
            return convertStreamToString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
