package com.datarangers.sdk.dslcontent;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter {
    @JSONField(name = "show_name")
    private String showName;
    @JSONField(name = "show_label")
    private String showLabel;
    private Map<String, Object> expression = new HashMap<String, Object>(){{
        put("conditions", new ArrayList<Condition>());
    }};

    public void updateExpression(String key, Object value) {
        expression.put(key, value);
    }

    public void updateExpressionCondition(List<Condition> conditions) {
        String key = "conditions";
        if (!expression.containsKey(key)) {
            expression.put(key, new ArrayList<Condition>());
        }
        ArrayList<Condition> cond = (ArrayList<Condition>) expression.get(key);
        cond.addAll(conditions);
        expression.put(key,cond);
    }

    public void updateExpressionCondition(Condition condition) {
        String key = "conditions";
        if (!expression.containsKey(key)) {
            expression.put(key, new ArrayList<Condition>());
        }
        ArrayList<Condition> cond = (ArrayList<Condition>) expression.get(key);
        cond.add(condition);
        expression.put(key,cond);
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setShowLabel(String showLabel) {
        this.showLabel = showLabel;
    }

    public void setExpression(Map<String, Object> expression) {
        this.expression = expression;
    }

    public String getShowName() {
        return showName;
    }

    public String getShowLabel() {
        return showLabel;
    }

    public Map<String, Object> getExpression() {
        return expression;
    }
    public static FilterBuilder builder(){
        return new FilterBuilder(new Filter());
    }
}
