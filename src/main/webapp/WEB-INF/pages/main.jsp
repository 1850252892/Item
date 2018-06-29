<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>换了呗</title>
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="/css/font-awesome.css">
    <link type="text/css" rel="stylesheet" href="/css/main.css">
    <!-- Bootstrap 核心 JavaScript 文件 -->
    <script type="text/javascript" src="/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/myJs/main.js"></script>

</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div style="width: 1170px;margin: 0 auto;height: 500px;">
    <div class="dock">
        <ul>
            <%--<li><a></a>今日热门</li>--%>
            <%--<li><a></a>最新上架</li>--%>
            <%--<li><a></a>我的收藏</li>--%>
            <%--<li><a></a>流览足迹</li>--%>
        </ul>
    </div>

    <div class="itemClass">
        <div class="sort" id="sort">
            <%--<div class="cTitle">全部分类</div>--%>
            <%--<div class="geduan"></div>--%>
            <ul id="class_ul">
                <li class="sortlist"><a href="#">洗护用品</a></li>
                <li class="sortlist"><a href="#">学习用具</a></li>
                <li class="sortlist"><a href="#">生活用品</a></li>
                <li class="sortlist"><a href="#">电脑配件</a></li>
                <li class="sortlist"><a href="#">手机配件</a></li>
                <li class="sortlist"><a href="#">化妆品</a></li>
                <li class="sortlist"><a href="#">手机</a></li>
                <li class="sortlist"><a href="#">电脑</a></li>
                <li class="sortlist"><a href="#">服装</a></li>
                <li class="sortlist"><a href="#">鞋靴</a></li>
                <li class="sortlist"><a href="#">箱包</a></li>
                <li class="sortlist"><a href="#">书本</a></li>
                <li class="sortlist"><a href="#">零食</a></li>
                <li class="sortlist"><a href="#">电器</a></li>
                <li class="sortlist"><a href="#">装饰</a></li>
                <li class="sortlist"><a href="#">其他</a></li>
            </ul>
        </div>
    </div>
    <div class="container"
         style="height: 500px; width: 800px; margin:0;  z-index: -1;padding: 0px;float: left;">
        <div id="carousel-example-generic" class="carousel slide"
             data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0"
                    class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>
            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox"
                 style="height: 500px; width: 800px;" id="lunbo">
                <div class="item active"
                     style="height: 500px; width: 100%; margin: 0; padding: 0;">
                    <a href="/mainPage"><img
                            src="https://img1.360buyimg.com/da/jfs/t15286/315/552134992/77628/d8eb2d02/5a30c4e7N41291ce7.jpg"
                            alt="/home"
                            style="height: 500px; width: 100%;"></a>

                </div>
            </div>
        </div>
    </div>

    <div class="hot-right" >


    </div>


</div>


<div class="bottom">
    <div class="class-box">

        <span>今日热门</span>
        <div class="item-box" id="hot">
            <%--<c:forEach items="${newlist}" var="items" end="7">--%>
            <%--<div class="item">--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="item-img">--%>
            <%--<img alt="" src="${items.imgpath[0]}">--%>
            <%--</div>--%>
            <%--</a>--%>
            <%--<div class="span" style="color: #ff1b46;font-size: 18px;">￥${items.price}</div>--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="span">${items.name}</div>--%>
            <%--</a>--%>
            <%--<div class="span"><b class="icon-time"></b>${items.time}</div>--%>
            <%--</div>--%>
            <%--</c:forEach>--%>
        </div>
    </div>
    <div class="class-box">
        <span>最新上架</span>
        <div class="item-box" id="new">
            <%--<c:forEach items="${newlist}" var="items" end="7">--%>
            <%--<div class="item">--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="item-img">--%>
            <%--<img alt="" src="${items.imgpath[0]}">--%>
            <%--</div>--%>
            <%--</a>--%>
            <%--<div class="span" style="color: #ff1b46;font-size: 18px;">￥${items.price}</div>--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="span">${items.name}</div>--%>
            <%--</a>--%>
            <%--<div class="span"><b class="icon-time"></b>${items.time}</div>--%>
            <%--</div>--%>
            <%--</c:forEach>--%>
        </div>
    </div>
    <div class="class-box">
        <span>手机</span>
        <div class="item-box" id="phone">
            <%--<c:forEach items="${phonelist}" var="items" end="7">--%>
            <%--<div class="item">--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="item-img">--%>
            <%--<img alt="" src="${items.imgpath[0]}">--%>
            <%--</div>--%>
            <%--</a>--%>
            <%--<div class="span" style="color: #ff1b46;font-size: 18px;">￥${items.price}</div>--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="span">${items.name}</div>--%>
            <%--</a>--%>
            <%--<div class="span"><b class="icon-time"></b>${items.time}</div>--%>
            <%--</div>--%>
            <%--</c:forEach>--%>
        </div>
    </div>
    <div class="class-box">
        <span>电脑</span>
        <div class="item-box" id="computer">
            <%--<c:forEach items="${computerlist}" var="items" end="7">--%>
            <%--<div class="item">--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="item-img">--%>
            <%--<img alt="" src="${items.imgpath[0]}">--%>
            <%--</div>--%>
            <%--</a>--%>
            <%--<div class="span" style="color: #ff1b46;font-size: 18px;">￥${items.price}</div>--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="span">${items.name}</div>--%>
            <%--</a>--%>
            <%--<div class="span"><b class="icon-time"></b>${items.time}</div>--%>
            <%--</div>--%>
            <%--</c:forEach>--%>
        </div>
    </div>
    <div class="class-box">
        <span>书籍</span>
        <div class="item-box" id="book">
            <%--<c:forEach items="${booklist}" var="items" end="7">--%>
            <%--<div class="item">--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="item-img">--%>
            <%--<img alt="" src="${items.imgpath[0]}">--%>
            <%--</div>--%>
            <%--</a>--%>
            <%--<div class="span" style="color: #ff1b46;font-size: 18px;">￥${items.price}</div>--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="span">${items.name}</div>--%>
            <%--</a>--%>
            <%--<div class="span"><b class="icon-time"></b>${items.time}</div>--%>
            <%--</div>--%>
            <%--</c:forEach>--%>
        </div>
    </div>
    <div class="class-box">
        <span>服装</span>
        <div class="item-box" id="clothing">
            <%--<c:forEach items="${clothinglist}" var="items" end="7">--%>
            <%--<div class="item">--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="item-img">--%>
            <%--<img alt="" src="${items.imgpath[0]}">--%>
            <%--</div>--%>
            <%--</a>--%>
            <%--<div class="span" style="color: #ff1b46;font-size: 18px;">￥${items.price}</div>--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="span">${items.name}</div>--%>
            <%--</a>--%>
            <%--<div class="span"><b class="icon-time"></b>${items.time}</div>--%>
            <%--</div>--%>
            <%--</c:forEach>--%>
        </div>
    </div>
    <div class="class-box">
        <span>运动</span>
        <div class="item-box" id="sport">
            <%--<c:forEach items="${sportlist}" var="items" end="7">--%>
            <%--<div class="item">--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="item-img">--%>
            <%--<img alt="" src="${items.imgpath[0]}">--%>
            <%--</div>--%>
            <%--</a>--%>
            <%--<div class="span" style="color: #ff1b46;font-size: 18px;">￥${items.price}</div>--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="span">${items.name}</div>--%>
            <%--</a>--%>
            <%--<div class="span"><b class="icon-time"></b>${items.time}</div>--%>
            <%--</div>--%>
            <%--</c:forEach>--%>
        </div>
    </div>
    <div class="class-box">
        <span>游戏</span>
        <div class="item-box" id="game">
            <%--<c:forEach items="${gamelist}" var="items" end="7">--%>
            <%--<div class="item">--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="item-img">--%>
            <%--<img alt="" src="${items.imgpath[0]}">--%>
            <%--</div>--%>
            <%--</a>--%>
            <%--<div class="span" style="color: #ff1b46;font-size: 18px;">￥${items.price}</div>--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="span">${items.name}</div>--%>
            <%--</a>--%>
            <%--<div class="span"><b class="icon-time"></b>${items.time}</div>--%>
            <%--</div>--%>
            <%--</c:forEach>--%>
        </div>
    </div>
    <div class="class-box">
        <span>其它</span>
        <div class="item-box" id="other">
            <%--<c:forEach items="${originalitylist}" var="items" end="7">--%>
            <%--<div class="item">--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="item-img">--%>
            <%--<img alt="" src="${items.imgpath[0]}">--%>
            <%--</div>--%>
            <%--</a>--%>
            <%--<div class="span" style="color: #ff1b46;font-size: 18px;">￥${items.price}</div>--%>
            <%--<a href="item/itemid=${items.id}">--%>
            <%--<div class="span">${items.name}</div>--%>
            <%--</a>--%>
            <%--<div class="span"><b class="icon-time"></b>${items.time}</div>--%>
            <%--</div>--%>
            <%--</c:forEach>--%>
        </div>
    </div>

</div>

</body>
</html>