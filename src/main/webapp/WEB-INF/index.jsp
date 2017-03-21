<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-1.12.3.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<h1>欢迎<span id="username"></span>登录</h1><a href="logout">退出</a>
<script>
    $.ajax({
        url:"info",
        type:"post",
        success:function(res){
            $("#username").text(res.username);
        }
    });
</script>
</body>
</html>
