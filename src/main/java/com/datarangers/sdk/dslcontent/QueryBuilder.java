package com.datarangers.sdk.dslcontent;

import java.util.HashMap;
import java.util.List;

public class QueryBuilder {
    private Query query;

    public QueryBuilder(Query query) {
        this.query = query;
    }

    public QueryBuilder sample(int samplePercent) {
        query.setSamplePrecent(samplePercent);
        return this;
    }

    public QueryBuilder showName(String name) {
        query.setShowName(name);
        return this;
    }

    public QueryBuilder showLabel(String label) {
        query.setShowLabel(label);
        return this;
    }

    public QueryBuilder event(String eventType,String eventName,String eventIndicator,int eventId){
        query.setEventID(eventId);
        query.setEventName(eventName);
        query.setEventType(eventType);
        query.setEventIndicator(eventIndicator);
        return this;
    }
    public QueryBuilder event(String eventType,String eventName,String eventIndicator){
        query.setEventName(eventName);
        query.setEventType(eventType);
        query.setEventIndicator(eventIndicator);
        return this;
    }
    public QueryBuilder event(String eventType,String eventName){
        query.setEventName(eventName);
        query.setEventType(eventType);
        return this;
    }

    public QueryBuilder measureInfo(String type,String name,int value){
        query.setMeasureInfo(new HashMap<String,Object>(){{
            put("measure_type",type);
            put("property_name",name);
            put("measure_value",value);
        }});
        return this;
    }

    public QueryBuilder andFilter(FilterBuilder fb){
        query.addFilter(fb.logic("and").builder());
        return this;
    }

    public QueryBuilder orFilter(FilterBuilder fb){
        query.addFilter(fb.logic("or").builder());
        return this;
    }

    public QueryBuilder group(String group){
        query.addGroup(group);
        return this;
    }

    public QueryBuilder group(List<String> group){
        query.addGroup(group);
        return this;
    }

    public Query builder(){
        return query;
    }
}
