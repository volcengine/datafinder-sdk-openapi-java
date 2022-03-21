package com.datarangers.sdk;

import com.datarangers.sdk.client.Client;
import com.datarangers.sdk.common.Constants;

import java.util.HashMap;

public class RangersClient extends Client {
    public RangersClient(String ak, String sk) {
        super(ak, sk);
    }

    public RangersClient(String ak, String sk, String url) {
        super(ak, sk, url);
    }

    public RangersClient(String ak, String sk, String url, int expiration) {
        super(ak, sk, url, expiration);
    }

    public RangersClient(String ak, String sk,  String url, int expiration, HashMap<String, String> services) {
        super(ak, sk, url, expiration, services);
    }

    public String dataRangers(String path, String method, HashMap<String, String> headers, HashMap<String, String> params, String body) throws Exception {
        return request(Constants.DATA_RANGGERS, method, path, headers, params, body);
    }

    public String dataRangers(String path, String method) throws Exception {
        return dataRangers(path, method, null, null, null);
    }

    public String dataRangers(String path) throws Exception {
        return dataRangers(path, Constants.GET);
    }


    public String analysisBase(String path, String method, HashMap<String, String> headers, HashMap<String, String> params, String body) throws Exception {
        return request(Constants.ANALYSIS_BASE, method, path, headers, params, body);
    }

    public String analysisBase(String path, String method) throws Exception {
        return analysisBase(path, method, null, null, null);
    }

    public String analysisBase(String path) throws Exception {
        return analysisBase(path, Constants.GET);
    }

    public String dataFinder(String path, String method, HashMap<String, String> headers, HashMap<String, String> params, String body) throws Exception {
        return request(Constants.DATA_FINDER, method, path, headers, params, body);
    }

    public String dataFinder(String path, String method) throws Exception {
        return dataFinder(path, method, null, null, null);
    }

    public String dataFinder(String path) throws Exception {
        return dataFinder(path, Constants.GET);
    }

    public String dataTracer(String path, String method, HashMap<String, String> headers, HashMap<String, String> params, String body) throws Exception {
        return request(Constants.DATA_TRACER, method, path, headers, params, body);
    }

    public String dataTracer(String path, String method) throws Exception {
        return dataTracer(path, method, null, null, null);
    }

    public String dataTracer(String path) throws Exception {
        return dataTracer(path, Constants.GET);
    }

    public String dataTester(String path, String method, HashMap<String, String> headers, HashMap<String, String> params, String body) throws Exception {
        return request(Constants.DATA_TESTER, method, path, headers, params, body);
    }

    public String dataTester(String path, String method) throws Exception {
        return dataTester(path, method, null, null, null);
    }

    public String dataTester(String path) throws Exception {
        return dataTester(path, Constants.GET);
    }

    public String dataAnalyzer(String path, String method, HashMap<String, String> headers, HashMap<String, String> params, String body) throws Exception {
        return request(Constants.DATA_ANALYZER, method, path, headers, params, body);
    }

    public String dataAnalyzer(String path, String method) throws Exception {
        return dataAnalyzer(path, method, null, null, null);
    }

    public String dataAnalyzer(String path) throws Exception {
        return dataAnalyzer(path, Constants.GET);
    }

    public String dataProfile(String path, String method, HashMap<String, String> headers, HashMap<String, String> params, String body) throws Exception {
        return request(Constants.DATA_PROFILE, method, path, headers, params, body);
    }

    public String dataProfile(String path, String method) throws Exception {
        return dataProfile(path, method, null, null, null);
    }

    public String dataProfile(String path) throws Exception {
        return dataProfile(path, Constants.GET);
    }
}
