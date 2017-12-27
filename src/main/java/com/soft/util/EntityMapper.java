package com.soft.util;

import org.springframework.stereotype.Repository;

@Repository
public class EntityMapper {

    private  String entityName;

    private Class<?> clazz;

    public EntityMapper() {

    }

    public Class<?> getClazz(String entityName){

        Class<?> clazz=null;
        try {
            clazz =Class.forName("com.soft.entity." + entityName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazz;
    }


}
