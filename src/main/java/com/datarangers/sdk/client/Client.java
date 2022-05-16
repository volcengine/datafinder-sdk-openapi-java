package com.datarangers.sdk.client;

import com.datarangers.sdk.common.Constants;
import com.datarangers.sdk.requests.Requests;
import com.datarangers.sdk.util.DslSign;

import java.util.HashMap;

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

    public final String request(String service, String method, String path, HashMap<String, String> headers, HashMap<String, String> params, String body) throws Exception {
        method = method.toUpperCase();
        if (!Constants.METHOD_ALLODED.contains(method)) {
            throw new Client.ClientNotSupportException(Constants.METHOD_NOT_SUPPORT + ":" + method);
        }
        if (Constants.POST.equals(method) && body == null) {
            throw new Client.ClientNotSupportException(Constants.POST_BODY_NULL);
        }
        String servicePath = getServicePath(service);
        if (servicePath == null) {
            throw new Client.ClientNotSupportException(Constants.SERVICE_NOT_SUPPORT + ":" + service);
        }
        String serviceUrl = servicePath + path;
        String authorization = DslSign.sign(ak, sk, expiration, method, serviceUrl, params, body);
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put(Constants.AUTHORIZATION, authorization);
        if (Constants.POST.equals(method)) {
            headers.put(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
        }
        String url = this.url + serviceUrl;
        return Requests.requests(method, url, headers, body, params);
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
