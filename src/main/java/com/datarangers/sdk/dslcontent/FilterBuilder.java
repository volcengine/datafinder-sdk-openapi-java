package com.datarangers.sdk.dslcontent;

import java.util.List;

public class FilterBuilder {
    private Filter profileFilter;

    public FilterBuilder(Filter profileFilter) {
        this.profileFilter = profileFilter;
    }

    public FilterBuilder showName(String showname) {
        profileFilter.setShowName(showname);
        return this;
    }

    public FilterBuilder showLabel(String showlabel) {
        profileFilter.setShowLabel(showlabel);
        return this;
    }

    public FilterBuilder show(String label,String name) {
        showName(name);
        showLabel(label);
        return this;
    }

    public FilterBuilder logic(String logic) {
        profileFilter.updateExpression("logic", logic);
        return this;
    }

    public FilterBuilder conditions(List<Condition> conditions) {
        profileFilter.updateExpressionCondition(conditions);
        return this;
    }

    public FilterBuilder conditions(Condition condition) {
        profileFilter.updateExpressionCondition(condition);
        return this;
    }

    public FilterBuilder stringExpr(String name, String operation, Object values, String type) {
        return conditions(new Condition("string", name, operation, values, type));
    }

    public FilterBuilder intExpr(String name, String operation, Object values, String type) {
        return conditions(new Condition("int", name, operation, values, type));
    }

    public FilterBuilder stringExpr(String name, String operation, Object values) {
        return conditions(new Condition("string", name, operation, values, "profile"));
    }

    public FilterBuilder intExpr(String name, String operation, Object values) {
        return conditions(new Condition("int", name, operation, values, "profile"));
    }

    public Filter builder() {
        return profileFilter;
    }

}
