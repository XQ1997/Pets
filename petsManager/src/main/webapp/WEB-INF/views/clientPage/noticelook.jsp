<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>公告详情</title>
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
                    <li class="act">&nbsp;首页</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>公告详情</h3>
        </div>
        <div class="box1 center-block">
            <table class="table">
                <tbody>
                <tr>
                    <td class="text-muted text-right"><i>公告主题:</i></td>
                    <td class="text-left">${notice.title}</td>
                </tr>
                <tr>
                    <td class="text-muted text-right"><i>公告内容:</i></td>
                    <td class="text-left"><textarea disabled cols=80>${notice.content}</textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="../client/footer.jsp"%>
<%@ include file="../client/js.jsp"%>
</body>
</html>