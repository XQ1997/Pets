<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>流浪宠物救助系统 | 首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Gato Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <%@ include file="client/css.jsp"%>
</head>
<body>
<div id="home" class="header">
    <jsp:include page="client/header.jsp">
        <jsp:param name="menu" value="home"/>
    </jsp:include>
    <div class="banner two">
        <div class="container">
            <div class="pag-nav">
                <ul class="p-list">
                    <li><a href="/home">首页</a></li> &nbsp;&nbsp;/&nbsp;
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>公告展示</h3>
        </div>
        <div class="portfolio-bottom">
            <table class="table">
                <thead>
                <tr>
                    <th class="text-center">公告主题</th>
                    <th class="text-center">公告内容</th>
                    <th class="text-center">发布时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="notice">
                    <tr>
                        <td class="text-center"><strong>${notice.title}</strong></td>
                        <td class="text-center"><strong>${notice.content}</strong></td>
                        <td class="text-center"><strong><fmt:formatDate value="${notice.createTime}"  pattern='yyyy年MM月dd日'/></strong></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${pageInfo.pages > 1}">
                <ul id="pagination-demo" class="pagination pull-right"></ul>
            </c:if>
        </div>
    </div>
</div>

<%@ include file="client/js.jsp"%>
<script type="text/javascript" src="/static/plugins/pet/js/jquery.flexisel.js"></script>
<script src="/static/plugins/jquery.twbsPagination.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script>
    $(function(){
        $('#pagination-demo').twbsPagination({
            totalPages: ${pageInfo.pages},
            visiblePage:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?pageNo={{number}}"
        });
        //datatime显示时间弹框
        var picker = $('#datepicker').datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true,//为true时，选择完时间，弹框消失，反之，不消失
            todayHighlight: true,//常量显示
        })
    })
</script>
</body>
</html>
