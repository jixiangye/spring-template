<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>角色管理</title>
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

        #btn-add-role {
            margin: 10px;
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
            <a class="navbar-brand" href="#">Brand</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Link </a></li>
                <li><a href="#">Link</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div id="main" class="table-responsive">
    <button type="button" class="btn btn-primary" id="btn-add-role" data-toggle="modal" data-target="#addRoleModal">
        新增角色
    </button>
    <table id="role-list" class="table table-striped table-bordered"></table>
</div>

<!-- Modal -->
<div class="modal fade" id="addRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增角色</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <input type="text" class="form-control" id="roleCode" placeholder="角色代码" name="roleCode">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="roleName" placeholder="角色名称" name="roleName">
                </div>
                <div class="form-group">
                    <span class="errorMsg"></span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="add-role-save">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="assignModal" tabindex="-1" role="dialog" aria-labelledby="assignModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:300px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="assignModalLabel"><span id="role-code"></span>分配权限</h4>
            </div>
            <div class="modal-body">
                <div class="form-group permission-list">

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
    var roleListInit = function () {
        $.ajax({
            url: "list",
            type: "post",
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    $("#role-list").table(
                            [
                                {
                                    title: "角色代码",
                                    field: "roleCode"
                                },
                                {
                                    title: "角色名称",
                                    field: "roleName"
                                },
                                {
                                    title: "操作",
                                    formatter: function (data, row) {
                                        return '<button type="button" data="' + row.roleCode + '" class="btn btn-danger delete">删除</button>&nbsp;&nbsp;' +
                                                '<button type="button" data="' + row.roleCode + '" class="btn btn-primary assign" data-toggle="modal" data-target="#assignModal">分配权限</button>';
                                    }
                                }
                            ],
                            res.data
                    );
                }

            }
        });
    }

    $("#add-role-save").click(function () {
        var params = $("#addRoleModal").values();
        $.ajax({
            url: "add",
            type: "post",
            dataType: "json",
            data: params,
            success: function (res) {
                if (res.success) {
                    roleListInit();
                    $("#addRoleModal").find(".close").click();
                } else {
                    $("#addRoleModal").find(".errorMsg").text(res.errorMsg).show();
                }
            }
        });
    });

    $("#role-list").on("click", ".assign", function () {
        var _this = $(this);
        var roleCode = _this.attr("data");
        $.ajax({
            url: "../permission/list",
            type: "post",
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    var html = "";
                    for (var i = 0; i < res.data.length; i++) {
                        html += '<div class="checkbox"><label>';
                        html += '<input type="checkbox" name="permissionCode" value="' + res.data[i].permissionCode + '">';
                        html += res.data[i].permissionName;
                        html += '</label></div>';
                    }
                    $("#role-code").text(roleCode);
                    $("#assignModal").find(".permission-list").html(html);
                    $.ajax({
                        url: "permission/list",
                        type: "post",
                        dataType: "json",
                        data: {roleCode: roleCode},
                        success: function (res) {
                            if (res.success) {
                                for (var i = 0; i < res.data.length; i++) {
                                    $(".permission-list").find("input[value='" + res.data[i].permissionCode + "']").prop('checked', true)
                                }
                            } else {
                                $("#assignModal").find(".errorMsg").text("查询权限失败").show();
                            }
                        }
                    });
                } else {
                    $("#assignModal").find(".errorMsg").text("查询权限失败").show();
                }
            }
        })

    });

    $("#role-list").on("click", ".delete", function () {
        var _this = $(this);
        $.confirm({
            msg: "确定删除角色？",
            ok: function () {
                $.ajax({
                    url: "delete",
                    type: "post",
                    dataType: "json",
                    data: {roleCode: _this.attr("data")},
                    success: function (res) {
                        if (res.success) {
                            roleListInit();
                        } else {
                            alert("删除失败");
                        }

                    }
                });
            }
        });
    });

    $(document).ready(function () {
        roleListInit();
        $('input').placeholder();
    });

</script>
</body>
</html>
