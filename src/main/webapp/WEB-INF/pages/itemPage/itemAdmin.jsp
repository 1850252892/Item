<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl表达式依赖文件 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- bootstrap相关文件的导入 -->
<link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="/css/bootstrap-table.css">
<link type="text/css" rel="stylesheet" href="/css/font-awesome.css">
<link type="text/css" rel="stylesheet" href="/css/itemAdmin.css">



<script type="text/javascript" src="/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/bootstrap-table.js"></script>
<script type="text/javascript" src="js/myJs/itemAdmin.js"></script>

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
	<div class="container">
		<div class="title">
			<label>信息总览</label>
		</div>
		
		<div class="base-data">
		<label style="font-size: 20px;font-weight: 700;border-bottom: 1px solid red;">原始信息</label>
		<div class="single" id="i-name"><label>商品名：</label></div>
		<div class="single" id="i-id"><label>商品编号：</label></div>
		<div class="single" id="i-date"><label>上架时间：</label></div>
		<div class="single" id="i-type"><label>商品分类:</label> </div>
		<div class="single" id="i-expect"><label>期望商品：</label></div>
		<div class="single" id="i-price"><label>估价：</label></div>
		<div class="i-img" id="i-img"><label>商品图片：</label></div>
		<div class="more" id="i-info"><label>商品描述：</label></div>
		</div>
		
		<div class="another-data">
		<label style="font-size: 20px;font-weight: 700;border-bottom: 1px solid red;">其它数据</label>
		<div class="single" id="i-browse"><label>浏览量：</label></div>
		<div class="single" id="i-collect"><label>收藏量：</label></div>
		<div class="single" id="i-rq"><label>请求交换量：</label></div>
		<div id="i-request"></div>
		<div class="single" id="i-comment"><label>评论数：</label></div>
		<div id="i-comment"></div>
		</div>
	</div>


</body>
</html>