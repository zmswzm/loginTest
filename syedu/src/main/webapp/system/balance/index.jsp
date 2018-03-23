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
    <link type="text/css" rel="stylesheet" href="/syedu/system/balance/css/index.css">
</head>
<body style="overflow-x:hidden">
<div class="row">
    <div class="panel-body">
        <!-- 过滤框 -->
        <div class="panel panel-default">
            <div class="panel-heading">提成结算</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top: 5px">
                        <label class="control-label col-sm-1" for="cname">代理姓名</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="cname" name = "cname">
                        </div>
                        <label class="control-label col-sm-1" for="cphone">代理手机号</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="cphone" name="cphone">
                        </div>
                        <label class="control-label col-sm-1" id="labcyearNo" for="cyearNo">结算批次</label>
                        <div class="col-sm-2">
                            <select name="cyearNo" class="form-control" id="cyearNo">
                            </select>
                        </div>

                        <div class="col-sm-12" style="text-align: right;">
                            <button type="button" style="margin-left: 10px" id="btn-balance" class="btn btn-primary" onclick = "balanceManage()">结算管理</button>
                            <button type="button" style="margin-left: 10px" id="btn-query" class="btn btn-primary" onclick = "cfind()">查询</button>
                            <button type="button" style="margin-left: 10px" id="btn-reset" class="btn btn-primary" onclick = "creset()">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <table class="table table-hover" id = "myUserTab">
        <thead>
        <tr>
            <th data-field="uid">代理编码</th>
            <th data-field="name">代理姓名</th>
            <th data-field="phone">代理手机</th>
            <th data-field="idNumCard">身份证号</th>
            <th data-field="yearNo">结算批次</th>
            <th data-field="fee">金额</th>
            <th data-field="status">操作</th>
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
<script type="text/javascript" src="/syedu/system/balance/js/index.js"></script>
</body>
</html>