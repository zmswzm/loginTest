$('#site-nav--toggle').click(function(){
  // toggle a class on body to affect nav and workarea behaviour
  $('body').toggleClass('body__expanded');
});

$('#site-nav--list').click(function(){
    // toggle a class on body to affect nav and workarea behaviour site-nav--list
    $('body').removeClass('body__expanded');
});

$(window).resize(function() {
  $('body').removeClass('body__expanded');
});

function getMenuli(url,icon,name){

    return '<li><a href=\"'+url+'\" target=\"main\"><i class=\"'+icon+'\"></i>'+name+'</a></li>';
}

function logout(){

    return '<li><a href=\"javascript:void(0);\" onclick=\"out()\" target=\"main\"><i class=\"entypo-logout\"></i>退&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp出</a></li>';
}
function out(){
    $.ajax({
        type: "post",
        url: "/syedu/user/loginOut.do",
        async : false, //同步
        dataType: 'JSON',
        data :{
        },
        success: function (data, status) {
            if (status == "success") {
                window.localStorage.clear();
                location.reload();
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

$(document).ready(function() {
    window.alert = function alertw(name) {
        var iframe = document.createElement("IFRAME");
        iframe.style.display = "none";
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(name);
        iframe.parentNode.removeChild(iframe);
    };
    //1:管理员  2：一级代理/公司普通员工   3：二级代理   4：教务   5:财务
    var utype = window.localStorage.getItem("utype");
    if(null != utype && ("1" == utype || "2" == utype || "4" == utype || "5" == utype)){

        document.getElementById("site-nav--list").innerHTML = document.getElementById("site-nav--list").innerHTML +
            getMenuli('/syedu/system/user/index.jsp','entypo-users','代理信息');
    }
    if(null != utype && "1" == utype){

        document.getElementById("site-nav--list").innerHTML = document.getElementById("site-nav--list").innerHTML +
            getMenuli('/syedu/system/courseAdd/index.jsp','entypo-list-add','课程管理');

        document.getElementById("site-nav--list").innerHTML = document.getElementById("site-nav--list").innerHTML +
            getMenuli('/syedu/system/school/index.jsp','entypo-skype','学校管理');

        document.getElementById("site-nav--list").innerHTML = document.getElementById("site-nav--list").innerHTML +
            getMenuli('/syedu/system/major/index.jsp','entypo-share','专业管理');
    }
    if(null != utype && ("1" == utype || "5" == utype)){

        document.getElementById("site-nav--list").innerHTML = document.getElementById("site-nav--list").innerHTML +
            getMenuli('/syedu/system/balance/index.jsp','entypo-cc-nc-jp','提成结算');
    }
    document.getElementById("site-nav--list").innerHTML = document.getElementById("site-nav--list").innerHTML +
        getMenuli('/syedu/system/updatePW/index.jsp','entypo-tools','修改密码');
    document.getElementById("site-nav--list").innerHTML = document.getElementById("site-nav--list").innerHTML +
        logout();
});

