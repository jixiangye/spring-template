<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-1.12.3.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        body {
            margin-top: 20px;
        }

        #errorMsg {
            color: red;
            display: none;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <form method="post" action="register" id="register-form">
        <div class="form-group">
            <input type="text" class="form-control" id="username" placeholder="用户名" name="username">
        </div>
        <div class="form-group">
            <input type="email" class="form-control" id="email" placeholder="邮箱" name="email">
        </div>
        <div class="form-group">
            <input type="tel" class="form-control" id="mobile" placeholder="手机号" name="mobile">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="password" placeholder="密码" name="password">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="repassword" placeholder="确认密码" name="repassword">
        </div>
        <div class="form-group">
            <button type="button" id="submit" class="btn btn-primary form-control">提交注册</button>
        </div>
        <div class="form-group">
            <span id="errorMsg"></span>
        </div>
    </form>
</div>
<script>
    $("#submit").click(function () {
        var username = $("input[name='username']").val();
        var email = $("input[name='email']").val();
        var mobile = $("input[name='mobile']").val();
        var password = $("input[name='password']").val();
        var repassword = $("input[name='repassword']").val();
        if (password != repassword) {
            $("#errorMsg").text("密码和确认密码不一致").show();
            return false
        }
        $.ajax({
            url: "register",
            type: "post",
            dataType: "json",
            data: {username: username, email: email, mobile: mobile, password: password},
            success: function (res) {
                if (res.success) {
                    location.href = "login";
                } else {
                    $("#errorMsg").text(res.errorMsg).show();
                }
            }
        });
    });
</script>
</body>
</html>
