package com.tiange.core.mysql.database;

import com.tiange.core.utils.others.ObjectUtils;

public class DatabaseConfig {
    private static final long serialVersionUID = -1258005721111182123L;
    /**
     * 数据库地址
     */
    private String host;
    /**
     * 数据库端口
     */
    private Integer port;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 数据库名
     */
    private String name;
    /**
     * 数据库连接地址
     */
    private String url;
    /**
     * 忽略编码及字符集对比
     */
    private Boolean ignoreCharacterCompare = false;
    /**
     * 连接时区
     */
    private String timezone;

    /**
     * @param host     数据库地址
     * @param port     数据库端口
     * @param username 数据库用户名
     * @param password 数据库密码
     * @param name     数据库名
     */
    public DatabaseConfig(String host, Integer port, String username, String password, String name) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.name = name;
        this.url = getUrl();
    }

    /**
     * 拼接数据库连接地址
     *
     * @return 数据库连接地址
     */
    public String getUrl() {
        if (!ObjectUtils.isEmpty(this.url)) {
            return this.url;
        }
        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + name;
        url += "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
        String timeZone;
        if (ObjectUtils.isEmpty(this.timezone)) {
            timeZone = "GMT%2B8";
        } else {
            timeZone = this.timezone;
        }
        url += "&serverTimezone=" + timeZone;
        return url;
    }

    /* getter & setter */

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIgnoreCharacterCompare() {
        return ignoreCharacterCompare;
    }

    public void setIgnoreCharacterCompare(Boolean ignoreCharacterCompare) {
        this.ignoreCharacterCompare = ignoreCharacterCompare;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
