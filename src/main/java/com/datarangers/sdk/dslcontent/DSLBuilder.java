package com.datarangers.sdk.dslcontent;

import com.datarangers.sdk.DSL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DSLBuilder {
    private DSL dsl;
    private String queryType;

    public DSLBuilder(DSL dsl) {
        this.dsl = dsl;
        queryType = null;
    }

    public DSLBuilder(DSL dsl, String queryType) {
        this.dsl = dsl;
        this.queryType = queryType;
    }

    public DSLBuilder appIds(List<Integer> appId) {
        dsl.addAppId(appId);
        return this;
    }

    public DSLBuilder appIds(int addId) {
        dsl.addAppId(addId);
        return this;
    }

    public DSLBuilder queryType(String queryType) {
        this.dsl.getContentBuilder().queryType(queryType);
        if ("funnel".equals(queryType)) {
            this.optimized(true);
        } else {
            this.optimized(false);
        }
        return this;
    }

    public DSLBuilder rangePeriod(String granularity, int start, int end, boolean realtime) {
        HashMap<String, Object> period = new HashMap<String, Object>() {{
            put("type", "range");
            put("granularity", granularity);
            put("range", new ArrayList<Integer>(Arrays.asList(start, end)));
        }};
        if (realtime) {
            period.put("real_time", realtime);
        }
        this.dsl.addPeriods(period);
        return this;
    }

    public DSLBuilder rangePeriod(String granularity, int start, int end) {
        HashMap<String, Object> period = new HashMap<String, Object>() {{
            put("type", "range");
            put("granularity", granularity);
            put("range", new ArrayList<Integer>(Arrays.asList(start, end)));
        }};
        this.dsl.addPeriods(period);
        return this;
    }

    public DSLBuilder lastPeriod(String granularity, int amount, String unit) {
        return lastPeriod(granularity, amount, unit, false);
    }

    public DSLBuilder lastPeriod(String granularity, int amount, String unit, boolean realtime) {
        HashMap<String, Object> period = new HashMap<String, Object>() {
            {
                put("type", "last");
                put("granularity", granularity);
                put("last", new HashMap<String, Object>() {
                    {
                        put("amount", amount);
                        put("unit", unit);
                    }
                });
            }
        };
        if (realtime) {
            period.put("real_time", realtime);
        }
        this.dsl.addPeriods(period);
        return this;
    }

    public DSLBuilder todayPeriod(String granularity, boolean realtime) {
        HashMap<String, Object> period = new HashMap<String, Object>() {{
            put("type", "today");
            put("granularity", granularity);
        }};
        if (realtime) {
            period.put("real_time", realtime);
        }
        this.dsl.addPeriods(period);
        return this;
    }
    public DSLBuilder todayPeriod(String granularity) {
        return todayPeriod(granularity,false);
    }

    public DSLBuilder group(String pg) {
        this.dsl.getContentBuilder().profileGroup(pg);
        return this;
    }

    public DSLBuilder group(List<String> pgs) {
        this.dsl.getContentBuilder().profileGroup(pgs);
        return this;
    }

    public DSLBuilder order(String order) {
        this.dsl.getContentBuilder().orders(order, "asc");
        return this;
    }

    public DSLBuilder order(String order, String direction) {
        this.dsl.getContentBuilder().orders(order, direction);
        return this;
    }

    public DSLBuilder order(HashMap<String, String> order) {
        this.dsl.getContentBuilder().orders(order);
        return this;
    }

    public DSLBuilder order(List<HashMap<String, String>> order) {
        this.dsl.getContentBuilder().orders(order);
        return this;
    }

    public DSLBuilder page(int limit, int offset) {
        this.dsl.getContentBuilder().page(limit, offset);
        return this;
    }

    public DSLBuilder limit(int limit) {
        this.dsl.getContentBuilder().limit(limit);
        return this;
    }

    public DSLBuilder offset(int offset) {
        this.dsl.getContentBuilder().offset(offset);
        return this;
    }

    public DSLBuilder skipCache(boolean sc) {
        this.dsl.getContentBuilder().option("skip_cache", sc);
        return this;
    }

    public DSLBuilder isStack(boolean stack) {
        this.dsl.getContentBuilder().option("is_stack", stack);
        return this;
    }

    public DSLBuilder optimized(boolean opt) {
        this.dsl.getContentBuilder().option("optimized", opt);
        return this;
    }

    protected DSLBuilder lifeCycle(String granularity, int interval, String type) {
        this.dsl.getContentBuilder().option("lifecycle_query_type", type);
        this.dsl.getContentBuilder().option("lifecycle_period", new HashMap<String, Object>() {
            {
                put("granularity", granularity);
                put("period", interval);
            }
        });
        return this;
    }

    protected DSLBuilder lifeCycle(String granularity, int interval) {
        return lifeCycle(granularity, interval, "stickiness");
    }

    protected DSLBuilder retention(String granularity, int interval) {
        this.dsl.getContentBuilder().option("retention_type", granularity);
        this.dsl.getContentBuilder().option("retention_n_days", interval);
        return this;
    }

    public DSLBuilder web(String type, int timeout) {
        this.dsl.getContentBuilder().option("web_session_params", new HashMap<String, Object>() {{
            put("session_params_type", type);
            put("session_timeout", timeout);
        }});
        return this;
    }

    public DSLBuilder product(String product) {
        this.dsl.getContentBuilder().option("product", product);
        return this;
    }

    public DSLBuilder advertise(HashMap<String, Object> adp) {
        return option(adp);
    }

    public DSLBuilder option(HashMap<String, Object> options) {
        for (String key : options.keySet()) {
            this.dsl.getContentBuilder().option(key, options.get(key));
        }
        return this;
    }

    public DSLBuilder tags(HashMap<String, Object> tags) {
        for (String key : tags.keySet()) {
            this.dsl.getContentBuilder().showOption(key, tags.get(key));
        }
        return this;
    }

    public DSLBuilder andProfileFilter(FilterBuilder fb) {
        this.dsl.getContentBuilder().profileFilter(fb.logic("and").builder());
        return this;
    }

    public DSLBuilder orProfileFilter(FilterBuilder fb) {
        this.dsl.getContentBuilder().profileFilter(fb.logic("or").builder());
        return this;
    }

    public DSLBuilder query(List<QueryBuilder> qbs) {
        List<Query> queries = new ArrayList<>();
        for (QueryBuilder qb : qbs) {
            queries.add(qb.builder());
        }
        dsl.getContentBuilder().query(queries);
        return this;
    }

    public DSLBuilder query(QueryBuilder qb) {
        dsl.getContentBuilder().query(qb.builder());
        return this;
    }

    public DSLBuilder periods(List<HashMap<String, Object>> periods) {
        dsl.setPeriods(periods);
        return this;
    }

    public DSL builder() {
        dsl.setContent(dsl.getContentBuilder().build());
        return dsl;
    }

    public DSLBuilder window(String granularity, int interval) {
        if ("life_cycle".equals(queryType)) {
            lifeCycle(granularity, interval);
        } else if ("retention".equals(queryType)) {
            retention(granularity, interval);
        } else {
            dsl.getContentBuilder().option("window_period_type", granularity);
            dsl.getContentBuilder().option("window_period", interval);
        }
        return this;
    }
}
