package com.tiange.core.opengauss.column;

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
     * 小数类型
     */
    NUMERIC("numeric"),

    /**
     * 包裹 float4 ，float8 这些类型长度固定
     * 建表的时候，不能指定它的长度 例如 float4(10) ，这样是错误的
     */
    FLOAT("float"),

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

    private String msg;

    /**
     * 数据类型与组名的映射关系
     * <columnType，groupName>
     */
    public static final Map<String, ColumnGroupEnum> map = new HashMap<String, ColumnGroupEnum>();

    // 构造方法
    private ColumnGroupEnum(String msg) {
        this.msg = msg;
    }

    public static ColumnGroupEnum findByName(String Name) {
        for (ColumnGroupEnum v : values()) {
            if (v.msg.equals(Name)) {
                return v;
            }
        }
        return null;
    }

    //初始化映射关系
    static {
        //加载 配置文件
        String columnTypeGroup = FileUtils.getStringByClasspath("mysql/json/dataTypeGroup.json");
        JSONObject GroupMap = JSONObject.parseObject(columnTypeGroup);

        for (Map.Entry<String, Object> entry : GroupMap.entrySet()) {
            String columnType = entry.getKey();
            String groupName = (String) entry.getValue();
            map.put(columnType, ColumnGroupEnum.findByName(groupName));
        }

    }

}
