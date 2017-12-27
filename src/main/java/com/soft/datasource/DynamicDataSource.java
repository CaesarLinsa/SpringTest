package com.soft.datasource;

import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    public Logger getParentLogger() {
        return null;
    }

    @Override
    protected Object determineCurrentLookupKey() {

        return DataSourceContextHolder. getDbType();

    }

}