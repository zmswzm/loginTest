$("#form").submit(function(){
    var oldPW = document.getElementById("oldPW").value.trim();
    var newPW = document.getElementById("newPW").value.trim();
    var confNewPW = document.getElementById("confirmNewPW").value.trim();

    if(newPW != confNewPW){
        alert("新密码两次输入不一致，请重输!");
        return;
    }

    $.ajax({
        type : "POST",
        url :  "/syedu/user/updatePW.do",
        dataType : "json",
        async : false, //同步
        data : {
            "password" : md5(oldPW),
            "newPassword" : md5(newPW)
        },
        success : function(data) {
            if (undefined !=data.message) {
                alert(data.message);
            } else {
                alert("系统错误，请联系管理员!");
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
});

$(document).ready(function () {
    window.alert = function alertw(name) {
        var iframe = document.createElement("IFRAME");
        iframe.style.display = "none";
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(name);
        iframe.parentNode.removeChild(iframe);
    };

});