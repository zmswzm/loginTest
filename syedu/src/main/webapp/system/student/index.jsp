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
    <link type="text/css" rel="stylesheet" href="/syedu/system/student/css/index.css">
</head>
<body style="overflow-x:hidden">
<div class="row">
    <div class="panel-body">
        <!-- 过滤框 -->
        <div class="panel panel-default">
            <div class="panel-heading">学生信息</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top: 5px">
                        <label class="control-label col-sm-1" for="cname">学生姓名</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="cname" name = "cname">
                        </div>
                        <label class="control-label col-sm-1" for="cuphone" id = "labcuphone">老师手机</label>
                        <div class="col-sm-2" id="divcuphone">
                            <input type="text" class="form-control" id="cuphone" name="cuphone">
                        </div>
                        <label class="control-label col-sm-1" for="yearNo">批次</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="yearNo" name="yearNo">
                        </div>
                        <label class="control-label col-sm-1" for="cstatus">缴费状态</label>
                        <div class="col-sm-2">
                            <select name="cstatus" class="form-control" id="cstatus" required>
                                <option value="0">所有学生</option>
                                <option value="1">全未缴清</option>
                                <option value="2">全已缴清</option>
                                <option value="3">第一学年已缴</option>
                                <option value="4">第二学年已缴</option>
                            </select>
                        </div>

                        <div class="col-sm-12" style="text-align: right;">
                            <button type="button" style="margin-left: 10px" id="btn-query" class="btn btn-primary" onclick = "cfind()">查询</button>
                            <button type="button" style="margin-left: 10px" id="btn-reset" class="btn btn-primary" onclick = "creset()">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

       <table class="table table-hover" id = "myStudentTab">
           <thead>
           <tr>
               <th data-field="id">学生编码</th>
               <th data-field="name">姓名</th>
               <th data-field="idNumCard">身份证</th>
               <th data-field="phone">手机号</th>
               <th data-field="schoolName">报考学校</th>
               <th data-field="major">报考专业</th>
               <th data-field="level">报考层次</th>
               <th data-field="yearNo">批次</th>
               <th data-field="createTime">报名时间</th>
               <th data-field="uid">招生老师</th>
               <th data-field="status1">第一学年</th>
               <th data-field="status1Time">缴费时间(一)</th>
               <th data-field="status2">第二学年</th>
               <th data-field="status2Time">缴费时间(二)</th>
               <th data-field="serverFee">服务费</th>
               <th data-field="serverFeeTime">缴费时间(三)</th>
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
<script type="text/javascript" src="/syedu/system/common/xlsx.core.min.js"></script>
<script type="text/javascript" src="/syedu/system/common/FileSaver.min.js"></script>
<script src="https://cdn.bootcss.com/TableExport/5.0.0-rc.9/js/tableexport.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/extensions/export/bootstrap-table-export.min.js"></script>
    <script type="text/javascript" src="/syedu/system/student/js/index.js"></script>
</body>
</html>