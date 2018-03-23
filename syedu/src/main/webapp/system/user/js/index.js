function cfind(){

    var cname = document.getElementById("cname").value.trim();
    var cphone = document.getElementById("cphone").value.trim();
    var creferee ="";
    var cmajor ="";
    if(null != window.localStorage.getItem("utype") && "1" != window.localStorage.getItem("utype") ){
        creferee = window.localStorage.getItem("uphone");
    }else{
        creferee = document.getElementById("creferee").value.trim();
        cmajor = document.getElementById("cmajor").value.trim();
    }
    initTable(cname,cphone,creferee,cmajor);
}

function creset(){
    document.getElementById("cname").value = "";
    document.getElementById("cphone").value = "";
    document.getElementById("creferee").value = "";
    document.getElementById("cmajor").value = "6";
}

function initTable(cname,cphone,creferee,cmajor) {
    //先销毁表格
    $('#myUserTab').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#myUserTab").bootstrapTable({
        method: "post", //使用get请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded",
        url: "/syedu/user/loadByPrama.do", //获取数据的Servlet地址
        striped: true, //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10, //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [10, 25, 50, 100], //记录数可选列表
        showColumns: true, //显示下拉框勾选要显示的列
        showRefresh: true, //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "limit",
        queryParams: function queryParams(params) { //设置查询参数
            var param = {
                offset: params.offset,
                limit: params.limit,
                cname: cname,
                cphone: cphone,
                creferee: creferee,
                cmajor: cmajor
            };
            return param;
        },
        columns:[
            {
                field:'uid',
                align:'center',
                visible:false

            },{
                field:'name',
                align:'center'
            },{
                field:'phone',
                align:'center'
            },
            {
                field:'idNumCard',
                align:'center',
                visible:false
            },
            {
                field:'sex',
                align:'center',
                visible:false
            },
            {
                field:'bankCardNo',
                align:'center',
                visible:false
            },
            {
                field:'bankName',
                align:'center',
                visible:false
            },
            {
                field:'userType',
                align:'center',
                editable: {
                    type: 'select',
                    title: "代理类别",
                    disabled: !(window.localStorage.getItem("utype") && "1" == window.localStorage.getItem("utype")),
                    source: [
                        {value: 1, text: '管理员'},
                        {value: 2, text: '一级代理/员工'},
                        {value: 3, text: '二级代理'},
                        {value: 4, text: '教务'},
                        {value: 5, text: '财务'}
                    ]
                }
            },
            {
                field:'referee',
                align:'center',
                visible:false
            },
            {
                field:'operate',
                align:'center',
                formatter:detailFormatter
            }
        ],
        onEditableSave: function (field, row, oldValue, $el) {
            $.ajax({
                type: "post",
                url: "/syedu/user/update.do",
                data: {
                    uid : row.uid,
                    userType : row.userType
                },
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
        },
        onLoadSuccess: function(){ //加载成功时执行
            // layer.msg("加载成功");
        },
        onLoadError: function(){ //加载失败时执行
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
};

function detailFormatter(value,row,index){
    var r =  '<a href="/syedu/system/percentage/index.jsp?uid='+row.uid+'" '+ 'class="btn btn-warning btn-sm">提成设置</a>';
    if("1" == window.localStorage.getItem("utype")){
        r = r + '<button  type="button" class="btn btn-danger btn-sm" onclick="delete2('+row.uid+')">删&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除</button>';
    }
    return r;
}

function delete2(id){
    var r=confirm("确认删除？")
    if (r != true)
    {
        return;
    }

    $.ajax({
        type: "post",
        url: "/syedu/user/delete.do",
        async : false, //同步
        data : {
            "uid" : id
        },
        dataType: 'JSON',
        success: function (data, status) {

            if (status == "success") {

                var cname = document.getElementById("cname").value.trim();
                var cphone = document.getElementById("cphone").value.trim();
                var creferee ="";
                var cmajor ="";
                if(null != window.localStorage.getItem("utype") && "1" != window.localStorage.getItem("utype") ){
                    creferee = window.localStorage.getItem("uphone");
                }else{
                    creferee = document.getElementById("creferee").value.trim();
                    cmajor = document.getElementById("cmajor").value.trim();
                }
                initTable(cname,cphone,creferee,cmajor);
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
    //调用函数，初始化表格
    var utype = window.localStorage.getItem("utype");
    var cmajor = document.getElementById("cmajor").value.trim();
    var uphone = "";
    if(null != utype && "1" != utype ){
        uphone = window.localStorage.getItem("uphone");
    }
    //调用函数，初始化表格
    initTable("","",uphone,cmajor);

    if(null != utype && ("1" != utype || "4" != utype || "5" != utype)){
        document.getElementById("labcreferee").style.display = "none";
        document.getElementById("creferee").style.display = "none";
        document.getElementById("labcmajor").style.display = "none";
        document.getElementById("cmajor").style.display = "none";
    }

});