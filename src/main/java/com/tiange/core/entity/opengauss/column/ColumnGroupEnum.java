package com.tiange.core.entity.opengauss.column;

import com.alibaba.fastjson.JSONObject;
import com.tiange.core.utils.others.FileUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 数据类型组枚举类
 * 因不同数据类型的处理方式不一样，故将数据类型分为不同的组
 *
 * @author li
 * @version 0.11
 */
public enum ColumnGroupEnum {

    /**
     * 整数类型
     */
    INT("int"),

    /**
     * 除整数外的其他数字类型
     */
    NUMERIC("numeric"),


    /**
     * TIMESTAMP[(p)]
     * 日期和时间。p表示小数点后的精度，取值范围为0~6。
     */
    DATE_AND_TIME("date_and_time"),

    /**
     * 字符串类型 包括 char 和 varchar
     */
    CHARS("chars"),
    /**
     * 字符串类型 包括 tinytext text mediumtext longtext
     */
    TEXT("text");

    private String name;

    /**
     * 数据类型与组名的映射关系
     * <columnType，groupName>
     */
    public static final Map<String, ColumnGroupEnum> map = new HashMap<String, ColumnGroupEnum>();

    // 构造方法
    private ColumnGroupEnum(String name) {
        this.name = name;
    }

    public static ColumnGroupEnum findByName(String Name) {
        for (ColumnGroupEnum v : values()) {
            if (v.name().equals(Name)) {
                return v;
            }
        }
        return null;
    }

    //初始化映射关系
    static {
        //加载 配置文件
        String columnTypeGroup = FileUtils.getStringByClasspath("mysql/json/dataTypeMap.json");
        JSONObject GroupMap = JSONObject.parseObject(columnTypeGroup);

        for (Map.Entry<String, Object> entry : GroupMap.entrySet()) {
            String columnType = entry.getKey();
            String groupName = (String) entry.getValue();
            map.put(columnType, ColumnGroupEnum.findByName(groupName));
        }

    }

}
