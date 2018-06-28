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
<link type="text/css" rel="stylesheet" href="/css/font-awesome.css">
<link type="text/css" rel="stylesheet" href="/css/excSuc.css">
<script type="text/javascript" src="/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="/js/myJs/excData.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/bootstrap-table.js"></script>
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
	<%--<jsp:include page="../head.jsp"></jsp:include>--%>

	<div class="box">
		<div class="title">交换请求详情</div>
		<div class="excForm">
			<div class="excId"><label>交换单号:</label></div>
			<!-- 交换单id -->
			<div class="excDate"><label>产生时间:</label></div>
			<div class="itemA">
				<label>提供商品:</label><br>
				<div class="item-img" id="glogo_A"></div>
				<div class="item-name" id="gname_A">商品名：</div>
			</div>
			<div class="itemB">
				<label>我的商品:</label><br>
				<div class="item-img" id="glogo_B"></div>
				<div class="item-name" id="gname_B">商品名：</div>
			</div>
			<div class="excInfo">
				<label>备注信息:</label><br>
				
			</div>
			
			<div class="bottom">
			<a href="javascript:accede()"><button class="btn btn-default">同意交换</button></a>
			<a href="javascript:refuse()"><button class="btn btn-default">拒绝交换</button></a>
				<a href="/mainPage"><button class="btn btn-default">返回主页</button></a>
			</div>
		</div>
	</div>

</body>
</html>