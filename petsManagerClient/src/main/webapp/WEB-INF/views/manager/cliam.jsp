<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>领养申请</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Gato Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <%@ include file="../include/css.jsp"%>
</head>
<body>
<div id="home" class="header">
    <%@ include file="../include/header.jsp"%>
    <div class="banner two">
        <div class="container">
            <div class="pag-nav">
                <ul class="p-list">
                    <li><a href="/home">首页</a></li> &nbsp;&nbsp;/&nbsp;
                    <li class="act">&nbsp;领养申请</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>领养申请信息</h3>
            <a href="/cliam/state" class="btn btn-success btn-sm pull-right"><i class="fa fa-plus"></i>查看申请状态</a>
        </div>
        <div class="portfolio-bottom">
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
            <div>
                <button class="btn pull-right btn-primary" id="saveBtn"><i class="fa fa-save"></i> 申请</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="../include/js.jsp"%>
<%@ include file="../include/footer.jsp"%>
<script>
    $(function(){
        $("#saveBtn").click(function(){
            $("#addCliam").submit();
        });
    })
</script>
</body>
</html>