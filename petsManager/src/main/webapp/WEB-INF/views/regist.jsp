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
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
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
<div class="container">
    <div class="content">
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">填写信息</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm" class="form-horizontal">
                        <div class="row">
                            <div class="col-md-8">
                                <br>
                                <input type="hidden" name="cardInPhoto" id="custPhotoIn">
                                <input type="hidden" name="cardOutPhoto" id="custPhotoOut">
                                <input type="hidden" name="userPhoto" id="userPhoto">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">用户姓名:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="username" class="form-control" autofocus/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">登录密码:</label>
                                    <div class="col-md-8">
                                        <input type="password" name="password" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">联系电话:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="mobile" class="form-control" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">年龄:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="age" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">性别</label>
                                    <div class="col-md-8">
                                        <input type="radio" name="sex" value="男" checked>男
                                        <input type="radio" name="sex" value="女">女
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">住址:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="address" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">从事工作:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="job" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">身份证号:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="cardnum" class="form-control" required/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="col-md-12">
                                    <div id="picker3">用户一寸照</div>
                                    <div class="custphoto" id="photo"></div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="row">
                            <div class="col-md-6">
                                <div id="picker">身份证正面照片</div>
                                <div class="photo" id="userinPhoto"></div>
                            </div>
                            <div class="col-md-6">
                                <div id="picker2">身份证反面照片</div>
                                <div class="photo" id="userblackPhoto"></div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary pull-right" id="saveBtn">注册</button>
                </div>
            </div>
        </section>
    </div>
</div>

<!-- jQuery 3 -->
<script src="/static/plugins/jQuery/jquery-3.2.1.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="/static/plugins/iCheck/icheck.min.js"></script>
<script src="/static/dist/js/canvas/canvas-nest-class.js"></script>
<script src="/static/plugins/uploader/webuploader.min.js"></script>
<script src="/static/dist/js/jquery.validate.min.js"></script>
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
        //初始化客户一寸照片
        var uploader = WebUploader.create({
            //选完文件之后，是否自动上传
            auto:true,
            // swf文件路径
            swf:'/static/plugins/uploader/Uploader.swf',

            // 文件接收服务端。
            server: 'http://upload-z1.qiniup.com',
            //文件上传域的name
            fileVal:'file',
            //文件上传请求的参数表，每次发送都会发送发送此对象中的参数
            formData:{
                "token":"${token}"
            },
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker3',
            //只允许接收图片文件
            accept:{
                title:'Images',
                extensions:'gif,jpg,jpeg,bmp,png',
                mineTypes:'image/*'
            }
        });
        var index = -1;
        //文件成功。失败处理
        uploader.on( 'uploadStart', function( file ) {
            index = layer.load(1);
        });
        uploader.on( 'uploadSuccess', function( file,response ) {
            //先清空内容
            $("#photo").html("");

            //获取七牛上图片名称
            var fileName = response.key;
            //动态添加从七牛请求的图像
            var $img = $("<img>").attr("src","http://pqtuic9ww.bkt.clouddn.com/" + fileName + "-custphoto");
            $img.appendTo($("#photo"));

            //将key存放进隐藏域中
            $("#userPhoto").val(fileName);

            layer.msg("上传成功");
        });
        uploader.on( 'uploadError', function( file ) {
            layer.msg("服务器异常");
        });
        uploader.on( 'uploadComplete', function( file ) {
            layer.close(index);
        });

        //初始化身份证照片正面
        var uploader = WebUploader.create({
            //选完文件之后，是否自动上传
            auto:true,
            // swf文件路径
            swf:'/static/plugins/uploader/Uploader.swf',

            // 文件接收服务端。
            server: 'http://upload-z1.qiniup.com',
            //文件上传域的name
            fileVal:'file',
            //文件上传请求的参数表，每次发送都会发送发送此对象中的参数
            formData:{
                "token":"${token}"
            },
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker',
            //只允许接收图片文件
            accept:{
                title:'Images',
                extensions:'gif,jpg,jpeg,bmp,png',
                mineTypes:'image/*'
            }
        });
        var index = -1;
        //文件成功。失败处理
        uploader.on( 'uploadStart', function( file ) {
            index = layer.load(1);
        });
        uploader.on( 'uploadSuccess', function( file,response ) {
            //先清空内容
            $("#userinPhoto").html("");

            //获取七牛上图片名称
            var fileName = response.key;
            //动态添加从七牛请求的图像
            var $img = $("<img>").attr("src","http://pqtuic9ww.bkt.clouddn.com/" + fileName + "-preview");
            $img.appendTo($("#userinPhoto"));

            //将key存放进隐藏域中
            $("#cardInPhoto").val(fileName);

            layer.msg("上传成功");
        });
        uploader.on( 'uploadError', function( file ) {
            layer.msg("服务器异常");
        });
        uploader.on( 'uploadComplete', function( file ) {
            layer.close(index);
        });

        //初始化身份证照片反面
        var uploader = WebUploader.create({
            //选完文件之后，是否自动上传
            auto:true,
            // swf文件路径
            swf:'/static/plugins/uploader/Uploader.swf',

            // 文件接收服务端。
            server: 'http://upload-z1.qiniup.com',
            //文件上传域的name
            fileVal:'file',
            //文件上传请求的参数表，每次发送都会发送发送此对象中的参数
            formData:{
                "token":"${token}"
            },
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker2',
            //只允许接收图片文件
            accept:{
                title:'Images',
                extensions:'gif,jpg,jpeg,bmp,png',
                mineTypes:'image/*'
            }
        });
        var index = -1;
        //文件成功。失败处理
        uploader.on( 'uploadStart', function( file ) {
            index = layer.load(1);
        });
        uploader.on( 'uploadSuccess', function( file,response ) {
            //先清空内容
            $("#userblackPhoto").html("");

            //获取七牛上图片名称
            var fileName = response.key;
            //动态添加从七牛请求的图像
            var $img = $("<img>").attr("src","http://pqtuic9ww.bkt.clouddn.com/" + fileName + "-preview");
            $img.appendTo($("#userblackPhoto"));

            //将key存放进隐藏域中
            $("#custPhotoOut").val(fileName);

            layer.msg("上传成功");
        });
        uploader.on( 'uploadError', function( file ) {
            layer.msg("服务器异常");
        });
        uploader.on( 'uploadComplete', function( file ) {
            layer.close(index);
        });

        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });
        //表单校验
        $("#saveForm").validate({
            errorClass : 'text-danger',
            errorElement : 'span',
            rules : {
                mobile :{
                    "required" : true,
                    "minlength": 11
                },
                cardnum : {
                    "required" : true,
                    "minlength": 18
                }
            },
            messages :{
                mobile :{
                    "required" : "请输入联系电话",
                    "minlength": "用户名长度不得少于11位"
                },
                cardnum : {
                    "required" : "请输入身份证号",
                    "minlength": "身份证号长度不得少于18位"
                }
            }
        });
    });
</script>
</body>
</html>
