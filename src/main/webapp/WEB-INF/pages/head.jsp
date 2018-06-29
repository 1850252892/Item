<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link type="text/css" rel="stylesheet" href="/css/head.css">
    <%--<script type="text/javascript" src="/js/jquery-3.1.1.js"></script>--%>
    <script type="text/javascript" src="/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="/js/PCASClass.js"></script>
    <script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
    <style type="text/css">
        select{
            background: rgb(242,242,242);
            float: left;
            border: 0px;
            width: auto;
        }
    </style>
</head>
<body>
<a name="top"></a>

    <div class="head-dock">
        <div id="address" style="float: left;z-index: 999">
            <select id="province" name="province" style="margin-left:50px;"></select>
            <select id="city" name="city" ></select>
            <select id="area" name="area"  ></select>
        </div>

            <div style="margin-right:10px;">
                <div id="uName" class="dropdown">
                    <c:if test="${empty sessionScope.user.nickname}" var="userExits">
                        <a href="/loginPage"><span class="icon-user"></span>请登录</a>
                    </c:if>
                    <c:if test="${!empty sessionScope.user.nickname}" var="userExits">

                        <a href="/admin/userMainPage"><span
                                class=""></span>${sessionScope.user.nickname}</a>

                        <a href="/admin/myCollectPage"><span
                                class=""></span>我的收藏</a>
                        <a href="/admin/message" id="msg"><span></span>消息：</a>
                        <a href="/logout"><span class=" ">
									</span>注销</a>

                    </c:if>
                </div>
            </div>

    </div>
<div class="head">
    <a href="/mainPage">
        <div class="logo">
           <H1>hlB</H1>
            <h4>换了呗</h4>
        </div>
    </a>
    <div class="">
        <div class="col-lg-6"
             style="margin-top: 30px; color: rgb(255, 0, 45);">
            <div class="input-group"
                 style="border: 4px solid red; outline: none;">
                <input type="text" class="form-control" id="inputNumber"
                       placeholder="搜索 商品/类型/描述"
                       style="border-radius: 0px; border: 0; outline: none; background: rgba(245, 245, 245, 0.5); color:black;">
                <span class="input-group-btn"> <a href="javascript:selectItem()"
                                                  style="display:block;width: 100px;height: 35px;"><button
                        class="" type="button" onclick="submitNumber()"
                        style="background-color: red; color: white;width: 100px;font-size: 16px;height: 35px;border: 0px;">搜索</button></a>
					</span>
            </div>
            <!-- /input-group -->
        </div>
        <!-- /.col-lg-6 -->
    </div>
    <!-- /.row -->


</div>
<!-- /.head -->
</body>

<script type="text/javascript">
    var p=$.cookie("province");
    var c=$.cookie("city");
    var d=$.cookie("district");

    new PCAS("province","city","area",p,c,d);
    if (p =="" ||c==""|| d=="" ||p==null||c==null||d==null){
        getAddress(returnCitySN["cip"]);
    }else {
        $("#province").css("width",2*p.length-1+"em");
        $("#city").css("width",2*c.length-1+"em");
        $("#area").css("width",2*d.length-2+"em");
    }

    $(document).ready(function(){
        $("select").change(function () {
            var province=$("#province option:selected").text();
            var city=    $("#city option:selected").text();
            var area=    $("#area option:selected").text();
            $.cookie("province",province);
            $.cookie("city",city);
            $.cookie("district",area);

        })
    });



    function getAddress(data) {
        $.ajax({
            url:"http://apis.map.qq.com/ws/location/v1/ip?ip="+data+"&key=XVBBZ-TM4CX-ZF242-7FBOB-A75IO-ZQBXN&output=jsonp",
            type:"get",
            dataType:"jsonp",
            jsonp:'callback',
            success:function (data) {
                var add=data.result.ad_info;
                $.cookie("province",add.province);
                $.cookie("city",add.city);
                if (add.district!=null || add.district!=""){
                    $.cookie("district",add.district);
                }
                var p=$.cookie("province");
                var c=$.cookie("city");
                var d=$.cookie("district");
                $("#province").append(p+"-");
                $("#city").append(c+"-");
                $("#area").append(d);
                new PCAS("province","city","area",p,c,d);
                $("#province").css("width",2*p.length-1+"em");
                $("#city").css("width",2*c.length-1+"em");
                $("#area").css("width",2*d.length-2+"em");
            }
        })
    }





    $(function () {
        $.ajax({
            url: "/unread",
            type: "get",
            dataType: "text",
            success: function (data) {
                $("#msg").empty();
                $("#msg").append("<span></span>消息：" + data);
            },
            error: function () {

            }

        });
    });

    function selectItem() {
        var s = $("#inputNumber").val();
        window.location.href = "/selectPage?s=" + s;
    }
</script>
</html>