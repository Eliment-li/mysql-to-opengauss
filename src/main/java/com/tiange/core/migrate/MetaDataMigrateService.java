package com.tiange.core.migrate;

import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.mysql.database.MysqlDatabaseService;
import com.tiange.core.opengauss.database.GaussDatabase;
import com.tiange.core.opengauss.key.GaussKey;
import com.tiange.core.opengauss.table.GaussTable;
import com.tiange.core.opengauss.table.GaussTableService;
import com.tiange.core.utils.database.jdbc.OpenGaussDbUtil;
import com.tiange.core.utils.others.SystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Properties;

/**
 * 元数据迁移业务管理类
 * 元数据迁移对象包括数据库表，字段，注释，主键，索引等
 */
public class MetaDataMigrateService {

    //日志工具
    private static final Logger logger = LoggerFactory.getLogger(MetaDataMigrateService.class);
    static Properties gaussProperties = SystemProperties.getDruidOpengausProperties();
    static Properties mysqlProperties = SystemProperties.getDruidMysqlProperties();
    static String MYSQL_DATABASE_NAME = mysqlProperties.getProperty("dataBaseName");
    static String GAUSS_DATABASE_NAME = gaussProperties.getProperty("dataBaseName");


    /**
     * 迁移数据库表，字段，注释等
     */
    public static MysqlDatabase migrateTables() {
        MysqlDatabase mysqlDatabase = null;
        try {

            mysqlDatabase = new MysqlDatabase(MYSQL_DATABASE_NAME);

            //读取MySQL元数据
            new MysqlDatabaseService(mysqlDatabase).ReadMetaData();

            //将 Mysql 转换为 OpenGauss
            GaussDatabase gaussDatabase = MysqlDatabaseService.Conert2GaussDatabase(mysqlDatabase,
                    new GaussDatabase(GAUSS_DATABASE_NAME));

            //迁移
            for (GaussTable gaussTable : gaussDatabase.getTables()) {

                logger.info("\n==============" + gaussTable.getTable_name() + "开始迁移输表结构=================");

                OpenGaussDbUtil.execute(gaussTable.toCreateSql().toString());

                logger.info("\n 验证表  " + gaussTable.getTable_name() + " ");

                boolean CheckResult = GaussTableService.metadataMigrateCheck(gaussTable);

                logger.info("验证结果: " + CheckResult);

                logger.info("==============" + gaussTable.getTable_name() + "表结构迁移完毕=================");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlDatabase;
    }

    /**
     * 迁移索引，主键等
     */
    public static void migrateKeys() {

        MysqlDatabase mysqlDatabase = new MysqlDatabase(MYSQL_DATABASE_NAME);

        //读取MySQL元数据
        try {
            new MysqlDatabaseService(mysqlDatabase).ReadMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("读取元数据失败");
        }

        GaussDatabase gaussDatabase = MysqlDatabaseService.Conert2GaussDatabase(mysqlDatabase,
                new GaussDatabase(GAUSS_DATABASE_NAME));

        //迁移
        for (GaussKey gaussKey : gaussDatabase.getKeys()) {

            String sql = gaussKey.toCreateTableSql().toString();
            //执行创建语句
            OpenGaussDbUtil.execute(sql);

            logger.info("创建key-{}", sql);

        }
    }

}
