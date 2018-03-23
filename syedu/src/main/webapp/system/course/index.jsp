<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>思宇教育</title>
    <link rel="shortcut icon" href="/syedu/resources/image/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/syedu/system/course/css/index.css">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

    <div id = "course-list" class="list-group">

        <a id = "user-card" class='list-group-item' style='margin:20px;display: none'>
            <div  class="row" style="height:230px">
                <div class="col-xs-4 col-md-6">
                    <div class="row text-center">
                        <img src="/syedu/resources/image/student.png" height="70" width="70" onclick='student()'>
                        <h6>招收学生</h6>
                    </div>
                    <div id = "group" class="row text-center" style="display: none">
                        <img src="/syedu/resources/image/group.png" height="70" width="70" onclick='group()'>
                        <h6>招收代理</h6>
                    </div>
                </div>
                <div class="col-xs-8 col-md-6">
                    <h2><strong id="uname"></strong><small><small id="utype"></small></small></h2><br>
                    <p id ="pcurrentNo">当前批次：<strong id="currentNo">-</strong></p>
                    <p id = "puStudentNo">招生人数：<strong id="uStudentNo">-</strong></p>
                    <p id = "group-per" style="display: none">招生提成：<strong id="uPercentage">-</strong></p>
                </div>
            </div>
        </a>

    </div>

  <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="/syedu/system/course/js/index.js"></script>
</body>

</html>