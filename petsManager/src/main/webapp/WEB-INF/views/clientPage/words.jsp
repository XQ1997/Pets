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
    <%@ include file="../client/css.jsp"%>
</head>
<body>
<div id="home" class="header">
    <jsp:include page="../client/header.jsp">
        <jsp:param name="menu" value="words"/>
    </jsp:include>
    <div class="banner two">
        <div class="container">
            <div class="pag-nav">
                <ul class="p-list">
                    <li><a href="/index">首页</a></li> &nbsp;&nbsp;/&nbsp;
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
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="portfolio-bottom">
                    <form method="post" id="saveword" class="form-horizontal center-block">
                        <div class="form-group">
                            <label class="col-md-4 control-label text-center">主题:</label>
                            <div class="col-md-8">
                                <input type="text" name="title" size=30 autofocus/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label text-right">留言:</label>
                            <div class="col-md-8">
                                <textarea name="content" rows=7 cols=30></textarea>
                            </div>
                        </div>
                    </form>
                    <div class="box-footer">
                        <button class="btn btn-success center-block" id="saveBtn">留言</button>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <c:if test="${not empty message}">
                    <div class="box1 center-block">
                        <table class="table">
                            <tbody>
                            <tr>
                                <td class="text-muted text-right"><i>留言人:</i></td>
                                <td class="text-left">${words.username}</td>
                            <tr>
                                <td class="text-muted text-right"><i>留言主题:</i></td>
                                <td class="text-left">${words.title}</td>
                            </tr>
                            <tr>
                                <td class="text-muted text-right"><i>留言内容:</i></td>
                                <td class="text-left"><textarea disabled>${words.content}</textarea></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<%@ include file="../client/footer.jsp"%>
<%@ include file="../client/js.jsp"%>
<script>
    $(function(){
        $("#saveBtn").click(function(){
            $("#saveword").submit();
        });
    })
</script>
</body>
</html>