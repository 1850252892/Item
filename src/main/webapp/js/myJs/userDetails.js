$(function () {
    getUserDetails();
});

function getUserDetails() {
    var username=getUrlParam("uid");
    $.ajax({
        url:"/user/details",
        type:"get",
        dataType:"text",
        data:{
            "username":username
        },
        success:function (data) {
            var result=JSON.parse(data);
            var user=result.user;
            var items=result.list;
            $("#nickname").append("<b>"+user.nickname+"</b>");
            $("#address").append("<b>"+user.address+"</b>");
            $("#email").append("<b>"+user.mail+"</b>");
            var len=items.length;
            for(var i=0;i<len;i++){
                $("#item-data").append("<a style='display: block;float: left;text-decoration: none;color: #9e9e9e;padding: 10px;' href='/item?itemid="+items[i].id+"'><img height='100px' src='"+items[i].imgpath[0]+"' alt=\"\">\n" +
                    "<div class=\"item-name\"><span>商品名</span>\n" +
                    "<p>"+items[i].name+"</p></div>\n" +
                    "<div class=\"item-mon\"><span>简介</span>\n" +
                    "<p>"+items[i].detail+"</p></div>\n" +
                    "</a>");
            }
        }
    })
}
function getUrlParam(key) {
    // 获取参数
    var url = window.location.search;
    // 正则筛选地址栏
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    // 匹配目标参数
    var result = url.substr(1).match(reg);
    //返回参数值
    return result ? decodeURIComponent(result[2]) : null;
}