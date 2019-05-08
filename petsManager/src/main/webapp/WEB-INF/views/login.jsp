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
        .photo {
            width: 100%;
            height: 300px;
            border: 2px dashed #ccc;
            margin-top: 20px;
            text-align: center;
            line-height: 300px;
        }
        .custphoto {
            width: 150px;
            height: 200px;
            border: 2px dashed #ccc;
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body class="hold-transition login-page body">
<div class="login-box">
    <div class="login-logo">
        <a href="/" class="shadow1" style="color:#833471">流浪宠物救助系统</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body radius">
        <p class="login-box-msg">请记住你的账号</p>
        <c:if test="${not empty message}">
            <div class="alert alert-danger text-center">${message}</div>
        </c:if>
        <form method="post" id="loginForm">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="Mobile" name="mobile" value="${mobile}" autofocus required">
                <span class="glyphicon glyphicon-phone form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="password" name="password" required>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-6">
                    <div class="checkbox icheck">
                        <label>
                            <input type="checkbox" name="rememberMe" <c:if test="${not empty mobile}">  checked</c:if>> 记住我
                        </label>
                    </div>
                </div>
                <div class="col-xs-6">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-6">
                    <a href="/losepass">忘记密码</a>
                </div>
                <div class="col-xs-6">
                    <a href="/regist" class="btn btn-success form-control">注册</a>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- jQuery 3 -->
<script src="/static/plugins/jQuery/jquery-3.2.1.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="/static/plugins/iCheck/icheck.min.js"></script>
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

        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' /* optional */
        });
        
		var callback = "${param.callback}";
		//点击回车提交表单
		$(document).keydown(function(event){
			if(event.keyCode == 13) {
				$("#loginForm").submit();
			}
		})
		//点击按钮提交表单
		$("#loginBtn").click(function(){
			$("#loginForm").submit();
		});
    });
</script>
</body>
</html>
