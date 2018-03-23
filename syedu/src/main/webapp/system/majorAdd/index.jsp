<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
        <h2 class="form-signin-heading text-center">新增专业</h2>
        <div class="form-group">
            <label for="schoolName">&nbsp&nbsp所属学校</label>
            <select name="schoolName" class="form-control" id="schoolName">
            </select>
        </div>
        <div class="form-group">
            <label for="level">&nbsp&nbsp专业层次</label>
            <select name="level" class="form-control" id="level" required>
                <option value="高起专">高起专</option>
                <option value="高起本">高起本</option>
                <option value="专升本">专升本</option>
            </select>
        </div>
        <div class="form-group">
            <label for="major">&nbsp&nbsp专业名称</label>
            <input type="text" class="form-control" name="major" id="major" placeholder="请输入专业名称" required>
        </div>
        <div class="form-group">
            <button type="submit" id="submit" class="btn btn-primary btn-lg btn-block">提&nbsp&nbsp交</button>
        </div>
    </form>

   <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="/syedu/system/majorAdd/js/index.js"></script>
</body>
</html>