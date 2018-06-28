<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的收藏</title>
<link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="/css/font-awesome.css">
<link type="text/css" rel="stylesheet" href="/css/myCollectPage.css">

<script type="text/javascript" src="/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/bootstrap-table.js"></script>
<script type="text/javascript" src="/js/myJs/myCollectPage.js"></script>
<style type="text/css">

</style>

</head>
<body>
<div class="left-menu">
	<a href="/mainPage" style="display: block;"><H2>HLeB</H2></a><br>
	<%--<div class="line_1" style="width: 100%;height: 1px;background: #3e4659;"></div>--%>
	<%--<div class="line_2" style="height: 1px;width: 100%;background-color: #50596e;"></div>--%>
	<div class="menu-param"><a href="/admin/userMainPage">个人信息</a></div>
	<div class="menu-param"><a href="/admin/myItem" >我的货架</a></div>
	<div class="menu-param"><a href="/admin/businessPage" >交易记录</a></div>
	<div class="menu-param"><a href="/item/addItemPage">发布商品</a></div>
	<div class="menu-param"><a href="/admin/myCollectPage" style="background: rgba(248,244,255,0.3);">我的收藏</a></div>
	<div class="menu-param"><a href="/admin/pending">待处理的</a></div>
	<div class="menu-param"><a href="/admin/mySubmitPage">我提交的</a></div>
	<div class="menu-param"><a href="/admin/message">消息通知</a></div>
</div>

	<div class="right-data">
		<div class="itembox">
		</div>
        <div class="loadbox"></div>
    </div>

</body>

<script type="text/javascript">
	
</script>
</html>