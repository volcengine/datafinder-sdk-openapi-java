package com.datarangers.sdk.dslcontent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContentBuilder {
    private Content content;

    public ContentBuilder(Content content) {
        this.content = content;
    }
    public ContentBuilder queryType(String queryType){
        this.content.setQueryType(queryType);
        return this;
    }

    public ContentBuilder profileFilter(Filter pf){
        content.addProfileFilter(pf);
        return this;
    }

    public ContentBuilder profileGroup(String pg){
        content.addProfileGroup(pg);
        return this;
    }

    public ContentBuilder profileGroup(List<String> pgs){
        content.addProfileGroup(pgs);
        return this;
    }

    public ContentBuilder orders(String order){
        content.addOrder(order);
        return this;
    }
    public ContentBuilder orders(String order,String direction){
        content.addOrder(order,direction);
        return this;
    }
    public ContentBuilder orders(HashMap<String,String> order){
        content.addOrder(order);
        return this;
    }
    public ContentBuilder orders(List<HashMap<String,String>> order){
        content.addOrder(order);
        return this;
    }

    public ContentBuilder page(int limit,int offset){
        content.updatePage("limit",limit);
        content.updatePage("offset",offset);
        return this;
    }

    public ContentBuilder limit(int limit){
        content.updatePage("limit",limit);
        return this;
    }

    public ContentBuilder offset(int offset){
        content.updatePage("offset",offset);
        return this;
    }

    public ContentBuilder option(String key,Object value){
        content.updateOption(key,value);
        return this;
    }

    public ContentBuilder showOption(String key,Object value){
        content.updateShowOption(key,value);
        return this;
    }

    public ContentBuilder query(Query query){
        content.addQuery(query);
        return this;
    }

    public ContentBuilder query(List<Query> queries){
        content.addQuery(queries);
        return this;
    }

    public Content build(){
        return content;
    }

}
