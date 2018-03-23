<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="java.util.*" %>
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
        <h2 class="form-signin-heading text-center">报名填单</h2>
        <div class="form-group">
            <label for="name">&nbsp&nbsp学生姓名</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="请输入姓名" required autofocus>
        </div>
        <div class="form-group">
            <label for="sexdiv">&nbsp&nbsp性&nbsp&nbsp&nbsp&nbsp别</label>
            <div id="sexdiv">
                <label class="radio-inline">
                    <input type="radio" name="sex" id="sex1" value="男" checked >&nbsp&nbsp男&nbsp&nbsp&nbsp&nbsp
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="sex2"  value="女">&nbsp&nbsp女
                </label>
            </div>
        </div>
        <div class="form-group">
            <label for="degree">&nbsp&nbsp文化程度</label>
            <select name="degree" class="form-control" id="degree" required>
                <option value="小学">小学</option>
                <option value="初中">初中</option>
                <option value="高中">高中</option>
                <option value="技工学校">技工学校</option>
                <option value="中专">中专</option>
                <option value="大专">大专</option>
                <option value="本科">本科</option>
                <option value="研究生">研究生</option>
            </select>
        </div>
        <div class="form-group">
            <label for="politicaStatus">&nbsp&nbsp政治面貌</label>
            <select name="politicaStatus" class="form-control" id="politicaStatus" required>
                <option value="团员">团员</option>
                <option value="党员">党员</option>
                <option value="群众">群众</option>
            </select>
        </div>
        <div class="form-group">
            <label for="title">&nbsp&nbsp职&nbsp&nbsp称</label>
            <input type="text" class="form-control" name="title" id="title" placeholder="请输入职称" required>
        </div>
        <div class="form-group">
            <label for="phone">&nbsp&nbsp手机号</label>
            <input type="text" class="form-control" name="phone" id="phone" placeholder="请输入手机号" required>
        </div>
        <div class="form-group">
            <label for="weChat">&nbsp&nbsp微信号</label>
            <input type="text" class="form-control" name="weChat" id="weChat" placeholder="请输入微信号" required>
        </div>
        <div class="form-group">
            <label for="QQNum">&nbsp&nbspQQ号</label>
            <input type="text" class="form-control" name="QQNum" id="QQNum" placeholder="请输入QQ号" required>
        </div>
        <div class="form-group">
            <label for="idNumCard">&nbsp&nbsp身份证号</label>
            <input type="text" class="form-control" name="idNumCard" id="idNumCard" placeholder="请输入身份证号" required>
        </div>
        <div class="form-group">
            <label for="company">&nbsp&nbsp工作单位</label>
            <input type="text" class="form-control" name="company" id="company" placeholder="请输入工作单位" required>
        </div>
        <div class="form-group">
            <label for="marriage">&nbsp&nbsp婚姻状况</label>
            <select name="marriage" class="form-control" id="marriage" required>
                <option value="未婚">未婚</option>
                <option value="已婚">已婚</option>
                <option value="离异">离异</option>
                <option value="丧偶">丧偶</option>
            </select>
        </div>
        <div class="form-group">
            <label for="graduated">&nbsp&nbsp毕业院校</label>
            <input type="text" class="form-control" name="graduated" id="graduated" placeholder="请输入毕业院校" required>
        </div>
        <div class="form-group">
            <label for="school">&nbsp&nbsp报考学校</label>
            <select name="school" class="form-control" id="school" disabled = "disabled" onchange = "schoolChange(this.options[this.options.selectedIndex].value)">
            </select>
        </div>
        <div class="form-group">
            <label for="level">&nbsp&nbsp报考层次</label>
            <select name="level" class="form-control" id="level"  onchange = "levelChange(this.options[this.options.selectedIndex].value)" required>
                <option value="高起专">高起专</option>
                <option value="高起本">高起本</option>
                <option value="专升本">专升本</option>
            </select>
        </div>
        <div class="form-group">
            <label for="major">&nbsp&nbsp报考专业</label>
            <select name="major" class="form-control" id="major"></select>
        </div>
        <div class="form-group">
            <label for="city">&nbsp&nbsp学生所在校区</label>
            <select name="city" class="form-control" id="city" required>
                <option value="西安">西安</option>
                <option value="户县">户县</option>
                <option value="神木">神木</option>
                <option value="平凉">平凉</option>
                <option value="华亭">华亭</option>
                <option value="庄浪">庄浪</option>
                <option value="静宁">静宁</option>
                <option value="庆阳">庆阳</option>
            </select>
        </div>
        <input type="hidden" name="uid" id="uid">

        <div class="form-group">
            <input type = "button" id="submit" class="btn btn-primary btn-lg btn-block" onclick="regsub()" value = "报&nbsp&nbsp名&nbsp&nbsp提&nbsp&nbsp交">
        </div>
    </form>

   <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="/syedu/system/register/js/index.js"></script>
</body>
</html>