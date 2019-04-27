<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 流浪宠物管理 | 新增宠物就医记录</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="sick"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">宠物就医记录</h3>
                    <div class="box-tools pull-right">
                        <a href="/sick" class="btn btn-success btn-sm pull-right"><i class="fa fa-backward"></i>返回</a>
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveSick" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-3 control-label">宠物类别:</label>
                            <div class="col-md-8">
                                <select name="petType" class="form-control">
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
                            <label class="col-md-3 control-label">宠物名称:</label>
                            <div class="col-md-8">
                                <input type="text" name="petname" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">宠物编号:</label>
                            <div class="col-md-8">
                                <input type="text" name="petnum" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">宠物生病类型:</label>
                            <div class="col-md-8">
                                <input type="text" name="sickType" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">花费:</label>
                            <div class="col-md-8">
                                <input type="text" name="money" class="form-control"/>
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
<script>
    $(function(){
        $("#saveBtn").click(function () {
            $("#saveSick").submit();
        });
    })
</script>
</body>
</html>

