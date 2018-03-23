function cfind(){

    //调用函数，初始化表格
    var cname = document.getElementById("cname").value.trim();
    var cphone = document.getElementById("cphone").value.trim();
    var cyearNo = document.getElementById("cyearNo").value.trim();
    //调用函数，初始化表格
    initTable(cname,cphone,cyearNo);
}

function creset(){
    document.getElementById("cname").value = "";
    document.getElementById("cphone").value = "";
    document.getElementById("cyearNo").value = "";
}

function balanceManage(){
    window.location.href = "/syedu/balanceManage.do";
}

function initTable(cname,cphone,cyearNo) {
    //先销毁表格
    $('#myUserTab').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#myUserTab").bootstrapTable({
        method: "post", //使用get请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded",
        url: "/syedu/register/loadBalance.do", //获取数据的Servlet地址
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
                name: cname,
                phone: cphone,
                yearNo: cyearNo
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
                align:'center',
                visible:false
            },
            {
                field:'idNumCard',
                align:'center',
                visible:false
            },
            {
                field:'yearNo',
                align:'center'
            },
            {
                field:'fee',
                align:'center'
            },
            {
                field:'status',
                align:'center',
                editable: {
                    type: 'select',
                    title: "结算",
                    source: [
                        {value: 1, text: '未结算'},
                        {value: 2, text: '已结算'}
                    ]
                },
                cellStyle:function(value,row,index){
                    if (value==1){
                        return {css:{"background-color":"#FFFF00"}}
                    }else{
                        return {css:{"background-color":"#ADFF2F"}}
                    }
                }
            }
        ],
        onEditableSave: function (field, row, oldValue, $el) {
            $.ajax({
                type: "post",
                url: "/syedu/register/updateBalance.do",
                data: {
                    uid : row.uid,
                    yearNo : row.yearNo,
                    status : row.status
                },
                dataType: 'JSON',
                success: function (data, status) {
                    if (status == "success") {
                        //调用函数，初始化表格
                        var cname = document.getElementById("cname").value.trim();
                        var cphone = document.getElementById("cphone").value.trim();
                        var cyearNo = document.getElementById("cyearNo").value.trim();
                        //调用函数，初始化表格
                        initTable(cname,cphone,cyearNo);
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
    var cname = document.getElementById("cname").value.trim();
    var cphone = document.getElementById("cphone").value.trim();
    var cyearNo = document.getElementById("cyearNo").value.trim();
    loadYearNo();
    //调用函数，初始化表格
    initTable(cname,cphone,cyearNo);

});