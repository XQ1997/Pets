<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 编辑用户</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
    <style>
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
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="user"/>
    </jsp:include>
    <div class="content-wrapper"> 
        <section class="content"> 
            <c:if test="${not empty message}">
                <div class="alert alert-success text-center">${message}</div>
            </c:if>
            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">编辑信息</h3>

                    <div class="box-tools pull-right">
                        <a href="/user" class="btn btn-success btn-sm pull-right"><i class="fa fa-backward"></i>返回</a>
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
                                        <input type="text" name="username" class="form-control" value="${account.username}" autofocus/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">联系电话:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="mobile" class="form-control" value="${account.mobile}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">年龄:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="age" class="form-control" value="${account.age}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">角色</label>
                                    <div class="col-md-8">
                                        <select name="role" class="form-control">
                                            <option value="管理员" ${account.role == "管理员" ? 'selected' : ''}>管理员</option>
                                            <option value="领养人" ${account.role == "领养人" ? 'selected' : ''}>领养人</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">住址:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="address" class="form-control" value="${account.address}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">从事工作:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="job" class="form-control" value="${account.job}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">身份证号:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="cardnum" class="form-control" value="${account.cardnum}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="col-md-6">
                                    <div id="picker3">用户一寸照片</div>
                                    <div class="custphoto" id="photo">
                                        <img src="http://pmupn7ccj.bkt.clouddn.com/${account.userPhoto}-preview" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="row">
                            <div class="col-md-6">
                                <div id="picker">身份证正面照片</div>
                                <div class="photo" id="userinPhoto">
                                    <img src="http://pmupn7ccj.bkt.clouddn.com/${account.cardInPhoto}-preview" alt="">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div id="picker2">身份证反面照片</div>
                                <div class="photo" id="userblackPhoto">
                                    <img src="http://pmupn7ccj.bkt.clouddn.com/${account.cardOutPhoto}-preview" alt="">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary pull-right" id="saveBtn">更新</button>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../include/footer.jsp"></jsp:include>
</div>
<jsp:include page="../include/js.jsp"></jsp:include>
<script src="/static/plugins/uploader/webuploader.min.js"></script>
<script>
    $(function () {
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
            var $img = $("<img>").attr("src","http://pmupn7ccj.bkt.clouddn.com/" + fileName + "-custphoto");
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
            var $img = $("<img>").attr("src","http://pmupn7ccj.bkt.clouddn.com/" + fileName + "-preview");
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
            swf:'${pageContext.request.contextPath}/static/plugins/uploader/Uploader.swf',

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
            var $img = $("<img>").attr("src","http://pmupn7ccj.bkt.clouddn.com/" + fileName + "-preview");
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
    })
</script>
</body>
</html>

