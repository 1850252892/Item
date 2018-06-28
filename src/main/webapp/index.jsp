<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<!-- bootstrp基础css文件 -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
<!-- bootstrap表格css文件 -->
<link type="text/css" rel="stylesheet" href="css/bootstrap-table.css">
<!-- awesome css文件 -->
<link type="text/css" rel="stylesheet" href="css/font-awesome.css">
<link type="text/css" rel="stylesheet" href="css/userLogin.css">
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- Bootstrap 核心 JavaScript 文件 -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-table.js"></script>

<style type="text/css">
body {
	background-image: url("images/logimg.jpg");
}
</style>
</head>
<body>
	<div class="div_1" id="div_1">
		<div class="left"></div>
		<div class="right">
			<div class="right_top">
				<span class="icon-home" ></span><a href="./mainPage">进入首页</a>
				<span class=" icon-user"></span><a href="./registerPage">注册帐号</a>
				<span class="icon-search"></span><a href="#">找回密码</a>
			</div>
			<div class="login_box">
				<h1>HLB</h1>
				<p>welcome to huanlebe</p>
				<form name="form1" action="/login" method="post">
					<div class="login_box_tab"></div>
					<input type="text" id="loginNumber" name="username"
						placeholder="plese enter id" />
					<div id="loginstate1"></div>
					<input type="text" onfocus="this.type='password'" id="userPwd"
						name="password" placeholder="password" />
					<div id="loginstate2"></div>
					<A></A>
					<div class="btn" onclick="login()" >登录</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	function login() {
		$("#loginstate1").empty();
		$("#loginstate2").empty();
		var login = document.getElementById('loginNumber').value;

		var password = document.getElementById('userPwd').value;

		if (login == "") {
			$("#loginstate1").append("<br>帐号不能为空<br>");
		}
		if (password == "") {
			$("#loginstate2").append("<br>密码不能为空<br>");
		}

		$.ajax({
			url : "./login",
			type : 'post',
			dataType : 'text',
			data : {
				'username' : login,
				'password' : password
			},
			success : function(msg) {
					var data=JSON.parse(msg);
					if(data.success=="false"){
						alert(data.msg);
					}else if(data.success=="true"){
						self.location=data.url;
					}
			},
			erro : function() {
				alert("访问错误");
			}

		});

	}

	function register() {

		self.location = "";

	}
</script>
</html>
