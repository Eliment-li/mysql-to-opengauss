
### 2021鲲鹏应用创新大赛作品 队名：天歌-郑州大学

#### 功能介绍：

一款高性能数据库迁移工具，支持数据库表，字段，索引，注释的迁移，支持数据库表中数据的迁移。在迁移完成后，支持对迁移内容的校验，包裹数据库元数据信息和数据库表中的数据。



### 如何使用：

1. 将 &nbsp;&nbsp;mysqltest.sql &nbsp;&nbsp;数据导入&nbsp;&nbsp;mysql&nbsp;&nbsp;数据库中
2. 修改MySQL配置文件与&nbsp;&nbsp;Opengauss&nbsp;&nbsp;配置文件
3. 运行 mian.java.com.tiange.Main 类中的&nbsp;&nbsp;main&nbsp;&nbsp;方法
4. 查看结果

#### 文件路径

- 测试数据：/test/data/mysqltest.sql



- MySQL配置文件： /resources/config/druidMysql.properties



- Opengauss配置文件：/resources/config/druidOpengauss.properties



- 多线程相关配置文件：/resources/config/dataMigrate.properties

  

- 日志工具配置文件 ：/resources/config/logback.xml

- 系统结构图&nbsp;& &nbsp;UML&nbsp; ：/resources/wiki/images



### 开发环境

- Maven 3.3.9 

- Jdk1.8 

#### 其他

- Github 地址：https://github.com/Eliment-li/mysql-to-opengauss.git

  目前为私有仓库，比赛结束后公开

- 联系方式：felix.litian@foxmail.com

