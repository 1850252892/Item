<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人主页</title>

<link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="/css/bootstrap-table.css">
<link type="text/css" rel="stylesheet" href="/css/font-awesome.css">
<link type="text/css" rel="stylesheet" href="/css/userMainPagev1.css">

<script type="text/javascript" src="/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/bootstrap-table.js"></script>
<script type="text/javascript" src="/js/myJs/userMainPage.js"></script>
<style type="text/css">
a {
	text-decoration: none;
	color: black;
}

a:link {
	text-decoration: none;
	color: black;
}

a:visited {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: none;
	color: black;
}

a:active {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body>
	<%--<jsp:include page="../head-top.jsp"></jsp:include>--%>
	
	<!-- top -->
	
	<div class="left-menu">
		<a href="/mainPage" style="display: block;"><H2>HLeB</H2></a><br>
		<%--<div class="line_1" style="width: 100%;height: 1px;background: #3e4659;"></div>--%>
		<%--<div class="line_2" style="height: 1px;width: 100%;background-color: #50596e;"></div>--%>
		<div class="menu-param"><a href="/admin/userMainPage">个人信息</a></div>
		<div class="menu-param"><a href="/admin/myItem">我的货架</a></div>
		<div class="menu-param"><a href="/admin/businessPage">交易记录</a></div>
		<div class="menu-param"><a href="/item/addItemPage">发布商品</a></div>
		<div class="menu-param"><a href="/admin/myCollectPage">我的收藏</a></div>
		<div class="menu-param"><a href="/admin/pending">待处理的</a></div>
		<div class="menu-param"><a href="/admin/mySubmitPage">我提交的</a></div>
		<div class="menu-param"><a href="/admin/message">消息通知</a></div>
	</div>
	<!-- left-menu -->
	
	<div class="right-data">
		<div class="u-data">

			<div class="u-nick"><div id="u_id" style="float:left;">帐号:${sessionScope.user.username}</div><br>昵称：${sessionScope.user.nickname}</div>
		</div>
		<div class="d-table">
			<div class="dt-param">已提交的</div>
			<div class="dt-param">待处理的</div>
			<div class="dt-param">未处理的</div>
			<div class="dt-param" style="border-right: 0;">已接受的</div>
		</div>
		<div class="all-data">
			<div class="a-top">
				<div id="a-top">
					<div id="a_top_label" style="width: 50%; float: left;">
						<div>详细信息</div>
					</div>
					<span class="icon-edit" onclick="show_changeData()"></span>
				</div>
			</div>

			<div class="show-data" id="show-data">
				<div class="a-param">
					<span>姓名：${sessionScope.user.name}</span>
				</div>
				<div class="a-param" >
					<span>所在地:</span>${sessionScope.user.address}
				</div>
				<div class="a-param">
					<span>邮箱：${sessionScope.user.mail}</span>
				</div>
				<div class="a-param">
					<span>历史成交量：</span>
				</div>
				<div class="a-param">
					<span>最近交易：</span>
				</div>
			</div>
			<!-- showdata -->

			<div id="change-data" style="padding: 10px; display: none;color:white;">
				<div class="col-lg-6">
					<div class="form-group">
						<label>昵称：</label> <input type="email" class="form-control"
							id="nickname" placeholder="nickname">
						<div id="tips_nick"></div>
					</div>

					<div class="form-group">
						<label>姓名：</label> <input type="email" class="form-control"
							id="name" placeholder="name">
					</div>
					<div class="form-group">
						<label>地址：</label> <input type="email" class="form-control"
							id="address" placeholder="address">
					</div>

					<div class="form-group">
						<label>邮箱：</label> <input type="email" class="form-control"
							id="mail" placeholder="mail">
						<div id="tips_mail"></div>
					</div>

					<a id="a_1"><span class="icon-caret-down" onclick="show_pass()"><label>修改密码</label></span></a>

					<div class="password" id="pass" style="display: none;">
						<div class="form-group">
							<label>密码：</label> <input type="password" class="form-control"
								id="new_password_1" placeholder="new password">
							<div id="tips_pass"></div>
						</div>
						<div class="form-group">
							<label>确认密码：</label> <input type="password" class="form-control"
								id="new_password_2" placeholder="validate password">
							<div id="tips_1"></div>
						</div>
						<div class="form-group">
							<label>输入原密码：</label> <input type="email" class="form-control"
								id="old_password" placeholder="old password">
							<div id="tips_2"></div>
						</div>

						<a><span class="icon-caret-up" onclick="close_pass()"><label>取消</label></span></a>
					</div>
				</div>
			</div>


		</div>
	</div>

</body>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">

</script>
</html>