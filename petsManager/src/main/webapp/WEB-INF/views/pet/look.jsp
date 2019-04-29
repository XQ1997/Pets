<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 流浪宠物管理 | 宠物详情</title>
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
        <jsp:param name="menu" value="pet"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">流浪宠物信息</h3>
                    <div class="box-tools pull-right">
                        <a href="/pet" class="btn btn-success btn-sm"><i class="fa fa-backward"></i>返回</a>
                    </div>
                </div>
                <div class="box-body">
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
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">关联资质</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="photo">
                                <img src="http://pmupn7ccj.bkt.clouddn.com/${pets.image}-preview" alt="">
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
<script src="/static/dist/js/layer/layer.js"></script>
</body>
</html>

