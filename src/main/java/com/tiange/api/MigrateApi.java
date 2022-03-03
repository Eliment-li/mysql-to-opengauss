package com.tiange.api;

import ch.qos.logback.classic.util.ContextInitializer;
import com.alibaba.fastjson.JSONObject;
import com.tiange.core.data.bucket.BucketConsumerThread;
import com.tiange.core.migrate.MetaDataMigrateService;
import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.utils.database.druid.DruidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Spark;


import java.sql.SQLException;
import java.util.Properties;

import static spark.Spark.*;

/**
 * 对外提供 HTTP 请求的服务，方便对接其他系统。
 */
public class MigrateApi {

    //日志工具
    private static  final Logger logger = LoggerFactory.getLogger(BucketConsumerThread.class);

    /**
     * 启动 httpserver
     */
    public  void  initHttpServer(){

        //加载日志配置文件
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/logback.xml");

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        port(4567);
        //允许跨域
        enableCORS("*","POST","*");


        // handle post request
        post("/doMigrate", (request, response) -> {

            JSONObject result=new JSONObject();//处理结果

            try{
                //配置数据库
                configDatabase(request);
                //迁移元数据

                MysqlDatabase mysqlDatabase = MetaDataMigrateService.migrateTables();
                MetaDataMigrateService.migrateKeys();

                //迁移数据
               // DataMigrateService.migrateData(mysqlDatabase);
            }
            catch (SQLException e){
                e.printStackTrace();
                result.put("success",false);
                result.put("msg",e.toString());
                return result.toJSONString();
            }

            logger.info("传输完毕");

            result.put("success",false);
            result.put("msg","传输完毕");
            return result.toJSONString();

        });//end post

    }

    /**
     * 配置数据库
     * @param request 请求
     *
     * @return 是否初始化成功
     */
    private  boolean configDatabase(Request request) throws Exception{
        Properties mysqlProperties=new Properties();

        //mysql config
        String mysqlUrl="jdbc:mysql://"+request.queryParams("from_host")
                +":"+request.queryParams("from_port")
                +"/"+request.queryParams("from_database_name")+""
                +"?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";

        mysqlProperties.setProperty("url",mysqlUrl);
        mysqlProperties.setProperty("username",request.queryParams("from_username")+"");
        mysqlProperties.setProperty("password",request.queryParams("from_password")+"");
        mysqlProperties.setProperty("dataBaseName",request.queryParams("from_database_name")+"");
        mysqlProperties.setProperty("initialSize","10");
        mysqlProperties.setProperty("maxActive","100");

        //openGauss config
        String gaussUrl="jdbc:postgresql://"+request.queryParams("to_host")
                +":"+request.queryParams("to_port")
                +"/"+request.queryParams("to_database_name");

        Properties gaussProperties=new Properties();

        gaussProperties.setProperty("url",gaussUrl);
        gaussProperties.setProperty("username",request.queryParams("to_username")+"");
        gaussProperties.setProperty("password",request.queryParams("to_password")+"");
        gaussProperties.setProperty("dataBaseName","jack"+"");//实际上是模式名
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
    private  void enableCORS(final String origin, final String methods, final String headers) {

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
       new MigrateApi().initHttpServer();
    }
}
