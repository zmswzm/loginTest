<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>思宇教育</title>
    <link rel="shortcut icon" href="/syedu/resources/image/favicon.ico" />
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/x-editable/1.5.1/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet">
</head>
<body style="overflow-x:hidden">
<div class="row">
    <div class="panel-body">
        <!-- 过滤框 -->

       <table class="table table-hover" id = "percentageTab">
           <thead>
           <tr>
               <th data-field="uid">用户编码</th>
               <th data-field="cid">课程编码</th>
               <th data-field="uname">用户姓名</th>
               <th data-field="uphone">用户手机</th>
               <th data-field="cname">课程名称</th>
               <th data-field="schoolName">课程所属学校</th>
               <th data-field="per">提成比例</th>
           </tr>
           </thead>
           <tbody>

           </tbody>
       </table>
</div>
   <script type="text/javascript" src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
   <script type="text/javascript" src="https://cdn.bootcss.com/x-editable/1.5.1/bootstrap3-editable/js/bootstrap-editable.js"></script>
   <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.js"></script>
   <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap-table/1.11.1/extensions/editable/bootstrap-table-editable.js"></script>
   <script type="text/javascript" src="/syedu/system/percentage/js/index.js"></script>
</body>
</html>