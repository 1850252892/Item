<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>用户主页</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/otherUser.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/font-awesome.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.1/jquery.js"></script>
    <script type="text/javascript" src="js/jpage.js"></script>
    <script type="text/javascript" src="js/page.js"></script>
    <script type="text/javascript" src="/js/myJs/userDetails.js"></script>
</head>
<body>
<jsp:include page="../head-top.jsp"></jsp:include>
<div class="left-menu left">
    <ul>
        <li class="active">基本信息</li>
        <li>商品列表</li>
    </ul>
</div>
<div class="right-data left">
    <div>
        <!-- 添加头像 -->
        <div class="user-name left" id="nickname"><span>用户:</span></div>
        <!--昵称-->
        <table class="user-infor clear back">
            <!--信息-->
            <caption>主要信息</caption>
            <tr>
                <td id="address"><span>所在地:</span></td>
            </tr>
            <tr>
                <td id="email"><span>邮箱:</span></td>
            </tr>
        </table>
    </div>
    <div>
        <div id="item-data">

        </div>
        <div id="test"></div>

    </div>

</div>
</body>
<script>
    $(document).ready(function () {
        $(".left-menu>ul>li").click(function () {
            $(this).parent().find("li").removeClass("active");
            $(this).addClass("active");
            var index = $(".left-menu>ul>li").index(this);
            $(".right-data").children("div").css("display", "none").eq(index).css("display", "block");
        });
        $(".right-data>div table tr:odd").addClass("backodd");
        $(".right-data>div table tr:even").addClass("backeven");
        $(".right-data>div table tr").mouseover(function () {
            $(this).addClass("backmouse");
        }).mouseout(function () {
            $(this).removeClass("backmouse");
        })

    })
</script>

</html>