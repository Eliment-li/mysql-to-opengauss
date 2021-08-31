package com.tiange.core.data.verify.strategy;

import java.util.List;
import java.util.Map;

public class DataCompareContext {

    private DataCompareStrategy strategy;

    public DataCompareContext() {
    }

    public DataCompareContext(DataCompareStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(DataCompareStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean areEqual(List<Map<String, Object>> mysqlData, List<Map<String, Object>> gaussData) {
        return strategy.areEqual(mysqlData, gaussData);
    }

    public boolean areEqual(Map<String, Object> first, Map<String, Object> second) {
        return strategy.areEqual(first, second);
    }
}
