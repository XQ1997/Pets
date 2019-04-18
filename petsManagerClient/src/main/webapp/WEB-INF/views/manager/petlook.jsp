<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <jsp:include page="../include/header.jsp">
        <jsp:param name="menu" value="pet"/>
    </jsp:include>
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
            <h3>宠物详细信息</h3>
        </div>
        <div class="portfolio-bottom">
            <table class="table">
                <tbody>
                <tr>
                    <td class="text-muted text-center"><i>流浪宠物名称:</i></td>
                    <td>${pets.petname}</td>
                    <td class="text-muted text-center"><i>当前状态:</i></td>
                    <td>${pets.state}</td>
                    <td class="text-muted text-center"><i>救助地点:</i></td>
                    <td>${pets.place}</td>
                </tr>
                <tr>
                    <td class="text-muted text-center"><i>宠物种类:</i></td>
                    <td>${pets.type}</td>
                    <td class="text-muted text-center"><i>宠物编号:</i></td>
                    <td>${pets.num}</td>
                </tr>
                <tr>
                    <td class="text-muted text-center"><i>年龄:</i></td>
                    <td>${pets.age}</td>
                    <td colspan="2"></td>
                    <td class="text-muted text-center"><i>创建时间:</i></td>
                    <td><fmt:formatDate value="${pets.createTime}"  pattern='yyyy年MM月dd日'/></td>
                </tr>
                <tr>
                    <td class="text-muted text-center"><i>宠物描述:</i></td>
                    <td>${pets.content}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="port-head">
            <h3>宠物介绍视频</h3>
        </div>
        <div class="portfolio-bottom">
            <div>
                <c:if test="${pets.video != null}">
                    <embed width="320" height="240" src="http://pq422kh3g.bkt.clouddn.com/${pets.video}" />
                </c:if>
            </div>
        </div>
    </div>
</div>
<%@ include file="../include/js.jsp"%>

</body>
</html>
