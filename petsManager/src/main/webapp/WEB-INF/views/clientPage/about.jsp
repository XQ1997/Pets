<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>关于我们</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Gato Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <%@ include file="../client/css.jsp"%>
</head>
<body>
<div id="home" class="header">
    <jsp:include page="../client/header.jsp">
        <jsp:param name="menu" value="about"/>
    </jsp:include>
    <div class="banner two">
        <div class="container">
            <div class="pag-nav">
                <ul class="p-list">
                    <li><a href="/home">首页</a></li> &nbsp;&nbsp;/&nbsp;
                    <li class="act">&nbsp;关于我们</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>关于我们</h3>
        </div>
        <div class="portfolio-bottom">
            <div class="col-md-4 contact-top-left">
                <div class="contact-top-one">
                    <h4>Address:</h4>
                    <h6>河南省郑州市
                        <span>中原区中原中路</span>
                        中原工学院信息商务学院
                    </h6>
                </div>
                <div class="contact-top-one">
                    <h4>Phone:</h4>
                    <p>电话:12345679871
                        <span>QQ:12345679871</span>
                    </p>
                </div>
                <div class="contact-top-one">
                    <h4>E-MAIL:</h4>
                    <p>mail@example.com</p>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../client/js.jsp"%>
</body>
</html>