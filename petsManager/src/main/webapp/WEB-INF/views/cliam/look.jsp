<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 认领申请管理 | 申请详情</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
    <style>
        .photo {
            width: 300px;
            height: 300px;
            border: 2px dashed #ccc;
            margin-top: 20px;
            text-align: center;
            line-height: 300px;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="cliam"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">申请认领信息</h3>
                    <div class="box-tools pull-right">
                        <a href="/cliam" class="btn btn-success btn-sm"><i class="fa fa-backward"></i>返回</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td class="text-muted text-center"><i>用户姓名:</i></td>
                            <td>${cliam.username}</td>
                            <td class="text-muted text-center"><i>认领宠物名称:</i></td>
                            <td>${cliam.petname}</td>
                            <td class="text-muted text-center"><i>联系电话:</i></td>
                            <td>${cliam.mobile}</td>
                        </tr>
                        <tr>
                            <td class="text-muted text-center"><i>申请状态:</i></td>
                            <td>${cliam.state}</td>
                            <td colspan="2"></td>
                            <td class="text-muted text-center"><i>申请时间:</i></td>
                            <td><fmt:formatDate value="${cliam.createTime}"  pattern='yyyy年MM月dd日'/></td>
                        </tr>
                        <tr>
                            <td class="text-muted text-center"><i>申请理由:</i></td>
                            <td>${cliam.content}</td>
                            <td colspan="2"></td>
                            <td class="text-muted text-center"><i>审核人:</i></td>
                            <td>${cliam.cliamName}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">申请人信息</h3>
                </div>
                <div class="box-body">
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
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">关联资质</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="photo">
                                <img src="http://pmupn7ccj.bkt.clouddn.com/${account.userPhoto}-preview" alt="">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="photo">
                                <img src="http://pmupn7ccj.bkt.clouddn.com/${account.cardInPhoto}-preview" alt="">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="photo">
                                <img src="http://pmupn7ccj.bkt.clouddn.com/${account.cardOutPhoto}-preview" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../include/footer.jsp"/>
</div>
<jsp:include page="../include/js.jsp"/>
</body>
</html>

