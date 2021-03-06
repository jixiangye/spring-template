<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>找回密码</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <script src="js/jquery-1.12.3.js"></script>
    <script src="js/jquery.placeholder.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/common.js"></script>
    <link rel="stylesheet" href="css/forget.css">
    <!--[if lte IE 9]>
    <script src="js/respond.min.js"></script>
    <script src="js/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>
<br class="xs-80">
<div class="container-fluid" id="main">
    <div id="forget-form">
        <h3>找回密码</h3>
        <div id="step1">
            <div class="form-group">
                <input type="text" class="form-control" id="username" placeholder="用户名" name="username">
            </div>
            <div class="form-group">
                <button type="button" id="next" class="btn btn-primary form-control">下一步</button>
            </div>
        </div>
        <div id="step2">
            <span id="tip"></span>
            <div class="form-group">
                <input type="text" class="form-control" id="code" placeholder="验证码" name="code">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" placeholder="密码" name="password">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="rePassword" placeholder="确认密码" name="rePassword">
            </div>
            <div class="form-group">
                <button type="button" id="submit" class="btn btn-primary form-control">提交</button>
            </div>
        </div>

        <div class="form-group">
            <span id="errorMsg"></span>
        </div>
    </div>
</div>
<script>
    $('input').placeholder();

    $("#next").click(function () {
        var username = $("input[name='username']").val()
        if (!username) {
            $("#errorMsg").text("用户名不能为空").show();
            return false;
        }

        $.ajax({
            url: "forget/sendCode",
            type: "post",
            data: {username: username},
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    $("#errorMsg").text("").hide();
                    $("#tip").text("验证码已发送至用户绑定邮箱");
                    $("#step1").hide();
                    $("#step2").show();
                } else {
                    $("#errorMsg").text(res.errorMsg).show();
                }
            }
        });
    });

    $("#submit").click(function () {
        $.ajax({
            url: "resetPassword",
            type: "post",
            dataType: "json",
            data: $("#forget-form").values(),
            success: function (res) {
                if (res.success) {
                    $.alert({
                        msg: "修改密码成功",
                        ok: function () {
                            location.href = "login";
                        }
                    });
                } else {
                    $("#errorMsg").text(res.errorMsg).show();
                }
            }
        });
    });
</script>
</body>
</html>
