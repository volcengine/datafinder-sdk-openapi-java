package com.datarangers.sdk.requests;

import com.datarangers.sdk.common.Constants;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

/***
 * 一些http请求方法
 */
public class Requests {
    public static String post(String url, HashMap<String, String> headers, HashMap<String, String> params, String body) {
        try {
            if (params != null) {
                url += formatParams(params);
            }
            URL urls = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
            connection.setRequestMethod("POST");//设置请求方式为POST
            headers.forEach((k, v) -> {
                connection.setRequestProperty(k, v);
            });
            connection.setDoOutput(true);//允许写出
            connection.setDoInput(true);//允许读入
            connection.setUseCaches(false);//不使用缓存
            connection.connect();//连接
            if (body != null) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(body);
                writer.close();
            }
            int responseCode = connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            String result = convertStreamToString(inputStream);//将流转换为字符串。
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
        }
    }

    public static String get(String url) {
        try {
            URL urls = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            String result = convertStreamToString(inputStream);//将流转换为字符串。
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public static String get(String url, HashMap<String, String> headers, HashMap<String, String> params) {
        try {
            if (params != null) {
                url += formatParams(params);
            }
            URL urls = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
            connection.setRequestMethod("GET");
            headers.forEach((k, v) -> {
                connection.setRequestProperty(k, v);
            });
            connection.connect();
            int responseCode = connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            String result = convertStreamToString(inputStream);//将流转换为字符串。
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    private static String formatParams(HashMap<String, String> params) {
        String param = "?";
        for (String key : params.keySet()) {
            param += key + "=" + params.get(key) + "&";
        }
        return param.substring(0, param.length() - 1);
    }


    public static String convertStreamToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        String reponse = sb.toString();
        return reponse;
    }

    public static String requests(String method, String url, HashMap<String, String> headers, String body, HashMap<String, String> params) {
        if (Constants.POST.equals(method)) {
            return post(url, headers, params, body);
        } else if (Constants.GET.equals(method)) {
            return get(url, headers, params);
        }
        return call(url, headers, params, body, method);
    }

    /**
     *  <LI>GET
     *  <LI>POST
     *  <LI>HEAD
     *  <LI>OPTIONS
     *  <LI>PUT
     *  <LI>DELETE
     *  <LI>TRACE
     */
    private static String call(String url, HashMap<String, String> headers, HashMap<String, String> params, String body, String method) {
        try {
            if (params != null) {
                url += formatParams(params);
            }
            URL urls = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
            connection.setRequestMethod(method);//设置请求方式为POST
            headers.forEach(connection::setRequestProperty);
            connection.setDoOutput(true);//允许写出
            connection.setDoInput(true);//允许读入
            connection.setUseCaches(false);//不使用缓存
            connection.connect();//连接
            if (body != null) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(body);
                writer.close();
            }
            int responseCode = connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            return convertStreamToString(inputStream);//将流转换为字符串。
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
