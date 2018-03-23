<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>思宇教育</title>
    <link rel="shortcut icon" href="/syedu/resources/image/favicon.ico" />
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-fileinput/4.4.2/css/fileinput.min.css" rel="stylesheet">

</head>
<body>
<h2 class="form-signin-heading text-center" id = "courseAddTitle">附件</h2>
<form role="form" id="attach"  enctype="multipart/form-data;charset=utf-8" >
    <div class="form-group">
        <label for="inputfile">&nbsp&nbsp图片选择</label>
        <input id="inputfile" type="file" name="imagefile" multiple>
    </div>
</form>
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.2/js/fileinput.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.1/js/fileinput_locale_zh.min.js"></script>
    <script type="text/javascript" src="/syedu/system/attach/js/index.js"></script>
</body>
</html>