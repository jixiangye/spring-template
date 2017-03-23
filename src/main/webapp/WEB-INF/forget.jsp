<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-1.12.3.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/common.js"></script>
    <style>
        body {
            margin-top: 20px;
        }

        #errorMsg {
            color: red;
            display: none;
        }

        #step2 {
            display: none;
        }
    </style>
</head>
<body>
<div class="container-fluid" id="forget-form">
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
<script>
    $("#next").click(function () {
        $.ajax({
            url: "forget/sendCode",
            type: "post",
            data:{username:$("input[name='username']").val()},
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
            data:getInputValues($("#forget-form")),
            success: function (res) {
                if (res.success) {
                    $("#errorMsg").text("修改密码成功").show();
                    setTimeout(function(){
                        location.href = "login";
                    },2000);
                }else{
                    $("#errorMsg").text(res.errorMsg).show();
                }
            }
        });
    });
</script>
</body>
</html>
