# 后端服务器部署帮助文档

## （一）安装JDK

### 1.安装 lrzsz

```she&#39;l
yum install lrzsz
```

### 2.在 /usr 下上传 JDK

```shell
cd /usr
rz -be
```

### 3.解压 JDK

```shell
tar -zxvf jdk-11.0.19_linux-x64_bin.tar.gz
```

### 4.配置环境变量

```shell
cd /etc
vim profile
source profile
```

其中，在profile文件末尾新增：

```shell
export JAVA_HOME=/usr/jdk-11.0.19
export CLASSPATH=$JAVA_HOME/lib/
export PATH=$PATH:$JAVA_HOME/bin
export PATHJAVA_HOME CLASSPATH
```

### 5.检查安装

```shell
java -version
```



## （二）安装 MySQL 8.0.33

### 1.路径准备

```shell
mkdir /mysql
cd /mysql
```

### 2.下载 rpm 文件

```shell
wget 'https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm'
```

### 3.安装 yum 源

```shell
rpm -Uvh mysql80-community-release-el7-3.noarch.rpm
```

### 4.安装 MySQL

```shell
yum module disable mysql
yum install -y mysql-community-server --nogpgcheck
```

### 5.启动 MySQL

```shell
systemctl start mysqld
systemctl status mysqld
```

### 6.获取初始密码

```shell
grep 'temporary password' /var/log/mysqld.log
```

### 7.登录数据库

```shell
mysql -u root -p
```

### 8.修改密码

```shell
ALTER USER 'root'@'localhost' IDENTIFIED BY 'YOURPASSWORD' PASSWORD EXPIRE NEVER;
FLUSH PRIVILEGES;
```

### 9.开启远程登录权限

```shell
UPDATE mysql.user SET host = '%' WHERE user='root';
FLUSH PRIVILEGES;
```

### 10.退出数据库

```shell
QUIT
```



## （三）部署 SpringBoot 项目

### 1.上传打包后的 jar 文件

```shell
rz
```

### 2.前台运行项目

```shell
java -jar smartairportsystem-0.0.1-SNAPSHOT.jar
```

### 3.后台运行项目

```shell
nohup java -jar smartairportsystem-0.0.1-SNAPSHOT.jar &
```

### 4.结束后台运行项目

```shell
ps -aux | grep java
kill -9 ***	//***为对应进程号
```

