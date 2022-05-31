package com.datarangers.sdk.client;

import com.datarangers.sdk.common.Constants;
import com.datarangers.sdk.requests.Requests;
import com.datarangers.sdk.util.DslSign;

import javax.activation.MimetypesFileTypeMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class Client {
    private String ak;
    private String sk;
    private String org = "dataRangers";
    private String url = "https://analytics.volcengineapi.com";
    private int expiration = 1800;
    private HashMap<String, String> services = new HashMap<String, String>() {
        {
            put(Constants.ANALYSIS_BASE, Constants.ANALYSIS_BASE_URL);
            put(Constants.DATA_FINDER, Constants.DATA_FINDER_URL);
            put(Constants.DATA_TRACER, Constants.DATA_TRACER_URL);
            put(Constants.DATA_TESTER, Constants.DATA_TESTER_URL);
            put(Constants.DATA_ANALYZER, Constants.DATA_ANALYZER_URL);
            put(Constants.DATA_RANGGERS, Constants.DATA_RANGGERS_URL);
            put(Constants.DATA_PROFILE, Constants.DATA_PROFILE_URL);
        }
    };

    public Client(String ak, String sk) {
        this.ak = ak;
        this.sk = sk;
    }

    public Client(String ak, String sk, String url) {
        this.ak = ak;
        this.sk = sk;
        this.url = url;
    }

    public Client(String ak, String sk, String url, int expiration) {
        this.ak = ak;
        this.sk = sk;
        this.url = url;
        this.expiration = expiration;
    }

    public Client(String ak, String sk, String url, int expiration, HashMap<String, String> services) {
        this.ak = ak;
        this.sk = sk;
        this.url = url;
        this.expiration = expiration;
        this.services = services;
    }

    protected String getServicePath(String service) {
        if (services.containsKey(service)) {
            return services.get(service);
        } else {
            return null;
        }
    }

    public final String request(String method, String serviceUrl, HashMap<String, String> headers, HashMap<String, String> params, String body) throws Exception {
        method = method.toUpperCase();
        if (!Constants.METHOD_ALLODED.contains(method)) {
            throw new Client.ClientNotSupportException(Constants.METHOD_NOT_SUPPORT + ":" + method);
        }

        String authorization = DslSign.sign(ak, sk, expiration, method, serviceUrl, params, body);
        if (headers == null) {
            headers = new HashMap<>();
        }
        headers.put(Constants.AUTHORIZATION, authorization);
        if (Constants.POST.equals(method) && !headers.containsKey(Constants.CONTENT_TYPE)) {
            headers.put(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
        }
        String url = this.url + serviceUrl;
        return Requests.requests(method, url, headers, body, params);
    }

    public final String request(String method,
                                String serviceUrl,
                                HashMap<String, String> headers,
                                HashMap<String, String> params,
                                File file) throws Exception {
        String boundary = UUID.randomUUID().toString();
        if (headers == null) {
            headers =  new HashMap<>();
        }
        headers.put(Constants.CONTENT_TYPE, "multipart/form-data; boundary=" + boundary);

        String contentType = new MimetypesFileTypeMap().getContentType(file);
        if (contentType == null || "".equals(contentType)) {
            contentType = "application/octet-stream";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n").append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:" + contentType + "\r\n\r\n");
        String fileContent = new BufferedReader(new InputStreamReader(new FileInputStream(file)))
                .lines().collect(Collectors.joining("\n"));
        sb.append(fileContent);
        sb.append("\r\n--" + boundary + "--\r\n");
        String body = sb.toString();

        return request(method, serviceUrl, headers, params, body);
    }

    protected final String request(String service, String method, String path, HashMap<String, String> headers, HashMap<String, String> params, String body) throws Exception {
        String servicePath = getServicePath(service);
        if (servicePath == null) {
            throw new Client.ClientNotSupportException(Constants.SERVICE_NOT_SUPPORT + ":" + service);
        }
        String serviceUrl = servicePath + path;

        return request(method, serviceUrl, headers, params, body);
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public String getAk() {
        return ak;
    }

    public String getSk() {
        return sk;
    }

    public String getOrg() {
        return org;
    }

    public String getUrl() {
        return url;
    }

    public int getExpiration() {
        return expiration;
    }

    class ClientNotSupportException extends Exception {
        public ClientNotSupportException(String message) {
            super(message);
        }
    }
}
