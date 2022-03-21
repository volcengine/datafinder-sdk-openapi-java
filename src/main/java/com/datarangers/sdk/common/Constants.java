package com.datarangers.sdk.common;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
    public static final ArrayList<String> METHOD_ALLODED = new ArrayList<String>(Arrays.asList("POST", "GET", "DELETE", "PUT", "PATCH"));
    public static final String METHOD_NOT_SUPPORT = "method not support";
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String POST_BODY_NULL = "post method mush contains body";
    public static final String SERVICE_NOT_SUPPORT = "service not support";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json;charset=utf-8";

    public static final String ANALYSIS_BASE = "analysis_base";
    public static final String DATA_FINDER = "data_finder";
    public static final String DATA_TRACER = "data_tracer";
    public static final String DATA_TESTER = "data_tester";
    public static final String DATA_ANALYZER = "data_analyzer";
    public static final String DATA_RANGGERS = "data_rangers";

    public static final String ANALYSIS_BASE_URL = "/analysisbase";
    public static final String DATA_FINDER_URL = "/datafinder";
    public static final String DATA_TRACER_URL = "/datatracer";
    public static final String DATA_TESTER_URL = "/datatester";
    public static final String DATA_ANALYZER_URL = "/dataanalyzer";
    public static final String DATA_RANGGERS_URL = "/datarangers";

    public static final String DATA_PROFILE = "dataprofile";
    public static final String DATA_PROFILE_URL = "/dataprofile";
}
