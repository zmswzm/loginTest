var school;
var schoolName;
var uid;
$(document).ready(function() {
    var search = window.location.search ;
    var tmparray = search.substr(1,search.length).split("&");
    var cid = tmparray[0].split("=")[1];
    var sch = tmparray[1].split("=")[1].split(";")
    school = sch[0];
    document.title = decodeURI(sch[1]);
    uid = tmparray[2].split("=")[1];
    $.ajax({
        type : "POST",
        url :  "/syedu/course/loadDetail.do", //校验验证码
        dataType : "json",
        async : false, //同步
        data : {
            "cid" :  cid
        },
        success : function(data) {
            if (undefined !=data.detail && null != data.detail) {
                document.getElementById("course-detail").innerHTML = data.detail;
            }
        },
        error : function(e) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });

});

function register() {
    location.href = "/syedu/system/register/index.jsp?school="+school+"&uid="+uid;
}
