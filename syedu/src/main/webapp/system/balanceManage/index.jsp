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

<div class="row">
    <div class="panel-body">
        <!-- 过滤框 -->
        <div class="panel panel-default">
            <div class="panel-heading">当前批次设置</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top: 5px">
                        <label class="control-label col-sm-1" id="labcyearNo" for="cyearNo">当前批次</label>
                        <div class="col-sm-2">
                            <select name="cyearNo" class="form-control" id="cyearNo">
                            </select>
                        </div>

                        <div class="col-sm-3" style="text-align: left;">
                            <button type="button" style="margin-left: 10px" id="btn-query" class="btn btn-primary" onclick = "cChange()">修改</button>
                            <button type="button" style="margin-left: 10px" id="btn-add" class="btn btn-primary" onclick = "cbalance()">生成结算</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>

   <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="/syedu/system/balanceManage/js/index.js"></script>
</body>
</html>