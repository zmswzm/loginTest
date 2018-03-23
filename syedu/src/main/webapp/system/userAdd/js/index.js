function userAdd(){
    var name = document.getElementById("name").value.trim();
    var phone = document.getElementById("phone").value.trim();
    var sex = $('#sexdiv input:radio:checked').val().trim();
    var userType = document.getElementById("userType").value.trim();
    var bankCardNo = document.getElementById("bankCardNo").value.trim();
    var bankName = document.getElementById("bankName").value.trim();
    var f = $('.form-control');
    for(var i=0;i<f.length;i++){
        if(f[i].value == ""){
            alert("请检查表单是否填写完整");
            return;
        }
    }
    $.ajax({
        type : "POST",
        url :  "/syedu/user/save.do",
        dataType : "json",
        async : false, //同步
        data : {
            "name" : name,
            "phone" : phone,
            "sex" : sex,
            "userType" : userType,
            "bankName":bankName,
            "bankCardNo":bankCardNo
        },
        success : function(data) {
            if (undefined !=data.message ) {
                window.location.href = "/syedu/system/regright/index.jsp?message="+data.message;
            }
        },
        error : function(data) {
            window.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

$(document).ready(function($) {

    window.alert = function alertw(name) {
        var iframe = document.createElement("IFRAME");
        iframe.style.display = "none";
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(name);
        iframe.parentNode.removeChild(iframe);
    };

    var search = window.location.search ;
    window.localStorage.setItem("userAdds",search);
    var tmparray = search.substr(1,search.length).split("&");
    document.getElementById("rName").value = tmparray[0].split("=")[1];
    document.getElementById("rId").value = tmparray[1].split("=")[1];
    var userTypeTemp = ("1"==tmparray[2].split("=")[1])? "2":"3";
    document.getElementById("userType").value = userTypeTemp;
    $("#userType").attr("disabled","disabled");
});