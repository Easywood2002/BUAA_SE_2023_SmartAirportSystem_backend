# 后端API格式说明

**（注：带 “ * ” 为测试API，可能仍需完善或进行较大修改）**

## 一、旅客用户：

### （一）旅客用户注册：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/tourist/logup
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"email": "12345@abc.com", 

		 	 "passwords": "123qwer", 

		 	 "repasswords": "123qwer"}
		 
（用户输入邮箱、密码与确认密码，点击注册按钮后进行注册，初始默认为非vip用户）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息"}
```

### （二）旅客用户登录：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/tourist/login
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"email": "12345@abc.com", 

			 "passwords": "123qwer"}
		 
（用户输入已注册的邮箱与密码进行登录）
```


​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息",
		 
			 "token": "12321tyuyt123hj878sdllo"(登陆成功时，否则为"null"字符串)}
```

### （三）列出实名信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/tourist/listperson
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo"}
		 
（前端传入登录后得到的识别码token，验证后进行查询）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": List形式的由该旅客用户所添加的实名信息列表/"具体原因信息"}
```

### （四）添加实名信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/tourist/addperson
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "78d789dasddasd8",

			"realname": "Jack",
			
			"idnumber": "42021234",
			
			"email": "123@qq.com"}
		 
（前端传入识别码token，用户输入真实姓名、身份证号和邮箱，验证后为该旅客用户添加实名信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息"}
```

### （五）修改实名信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/tourist/updateperson
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "78d789dasddasd8",

			"personid": "2",

			"realname": "Jack",
			
			"idnumber": "42021234",
			
			"email": "123@qq.com"}
		 
（前端传入识别码token与所选实名信息的id，用户输入修改后的真实姓名、身份证号和邮箱，验证后为该用户修改该实名信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息"}
```

### （六）删除实名信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/tourist/removeperson
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "78d789dasddasd8"，

			"personid": "2"}
		 
（前端传入识别码token与所选实名信息的id，验证后为该用户删除该实名信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息"}
```

### （七）查询航班信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/tourist/searchflight
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "78d789dasddasd8"，

			"takeofflocation": "北京",
			
			"landinglocation": "上海",
			
			"date": "2023-06-01"}
		 
（前端传入识别码token，用户输入出发地、目的地和日期，验证后进行查询）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": List形式的由该限制条件筛选出的航班信息列表/"具体原因信息"}
```

### （八）列出航班机票：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/tourist/listticket
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"flightid": "2"}
		 
（前端传入登录后得到的识别码token和航班id，验证后进行查询）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message":  List形式的该航班的机票信息列表/"具体原因信息"}
```

### （九）旅客用户购票：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/tourist/purchaseflight
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"ticketid": "2",
			
			"personidlist": "1&2&3&5&7&11"}
		 
（前端传入用户识别码usertoken，同时传入所选机票id及所选实名信息（同时为多人买票时实名信息id使用“&”字符隔开），验证后为该用户所选的实名信息购票）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息"}
```

### （十）列出已购机票：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/tourist/listpurchase
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo"}

（前端传入登录后得到的识别码token，验证后进行查询）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": List形式的该旅客用户所购买的电子机票列表/"具体原因信息"}
```

### （十一）旅客用户退票：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/tourist/returnpurchase
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"orderid": "2"}
		 
（前端传入登录后得到的识别码token和所选订单id，验证后为该用户退票）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息"}
```



## 二、航空公司：

### （一）航空公司注册：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/company/logup
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"email": "12345@abc.com", 

			"name": "中国东方航空"

		 	"passwords": "123qwer", 

		 	"repasswords": "123qwer"}
		 
（航司输入邮箱、名称、密码与确认密码，点击注册按钮后进行注册）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （二）航空公司登录：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/company/login
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"email": "12345@abc.com", 

		 	 "passwords": "123qwer"}
		 
（航司输入已注册的邮箱与密码进行登录）
```


​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息",
		 
			 "token": "12321tyuyt123hj878sdllo"(登陆成功时，否则为"null"字符串)}
```

### （三）列出航班信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/company/listflight
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo"}
		 
（前端传入登录后得到的识别码token，验证后进行查询）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": List形式的由该航空公司所添加的航班信息列表/"具体原因信息"}
```

### （四）添加航班信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/company/addflight
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "78d789dasddasd8",

			"name": "AB1234",
			
			"takeofflocation": "北京",
			
			"landinglocation": "上海",
			
			"departuretime": "2023-06-01 08:00",
			
			"landingtime": "2023-06-01 10:00",
			
			"departuregate": "10",
			
			"terminal": "2"}
		 
（前端传入识别码token，航司输入航班号、出发地、目的地、起飞时间、到达时间、登机口和航站楼，验证后添加航班信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （五）修改航班信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/company/updateflight
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "78d789dasddasd8",

			"flightid": "3",

			"name": "AB1234",
			
			"takeofflocation": "北京",
			
			"landinglocation": "上海",
			
			"departuretime": "2023-06-01 08:00",
			
			"landingtime": "2023-06-01 10:00",
			
			"departuregate": "10",
			
			"terminal": "2"}
		 
（前端传入识别码token与所选航班信息的id，航司输入修改后的航班号、出发地、目的地、起飞时间、到达时间、登机口和航站楼，验证后修改该航班信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （六）删除航班信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/company/removeflight
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "78d789dasddasd8"，

			"flightid": "3"}
		 
（前端传入识别码token与所选航班信息的id，验证后为该航司删除该航班信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （七）添加机票信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/company/addticket
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "78d789dasddasd8",

			"flightid": "3",
			
			"tickettype": "经济舱",
			
			"price": "588.5",
			
			"amount": "50"}
		 
（前端传入识别码token，航司输入航班id、机票类型、价格和机票数量，验证后为该航班添加机票信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （八）修改机票信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/company/updateticket
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "78d789dasddasd8",

			"ticketid": "3",
			
			"tickettype": "经济舱",
			
			"price": "588.5",
			
			"amount": "50"}
		
（前端传入识别码token与所选机票信息的id，航司输入修改后的机票类型、价格和机票数量，验证后修改该机票信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （九）删除机票信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/company/removeticket
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "78d789dasddasd8"，

			"ticketid": "3"}
			
（前端传入识别码token与所选机票信息的id，验证后为该航司删除该航班的该机票信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"
```

### （十）列出机票信息：

​	**根路径：**

```
http://localhost:8080
```

​	**API路径：**

```
/company/listticket
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"flightid": "2"}
		 
（前端传入登录后得到的识别码token和航班id，验证后进行查询）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": List形式的该航班的机票信息列表/"具体原因信息"}
```



## 三、商户用户：



## 四、工作人员：



**（注：带 “ * ” 为测试API，可能仍需完善或进行较大修改）**
