<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>思宇教育</title>
    <link rel="shortcut icon" href="/syedu/resources/image/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/syedu/system/updatePW/css/index.css">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <form role="form" id="form">
        <h2 class="form-signin-heading text-center">修改密码</h2>
        <div class="form-group">
            <label for="oldPW">&nbsp&nbsp原密码</label>
            <input type="password" class="form-control" name="oldPW" id="oldPW" placeholder="请输入原密码" required autofocus>
        </div>
        <div class="form-group">
            <label for="newPW">&nbsp&nbsp新密码</label>
            <input type="password" class="form-control" name="newPW" id="newPW" placeholder="请输入新密码" required>
        </div>
        <div class="form-group">
            <label for="confirmNewPW">&nbsp&nbsp再次确认</label>
            <input type="password" class="form-control" name="confirmNewPW" id="confirmNewPW" placeholder="请再输入新密码" required>
        </div>

        <div class="form-group">
            <button type="submit" id="submit" class="btn btn-primary btn-lg btn-block">确&nbsp&nbsp认&nbsp&nbsp提&nbsp&nbsp交</button>
        </div>
    </form>

   <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.min.js"></script>
   <script type="text/javascript" src="/syedu/system/updatePW/js/index.js"></script>
</body>
</html>