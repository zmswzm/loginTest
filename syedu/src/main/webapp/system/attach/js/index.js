var image = [];
var imageKey = [];
var attachCid = "";
function update(cid){
    attachCid = cid;
    $.ajax({
        type: "post",
        url: "/syedu/register/loadAttach.do",
        async : false, //同步
        dataType: 'JSON',
        data : {
            "cid" :  cid
        },
        //{caption: image, width: "100px", url: "/syedu/course/deleteFile.do", key: image},
        success: function (data, status) {
            if (status == "success" && undefined != data.att) {
                for(var i = 0;i<data.att.length;i++){
                    image[i] = "/syedu/image/" + data.att[i];
                    var cap = {caption:'', width: "100px", url: "/syedu/register/deleteAtt.do", key:''};
                    cap.caption = data.att[i];
                    cap.key = data.att[i];
                    cap.url = cap.url + "?cid="+cid;
                    imageKey[i] = cap;
                }
            }
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

    var search = window.location.search ;
    var tmparray = search.substr(1,search.length).split("&");
    if(null != tmparray && tmparray[0].indexOf('cid') > -1){
        var cid = tmparray[0].split("=")[1];
        update(cid);
    }

    $('#inputfile').fileinput({
        theme: 'fa',
        language: 'zh',
        uploadUrl: "/syedu/register/attach.do",
        uploadAsync: false,
        minFileCount: 1,
        maxFileCount: 5,
        overwriteInitial: false,
        initialPreview: image,
        initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
        initialPreviewFileType: 'image', // image is the default and can be overridden in config below
        initialPreviewConfig: imageKey,
        msgUploadEnd : '完成',
        uploadExtraData: {
            cid: attachCid
        }
    }).on('filesorted', function(e, params) {
        console.log('file sorted', e, params);
    }).on('fileuploaded', function(e, params) {
        console.log('file uploaded', e, params);
    });

});