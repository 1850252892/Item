<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册</title>
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="/css/font-awesome.css">
    <style type="text/css">
        body, div, form, img, ul, ol, li, dl, dt, dd, form, p, h1 {
            margin: 0px;
            padding: 0px;
            border: 0px none;
            list-style: none outside none;
            text-decoration: none;
        }

        body {
            text-align: center;
        }

        .r_top {
            width: 100%;
            height: 50px;
            line-height: 50px;
            float: left;
            text-align: left;
            padding-left: 20%;
            background-color: rgb(63, 68, 72);
            color: rgb(250, 250, 250);
            font-size: 25px;
        }

        .dataInput {
            margin: 0 auto;
            width: 60%;
            text-align: center;
            color: rgb(25, 25, 25);
            background-color: rgb(240, 240, 255);
            padding-bottom: 30px;
        }
    </style>
</head>

<body>

<div class="r_top">用户注册><a href="/mainPage" style="text-decoration: none;">返回首页</a>><a style="text-decoration: none;"
                                                                                       href="/loginPage">返回登陆</a></div>
<div class="dataInput">
    <h3>请输入下列信息</h3>
    <table
            style="border-collapse: separate; border-spacing: 0px 10px; margin: auto; text-align: left;">

        <tr>
            <td>帐号：<input type="text" id="username" name="username"
                          class="form-control" placeholder="帐号"></td>
        </tr>
        <tr>
            <td>密码：<input type="password" id="password" name="password"
                          class="form-control" placeholder="输入密码"></td>
        </tr>
        <tr>
            <td>确认密码：<input type="password" id="password2" name="password"
                            class="form-control" placeholder="确认密码"></td>
        </tr>
        <tr>
            <td>昵称：<input type="text" id="nickname" name="nickname"
                          class="form-control" placeholder="请输入昵称"></td>
        </tr>
        <tr>
            <td>姓名：<input type="text" id="name" name="name"
                          class="form-control" placeholder="姓名"></td>
        </tr>
        <tr>
            <td>地址：<select id="province" name="province3"></select>
                <select id="city" name="city3"></select>
                <select id="area" name="area3"></select><br>
                <br><input type="text" id="address">
            </td>
        </tr>
        <tr>
            <td>邮箱：<input type="text" id="mail" name="mail"
                          class="form-control" placeholder="邮箱">
                <div id="tips_mail">
            </td>
        </tr>
    </table>

    <div>
        <!-- /.row -->
        <label id="message"></label> <br>
        <button class="btn btn-info" type="button" onclick="sendData()">提交信息</button>
    </div>

</div>
<!-- /.col-lg-6 -->

</body>

<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="/js/PCASClass.js"></script>
<script type="text/javascript" src="/js/jquery.md5.js"></script>

<script type="text/javascript">
    new PCAS("province3", "city3", "area3");

    function sendData() {
        var username = document.getElementById('username').value;
        var nickname = document.getElementById('nickname').value;
        var password = document.getElementById('password').value;
        var password2 = document.getElementById('password2').value;
        var name = document.getElementById('name').value;
        var province = $("#province option:selected").text();
        var city = $("#city option:selected").text();
        var area = $("#area option:selected").text();
        var address = document.getElementById('address').value;
        var mail = document.getElementById('mail').value;
        var result;

        $("#message").empty();

        if (nickname == "" || username == "" || password == "" || name == ""
            || address == "" || mail == "" ||province == "" || city=="" || area=="") {
            $("#message").append("<br>请将信息填写完整<br><br>");
            return;

        }
        if (password != password2) {
            $("#message").append("<br>两次密码不一样<br><br>");
            return;
        }
        result = username.match(/^\d{10}$/);
        if (username != "" && result == null) {
            $("#message").append("<br>帐号格式出错（帐号只能由10位数字组成）");
            return;
        }
        result = password.match(/^\w{6,16}$/);
        if (password != "" && result == null) {
            $("#message").append("<br>密码格式错误(密码由6-16位数字，字母或下划线组成)");
            return;
        }
        result = nickname.match(/^.{1,12}$/);
        if (nickname != "" && result == null) {
            $("#message").append("<br>昵称长度超出（最长为12个字符）");
            return;
        }

        result = mail.match(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/);
        if (result == null) {
            $("#message").append("<br>邮箱格式错误(格式示例：xxxxxx@xx.xx)<br><br>");
            return;
        }
        address=province+"-"+city+"-"+area+"-"+address;
        $.ajax({
            url: './register',
            type: 'post',
            dataType: 'text',
            data: {
                "username": username,
                "nickname": nickname,
                "name": name,
                "password": $.md5(password),
                "address": address,
                "mail": mail
            },
            success: function (data) {
                if (data == "SUCCESS") {
                    alert("恭喜您注册成功，点击确定返回到登录页面");
                    self.location = "./loginPage";
                } else {
                    alert(data);
                }
            },
            error: function () {
                alert("出现预期之外的错误，请联系维护人员...");
            }
        });

    }
</script>

</html>