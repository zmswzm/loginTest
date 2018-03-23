function cfind(){

    var cSchoolName = document.getElementById("cSchoolName").value.trim();
    initTable(cSchoolName);
}

function add(){

    window.location.href = "/syedu/schoolAdd.do";
}

function initTable(cSchoolName) {
    //先销毁表格
    $('#mySchoolTab').bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#mySchoolTab").bootstrapTable({
        method: "post", //使用get请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded",
        url: "/syedu/school/loadByParams.do", //获取数据的Servlet地址
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
                schoolName: cSchoolName
            };
            return param;
        },
        columns:[
            {
                field:'id',
                align:'center'

            },{
                field:'schoolName',
                align:'center',
                editable: {
                    type: 'text',
                    title: '学校名称',
                    validate: function (v) {
                        if (!v) return '学校名称不能为空';

                    }
                },
                formatter:schoolNameFormatter
            },
            {
                field:'schoolNo',
                align:'center',
                editable: {
                    type: 'text',
                    title: '当前批次',
                    validate: function (v) {
                        if (!v) return '当前批次不能为空';

                    }
                }
            },
            {
                field:'detail',
                align:'center',
                formatter:operateFormatter
            }
        ],
        onEditableSave: function (field, row, oldValue, $el) {
            $.ajax({
                type: "post",
                url: "/syedu/school/update.do",
                data: row,
                dataType: 'JSON',
                success: function (data, status) {
                    if (status == "success") {
                        alert('修改数据成功');
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
        onLoadError: function(data){ //加载失败时执行
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
};

function schoolNameFormatter(value,row,index){
    if("currentNo" == value){
        return "结算批次";
    }
    return value;
}

function operateFormatter(value,row,index){
    if("currentNo" == row.schoolName){
        return "";
    }
    return ['<button  type="button" class="btn btn-primary btn-sm" onclick="delete2('+row.id+')">删除</button>'
    ].join('');
}

function delete2(id){
    var r=confirm("确认删除？")
    if (r==false)
    {
        return;
    }
    $.ajax({
        type: "post",
        url: "/syedu/school/delete.do",
        async : false, //同步
        data : {
            "id" : id
        },
        dataType: 'JSON',
        success: function (data, status) {
            if (status == "success") {
                $('#mySchoolTab').bootstrapTable('remove',{
                    field:'id',
                    values:[parseInt(id)]
                });
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
    window.confirm = function alertw(name) {
        var iframe = document.createElement("IFRAME");
        iframe.style.display = "none";
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(name);
        iframe.parentNode.removeChild(iframe);
    };
    //调用函数，初始化表格
    initTable("");
});