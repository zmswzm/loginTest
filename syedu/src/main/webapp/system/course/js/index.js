$(document).ready(function() {
    window.alert = function alertw(name) {
        var iframe = document.createElement("IFRAME");
        iframe.style.display = "none";
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(name);
        iframe.parentNode.removeChild(iframe);
    };

    var url = "/syedu/register/loadStudentNo.do";
    if(window.localStorage.getItem("isLogin")){
        document.getElementById("user-card").style.display="";
    }else{
        document.getElementById("user-card").style.display="none";
    }
    if("3" == window.localStorage.getItem("utype")){
        document.getElementById("group-per").style.display="none";
        document.getElementById("group").style.display="none";
    }else if("1" == window.localStorage.getItem("utype")){
        url = "/syedu/register/loadAdminStudentNo.do";
        document.getElementById("group-per").style.display="none";
        document.getElementById("group").style.display="";
    }else{
        document.getElementById("group-per").style.display="";
        document.getElementById("group").style.display="";
    }
    loadCourse();
    loadAchieve(url);

    document.getElementById("uname").innerHTML = window.localStorage.getItem("uname");
    document.getElementById("utype").innerHTML = "("+getUsertype(window.localStorage.getItem("utype"))+")";
});

function getUsertype(utype) {
    switch(utype){
        case "1": return "管理员";
        case "2": return "一级代理/员工";
        case "3": return "二级代理";
        case "4": return "教务";
        case "5": return "财务";
        default: "";
    }
}

function register(school) {
    location.href = "/syedu/system/register/index.jsp?school="+school
}

function updateCourse(cid){
    location.href = "/syedu/system/courseAdd/index.jsp?cid="+cid;
}

function delCourse(cid){
    if(confirm("确定删除吗？")){
        $.ajax({
            type: "post",
            url: "/syedu/course/delete.do",
            async : false, //同步
            dataType: 'JSON',
            data :{
                cid : cid
            },
            success: function (data, status) {
                if (status == "success") {
                    location.reload();
                }
            },
            error : function(data) {
                window.top.location.href = "/syedu/system/error/index.jsp";
            }
        });
    }
}

function addCourse(id,school,image,title) {
    var del = "";
    if("1" == window.localStorage.getItem("utype")){
        del ="<button type='button' class='btn btn-warning btn-sm btn-block' style='float:left;width:50%' id='"+id+"' onclick='delCourse(this.id)'>删&nbsp&nbsp除</button>"+
            "<button type='button' class='btn btn-info btn-sm btn-block' style='width:50%' id='"+id+"' onclick='updateCourse(this.id)'>修&nbsp&nbsp改</button>";
    }
    document.getElementById("course-list").innerHTML = document.getElementById("course-list").innerHTML +
        "<a class='list-group-item' style='margin-top:10px;margin-bottom:10px;border-radius:6px'>"+
        "<img src='/syedu/image/"+image+"' class='img-rounded' style='width: 100%;height:160px' id='"+id+"' name='"+school+";"+title+"' onclick='s(this.id,this.name)'>"+
        "<h3 class='list-group-item-heading'>"+title+"</h3>"+
        "<button type='button' class='btn btn-danger btn-sm btn-block' id='"+school+"' onclick='register(this.id)'>我&nbsp&nbsp&nbsp&nbsp要&nbsp&nbsp&nbsp&nbsp报&nbsp&nbsp&nbsp&nbsp名</button>"+
        del + "</a>";
}

function s(id,school) {
    window.top.location.href = "/syedu/system/courseDetail/index.jsp?id="+id+"&school="+encodeURI(school)+"&uid="+window.localStorage.getItem("uid");
}

function student() {
    window.top.location.href = "/syedu/courseQRCode.do";
}

function group() {
    window.top.location.href = "/syedu/QRCode.do";
}

function loadCourse() {
    $.ajax({
        type : "POST",
        url :  "/syedu/course/loadAll.do", //校验验证码
        dataType : "json",
        async : false, //同步
        data : {
        },
        success : function(data) {
            if (undefined !=data.total && undefined != data.uid && null != data.uid && data.total > 0) {
                this.uid = data.uid;
                for (var k = 0; k < data.total; k++) {
                    addCourse(data.rows[k].cid,data.rows[k].school,data.rows[k].image,data.rows[k].title);
                }
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

function loadAchieve(url) {
    $.ajax({
        type : "POST",
        url :  url, //校验验证码
        dataType : "json",
        async : false, //同步
        data : {
            "uid" : window.localStorage.getItem("uid")
        },
        success : function(data) {
            if (undefined !=data.currentNo && undefined != data.stuNum) {
                var cn = data.currentNo.split(' ');
                var currentNo = '';
                for(var c in cn){
                    currentNo = currentNo + '<br>'+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+cn[c];
                }
                document.getElementById("currentNo").innerHTML = currentNo;
                document.getElementById("uStudentNo").innerHTML = data.stuNum;
                document.getElementById("uPercentage").innerHTML = data.percentage;
            } else{
                window.top.location.href = "/syedu/system/error/index.jsp";
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}