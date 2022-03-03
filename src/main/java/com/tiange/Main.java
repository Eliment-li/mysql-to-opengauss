package com.tiange;

import ch.qos.logback.classic.util.ContextInitializer;
import com.tiange.core.migrate.DataMigrateService;
import com.tiange.core.migrate.MetaDataMigrateService;
import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.utils.database.druid.DruidUtil;

/*数据迁移，启动类*/
public class Main {



    /**
     * 主方法
     */
    public static void main(String[] args) {

        //加载日志配置文件
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/logback.xml");
        //初始化数据库连接池
        DruidUtil.init();

        //迁移元数据
      MysqlDatabase mysqlDatabase = MetaDataMigrateService.migrateTables();
        MetaDataMigrateService.migrateKeys();

        //迁移数据
        //DataMigrateService.migrateData(mysqlDatabase);

        //todo 销毁资源

    }
}
