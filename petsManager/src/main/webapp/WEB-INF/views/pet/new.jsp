<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 流浪宠物管理 | 新增流浪宠物</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/uploader/webuploader.css">
    <style>
        .petphoto {
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
        <jsp:param name="menu" value="pet"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">流浪宠物信息</h3>
                    <div class="box-tools pull-right">
                        <a href="/pet" class="btn btn-success btn-sm pull-right"><i class="fa fa-backward"></i>返回</a>
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="savePet" class="form-horizontal">
                        <div class="row">
                            <div class="col-md-8">
                                <input type="hidden" name="image" id="petphoto">
                                <input type="hidden" name="video" id="petvideo">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">宠物名称:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="petname" class="form-control" autofocus/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">年龄:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="age" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">救助地点:</label>
                                    <div class="col-md-8">
                                        <input type="text" name="place" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">宠物类别:</label>
                                    <div class="col-md-8">
                                        <select name="type" class="form-control">
                                            <option value="哺乳动物类">哺乳动物类</option>
                                            <option value="鸟类">鸟类</option>
                                            <option value="爬行动物类">爬行动物类</option>
                                            <option value="两栖动物类">两栖动物类</option>
                                            <option value="鱼类">鱼类</option>
                                            <option value="昆虫动物类">昆虫动物类</option>
                                            <option value="植物宠物类">植物宠物类</option>
                                            <option value="茶宠宠物">茶宠宠物</option>
                                            <option value="另类宠物">另类宠物</option>
                                            <option value="其他">其他</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">宠物描述:</label>
                                    <div class="col-md-8">
                                        <textarea name="content" rows=7 cols=75>${pets.content}</textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="col-md-6">
                                    <div id="picker">宠物照片</div>
                                    <div class="petphoto" id="photo"></div>
                                </div>
                                <div class="col-md-6">
                                    <div id="picker1">上传视频</div>
                                    <div class="petphoto" id="video"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary pull-right" id="saveBtn">保存</button>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../include/footer.jsp"/>
</div>
<jsp:include page="../include/js.jsp"/>
<script src="/static/plugins/uploader/webuploader.min.js"></script>
<script src="/static/dist/js/layer/layer.js"></script>
<script src="/static/plugins/jquery.twbsPagination.js"></script>
<script>
    $(function(){
        //初始化webuploader组件 上传图片
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
            $("#photo").html("");

            //获取七牛上图片名称
            var fileName = response.key;
            //动态添加从七牛请求的图像
            var $img = $("<img>").attr("src","http://pq422kh3g.bkt.clouddn.com/" + fileName);
            $img.appendTo($("#photo"));

            //将key存放进隐藏域中
            $("#petphoto").val(fileName);

            layer.msg("上传成功");
        });
        uploader.on( 'uploadError', function( file ) {
            layer.msg("服务器异常");
        });
        uploader.on( 'uploadComplete', function( file ) {
            layer.close(index);
        });

        //初始化webuploader组件 上传视频
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
            pick: '#picker1',
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
            $("#video").html("");

            //获取七牛上图片名称
            var fileName = response.key;
            //动态添加从七牛请求的图像
            var $img = $("<img>").attr("src","http://pq422kh3g.bkt.clouddn.com/" + fileName);
            $img.appendTo($("#video"));

            //将key存放进隐藏域中
            $("#petvideo").val(fileName);

            layer.msg("上传成功");
        });
        uploader.on( 'uploadError', function( file ) {
            layer.msg("服务器异常");
        });
        uploader.on( 'uploadComplete', function( file ) {
            layer.close(index);
        });

        $("#saveBtn").click(function () {
            $("#savePet").submit();
        });
    })
</script>
</body>
</html>

