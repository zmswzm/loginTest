<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>思宇教育</title>
    <link rel="shortcut icon" href="/syedu/resources/image/favicon.ico" />
    <link rel="stylesheet" href="/syedu/system/main/css/normalize.css">

    <link rel="stylesheet" href="/syedu/system/main/css/style.css" media="screen" type="text/css" />

    <script src="/syedu/system/main/js/modernizr.js"></script>

</head>

<body>

<nav id="site-nav" class="site-nav" role='navigation'>
    <button href="#" id="site-nav--toggle"  class="site-nav--toggle"><i class="entypo-menu"></i>菜&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp单</button>
    <ul id = "site-nav--list" class="site-nav--list">
        <li><a href='/syedu/system/course/index.jsp' target='main'><i class='entypo-book-open'></i>主&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp页</a></li>
        <li><a href='/syedu/system/student/index.jsp' target='main'><i class='entypo-graduation-cap'></i>学生信息</a></li>
    </ul>
</nav>
<div class="wrapper">
    <div class = "kc-outsaid">
        <iframe id="mainframe" src='/syedu/system/course/index.jsp' name = 'main' width="100%" height="100%" frameborder=0 ></iframe>
    </div>
</div>

<script src="/syedu/system/main/js/jquery.js"></script>
<script src="/syedu/system/main/js/index.js"></script>


</body>

</html>