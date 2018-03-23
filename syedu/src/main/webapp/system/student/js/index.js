function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}

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
        language: 'zh',
        method: "post", //使用get请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded",
        url: "/syedu/register/loadByParams.do", //获取数据的Servlet地址
        cache: false,
        showExport: true,//显示导出按钮
        exportTypes:['excel'],  //导出文件类型
        exportDataType: "all",//导出类型
        exportOptions:{
            fileName: '学生信息',  //文件名称设置
            worksheetName: 'sheet1',  //表格工作区名称
            tableName: '学生信息'
        },
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
                align:'center',
                visible:IsPC()
            },
            {
                field:'phone',
                align:'center',
                visible:IsPC()
            },
            {
                field:'schoolName',
                align:'center'
            },
            {
                field:'major',
                align:'center',
                visible:IsPC()
            },
            {
                field:'level',
                align:'center',
                visible:IsPC()
            },
            {
                field:'yearNo',
                align:'center'
            },
            {
                field:'createTime',
                align:'center',
                visible:IsPC()
            },
            {
                field:'uid',
                align:'center',
                visible:IsPC()
            },
            {
                field:'status1',
                align:'center',
                editable: {
                    type: 'text',
                    title: "第一学年",
                    disabled: !(window.localStorage.getItem("utype") && ("5" == window.localStorage.getItem("utype")||"1" == window.localStorage.getItem("utype")))
                },
                visible:IsPC()
            },
            {
                field:'status1Time',
                align:'center',
                visible:IsPC()
            },
            {
                field:'status2',
                align:'center',
                editable: {
                    type: 'text',
                    title: "第二学年",
                    disabled:!(window.localStorage.getItem("utype") && ("5" == window.localStorage.getItem("utype")||"1" == window.localStorage.getItem("utype")))
                },
                visible:IsPC()
            },
            {
                field:'status2Time',
                align:'center',
                visible:IsPC()
            },
            {
                field:'serverFee',
                align:'center',
                editable: {
                    type: 'text',
                    title: "服务费",
                    disabled:!(window.localStorage.getItem("utype") && ("5" == window.localStorage.getItem("utype")||"1" == window.localStorage.getItem("utype")))
                },
                visible:IsPC()
            },
            {
                field:'serverFeeTime',
                align:'center',
                visible:IsPC()
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
                    status1Time : (null==row.status1Time  || '-'==row.status1Time)? new Date().Format("yyyy-MM-dd hh:mm:ss") : row.status1Time,
                    status2 : row.status2,
                    status2Time : (null==row.status2Time  || '-'==row.status2Time)? new Date().Format("yyyy-MM-dd hh:mm:ss") : row.status2Time,
                    serverFee : row.serverFee,
                    serverFeeTime : (null==row.serverFeeTime  || '-'==row.serverFeeTime)? new Date().Format("yyyy-MM-dd hh:mm:ss") : row.serverFeeTime
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
    var r = '<a href="/syedu/system/updateRegister/index.jsp?op=d&id='+row.id+'" '+ 'class="btn btn-info btn-sm">详情</a>' +
        '<a href="/syedu/system/updateRegister/index.jsp?op=u&id='+row.id+'" '+ 'class="btn btn-warning btn-sm">修改</a>' +
        '<a href="/syedu/system/attach/index.jsp?cid='+row.id+'" '+ 'class="btn btn-primary btn-sm">附件</a>';
    if("1" == window.localStorage.getItem("utype")){
        r = r + '<button  type="button" class="btn btn-danger btn-sm" onclick="delete2('+row.id+')">删除</button>';
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

    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
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