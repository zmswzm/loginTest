function cfind(){
    var uphone = "";
    var cname = document.getElementById("cname").value.trim();
    if(null != window.localStorage.getItem("utype") && "1" != window.localStorage.getItem("utype") ){
        uphone = window.localStorage.getItem("uphone");
    }else{
        uphone = document.getElementById("cuphone").value.trim();
    }
    var cstatus = document.getElementById("cstatus").value.trim();
    var cyearNo = document.getElementById("yearNo").value.trim();
    initTable(cname,uphone,cstatus,cyearNo);
}

function creset(){
    document.getElementById("cname").value = "";
    document.getElementById("cuphone").value = "";
    document.getElementById("yearNo").value = "";
    document.getElementById("cstatus").value = "0";
}

function initTable(cname,cuphone,cstatus,cyearNo) {
    //先销毁表格
    $('#myStudentTab').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#myStudentTab").bootstrapTable({
        method: "post", //使用get请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded",
        url: "/syedu/register/loadByParams.do", //获取数据的Servlet地址
        cache: false,
        showExport: true,//显示导出按钮
        exportDataType: "basic",//导出类型
        striped: true, //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10, //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [5, 10, 15, 20, 25], //记录数可选列表
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
                cyearNo: cyearNo,
                cuphone: cuphone,
                cstatus: cstatus
            };
            return param;
        },
        columns:[
            {
                field:'id',
                align:'center',
                visible:false

            },{
                field:'name',
                align:'center'
            },{
                field:'idNumCard',
                align:'center'
            },
            {
                field:'phone',
                align:'center'
            },
            {
                field:'schoolName',
                align:'center'
            },
            {
                field:'major',
                align:'center'
            },
            {
                field:'level',
                align:'center'
            },
            {
                field:'yearNo',
                align:'center'
            },
            {
                field:'uid',
                align:'center'
            },
            {
                field:'status1',
                align:'center',
                editable: {
                    type: 'select',
                    title: "缴费状态",
                    disabled: !(window.localStorage.getItem("utype") && ("5" == window.localStorage.getItem("utype")||"1" == window.localStorage.getItem("utype"))),
                    source: [
                        {value: 2, text: '已缴费'},
                        {value: 1, text: '未缴费'}
                    ]
                }
            },
            {
                field:'status2',
                align:'center',
                editable: {
                    type: 'select',
                    title: "缴费状态",
                    disabled:!(window.localStorage.getItem("utype") && ("5" == window.localStorage.getItem("utype")||"1" == window.localStorage.getItem("utype"))),
                    source: [
                        {value: 2, text: '已缴费'},
                        {value: 1, text: '未缴费'}
                    ]
                }
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
                url: "/syedu/register/updateStatus.do",
                data: {
                    id : row.id,
                    status1 : row.status1,
                    status2 : row.status2
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
    return '<a href="/syedu/system/updateRegister/index.jsp?op=d&id='+row.id+'" '+ 'class="btn btn-info btn-sm">详情</a>' +
        '<a href="/syedu/system/updateRegister/index.jsp?op=u&id='+row.id+'" '+ 'class="btn btn-warning btn-sm">修改</a>'+
        '<button  type="button" class="btn btn-danger btn-sm" onclick="delete2('+row.id+','+row.status1+','+row.status2+')">删除</button>'
}

function delete2(id,status1,status2){
    if(!("1" == status1 && "1" == status2)){
        alert("当前学生不能删除!");
        return;
    }
    var r=confirm("确认删除？")
    if (r != true)
    {
        return;
    }

    $.ajax({
        type: "post",
        url: "/syedu/register/deleteById.do",
        async : false, //同步
        data : {
            "id" : id
        },
        dataType: 'JSON',
        success: function (data, status) {

            if (status == "success") {
                if(null != window.localStorage.getItem("utype") && "1" != window.localStorage.getItem("utype") ){
                    var uphone = window.localStorage.getItem("uphone");
                }else{
                    var uphone = document.getElementById("cuphone").value.trim();
                }
                var cname = document.getElementById("cname").value.trim();
                var cstatus = document.getElementById("cstatus").value.trim();
                var cyearNo = document.getElementById("yearNo").value.trim();
                initTable(cname,uphone,cstatus,cyearNo);
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
    var utype = window.localStorage.getItem("utype");
    var uphone = "";
    if(null != utype && "1" != utype ){
        uphone = window.localStorage.getItem("uphone");
    }
    //调用函数，初始化表格
    initTable("",uphone,"","");

    if(null != utype && "1" != utype ){
        document.getElementById("labcuphone").style.display = "none"
        document.getElementById("divcuphone").style.display = "none"
    }
});