<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/dist/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/static/plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

    <style>
        .body {
            padding: 0px;
            margin: 0px;
        }
        .radius {
            border-radius: 20px;
        }
        .input_wrapper {
            height: 40px;
            line-height: 40px;
            background-color: rgba(0,0,0,0.3);
            border-radius: 10px;
        }
        .input_wrapper input::-webkit-input-placeholder { /* Chrome/Opera/Safari */
            color: #f4f4f4;
            text-shadow: 0px 1px 2px rgba(0,0,0,0.8);
            font-size: 15px;
        }
    </style>
</head>
<body class="hold-transition login-page body">
<div class="login-box">
    <div class="login-box-body radius">
        <form method="post" id="saveForm" class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">新密码:</label>
                <div class="col-md-8">
                    <input type="hidden" name="mobile" value="${mobile}" class="form-control"/>
                    <input type="password" name="password" class="form-control"/>
                </div>
            </div>
        </form>
        <div class="box-footer">
            <button class="btn btn-primary pull-right" id="saveBtn">修改</button>
        </div>
    </div>
</div>
<!-- jQuery 3 -->
<script src="/static/plugins/jQuery/jquery-3.2.1.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/canvas/canvas-nest-class.js"></script>
<script>
    $(function () {
        new CanvasNest({
            el: document.querySelector('canvas'), // 默认自动添加 <canvas> 到 <body>
            opacity: 0.1, // 最低透明度，默认 0.1
            color: '0,0,0', // 线条颜色，默认 "0,0,0"
            count: 209, // 生成点的数量，默认 99
            zIndex: -1 // 自动添加的 <canvas> z-index样式属性，默认 -1
        });
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });
    });
</script>
</body>
</html>
