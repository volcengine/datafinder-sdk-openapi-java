package com.datarangers.sdk.dslcontent;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class Condition {
    @JSONField(name = "property_value_type")
    private String propertyValueType;
    @JSONField(name = "property_name")
    private String propertyName;
    @JSONField(name = "property_operation")
    private String propertyOperation;
    @JSONField(name = "property_type")
    private String propertyType;
    @JSONField(name = "property_values")
    private List<Object> propertyValues;

    public Condition(String property_value_type, String property_name, String property_operation,Object value,String type) {
        this.propertyValueType = property_value_type;
        this.propertyName = property_name;
        this.propertyOperation = property_operation;
        propertyValues=new ArrayList<>();
        if(value instanceof List){
            propertyValues.addAll((List<Object>)value);
        }else {
            propertyValues.add(value);
        }
        propertyType=type;
    }
    public Condition(String property_value_type, String property_name, String property_operation,List<Object> value,String type) {
        this.propertyValueType = property_value_type;
        this.propertyName = property_name;
        this.propertyOperation = property_operation;
        propertyValues=new ArrayList<>();
        propertyValues.addAll(value);
        propertyType=type;
    }

    public void addPropertyValue(Object value){
        if(value instanceof List){
            propertyValues.addAll((List<Object>)value);
        }else {
            propertyValues.add(value);
        }
    }
    public void addPropertyValue(List<Object> value){
        propertyValues.addAll(value);
    }

    public void setPropertyValueType(String propertyValueType) {
        this.propertyValueType = propertyValueType;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setPropertyOperation(String propertyOperation) {
        this.propertyOperation = propertyOperation;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setPropertyValues(List<Object> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getPropertyValueType() {
        return propertyValueType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyOperation() {
        return propertyOperation;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public List<Object> getPropertyValues() {
        return propertyValues;
    }
}
