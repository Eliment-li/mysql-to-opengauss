package com.tiange.api;

import com.alibaba.fastjson.JSON;
import com.tiange.Main;
import com.tiange.core.migrate.DataMigrateService;
import com.tiange.core.migrate.MetaDataMigrateService;
import com.tiange.core.mysql.database.MysqlDatabase;

import static spark.Spark.*;

public class MigrateApi {

    public static void main(String[] args) {

/*        enableCORS("*","POST","*");

        post("/doMigrate", (request, response) -> {
            System.out.println(request.params());
            return "ok";
        });

        get("/doMigrate", (request, response) -> {
            System.out.println(request.params());
            return "ok";
        });*/

    }


    /**
     * 初始化 http服务
     */
    public static void  initHttpServer(){

        Main.initMigrateTool();

        enableCORS("*","POST","*");

        post("/doMigrate", (request, response) -> {
            System.out.println(request.params());


            //迁移元数据
            MysqlDatabase mysqlDatabase = MetaDataMigrateService.migrateTables();
            MetaDataMigrateService.migrateKeys();

            //迁移数据
            DataMigrateService.migrateData(mysqlDatabase);

            return "ok";
        });

        get("/doMigrate", (request, response) -> {
            System.out.println(request.params());
            return "ok";
        });
    }


    /**
     * 解决前端跨域请求问题
     *
     * @param origin  Access-Control-Allow-Origin 的值，（允许哪个 origin 请求资源？）
     * @param methods Access-Control-Request-Method的值 （允许哪种方法请求？）
     * @param headers Access-Control-Allow-Headers的值 如果没有特殊需要，传入 “*”即可
     */
    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }
}
