function regsub(){
    var f = $('.form-control');
    for(var i=0;i<f.length;i++){
        if(f[i].value == ""){
            alert("请检查表单是否填写完整");
            return;
        }
    }
    var subdata = $('#form').serialize() + "&school="+document.getElementById("school").value;
    $.ajax({
        type : "post",
        url :  "/syedu/register/submit.do",
        dataType: 'JSON',
        async : false, //同步
        data :  subdata,
        success : function(data,status) {
            if ("success" == status) {
                window.top.location.href = "/syedu/system/right/index.jsp";
            } else {
                window.top.location.href = "/syedu/system/error/index.jsp";
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

function load(){
    $.ajax({
        type: "post",
        url: "/syedu/school/load.do",
        async : false, //同步
        dataType: 'JSON',
        success: function (data, status) {
            if (status == "success") {
                var s = document.getElementById("school");
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

function loadMajor(school,level){
    $.ajax({
        type: "post",
        url: "/syedu/major/getMajor.do",
        async : false, //同步
        dataType: 'JSON',
        data :{
            "school":school,
            "level":level
        },
        success: function (data, status) {
            if (status == "success") {
                var s = document.getElementById("major");
                s.options.length=0;
                for(var sh =0 ;sh < data.rows.length ;sh ++){
                    s.add(new Option(data.rows[sh].major,data.rows[sh].major));
                }
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

function schoolChange(val){
    loadMajor(val,document.getElementById("level").value.trim());
}

function levelChange(val){
    loadMajor(document.getElementById("school").value.trim(),val);
}

$(document).ready(function () {
    var search = window.location.search ;
    var tmparray = search.substr(1,search.length).split("&");
    var school = tmparray[0].split("=")[1];
    if(undefined != tmparray[1] && undefined != tmparray[1].split("=")[1]){
        var uid = tmparray[1].split("=")[1];
        document.getElementById("uid").value = uid;
    }
    window.alert = function alertw(name) {
        var iframe = document.createElement("IFRAME");
        iframe.style.display = "none";
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(name);
        iframe.parentNode.removeChild(iframe);
    };

    load();
    loadMajor(school,document.getElementById("level").value.trim());

    document.getElementById("school").value = school;
});