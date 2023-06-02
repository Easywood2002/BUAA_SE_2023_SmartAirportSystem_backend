# BUAA_SE_2023_SmartAirportSystem_backend

## 项目简介

1. 本仓库为BUAA《软件工程基础》课程大作业项目“智慧机场系统”后端仓库，用于存放后端开发源代码与相关配置文件；

2. 本项目使用 `Java 11` 与 `Spring Boot` 框架进行后端开发，数据源连接到名为 `smartairport` 的 `MySQL` 数据库，具体配置请参考下一节；

3. 本后端项目现已完成服务器部署，公网IP为：101.200.143.220；

4. 其他相关仓库如下：

   [智慧机场系统前端仓库](https://github.com/Boat1098/BUAA_SE_2023_SmartAirportSystem_frontend)

   [智慧机场系统文档仓库](https://github.com/Easywood2002/BUAA_SE_2023_SmartAirportSystem_documents)



## 配置说明

1. 依赖项相关配置请参照并修改 `./pom.xml` 文件；

2. 数据库相关配置请参照并修改 `./src/main/resources/application.yml` 和 `./helpful/SmartAirportCreate.sql` 文件，其中：

   a. `application.yml` 文件用于配置数据库连接，可通过其修改连接时的用户名与密码；

   b. `SmartAirportCreate.sql` 脚本用于建立数据库、表与其他必要的触发器和存储过程；

3. 其他项目整体信息请参考上一节中提供的文档仓库中的相关文件。