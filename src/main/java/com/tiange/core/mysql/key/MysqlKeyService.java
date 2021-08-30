package com.tiange.core.mysql.key;

import com.tiange.core.utils.database.jdbc.MySqlDbUtil;
import com.tiange.core.utils.others.FileUtils;

import java.util.List;

public class MysqlKeyService {

    static String KEY_SQL = FileUtils.getStringByClasspath("mysql/sql/key.sql");
    MySqlDbUtil dbUtil = new MySqlDbUtil();

    /**
     * @return 数据库中所有的 key
     */
    public List<MysqlKey> listKeys(String databseName) {

        KEY_SQL = KEY_SQL.replace("?", "'" + databseName + "'");

        List<MysqlKey> keyList = (List<MysqlKey>) dbUtil.queryForObjectList(
                KEY_SQL,
                MysqlKey.class);

        return keyList;
    }

}
