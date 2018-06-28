<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl表达式依赖文件 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="/css/font-awesome.css">
<link type="text/css" rel="stylesheet" href="/css/excPage.css">
<script type="text/javascript" src="/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="/js/myJs/excPage.js"></script>
<style type="text/css">

/* 去除一些基础标签的属性值 */
body, div, form, img, ul, ol, li, dl, dt, dd, form, p, h1 {
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

	<div class="excBox">
		<p><span class="icon-edit" style="margin-right:5px;"></span>交换信息填写</p>
		<div class="exc_top">
		</div>
		<div class="exc_middle">
			<div class="d_write">
				<%--<div class="e_param"><span class="icon-caret-right">商品ID:</span><div id="gid_b">${sessionScope.itemdata.id}</div></div>--%>
				<div class="e_param" id="item-name"><span class="icon-caret-right"></span></div>
				<div class="e_img" id="item-img"><span class="icon-caret-right"></span>商品图片：<br />

					
				</div>
				<div class="e_param" id="item-type"><span class="icon-caret-right"></span></div>
				<div class="e_mItem"><span class="icon-caret-right"></span>交换商品：
					<br><div class="e_mP"><span>点击右侧我的商品添加到此处</span></div>
				</div>
				<div class="e_mData"><span class="icon-caret-right"></span>备注：<br/>
				<textarea rows="5" cols="" id="info"></textarea>
				</div>
				<div class="button">
					<button type="button" class="btn btn-default" onclick="sendData()">提交</button>
					<a href="./itemPage/itemid=${sessionScope.itemdata.id}"><button type="button" class="btn btn-default">取消</button></a>
				</div>
			</div>
			<div class="m_item">
				<p>我的商品:（点击选中交换）</p>
				<ul class="item_l">
				</ul>
				<div class="fydiv">
					<ul class="fenye">
					</ul>
				</div>
			</div>
			<!-- .m_item -->
		</div>
		<div class="exc_bottom"></div>
	</div>
	<div id="username" style="display: none;">${sessionScope.user.username}</div>
</body>
</html>