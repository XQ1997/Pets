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
            <a href="/cliam/state" class="btn btn-success btn-sm pull-right"><i class="fa fa-plus"></i>查看申请状态</a>
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
<%@ include file="../include/js.jsp"%>
<%@ include file="../include/footer.jsp"%>
</body>
</html>