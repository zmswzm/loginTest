$("#form").submit(function(){
    var schoolName = document.getElementById("schoolName").value.trim();
    var schoolNo = document.getElementById("schoolNo").value.trim();
    $.ajax({
        type : "POST",
        url :  "/syedu/school/save.do",
        dataType : "json",
        async : false, //同步
        data : {
            "schoolName" : schoolName,
            "schoolNo" : schoolNo
        },
        success : function(data) {
            if (undefined !=data.message) {
                alert(data.message);
            } else {
                window.top.location.href = "/syedu/system/error/index.jsp";
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