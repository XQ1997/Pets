<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>留言板</title>
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
                    <li class="act">&nbsp;留言板</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>留言板</h3>
            <a href="/cliam/state" class="btn btn-success btn-sm pull-right"><i class="fa fa-plus"></i>查看申请状态</a>
        </div>
        <div class="portfolio-bottom">
            <form method="post" id="saveword" class="form-horizontal center-block">
                <div class="form-group">
                    <label class="col-md-4 control-label">主题:</label>
                    <div class="col-md-8">
                        <input type="text" name="title" size=30 autofocus/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">留言:</label>
                    <div class="col-md-8">
                        <textarea name="content" rows=7 cols=30></textarea>
                    </div>
                </div>
            </form>
            <div class="box-footer">
                <button class="btn btn-primary center-block" id="saveBtn">留言</button>
            </div>
        </div>
        <c:if test="${not empty message}">
            <div class="box1 center-block">
                <table class="table">
                    <tbody>
                    <tr>
                        <td class="text-muted text-center"><i>留言人:</i></td>
                        <td><shiro:principal property="username"/></td>
                    <tr>
                        <td class="text-muted text-center"><i>留言主题:</i></td>
                        <td>${words.title}</td>
                    </tr>
                    <tr>
                        <td class="text-muted text-center"><i>留言内容:</i></td>
                        <td><textarea disabled>${words.content}</textarea></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>
<%@ include file="../include/js.jsp"%>
<%@ include file="../include/footer.jsp"%>
<script>
    $(function(){
        $("#saveBtn").click(function(){
            $("#saveword").submit();
        });
    })
</script>
</body>
</html>