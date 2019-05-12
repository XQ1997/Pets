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
            <h3>查看回复</h3>
        </div>
        <div>
            <table class="table">
                <tbody>
                <tr>
                    <td class="text-muted text-center text-success"><h4>${words.username}&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;留言标题为:"${words.title}"&nbsp;&nbsp;&nbsp;&nbsp;内容为:</h4></td>
                    <td><textarea disabled rows="4" cols="50">${words.content}</textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-md-offset-2">
            <table class="table">
                <tbody>
                <c:forEach items="${replyList}" var="reply">
                    <tr>
                        <td class="text-muted text-center text-info"><h4>${reply.replyname}&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;回复标题为:"${words.title}"&nbsp;&nbsp;&nbsp;&nbsp;内容为:</h4></td>
                        <td><textarea disabled rows="4" cols="50" >${reply.content}</textarea></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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