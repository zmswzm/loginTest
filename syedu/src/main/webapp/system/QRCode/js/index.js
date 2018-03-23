function qrcode(text){
    $('.qrcode').empty();
    jQuery('.qrcode').qrcode({
        text: text,
        width: 120,
        height: 120,
        background: "#ffffff",  //背景颜色
        foreground: "#000000",  //前景颜色
    });

    var canvas = $('.qrcode canvas')
    var img = canvas[0].toDataURL("image/png")
    $('.qrcode').html("<img src='" + img + "'>")
}

$(document).ready(function() {

    qrcode("http://siyujiaoyu.com/syedu/userAdd.do?uphone="+ window.localStorage.getItem("uphone")+"&uid="+ window.localStorage.getItem("uid")+"&utype="+ window.localStorage.getItem("utype"));
});
