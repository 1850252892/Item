<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <script type="text/javascript">
        $(function () {
            $.ajax({
                url: "/unread",
                type: "get",
                dataType: "text",
                success: function (data) {
                    $("#msg").append("消息：" + data);
                },
                error: function () {

                }

            });
        });
    </script>
    <style type="text/css">
        .head-top {
            width: 100%;
            height: 35px;
            line-height: 35px;

            background-color: rgb(242, 242, 242);
            padding-left: 70px;
            font-weight: 300;
            font-size: 15px;
            top: 0px;
            left: 0px;
            position: absolute;
        }

        .head-top a {
            text-decoration: none;
            color: black;
            float: left;
            line-height: 35px;
            margin-right: 10px;
        }

        .head-top #uName {
            float: right;
            margin-left: 10px;
        }

    </style>
</head>
<body>
<div class="head-top">
    <a href="/mainPage"
       style="font-weight: 500; font-size: 20px; font-style: italic;"><span
            class="icon-home"></span>HLB</a>


        <div id="uName">
            <c:if test="${empty sessionScope.user.nickname}" var="userExits">
                <a href="/loginPage">请登录</a>
            </c:if>
            <c:if test="${!empty sessionScope.user.nickname}" var="userExits">

                <a href="/admin/userMainPage">${sessionScope.user.nickname}</a>
                <a href="/admin/myCollectPage"><span
                        class=""></span>我的收藏</a>
                <a href="/admin/message" id="msg"><span
                        class=""></span></a>
                <a href="/logout"><span class=""> </span>注销</a>
            </c:if>
        </div>

</div>
</body>

</html>