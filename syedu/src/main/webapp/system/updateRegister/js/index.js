var major = '';
function regsub(){
    var f = $('.form-control');
    for(var i=0;i<f.length;i++){
        if(f[i].value == ""){
            alert("请检查表单是否填写完整");
            return;
        }
    }
    $.ajax({
        type : "POST",
        url :  "/syedu/register/update.do",
        dataType : "json",
        async : false, //同步
        data : $( '#form').serialize(),
        success : function(data) {
            if (undefined !=data.message) {
                alert(data.message);
            } else {
                alert("错误!");
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

function loadStu(sid,dis) {
    $.ajax({
        type : "POST",
        url :  "/syedu/register/loadByParams.do",
        dataType : "json",
        async : false, //同步
        data : {
            id : sid
        },
        success : function(data) {
            if(undefined != data && undefined != data.rows){
                setForm(data.rows[0],dis);
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

function setForm(obj,dis) {
    major = obj['major'];
    for(var attr in obj) {

        if (typeof(obj[attr]) == 'function') {
            continue;
        }
        var $input = $("#form :input[name='" + attr + "']");
        var type = $input.attr("type");
        if (type == "checkbox" || type == "radio") {

            var avalues = obj[attr].split(",");

            for (var v = 0; v < avalues.length; v++) {
                $input.each(function (i, n) {
                    var value = $(n).val();
                    if (value == avalues[v]) {
                        $(n).attr("checked", "checked");
                    }
                });
            }
        } else {
            $input.val(obj[attr]);
        }

        if("d" == dis){
            $input.attr("disabled" ,"disabled");
        }
    }
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

function schoolChange(val){
    loadMajor(val,document.getElementById("level").value.trim());
}

function levelChange(val){
    loadMajor(document.getElementById("school").value.trim(),val);
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

    var search = window.location.search ;
    var tmparray = search.substr(1,search.length).split("&");

    load();
    loadStu(tmparray[1].split("=")[1],tmparray[0].split("=")[1]);
    loadMajor(document.getElementById("school").value,document.getElementById("level").value);
    document.getElementById("major").value = major;
    if("d" == tmparray[0].split("=")[1]){
        document.getElementById("submit").style.display = "none";
        document.getElementById("formTitle").innerHTML = "学生信息详情";
    }else{
        document.getElementById("submit").style.display = "block";
        document.getElementById("formTitle").innerHTML = "修改学生信息";
    }
});