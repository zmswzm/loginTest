function login(){
    var phone = document.getElementById("username").value.trim();
    var password = document.getElementById("password").value.trim();

    $.ajax({
        type : "POST",
        url :  "/syedu/user/login.do", //校验验证码
        dataType : "json",
        async : false, //同步
        data : {
            "phone" : phone,
            "password" : md5(password)
        },
        success : function(data) {
            if (data.uid && data.uphone && data.utype) {
                window.localStorage.setItem("syeduNo",phone);
                window.localStorage.setItem("syeduPw",password);
                window.localStorage.setItem("uname",data.uname);
                window.localStorage.setItem("uphone",data.uphone);
                window.localStorage.setItem("uid",data.uid);
                window.localStorage.setItem("utype",data.utype);
                window.localStorage.setItem("isLogin",true);
                window.location.href ="/syedu/main.do";
            } else {
                alert("用户名或密码错误!");
            }
        },
        error : function() {
            alert("系统错误，请联系管理员!");
            return false;
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
    var No = window.localStorage.getItem("syeduNo");
    var Pw = window.localStorage.getItem("syeduPw");
    if(undefined != No && undefined != Pw && null != No && null != Pw){
        document.getElementById("username").value = No;
        document.getElementById("password").value = Pw;
        login(No,Pw)
    }
});