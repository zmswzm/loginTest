<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>思宇教育</title>
    <link rel="shortcut icon" href="/syedu/resources/image/favicon.ico" />
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <form role="form" id="form">
        <h2 class="form-signin-heading text-center">代理注册</h2>
        <div class="form-group">
            <label for="name">&nbsp&nbsp姓&nbsp&nbsp&nbsp&nbsp名</label>
            <input type="text" class="form-control" id="name" placeholder="请输入姓名" required autofocus>
        </div>
        <div class="form-group">
            <label for="idCardNo">&nbsp&nbsp&nbsp身份证号</label>
            <input type="text" class="form-control" id="idCardNo" placeholder="请输入身份证" required>
        </div>
        <div class="form-group">
            <label for="bankCardNo">&nbsp&nbsp&nbsp银行卡号</label>
            <input type="text" class="form-control" id="bankCardNo" placeholder="请输入银行卡号" required>
        </div>
        <div class="form-group">
            <label for="bankName">&nbsp&nbsp&nbsp开户银行</label>
            <input type="text" class="form-control" id="bankName" placeholder="请输入开户银行" required>
        </div>
        <div class="form-group">
            <label for="phone">&nbsp&nbsp手机号码</label>
            <input type="text" class="form-control" id="phone" placeholder="请输入手机号码" required>
        </div>
        <div class="form-group">
            <label for="sexdiv">&nbsp&nbsp性&nbsp&nbsp&nbsp&nbsp别</label>
            <div id="sexdiv">
                <label class="radio-inline">
                    <input type="radio" name="sex" id="sex1" value="男" checked >&nbsp&nbsp男&nbsp&nbsp&nbsp&nbsp
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="sex2"  value="女">&nbsp&nbsp女
                </label>
            </div>
        </div>
        <div class="form-group">
            <label for="userType">&nbsp&nbsp代理类型</label>
            <select name="userType" class="form-control" id="userType" required>
                <option value="1">管理员</option>
                <option value="2">一级代理</option>
                <option value="3">二级代理</option>
            </select>
        </div>
        <div class="form-group">
            <label for="rName">&nbsp&nbsp推荐人</label>
            <input type="text" class="form-control" id="rName" value = "" readonly>
        </div>
        <div class="form-group">
            <input type="hidden" class="form-control" id="rId" value = "" >
        </div>

        <input type="button" class="btn btn-primary btn-lg btn-block" onclick="userAdd()" value = "提&nbsp&nbsp交&nbsp&nbsp信&nbsp&nbsp息">
    </form>

   <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script src="/syedu/system/userAdd/js/index.js"></script>
</body>
</html>