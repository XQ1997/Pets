<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 宠物认领审核</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
    <!-- iCheck -->
    <link rel="stylesheet" href="/static/plugins/iCheck/square/blue.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="cliam"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">申请认领信息</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success text-center">${message}</div>
                    </c:if>
                    <form method="post" class="form-horizontal" id="addCliam">
                        <div class="form-group has-feedback">
                            <label class="col-md-3 control-label">宠物名称:</label>
                            <div class="col-md-8">
                                <input type="text" name="petname" class="form-control" autofocus required/>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="col-md-3 control-label">申请理由:</label>
                            <div class="col-md-8">
                                <textarea name="content" class="form-control" rows="3" required></textarea>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="col-md-3 control-label">联系电话:</label>
                            <div class="col-md-8">
                                <input type="text" name="mobile" class="form-control" autofocus required/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn pull-right btn-primary" id="saveBtn"><i class="fa fa-save"></i> 申请</button>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../include/footer.jsp"></jsp:include>
</div>

<jsp:include page="../include/js.jsp"></jsp:include>
<script src="/static/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function(){
        $("#saveBtn").click(function(){
            $("#addCliam").submit();
        });
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' /* optional */
        });
    })
</script>
</body>
</html>

