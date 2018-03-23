$(document).ready(function($) {

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
    if(undefined != tmparray[0].split("=")[1] && '' !=tmparray[0].split("=")[1]){
        var m = tmparray[0].split("=")[1];
        if('success' == m){
            var message = '注册成功，用户名和密码默认为手机号';
        }else if('have' == m){
            var message = '手机号已存在，请更换其他手机号';
            document.getElementById("operate").href = "/syedu/userAdd.do"+window.localStorage.getItem("userAdds");;
            document.getElementById("operate").innerHTML = "重新注册";
        }
        document.getElementById("message").innerHTML = message;
    }
});