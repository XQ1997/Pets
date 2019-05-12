<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>宠物发布</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Gato Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <%@ include file="../client/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
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
<body>
<div id="home" class="header">
    <jsp:include page="../client/header.jsp">
        <jsp:param name="menu" value="publish"/>
    </jsp:include>
    <div class="banner two">
        <div class="container">
            <div class="pag-nav">
                <ul class="p-list">
                    <li><a href="/index">首页</a></li> &nbsp;&nbsp;/&nbsp;
                    <li class="act">&nbsp;宠物发布</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>宠物发布</h3>
        </div>
        <br/>
        <c:if test="${not empty message}">
            <div class="alert alert-success text-center">${message}</div>
        </c:if>
        <div class="portfolio-bottom">
            <form method="post" class="form-horizontal" id="addPet">
                <div class="row">
                    <div class="col-md-8">
                        <input type="hidden" name="image" id="petphoto">
                        <div class="form-group">
                            <label class="col-md-3 control-label">宠物学名:</label>
                            <div class="col-md-9">
                                <input type="text" name="petLanguageName" class="form-control" autofocus/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">宠物名称:</label>
                            <div class="col-md-9">
                                <input type="text" name="petname" class="form-control" autofocus/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">年龄:</label>
                            <div class="col-md-9">
                                <input type="text" name="age" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">详细地址:</label>
                            <div class="col-md-9">
                                <input type="text" name="place" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">联系方式:</label>
                            <div class="col-md-9">
                                <input type="text" name="mobile" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">发布类型:</label>
                            <div class="col-md-9">
                                <select name="sendtype" class="form-control">
                                    <option value="送养">送养</option>
                                    <option value="托管">托管</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">注意事项:</label>
                            <div class="col-md-9">
                                <textarea name="content" rows=7 cols=60></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="col-md-6">
                            <div id="picker">宠物照片</div>
                            <div class="petphoto" id="photo"></div>
                        </div>
                    </div>
                </div>
            </form>
            <div>
                <button class="btn pull-right btn-success" id="saveBtn"><i class="fa fa-save"></i> 发布</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="../client/footer.jsp"%>
<%@ include file="../client/js.jsp"%>
<script src="/static/plugins/uploader/webuploader.min.js"></script>
<script src="/static/dist/js/layer/layer.js"></script>
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
            var $img = $("<img>").attr("src","http://pqtuic9ww.bkt.clouddn.com/" + fileName + "-custphoto");
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

        $("#saveBtn").click(function(){
            $("#addPet").submit();
        });
    })
</script>
</body>
</html>