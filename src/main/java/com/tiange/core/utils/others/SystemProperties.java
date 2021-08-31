package com.tiange.core.utils.others;

import java.io.IOException;
import java.util.Properties;

/**
 * 管理系统配置
 */
public class SystemProperties {
    private static Properties dataMigrate;
    private static Properties druidMysql;
    private static Properties druidOpengauss;

    static {

        dataMigrate = new Properties();
        druidMysql = new Properties();
        druidOpengauss = new Properties();

        try {
            dataMigrate.load(FileUtils.getInputStreamByClasspath("config/dataMigrate.properties"));
            druidMysql.load(FileUtils.getInputStreamByClasspath("config/mysqlDataBase.properties"));
            druidOpengauss.load(FileUtils.getInputStreamByClasspath("config/OpengaussDataBase.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据迁移配置
     *
     * @param key
     * @return value
     */
    public static String dataMigrate(String key) {
        return dataMigrate.getProperty(key);
    }

    /**
     * 获取 mysql 数据库连接池配置
     */
    public static Properties getDruidMysqlProperties() {
        return druidMysql;
    }

    public static String druidMysql(String key) {
        return druidMysql.getProperty(key);
    }

    /**
     * 获取 openGauss 数据库连接池配置
     */
    public static Properties getDruidOpengausProperties() {
        return druidOpengauss;
    }

    public static String druidOpengauss(String key) {
        return druidOpengauss.getProperty(key);
    }

}
