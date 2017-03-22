<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
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
            <input type="text" class="form-control" id="username" placeholder="用户名" name="username" required="required">
        </div>
        <div class="form-group">
            <input type="email" class="form-control" id="email" placeholder="邮箱" name="email" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="password" placeholder="密码" name="password"
                   required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="repassword" placeholder="确认密码" name="repassword"
                   required="required">
        </div>
        <div class="form-group">
            <button type="button" id="submit" class="btn btn-primary form-control">下一步</button>
        </div>
        <div class="form-group">
            <span id="errorMsg"></span>
        </div>
    </form>
</div>
</body>
</html>
