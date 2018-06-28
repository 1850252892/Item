/**
 * 
 */
$(function () {
	var left=$(".left-menu");
	left.height($(window).height());
    $(".right-data").width($(window).width()-200);
	$(".right-data").height($(window).height());
});

	function sendData() {
		var username=$("#u_id").text();
		var nickname = $("#nickname").val();
		var name = $("#name").val();
		var address = $("#address").val();
		var mail = $("#mail").val();
		var new_password_1 = $("#new_password_1").val();
		var new_password_2 = $("#new_password_2").val();
		var old_password = $("#old_password").val();
		$("#tips_mail").empty();
		$("#tips_pass").empty();
		$("#tips_nick").empty();
		$("#tips_1").empty();
		$("#tips_2").empty();
		if (new_password_1 != null) {
			if (new_password_2 != new_password_1) {
				$("#tips_1").append("两次密码不同");
				return;
			}
		}
		
		//正则表达式匹配
		var result = mail
				.match(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/);
		if (mail != "" && result == null) {
			$("#tips_mail").append("邮箱格式错误(格式示例：xxxxxx@xx.xx)");
			return;
		}
		result = new_password_1.match(/^\w{6,16}$/);
		if (new_password_1 != "" && result == null) {
			$("#tips_pass").append("密码格式错误(密码由6-16位数字，字母或下划线组成)");
			return;
		}
		result = nickname.match(/^.{1,12}$/);
		if (nickname != "" && result == null) {
			$("#tips_nick").append("昵称长度超出（最长为12个字符）");
			return;
		}
		
		//ajax
		$.ajax({
			type : "post",
			url : "/admin/changeInfo",
			dataType : "text",
			contentType:"application/json",
			data : JSON.stringify({
				"username":username,
				"nickname" : nickname,
				"name" : name,
				"address" : address,
				"mail" : mail,
				"password" : new_password_1,
				"oldpassword" : old_password
			}),
			success : function(data) {
				if (data == "oldpasserro") {
					$("#tips_2").append("密码错误");
				} else if (data == "success") {
					window.location = "";
				}
			},
			erro : function() {
				alert("访问服务器出错...")
			}
		});

	}

	function show(id) {

		var m = document.getElementById(id);
		m.style.display = "block";
	}

	function remove(id) {
		var m = document.getElementById(id);
		m.style.display = "none";
	}

	function color(b) {

		for (var a = 1; a < 6; a++) {
			if (a == b) {
				var m = document.getElementById(a);
				m.style.background = "white";
			} else {
				m = document.getElementById(a);
				m.style.background = "rgb(238,238,238)";
			}

		}

	}

	function show_changeData() {

		document.getElementById("change-data").style.display = "block";
        // $(".right-data").width($(window).width()-185);
		document.getElementById("show-data").style.display = "none";
		document.getElementById("a-top").style.display = "none";
		$(".a-top")
				.append(
						"<p style='float:left;'>修改资料</p>"
								+ "<button onclick='back()' type='button' "
								+ "class='btn btn-default' style='margin-top:10px;float:right;'>取消</button>"
								+ "<button onclick='sendData()' type='button' "
								+ "class='btn btn-default' style='margin-top:10px;float:right;'>确认</button>")

	}
	function back() {
		$(".a-top button").remove();
		$(".a-top p").remove();
		document.getElementById("a-top").style.display = "block";
		document.getElementById("change-data").style.display = "none";
		document.getElementById("show-data").style.display = "block";
	}

	function show_pass() {
		document.getElementById("pass").style.display = "block";
		document.getElementById("a_1").style.display = "none";
	}

	function close_pass() {
		document.getElementById("pass").style.display = "none";
		document.getElementById("a_1").style.display = "block";
	}