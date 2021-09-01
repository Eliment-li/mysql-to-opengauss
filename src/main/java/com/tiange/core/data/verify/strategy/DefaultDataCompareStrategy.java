package com.tiange.core.data.verify.strategy;

import com.tiange.core.data.query.QueryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class DefaultDataCompareStrategy implements DataCompareStrategy {


    private Logger logger = LoggerFactory.getLogger(QueryRequest.class);
    /**
     * 比较两个page的数据是否相等
     *
     * @param mysqlData
     * @param gaussData
     * @return
     */
    public boolean areEqual(List<Map<String, Object>> mysqlData, List<Map<String, Object>> gaussData) {

        if (mysqlData.size() != gaussData.size()) return false;

        for (int i = 0; i < mysqlData.size(); i++) {

            //若存在不相等的，直接返回false
            if (!areEqual(mysqlData.get(i), gaussData.get(i))) {
                return false;
            }

        }

        return true;
    }


    /**
     * 比较两个 Map 是否相等，比较内容：key 和 value
     * 该方法有一定几率误报，正在改进中
     * @param first
     * @param second
     * @return
     */
    public boolean areEqual(Map<String, Object> first, Map<String, Object> second) {

        if (first.size() != second.size()) {
            return false;
        }

        for (Map.Entry<String, Object> entry : first.entrySet()) {

            String key = entry.getKey();
            if (!(first.get(key).equals(second.get(key)))) {

                logger.error("验证不一致 key={},value1={},value2={},注{} ", key, first.get(key), second.get(key), "该方法有一定几率误报，正在改进中");
            }
        }


        return first.entrySet().stream()
                .allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }

}
