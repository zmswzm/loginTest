function loadYearNo() {

    $.ajax({
        type: "post",
        url: "/syedu/school/loadYearNo.do",
        async : false, //同步
        dataType: 'JSON',
        data :{
        },
        success: function (data, status) {
            if (status == "success") {
                var s = document.getElementById("cyearNo");
                for(var sh =0 ;sh < data.rows.length ;sh ++){
                    s.add(new Option(data.rows[sh].yearNo, data.rows[sh].yearNo));
                }
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

function loadCurrentNo() {

    $.ajax({
        type: "post",
        url: "/syedu/school/loadCurrentNo.do",
        async : false, //同步
        dataType: 'JSON',
        data :{
        },
        success: function (data, status) {
            if (status == "success") {
                document.getElementById("cyearNo").value = data.currentNo;
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

function cChange(){
    $.ajax({
        type: "post",
        url: "/syedu/school/updateYearNo.do",
        async : false, //同步
        dataType: 'JSON',
        data :{
            yearNo : document.getElementById("cyearNo").value.trim()
        },
        success: function (data, status) {
            if (status == "success") {
                alert("修改成功")
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

function cbalance(){
    $.ajax({
        type: "post",
        url: "/syedu/register/execBalance.do",
        data: {},
        dataType: 'JSON',
        success: function (data, status) {
            if (status == "success") {
                alert(data.message);
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        },
        complete: function () {

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

    loadYearNo();
    loadCurrentNo();
});