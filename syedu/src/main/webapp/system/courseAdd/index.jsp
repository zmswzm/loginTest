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
    <link href="https://cdn.bootcss.com/summernote/0.8.6/summernote.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/summernote/0.6.16/summernote-bs3.css" rel="stylesheet">

</head>
<body>
<h2 class="form-signin-heading text-center" id = "courseAddTitle">新增课程</h2>
<form role="form" id="courseAdd"  enctype="multipart/form-data;charset=utf-8" >
    <input type="hidden" name="cid" id="cid">
    <div class="form-group">
        <label for="school">&nbsp&nbsp所属学校</label>
        <select name="school" class="form-control" id="school">
        </select>
    </div>
    <div class="form-group">
        <label for="name">&nbsp&nbsp标题</label>
        <input type="text" class="form-control" id="name" name="name"
               placeholder="请输入标题">
    </div>
    <div class="form-group">
        <label for="inputfile">&nbsp&nbsp图片选择</label>
        <input id="inputfile" type="file" name="imagefile" multiple>
    </div>
    <div class="form-group">
        <label for="detail">&nbsp&nbsp详细内容</label>
        <div id = "detail" class="summernote"></div>
    </div>
    <button type="submit" class="btn btn-primary btn-block" id="courseSubmit">提交</button>
</form>
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.2/js/fileinput.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.1/js/fileinput_locale_zh.js"></script>
    <script type="text/javascript" src="/syedu/system/courseAdd/js/index.js"></script>
    <script src="https://cdn.bootcss.com/summernote/0.8.6/summernote.min.js"></script>
</body>
</html>