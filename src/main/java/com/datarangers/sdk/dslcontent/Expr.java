package com.datarangers.sdk.dslcontent;

public class Expr {
    public static FilterBuilder expr(String valueType,String name,
                                     String operation,Object values,String type){
        return Filter.builder().conditions(new Condition(valueType,name,operation,values,type));
    }

    public static FilterBuilder emptyExpr(){
        return Filter.builder();
    }

    public static FilterBuilder stringExpr(String name,
                                           String operation,Object values,String type){
        return expr("string",name,operation,values,type);
    }
    public static FilterBuilder stringExpr(String name,
                                           String operation,Object values){
        return expr("string",name,operation,values,"profile");
    }

    public static FilterBuilder intExpr(String name,
                                           String operation,Object values,String type){
        return expr("int",name,operation,values,type);
    }
    public static FilterBuilder intExpr(String name,
                                        String operation,Object values){
        return expr("int",name,operation,values,"profile");

    }

    public static QueryBuilder show(String label,String name){
        return Query.builder().showLabel(label).showName(name);
    }
}
