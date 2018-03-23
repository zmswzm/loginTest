function initTable(uid) {
    //先销毁表格
    $('#percentageTab').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#percentageTab").bootstrapTable({
        method: "post", //使用get请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded",
        url: "/syedu/user/loadByPer.do", //获取数据的Servlet地址
        striped: true, //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10, //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [10, 30, 50, 100], //记录数可选列表
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
                uid: uid
            };
            return param;
        },
        columns:[
            {
                field:'uid',
                align:'center',
                visible:false

            },{
                field:'cid',
                align:'center',
                visible:false

            },{
                field:'uname',
                align:'center'
            },{
                field:'uphone',
                align:'center'
            },
            {
                field:'cname',
                align:'center'
            },
            {
                field:'schoolName',
                align:'center'
            },
            {
                field:'per',
                align:'center',
                editable: {
                    type: 'text',
                    title: "提成比例",
                    validate: function (value) { //字段验证
                        if (!$.trim(value)) {
                            return '不能为空';
                        }
                    }
                }
            }
        ],
        onEditableSave: function (field, row, oldValue, $el) {
            $.ajax({
                type: "post",
                url: "/syedu/user/updatePer.do",
                data: {
                    uid : row.uid,
                    cid : row.cid,
                    per : row.per
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
    var utype = window.localStorage.getItem("utype");
    var uid = "";
    if(null != utype){
        uid = tmparray[0].split("=")[1];
    }
    //调用函数，初始化表格
    initTable(uid);

});