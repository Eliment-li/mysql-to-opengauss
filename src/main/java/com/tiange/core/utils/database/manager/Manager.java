package com.tiange.core.utils.database.manager;

import java.util.List;

public interface Manager {
    /**
     * @param sql
     * @return
     */
    //List<Map<String, Object>> executeQuery(StringBuilder sql);

    /**
     * 查询数据库，并按照字段名封装到对象中
     * 只要查询的数据库表字段和对象的属性同名就可以封装成所需对象
     *
     * @param sql   查询语句
     * @param clazz 封装目标类的 class 对象
     * @return 对象List
     */
    List<?> queryForObjectList(String sql, Class clazz);

    /**
     * 查询数据库，并按照字段名封装到对象中
     * 只要查询的数据库表字段和对象的属性同名就可以封装成所需对象
     *
     * @param sql   查询语句
     * @param clazz 封装目标类的 class 对象
     * @return 单个对象
     */
    Object queryForObject(String sql, Class clazz);

    /**
     * 执行单个sql
     *
     * @param sql
     * @return
     */
    int execute(String sql);

    /**
     * 预留 执行单个sql
     *
     * @param sql
     * @param args
     * @return
     */
    int execute(String sql, Object[] args);

    //TODO 执行单个事务接口

}
