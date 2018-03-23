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
    <link type="text/css" rel="stylesheet" href="/syedu/system/major/css/index.css">
</head>
<body style="overflow-x:hidden">
<div class="row">
    <div class="panel-body">
        <!-- 过滤框 -->
        <div class="panel panel-default">
            <div class="panel-heading">专业管理</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top: 5px">
                        <label class="control-label col-sm-1" for="SchoolName">学校名称</label>
                        <div class="col-sm-2">
                            <select name="schoolName" class="form-control" id="schoolName">
                            </select>
                        </div>
                        <label class="control-label col-sm-1" for="level">&nbsp&nbsp专业层次</label>
                        <div class="col-sm-2">
                            <select name="level" class="form-control" id="level" required>
                                <option value="高起专">高起专</option>
                                <option value="高起本">高起本</option>
                                <option value="专升本">专升本</option>
                            </select>
                        </div>
                        <div class="col-sm-3" style="text-align: left;">
                            <button type="button" style="margin-left: 10px" id="btn-query" class="btn btn-primary" onclick = "cfind()">查询</button>
                            <button type="button" style="margin-left: 10px" id="btn-add" class="btn btn-primary" onclick = "addM()">新增专业</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

       <table class="table table-hover" id = "myMajorTab">
           <thead>
           <tr>
               <th data-field="id">专业编码</th>
               <th data-field="schoolName">学校名称</th>
               <th data-field="level">专业层次</th>
               <th data-field="major">专业名称</th>
               <th data-field="operate">操作</th>
           </tr>
           </thead>
           <tbody>

           </tbody>
       </table>
</div>
   <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
   <script src="https://cdn.bootcss.com/x-editable/1.5.1/bootstrap3-editable/js/bootstrap-editable.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/extensions/editable/bootstrap-table-editable.js"></script>
   <script type="text/javascript" src="/syedu/system/major/js/index.js"></script>
</body>
</html>