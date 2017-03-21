<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-1.12.3.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        #login-form{
            margin-top:20px;
        }
        #errorMsg{
            color: red;
            display: none;
        }
    </style>
</head>
<body>
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
        </div>
        <div class="form-group">
            <span id="errorMsg"></span>
        </div>
    </form>
</div>
<script>
    $("#submit").click(function(){
        var username = $("input[name='username']").val();
        var password = $("input[name='password']").val();
        $.ajax({
            url:"login",
            type:"post",
            data:{username:username,password:password},
            success:function(res){
                if(res.success){
                    location.href="index";
                }else{
                    $("#errorMsg").text(res.errorMsg).show();
                }
            }
        });
    });
</script>
</body>
</html>