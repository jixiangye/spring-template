<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <!--[if lte IE 9]>
    <script src="../js/respond.min.js"></script>
    <script src="../js/html5shiv.min.js"></script>
    <![endif]-->
    <script src="../js/jquery-1.12.3.js"></script>
    <script src="../js/jquery.placeholder.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/common.js"></script>
    <style>
        #main {
            width: 97%;
            margin: 0 auto;
        }

        .errorMsg {
            color: red;
            display: none;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index">首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/role/view">角色权限管理</a></li>
                <li class="active"><a href="/user/list">用户角色管理</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><span id="username"></span><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/logout">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div id="main" class="table-responsive">
    <table id="user-list" class="table table-striped table-bordered"></table>
</div>

<div class="modal fade" id="assignModal" tabindex="-1" role="dialog" aria-labelledby="assignModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:300px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="assignModalLabel"><span class="username"></span>分配角色</h4>
            </div>
            <div class="modal-body">
                <div class="form-group role-list">

                </div>
                <div class="form-group">
                    <span class="errorMsg"></span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="assign-save">保存</button>
            </div>
        </div>
    </div>
</div>
<script>
    var userListInit = function () {
        $.ajax({
            url: "list",
            type: "post",
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    $("#user-list").table(
                            [
                                {
                                    title: "用户名",
                                    field: "username"
                                },
                                {
                                    title: "邮箱",
                                    field: "email"
                                },
                                {
                                    title: "手机号",
                                    field: "mobile"
                                },
                                {
                                    title: "创建时间",
                                    field: "createTime"
                                },
                                {
                                    title: "修改时间",
                                    field: "modifyTime"
                                },
                                {
                                    title: "操作",
                                    formatter: function (data, row) {
                                        return '<button type="button" data="' + row.username + '" class="btn btn-primary assign" data-toggle="modal" data-target="#assignModal">分配角色</button>';
                                    }
                                }
                            ],
                            res.data
                    );
                }

            }
        });
    }

    $("#user-list").on("click", ".assign", function () {
        var _this = $(this);
        var username = _this.attr("data");
        $.ajax({
            url: "/role/list",
            type: "post",
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    var html = "";
                    for (var i = 0; i < res.data.length; i++) {
                        html += '<div class="checkbox"><label>';
                        html += '<input type="checkbox" name="roleCode" value="' + res.data[i].roleCode + '">';
                        html += res.data[i].roleName;
                        html += '</label></div>';
                    }
                    $("#assignModal").find(".username").text(username);
                    $("#assignModal").find(".role-list").html(html);
                    $.ajax({
                        url: "/user/role/list",
                        type: "post",
                        dataType: "json",
                        data: {username: username},
                        success: function (res) {
                            if (res.success) {
                                for (var i = 0; i < res.data.length; i++) {
                                    $(".role-list").find("input[value='" + res.data[i].roleCode + "']").prop('checked', true)
                                }
                            } else {
                                $("#assignModal").find(".errorMsg").text("查询角色失败").show();
                            }
                        }
                    });
                } else {
                    $("#assignModal").find(".errorMsg").text("查询角色失败").show();
                }
            }
        })

    });

    $("#assign-save").click(function () {
        var params = $("#assignModal").values();
        params['username'] = $(".username").text();


        $.ajax({
            url: "/user/role/save",
            type: "post",
            dataType: "json",
            data: JSON.stringify(params),
            contentType: 'application/json',
            success: function (res) {
                if (res.success) {
                    $("#assignModal").find(".close").click();
                } else {
                    $.alert({msg: "分配角色失败"});
                }
            }
        });
    });

    $(document).ready(function () {
        userListInit();
        $('input').placeholder();
    });

</script>
</body>
</html>
