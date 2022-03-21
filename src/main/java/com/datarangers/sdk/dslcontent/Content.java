package com.datarangers.sdk.dslcontent;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.*;

public class Content {
    @JSONField(name = "query_type")
    private String queryType = "event";
    @JSONField(name = "profile_filters")
    private List<Filter> profileFilters = new ArrayList<>();
    @JSONField(name = "profile_groups")
    private List<String> profileGroup = new ArrayList<>();
    private List<HashMap<String, String>> orders = new ArrayList<HashMap<String, String>>();
    private Map<String, Integer> page = new HashMap<>();
    private Map<String, Object> option = new HashMap<>();
    @JSONField(name = "show_option")
    private Map<String, Object> showOption = new HashMap<>();
    private List<List<Query>> queries = new ArrayList<>();

    public void addProfileFilter(Filter pf) {
        profileFilters.add(pf);
    }

    public void addProfileGroup(String pg) {
        profileGroup.add(pg);
    }

    public void addProfileGroup(List<String> pgs) {
        profileGroup.addAll(pgs);
    }

    public void addOrder(HashMap<String, String> order){
        orders.add(order);
    }

    public void addOrder(String order){
        addOrder(order,"asc");
    }

    public void addOrder(String order,String direction){
        orders.add(new HashMap<String,String>(){
            {
                put("field",order);
                put("direction",direction);
            }
        });
    }

    public void addOrder(List<HashMap<String,String>> order){
        orders.addAll(order);
    }

    public void addQuery(Query query){
        List<Query> q=new ArrayList<>();
        q.add(query);
        queries.add(q);
    }

    public void addQuery(List<Query> queries){

        this.queries.add(queries);
    }

    public void updatePage(String key,int value){
        page.put(key,value);
    }

    public void updateOption(String key, Object option){
        this.option.put(key,option);
    }

    public void updateShowOption(String key,Object showOpt){
        this.showOption.put(key,showOpt);
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public void setProfileFilters(List<Filter> profileFilters) {
        this.profileFilters = profileFilters;
    }

    public void setProfileGroup(List<String> profileGroup) {
        this.profileGroup = profileGroup;
    }

    public void setOrders(List<HashMap<String, String>> orders) {
        this.orders = orders;
    }

    public void setPage(Map<String, Integer> page) {
        this.page = page;
    }

    public void setOption(Map<String, Object> option) {
        this.option = option;
    }

    public void setShowOption(Map<String, Object> showOption) {
        this.showOption = showOption;
    }

    public void setQueries(List<List<Query>> queries) {
        this.queries = queries;
    }

    public String getQueryType() {
        return queryType;
    }

    public List<Filter> getProfileFilters() {
        return profileFilters;
    }

    public List<String> getProfileGroup() {
        return profileGroup;
    }

    public List<HashMap<String, String>> getOrders() {
        return orders;
    }

    public Map<String, Integer> getPage() {
        return page;
    }

    public Map<String, Object> getOption() {
        return option;
    }

    public Map<String, Object> getShowOption() {
        return showOption;
    }

    public List<List<Query>> getQueries() {
        return queries;
    }

    public static ContentBuilder builder(){
        return new ContentBuilder(new Content());
    }
}
