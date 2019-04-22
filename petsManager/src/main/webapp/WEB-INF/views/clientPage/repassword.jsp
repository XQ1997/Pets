<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>修改密码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Gato Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <%@ include file="../client/css.jsp"%>
</head>
<body>
<div id="home" class="header">
    <jsp:include page="../client/header.jsp">
        <jsp:param name="menu" value="cliam"/>
    </jsp:include>
    <div class="banner two">
        <div class="container">
            <div class="pag-nav">
                <ul class="p-list">
                    <li><a href="/home">首页</a></li> &nbsp;&nbsp;/&nbsp;
                    <li class="act">&nbsp;修改密码</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>修改密码</h3>
        </div>
        <br/>
        <div class="portfolio-bottom">
            <c:if test="${not empty message}">
                <div class="alert alert-success text-center">${message}</div>
            </c:if>
            <form method="post" class="form-horizontal" id="addCliam">
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">原密码:</label>
                    <div class="col-md-8">
                        <input type="text" name="oldpass" class="form-control" autofocus required/>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">新密码:</label>
                    <div class="col-md-8">
                        <input type="text" name="newpass" class="form-control" autofocus required/>
                    </div>
                </div>
            </form>
            <div>
                <button class="btn pull-right btn-success" id="saveBtn"><i class="fa fa-save"></i> 修改</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="../client/js.jsp"%>
<script>
    $(function(){
        $("#saveBtn").click(function(){
            $("#addCliam").submit();
        });
    })
</script>
</body>
</html>