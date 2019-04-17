<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>宠物管理</title>
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
                    <li class="act">&nbsp;宠物管理</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>宠物列表</h3>
        </div>
        <div class="portfolio-bottom">
            <div class="gallery-one two">
                <div class="col-md-3 gallery-left two">
                    <c:forEach items="${petsList}" var="pets">
                        <a href="/pet/${pets.id}">
                            <img src="http://pq422kh3g.bkt.clouddn.com/${pets.image}" alt="" class="img-responsive zoom-img"/>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../include/js.jsp"%>
<%@ include file="../include/footer.jsp"%>
</body>
</html>
