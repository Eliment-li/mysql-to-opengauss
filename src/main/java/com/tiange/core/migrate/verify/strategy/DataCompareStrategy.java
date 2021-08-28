package com.tiange.core.migrate.verify.strategy;

import java.util.List;
import java.util.Map;

public interface DataCompareStrategy {

    public boolean areEqual(List<Map<String, Object>> mysqlData, List<Map<String, Object>> gaussData);

    public boolean areEqual(Map<String, Object> first, Map<String, Object> second);
}
