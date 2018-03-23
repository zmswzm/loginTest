<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>思宇教育</title>
	<link rel="shortcut icon" href="/syedu/resources/image/favicon.ico" />
	<link rel="shortcut icon" href="/syedu/resources/image/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/syedu/system/login/css/login.css">
</head>
<body style="overflow-y:hidden">


	<div class="container">
		<div class="info">
			<h1>思宇教育</h1>
		</div>
	
	<div class="form" id="form">
		<div class="thumbnail"><img src="/syedu/system/login/image/logo.jpg"/></div>
		<input id="username" type="text" placeholder="请输入用户名" required autofocus/>
		<input id="password" type="password" placeholder="请输入密码" required/>
		<button onclick="login()">登&nbsp;&nbsp;陆</button>
	</div>
		<p class="message">Copyright &copy; 2016-<% out.print(new java.text.SimpleDateFormat("yyyy").format(new Date())); %> 思宇教育. All rights reserved.</p>
	</div>
	<script type="text/javascript" src='/syedu/system/common/jquery-3.2.1.min.js'></script>
	<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.min.js"></script>
	<script type="text/javascript" src="/syedu/system/login/js/login.js"></script>
</body>
</html>