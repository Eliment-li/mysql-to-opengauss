package com.tiange.api;

import ch.qos.logback.classic.util.ContextInitializer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tiange.Main;
import com.tiange.core.migrate.DataMigrateService;
import com.tiange.core.migrate.MetaDataMigrateService;
import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.utils.database.druid.DruidUtil;

import java.util.HashMap;
import java.util.Properties;

import static spark.Spark.*;

public class MigrateApi {




    /**
     * 初始化 http服务
     */
    public static void  initHttpServer(){




        //加载日志配置文件
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/logback.xml");


        enableCORS("*","POST","*");

        post("/doMigrate", (request, response) -> {


            JSONObject result=new JSONObject();

            //设置配置文件
            boolean isDatabaseOK=initDatabase((HashMap<String, String>) request.params());

            if (!isDatabaseOK){
                result.put("success",false);
                result.put("msg","数据库连接失败");
                return result.toJSONString();
            }


            //迁移元数据
            MysqlDatabase mysqlDatabase = MetaDataMigrateService.migrateTables();
            MetaDataMigrateService.migrateKeys();

            //迁移数据
            DataMigrateService.migrateData(mysqlDatabase);

            //todo 销毁资源

            result.put("success",true);
            result.put("msg","迁移成功！");
            return result.toJSONString();
        });

    }

    private static boolean initDatabase(HashMap<String,String> parameters){
        Properties mysqlProperties=new Properties();

        //mysql config
        String mysqlUrl="jdbc:mysql://"+parameters.get("from_host")
                +":"+parameters.get("from_port")
                +"/"+parameters.get("database_name")+""
                +"?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";

        mysqlProperties.setProperty("url",mysqlUrl);
        mysqlProperties.setProperty("username",parameters.get("from_username"));
        mysqlProperties.setProperty("password",parameters.get("from_password"));
        mysqlProperties.setProperty("initialSize","100");
        mysqlProperties.setProperty("maxActive","100");


        //openGauss config

        String gaussUrl="jdbc:postgresql://"+parameters.get("to_host")
                +":"+parameters.get("to_port")
                +"/"+parameters.get("to_database_name");

        Properties gaussProperties=new Properties();

        gaussProperties.setProperty("url",mysqlUrl);
        gaussProperties.setProperty("username",parameters.get("from_username"));
        gaussProperties.setProperty("password",parameters.get("from_password"));
        gaussProperties.setProperty("initialSize","100");
        gaussProperties.setProperty("maxActive","100");

        return DruidUtil.init(mysqlProperties,gaussProperties);

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
}
