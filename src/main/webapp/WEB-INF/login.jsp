<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!--[if lte IE 9]>
    <script src="js/respond.min.js"></script>
    <script src="js/html5shiv.min.js"></script>
    <![endif]-->
    <script src="js/jquery-1.12.3.js"></script>
    <script src="js/jquery.placeholder.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/common.js"></script>
    <style>
        .page-header{
            text-align:center;
        }

        #errorMsg {
            color: red;
            display: none;
        }
    </style>
</head>
<body>
<div class="page-header">
    <h1>登录</h1>
</div>
<div class="container-fluid">
    <form method="post" action="login" id="login-form">
        <div class="form-group">
            <input type="text" class="form-control" id="username" placeholder="用户名" name="username">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="password" placeholder="密码" name="password">
        </div>
        <div class="form-group">
            <button id="submit" type="button" class="btn btn-primary form-control">登录</button>
        </div>
        <div class="form-group">
            <a href="register">注册</a>
            <a href="forget">找回密码</a>
        </div>
        <div class="form-group">
            <span id="errorMsg"></span>
        </div>
    </form>
</div>
<script>
    $('input').placeholder();

    $("#submit").click(function () {
        var params = $("#login-form").values();
        if(!params.username){
            $("#errorMsg").text("用户名不能为空").show();
            return false;
        }
        if(!params.password){
            $("#errorMsg").text("密码不能为空").show();
            return false;
        }
        $.ajax({
            url: "login",
            type: "post",
            dataType: "json",
            data: params,
            success: function (res) {
                if (res.success) {
                    location.href = "index";
                } else {
                    $("#errorMsg").text(res.errorMsg).show();
                }
            }
        });
    });
</script>
</body>
</html>
