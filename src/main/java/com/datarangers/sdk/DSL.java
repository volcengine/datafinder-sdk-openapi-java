package com.datarangers.sdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.datarangers.sdk.dslcontent.Content;
import com.datarangers.sdk.dslcontent.ContentBuilder;
import com.datarangers.sdk.dslcontent.DSLBuilder;
import com.datarangers.sdk.dslcontent.Expr;

import java.util.*;

public class DSL {
    private int version = 3;
    @JSONField(name = "use_app_cloud_id")
    private boolean useAppCloudId = true;
    @JSONField(name = "app_ids")
    private List<Integer> appIds = new ArrayList<>();
    private List<HashMap<String, Object>> periods = new ArrayList<>();
    private Content content = null;
    @JSONField(name = "contents")
    private List<Content> contents = null;
    @JSONField(name = "option")
    private Map<String,Object> option=null;
    @JSONField(name = "content_builder")
    private transient ContentBuilder contentBuilder = Content.builder();

    public void addAppId(int appid) {
        appIds.add(appid);
    }

    public Content getContent() {
        return content;
    }


    public void addAppId(List<Integer> appid) {
        appIds.addAll(appid);
    }

    public void addPeriods(HashMap<String, Object> period) {
        periods.add(period);
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public List<Content> getContents() {
        return contents;
    }

    public Map<String, Object> getOption() {
        return option;
    }

    public void setContentBuilder(ContentBuilder cb) {
        this.contentBuilder = cb;

    }

    public ContentBuilder getContentBuilder() {
        return contentBuilder;
    }

    public int getVersion() {
        return version;
    }

    public boolean isUseAppCloudId() {
        return useAppCloudId;
    }

    public List<Integer> getAppIds() {
        return appIds;
    }

    public List<HashMap<String, Object>> getPeriods() {
        return periods;
    }


    public void setVersion(int version) {
        this.version = version;
    }

    public void setUseAppCloudId(boolean useAppCloudId) {
        this.useAppCloudId = useAppCloudId;
    }

    public void setAppIds(List<Integer> appIds) {
        this.appIds = appIds;
    }

    public void setPeriods(List<HashMap<String, Object>> periods) {
        this.periods = periods;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public static DSLBuilder builder(String queryType) {
        return new DSLBuilder(new DSL(), queryType);
    }

    public static DSLBuilder eventBuilder() {
        return builder("event");
    }

    public static DSLBuilder funnelBuilder() {
        return builder("funnel");
    }

    public static DSLBuilder lifeCycleBuilder() {
        return builder("life_cycle");
    }

    public static DSLBuilder pathFindBuilder() {
        return builder("path_find");
    }

    public static DSLBuilder retentionBuilder() {
        return builder("retention");
    }

    public static DSLBuilder webBuilder() {
        return builder("web_session");
    }

    public static DSLBuilder confidenceBuilder() {
        return builder("confidence");
    }

    public static DSLBuilder topKBuilder() {
        return builder("event_topk");
    }

    public static DSLBuilder advertiseBuilder() {
        return builder("advertise");
    }

    private void moveContentToContents() {
        if (content != null && contents == null) {
            contents = new ArrayList<Content>();
            contents.add(content);
            content = null;
        }
    }

    public void addContents(Content content) {
        if (contents != null) {
            contents.add(content);
        }
    }

    public void setOption(Map<String, Object> option) {
        this.option = option;
    }

    private static DSL mergeDSLs(Map<String, Object> params, List<DSL> dsls) {
        DSL dsl = null;
        for (DSL d : dsls) {
            if (dsl == null) {
                dsl = d;
                dsl.moveContentToContents();
            } else {
                dsl.addContents(d.getContent());
            }
        }
        if(params!=null&&params instanceof Map){
            dsl.setOption(params);
        }
        return dsl;
    }

    public static DSL blendDSLs(int base, List<DSL> dsls) {
        return mergeDSLs(new HashMap<String, Object>(){{
            put("blend",new HashMap<String,Object>(){{
                put("status",true);
                put("base",base);
            }});
        }},dsls);
    }

}
