<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!-- 引入jstl表达式依赖文件 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品详情</title>

    <!-- bootstrap相关文件的导入 -->
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="/css/bootstrap-table.css">
    <link type="text/css" rel="stylesheet" href="/css/font-awesome.css">
    <link type="text/css" rel="stylesheet" href="/css/itemPage.css">

    <script type="text/javascript" src="/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-table.js"></script>
    <script type="text/javascript" src="/js/myJs/itemPage.js"></script>
    <style type="text/css">

        /* 去除一些基础标签的属性值 */
        body, div, form, img, ul, ol, li, dl, dt, dd, form, p, h1, h3 {
            margin: 0px;
            padding: 0px;
            border: 0px none;
            list-style: none outside none;
            text-decoration: none;
        }
    </style>
</head>
<body>
<!-- 导入顶部导航页面-->
<jsp:include page="../head-top.jsp"></jsp:include>
<%--<div class="h-top">--%>
<%--<a href="/mainPage">HLeB</a> <span>换出新感觉</span>--%>
<%--</div>--%>
<div class="i-top">
    <div class="u-logo">
        <!-- 用户头像 -->
    </div>
    <div class="u-nickname" id="u-nickname">
        <%--拥有者<br> <a href="/otherUser?sellerid=${user_b.username}">${user_b.nickname}</a>--%>
    </div>
    <div class="i-browse" id="i-browse">
        <%--浏览量<br>${itemdata.browser}--%>
    </div>
    <div class="i-date" id="i-date">
        <%--发布时间<br>${itemdata.time}--%>
    </div>
    <div class="i-date" id="i_collect">

    </div>
</div>
<!-- /.i-top -->

<div class="i-box">
    <div class="i-img">
        <div class="img-bg">
           <%--&lt;%&ndash;&ndash;%&gt; <img src="${itemdata.imgpath[0]}">--%>
        </div>
        <div class="img-li">
          <%--  <c:forEach items="${itemdata.imgpath}" var="imgpath">
                <img src="${imgpath}">
            </c:forEach>--%>
        </div>
    </div>
    <!-- /.i-img -->

    <div class="box-r">
        <div class="i-title" id="i-title"><%--${itemdata.name}--%></div>
        <div class="i-price">
            参考价:<b>￥</b><span class="span" id="i-price"><%--${itemdata.price}.00--%></span>
        </div>
        <div class="u-address" id="i-address"><%--所在地:${user_b.address}--%></div>
        <div class="u-address" id="i-expect"><%--期望商品:${itemdata.expect}--%></div>

        <c:if test="${sessionScope.user.username==null}">
            <div class="i-exchange">
                <a href="/loginPage">
                    <button type="button"
                            style="width: 200px;" class="btn btn-info btn-lg">点我登录
                    </button>
                </a>
            </div>
        </c:if>
        <c:if test="${sessionScope.user.username!=null}">
            <div class="i-exchange">
                <a href="javascript:swap()">
                    <button type="button"
                            style="width: 200px;" class="btn btn-info btn-lg">发起交换
                    </button>
                </a>
            </div>
            <div class="i-message">
                <button type="button" style="width: 200px;"
                        class="btn btn-primary btn-lg"
                        onclick="sendMessage(${sessionScope.user.username});">发送留言
                </button>
            </div>
            <textarea id="i-message" rows="5" cols="" style="width: 200px"
                      placeholder="添加留言"></textarea>
        </c:if>


    </div>
    <!-- /.box-r -->
</div>
<!-- /.i-box -->

<div class="detail">
    <div class="label">
        <ul>
            <li><a href="#1" id="div1"><span>商品介绍</span></a></li>
            <li><a href="#2" id="div2"><span>留言列表</span></a></li>
        </ul>
    </div>
    <div class="detail-box" id="div3">
        <%--${itemdata.detail}--%>
    </div>
    <div class="detail-box" id="div4" style="display: none;">
        <span id="comment_num"></span><br>
        <div class="messagebox">
        </div>
        <div class="loadbox"></div>
    </div>

    <div class="u-item-l">
        <h3>该用户的其它商品</h3>
        <%-- <c:if test="${b_itemlist.size()}">
        该用户暂无其它商品
        </c:if> --%>
        <div id="items">
            <%--<c:forEach items="${b_itemlist}" var="iteml" end="4">--%>
                <%--<c:if test="${iteml.id!=itemdata.id}">--%>
                    <%--<div class="u-item">--%>
                        <%--<div class="u-item-img">--%>
                            <%--<a href="/item/itemid=${iteml.id}"><img alt=""--%>
                                                                    <%--src="${iteml.imgpath[0]}"></a>--%>
                        <%--</div>--%>
                        <%--<div class="u-item-name">${iteml.name}</div>--%>
                        <%--<div class="u-item-price">--%>
                            <%--参考价：<span>${iteml.price}.00</span>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</c:if>--%>
            <%--</c:forEach>--%>
        </div>
    </div>
</div>
<!-- detail -->
<div style="display:none;" id="itemid"><%--${itemdata.id}--%></div>

</body>


</html>