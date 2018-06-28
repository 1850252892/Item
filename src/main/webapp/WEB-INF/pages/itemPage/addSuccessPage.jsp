<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布成功</title>
<link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="/css/bootstrap-table.css">
<link type="text/css" rel="stylesheet" href="/css/font-awesome.css">
<link type="text/css" rel="stylesheet" href="/css/addSuccessPage.css">

<script type="text/javascript" src="/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/bootstrap-table.js"></script>
<style type="text/css">

</style>
</head>

<body>
	<jsp:include page="../head.jsp"></jsp:include>
	<div class="data">
		<div class="title">发布成功:${additem.id}</div>

		<div class="item">
			<div class="name">名字:${additem.name}</div>
			<div class="classification">分类:${additem.classification}</div>
			<span>商品图片：</span> <br>
			<div class="imgs">

				<c:forEach items="${additem.imgpath}" var="imgpath">
					<img width=160px
						style="float: left; margin: 10px; border: 1px solid;" class="imgs"
						src="${imgpath}">
				</c:forEach>
			</div>
			<br> <span>商品介绍：</span>
			<div class="detail">${additem.detail}</div>
		</div>
		<!-- item -->

		<div class="bottom">
			<a href="/item/addItemPage">继续添加</a>/<a href="/mainPage">返回主页</a>
		</div>
		<!-- botton -->
	</div>
</body>
</html>