<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>用户信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Gato Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <%@ include file="../client/css.jsp"%>
</head>
<body>
<div id="home" class="header">
    <jsp:include page="../client/header.jsp">
        <jsp:param name="menu" value="user"/>
    </jsp:include>
    <div class="banner two">
        <div class="container">
            <div class="pag-nav">
                <ul class="p-list">
                    <li><a href="/home">首页</a></li> &nbsp;&nbsp;/&nbsp;
                    <li class="act">&nbsp;用户信息</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>用户信息查看</h3>
            <a class="btn btn-success pull-right" href="/user/${account.id}/edit"><i class="fa fa-edit"></i>修改个人信息</a>
            <a class="btn btn-success pull-right" href="/client/repassword"><i class="fa fa-edit"></i>修改密码</a>
        </div>
        <div class="portfolio-bottom">
            <table class="table">
                <tbody>
                <tr>
                    <td class="text-muted text-center"><i>申请人姓名:</i></td>
                    <td>${account.username}</td>
                    <td class="text-muted text-center"><i>角色:</i></td>
                    <td>${account.role}</td>
                    <td class="text-muted text-center"><i>年龄:</i></td>
                    <td>${account.age}</td>
                </tr>
                <tr>
                    <td class="text-muted text-center"><i>职业:</i></td>
                    <td>${account.job}</td>
                    <td class="text-muted text-center"><i>性别:</i></td>
                    <td>${account.sex}</td>
                    <td class="text-muted text-center"><i>住址:</i></td>
                    <td>${account.address}</td>
                </tr>
                <tr>
                    <td class="text-muted text-center"><i>身份证号:</i></td>
                    <td>${account.cardnum}</td>
                    <td class="text-muted text-center"><i>联系电话:</i></td>
                    <td>${account.mobile}</td>
                    <td class="text-muted text-center"><i>注册时间:</i></td>
                    <td><fmt:formatDate value="${account.createTime}"  pattern='yyyy年MM月dd日'/></td>
                </tr>
                </tbody>
            </table>
            <div class="box-body">
                <div class="row">
                    <div class="col-md-4">
                        <div class="photo">
                            <img src="http://pq422kh3g.bkt.clouddn.com/${account.userPhoto}" alt="">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="photo">
                            <img src="http://pq422kh3g.bkt.clouddn.com/${account.cardInPhoto}" alt="">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="photo">
                            <img src="http://pq422kh3g.bkt.clouddn.com/${account.cardOutPhoto}" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../client/js.jsp"%>
</body>
</html>