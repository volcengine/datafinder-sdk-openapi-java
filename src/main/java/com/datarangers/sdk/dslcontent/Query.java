package com.datarangers.sdk.dslcontent;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query {
    @JSONField(name = "sample_percent")
    private int samplePrecent = 100;
    @JSONField(name = "show_name")
    private String showName;
    @JSONField(name = "show_label")
    private String showLabel;
    @JSONField(name = "event_id")
    private Object eventID = null;
    @JSONField(name = "event_type")
    private String eventType;
    @JSONField(name = "event_name")
    private String eventName;
    @JSONField(name = "event_indicator")
    private String eventIndicator;
    @JSONField(name = "measure_info")
    private Map<String, Object> measureInfo = new HashMap<>();
    private List<Filter> filters = new ArrayList<>();
    private List<String> groups = new ArrayList<>();

    public Query() {
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    public void addGroup(String group) {
        groups.add(group);
    }

    public void addGroup(List<String> group) {
        groups.addAll(group);
    }

    public void setSamplePrecent(int samplePrecent) {
        this.samplePrecent = samplePrecent;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setShowLabel(String showLabel) {
        this.showLabel = showLabel;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventIndicator(String eventIndicator) {
        this.eventIndicator = eventIndicator;
    }

    public void setMeasureInfo(Map<String, Object> measureInfo) {
        this.measureInfo = measureInfo;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public int getSamplePrecent() {
        return samplePrecent;
    }

    public String getShowName() {
        return showName;
    }

    public String getShowLabel() {
        return showLabel;
    }

    public Object getEventID() {
        return eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventIndicator() {
        return eventIndicator;
    }

    public Map<String, Object> getMeasureInfo() {
        return measureInfo;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public List<String> getGroups() {
        return groups;
    }

    public static QueryBuilder builder() {
        return new QueryBuilder(new Query());
    }
}
