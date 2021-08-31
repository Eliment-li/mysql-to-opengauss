package com.tiange.core.data.verify.strategy;

import java.util.List;
import java.util.Map;

public interface DataCompareStrategy {

    boolean areEqual(List<Map<String, Object>> mysqlData, List<Map<String, Object>> gaussData);

    boolean areEqual(Map<String, Object> first, Map<String, Object> second);
}
