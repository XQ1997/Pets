<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 认领申请结果查看</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="state"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">宠物认领申请结果显示</h3>
                </div>
              <c:forEach items="${cliamList}" var="cliam">
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
                            </tr>
                            </tbody>
                        </table>
                </div>
              </c:forEach>
            </div>
        </section>
    </div>
    <jsp:include page="../include/footer.jsp"/>
</div>
<jsp:include page="../include/js.jsp"/>
</body>
</html>

