var image = "";
function doUpload() {
    var formData = new FormData($( "#courseAdd" )[0]);
    formData.delete("files");
    formData.append("detail",$('.summernote').summernote('code'));
    $.ajax({
        url: '/syedu/course/save.do' ,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            alert(returndata.message);
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

function update(cid){
    document.getElementById("cid").value = cid;
    $.ajax({
        type: "post",
        url: "/syedu/course/loadCourseInfo.do",
        async : false, //同步
        dataType: 'JSON',
        data : {
            "cid" :  cid
        },
        success: function (data, status) {
            if (status == "success" && undefined != data.title) {
                document.getElementById("school").value = data.school;
                document.getElementById("name").value = data.title;
                $('.summernote').summernote('code', data.detail);
                document.getElementById("courseAddTitle").innerHTML = "修改课程";
                image = data.image;
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
                    if('currentNo' != data.rows[sh].schoolName) {
                        s.add(new Option(data.rows[sh].schoolName, data.rows[sh].id))
                    }
                }
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

function sendFile($summernote,files) {
    // var imagePrefix =
        data = new FormData();
        data.append("file", files[0]);
        url = "/syedu/course/saveDetailImage.do";
        $.ajax({
            data: data,
            type: "POST",
            url: url,
            cache: false,
            processData : false,
            contentType : false,
            success : function(data) {
                $summernote.summernote('insertImage',"/syedu/image/" + data.fileName);
            },
            error : function(data) {
                window.top.location.href = "/syedu/system/error/index.jsp";
            }
        });
}

jQuery(document).ready(function($) {
    window.alert = function alertw(name) {
        var iframe = document.createElement("IFRAME");
        iframe.style.display = "none";
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(name);
        iframe.parentNode.removeChild(iframe);
    };

    load();

    var $summernote = $('.summernote').summernote({
        height: 500,
        toolbar: [
            ['style', ['style']],
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['fontsize', ['fontsize']],
            ['fontname',['fontname']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['table',['table']],
            ['insert', ['picture','link']] ,
            ['help',['help']]
        ],
        callbacks: {
            onImageUpload: function (files) {
                sendFile($summernote, files);
            }
        },
        bFullscreen:false,

    });

    var search = window.location.search ;
    var tmparray = search.substr(1,search.length).split("&");
    var cid = '';
    if(null != tmparray && tmparray[0].indexOf('cid') > -1){
        cid = tmparray[0].split("=")[1];
        update(cid);
        if("" != image){
            image = "/syedu/image/" + image;
        }
    }

    $('#inputfile').fileinput({
        theme: 'fa',
        uploadUrl: "/syedu/image",
        uploadAsync: false,
        minFileCount: 1,
        maxFileCount: 1,
        overwriteInitial: false,
        initialPreview: [
            image
        ],
        initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
        initialPreviewFileType: 'image', // image is the default and can be overridden in config below
        initialPreviewConfig: [
            {caption: image, size: 273237, width: "100px", url: "/syedu/course/deleteFile.do?cid="+cid, key: image},
        ],
        uploadExtraData: {
            // img_key: "1000",
            // img_keywords: "happy, places",
        }
    }).on('filesorted', function(e, params) {
        console.log('file sorted', e, params);
    }).on('fileuploaded', function(e, params) {
        console.log('file uploaded', e, params);
    });

});

$("#courseAdd").submit(function(){
    doUpload();
});

function loadMajor(school){
    $.ajax({
        type: "post",
        url: "/syedu/course/loadMajor.do",
        async : false, //同步
        dataType: 'JSON',
        data :{
            "school":school
        },
        success: function (data, status) {
            if (status == "success") {
                var s = document.getElementById("major");
                s.options.length=0;
                for(var sh =0 ;sh < data.rows.length ;sh ++){
                    s.add(new Option(data.rows[sh].name,data.rows[sh].id));
                }
            }
        },
        error : function(data) {
            window.top.location.href = "/syedu/system/error/index.jsp";
        }
    });
}

function cSchoolChange(val){
    loadMajor(val);
}