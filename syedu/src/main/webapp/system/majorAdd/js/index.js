$("#form").submit(function(){
    var schoolName = document.getElementById("schoolName").value.trim();
    var major = document.getElementById("major").value.trim();
    var level = document.getElementById("level").value.trim();
    $.ajax({
        type : "POST",
        url :  "/syedu/major/save.do",
        dataType : "json",
        async : false, //同步
        data : {
            "schoolName" : schoolName,
            "major" : major,
            "level" : level
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

function load(){
    $.ajax({
        type: "post",
        url: "/syedu/school/load.do",
        async : false, //同步
        dataType: 'JSON',
        success: function (data, status) {
            if (status == "success") {
                var s = document.getElementById("schoolName");
                for(var sh =0 ;sh < data.rows.length ;sh ++){
                    s.add(new Option(data.rows[sh].schoolName,data.rows[sh].id))
                }
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

$(document).ready(function () {
    window.alert = function alertw(name) {
        var iframe = document.createElement("IFRAME");
        iframe.style.display = "none";
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(name);
        iframe.parentNode.removeChild(iframe);
    };

    load();

});