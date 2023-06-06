# 后端API格式说明

**（注：带 “ * ” 为测试API，可能仍需完善或进行较大修改）**

## 一、旅客用户：

### （一）旅客用户注册：

​	**根路径：**

```
http://101.200.143.220:8080
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
http://101.200.143.220:8080
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

### （三）旅客修改密码：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/updatepassword
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

             "passwords": "123qwer",

		 	"newpasswords": "new123qwer", 

		 	"renewpasswords": "newa123qwer"}
		 
（前端传入识别码token，旅客输入旧密码、新密码与确认密码，验证后为该旅客用户修改旧密码为新密码）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （四）旅客改绑邮箱：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/updateemail
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

             "newemail": "223@qq.com"}
		 
（前端传入识别码token，旅客输入新邮箱，验证后为该旅客用户改绑邮箱）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （五）列出实名信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （六）添加实名信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （七）修改实名信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （八）删除实名信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （九）查询航班信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （十）列出航班机票：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （十一）旅客用户购票：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （十二）列出已购机票：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （十三）旅客用户退票：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （十四）列出空余座位：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/listseat
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"orderid": "2"}
		 
（前端传入登录后得到的识别码token和所选订单id，验证后进行查询）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": List形式的该订单所对应的机票类型的座位列表/"具体原因信息"}
```

### （十五）旅客用户选座：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/selectseat
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"orderid": "2",
			
			"seatid": "33"}
		 
（前端传入登录后得到的识别码token、所选订单id和所选座位号，验证后为该用户的该机票进行选座）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息"}
```

### （十六）旅客预定泊车：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/selectparking
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"starttime": "2023-06-01 11:30",
			
			"endtime": "2023-06-01 12:00",
			
			"parkingspaceid": "1"}
		 
（前端传入登录后得到的识别码token，用户输入预定开始时间、预定结束时间和所选车位id，验证后为该用户预定该停车位）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息"}
```

### （十七）列出泊车订单：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/listparkingorder
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo"}
		 
（前端传入登录后得到的识别码token，验证后列出该用户的泊车订单）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": List形式的该旅客用户所预定的停车位订单列表/"具体原因信息"}
```

### （十八）旅客退订泊车：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/returnparkingorder
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"orderid": "2"}
		 
（前端传入登录后得到的识别码token，用户输入所选订单的订单号，验证后为该用户取消该泊车订单）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息"}
```

### （十九）列出空余车位：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/listparkinspace
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo"}
		 
（前端传入登录后得到的识别码token，验证后为该用户列出该机场的所有空余车位）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": List形式的机场空余车位列表/"具体原因信息"}
```

### （二十）列出机场商户：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/listmerchant
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo"}
		 
（前端传入登录后得到的识别码token，验证后为该用户列出该机场的所有商户）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": List形式的机场商户列表/"具体原因信息"}
```

### （二十一）列出商户商品：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/listcommodity
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

             "merchantid": "5"}
		 
（前端传入登录后得到的识别码token及用户所选的商户id，验证后为该用户列出该商户的所有商品）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": List形式的商品信息列表/"具体原因信息"}
```

### （二十二）旅客订购商品：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/purchasecommodity
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

             "commodityid": "2",
             
             "counts": "10",
             
             "terminal": "2",
             
             "departuregate": "10",
             
             "arrivetime": "2023-6-4 09:30"}
		 
（前端传入登录后得到的识别码token，用户输入所选商品id、订购数量、航站楼、登机口和期望送达时间，验证后为该用户订购一定数量的该商品并生成订单）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息"}
```

### （二十三）列出商品订单：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/listcommodityorder
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo"}
		 
（前端传入登录后得到的识别码token，验证后为该用户列出其所有商品订单）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": List形式的商品订单列表/"具体原因信息"}
```

### （二十四）旅客退订商品：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/returncommodity
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

             "orderid": "2"}
		 
（前端传入登录后得到的识别码token及订单id，验证后为该用户取消该订单）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息"}
```

### （二十五）查询行李状态：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/tourist/checkluggage
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

             "orderid": "2"}
		 
（前端传入登录后得到的识别码token及订单id，验证后为该用户查询该机票订单所对应的实名乘客的行李状态）
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
http://101.200.143.220:8080
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

			"name": "中国东方航空",

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
http://101.200.143.220:8080
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

### （三）航司修改密码：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/company/updatepassword
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

             "passwords": "123qwer",

		 	"newpasswords": "new123qwer", 

		 	"renewpasswords": "newa123qwer"}
		 
（前端传入识别码token，航司输入旧密码、新密码与确认密码，验证后为该航空公司修改旧密码为新密码）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （四）修改航司信息：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/company/updatecompany
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"email": "12345@abc.com", 

			"name": "中国东方航空"}
		 
（前端传入识别码token，航司输入修改后的名称与邮箱，验证后为该航司修改航司信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （五）列出航班信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （六）添加航班信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （七）修改航班信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （八）删除航班信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （九）添加机票信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （十）修改机票信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （十一）删除机票信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （十二）列出机票信息：

​	**根路径：**

```
http://101.200.143.220:8080
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

### （一）商户入驻申请：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/merchant/logup
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"realname": "Jack",

			"shopname": "ABCShop",
			
			"email": "223@qq.com",
			
			"idnumber": "42021234",

		 	"passwords": "123qwer", 

		 	"repasswords": "123qwer"}
		 
（商户输入真实姓名、店铺名称、邮箱、身份证号、密码与确认密码，点击入驻按钮后提交入驻申请并等待审批）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （二）商户用户登录：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/merchant/login
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"email": "12345@abc.com", 

			 "passwords": "123qwer"}
		 
（商户输入已注册的邮箱与密码进行登录）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息",
		 
			 "token": "12321tyuyt123hj878sdllo"(登陆成功时，否则为"null"字符串)}
```

### （三）商户修改密码：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/merchant/updatepassword
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

             "passwords": "123qwer",

		 	"newpasswords": "new123qwer", 

		 	"renewpasswords": "newa123qwer"}
		 
（前端传入识别码token，商户输入旧密码、新密码与确认密码，验证后为该商户用户修改旧密码为新密码）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （四）修改商户信息：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/merchant/updatemerchant
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"shopname": "ABCShop",
			
			"email": "223@qq.com",
			
			"idnumber": "42021234"}
		 
（前端传入识别码token，商户输入修改后的真实姓名、店铺名称、邮箱和身份证号，验证后为该商户修改店铺信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （五）添加商品信息：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/merchant/addcommodity
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"name": "Cookie",
			
			"counts": "100",
			
			"price": "23.5"}
		 
（前端传入识别码token，商户输入商品名称、库存数量与单价，验证后为该店铺添加该商品信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （六）修改商品信息：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/merchant/updatecommodity
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"commodityid": "5",

			"name": "Cookie",
			
			"counts": "100",
			
			"price": "23.5"}
		 
（前端传入识别码token与所选商品信息的id，商户输入修改后的商品名称、库存数量与单价，验证后修改该商品信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （七）删除商品信息：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/merchant/removecommodity
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"commodityid": "5"}
		 
（前端传入识别码token与所选商品信息的id，验证后为该商户删除该商品信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （八）列出商品信息：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/merchant/listcommodity
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo"}
		 
（前端传入识别码token，验证后列出该商户的所有商品信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": List形式的该商户的商品信息列表/"具体原因信息"}
```

### （九）列出商品订单：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/merchant/listcommodityorder
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo"}
		 
（前端传入识别码token，验证后列出该商户的所有订单信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": List形式的该商户的商品订单列表/"具体原因信息"}
```



## 四、工作人员：

### （一）工作人员注册：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/staff/logup
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"realname": "Jack",

			"positionpost": "0",
			
			"email": "223@qq.com",
			
			"idnumber": "42021234",

		 	"passwords": "123qwer", 

		 	"repasswords": "123qwer"}
		 
（员工输入真实姓名、职位、邮箱、身份证号、密码与确认密码，点击注册按钮后进行注册）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （二）工作人员登录：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/staff/login
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"email": "12345@abc.com", 

			 "passwords": "123qwer"}
		 
（员工输入已注册的邮箱与密码进行登录）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": "具体原因信息",
		 
			 "token": "12321tyuyt123hj878sdllo"(登陆成功时，否则为"null"字符串)}
```

### （三）员工修改密码：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/staff/updatepassword
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

             "passwords": "123qwer",

		 	"newpasswords": "new123qwer", 

		 	"renewpasswords": "newa123qwer"}
		 
（前端传入识别码token，员工输入旧密码、新密码与确认密码，验证后为该工作人员修改旧密码为新密码）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （四）修改员工信息：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/staff/updatestaff
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"realname": "Jack",

			"positionpost": "0",
			
			"email": "223@qq.com",
			
			"idnumber": "42021234"}
		 
（前端传入识别码token，员工输入修改后的真实姓名、职位、邮箱和身份证号，验证后为该员工修改员工信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （五）添加报修申请：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/staff/addrepairrecord
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"devicename": "Light",

			"devicepicture": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAB …",
			
			"deviceinfo": "Broken",
			
			"location": "North Gate"}
		 
（前端传入识别码token，员工输入设备名称、设备图片、设备信息和位置，验证后生成报修清单并提交）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （六）列出报修申请：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/merchant/listrepairrecord
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo"}
		 
（前端传入识别码token，验证后列出所有设备报修申请信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": List形式的设备报修申请信息列表/"具体原因信息"}
```

### （七）审核报修申请：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/staff/examinerepairrecord
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"recordid": "4",

			"approved": "0"}
		 
（前端传入识别码token，管理员输入所选的申请id和处理意见，验证后对该申请进行相应的处理）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （八）删除报修申请：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/staff/removerepairrecord
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"recordid": "5"}
		 
（前端传入识别码token，管理员输入所选的申请id，验证后删除该申请）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （九）列出入驻申请：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/merchant/listmerchantrequest
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo"}
		 
（前端传入识别码token，验证后列出所有商户入驻申请信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": List形式的所有商户入驻申请信息列表/"具体原因信息"}
```

### （十）审核入驻申请：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/staff/examinemerchantrequest
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"requestid": "4",

			"approved": "0"}
		 
（前端传入识别码token，管理员输入所选的申请id和处理意见，验证后对该申请进行相应的处理）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （十一）添加行李信息：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/staff/addluggage
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"orderid": "4",

			"state": "arrived",
			
			"location": "A10"}
		 
（前端传入识别码token，管理员输入机票订单号、行李状态和行李位置，验证后为该订单添加行李信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （十二）更新行李信息：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/staff/updateluggage
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"luggageid": "4",

			"state": "arrived",
			
			"location": "A10"}
		 
（前端传入识别码token，管理员输入行李id、更新后的状态和位置，验证后对该行李信息进行更新）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```

### （十三）删除行李信息：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/staff/removeluggage
```

​	**请求方式**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"token": "12321tyuyt123hj878sdllo",

			"luggageid": "4"}
		 
（前端传入识别码token，管理员输入行李id，验证后删除该行李信息）
```

​	**返回数据示例：**

```
			{"success": true/false, 

			 "message": "具体原因信息"}
```



## 五、普通游客：

### （一）查询航班信息：

​	**根路径：**

```
http://101.200.143.220:8080
```

​	**API路径：**

```
/passby/searchflight
```

​	**请求方式：**

```
POST
```

​	**携带数据示例（最佳建议）：**

```
Form Data请求{"takeofflocation": "北京",
			
			"landinglocation": "上海",
			
			"date": "2023-06-01"}
		 
（前端传入识别码token，游客输入出发地、目的地和日期进行查询）
```

​	**返回数据示例：**

```
			{"success": true/false, 

		 	 "message": List形式的由该限制条件筛选出的航班信息列表/"具体原因信息"}
```



**（注：带 “ * ” 为测试API，可能仍需完善或进行较大修改）**
