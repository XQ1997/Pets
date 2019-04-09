<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 认领申请管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="cliam"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <%--搜索表单--%>
            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" name="username" placeholder="申请人" class="form-control" value="${param.username}">
                        <input type="text" name="mobile" placeholder="联系电话" class="form-control" value="${param.mobile}">
                        <select name="state" class="form-control">
                            <option value="">全部</option>
                            <option value="未通过" ${param.state == "未通过" ? 'selected' : ''}>未通过</option>
                            <option value="已通过" ${param.state == "已通过" ? 'selected' : ''}>已通过</option>
                            <option value="已提交" ${param.state == "已提交" ? 'selected' : ''}>已提交</option>
                        </select>
                        <button class="btn btn-flat"><i class="fa fa-search"></i></button>
                    </form>
                </div>
            </div>

            <c:if test="${not empty message}">
                <div class="alert alert-success text-center">${message}</div>
            </c:if>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">申请认领信息</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                     <table class="table">
                         <thead>
                             <tr>
                                 <th class="text-center">申请人</th>
                                 <th class="text-center">当前状态</th>
                                 <th class="text-center">认领宠物</th>
                                 <th class="text-center">联系电话</th>
                                 <th class="text-center">审核人</th>
                             </tr>
                         </thead>
                         <tbody>
                            <c:forEach items="${pageInfo.list}" var="cliam">
                                <tr>
                                    <td class="text-center"><strong><a href="/cliam/${cliam.id}">${cliam.username}</a></strong></td>
                                    <td class="text-center"><strong>${cliam.state}</strong></td>
                                    <td class="text-center"><strong>${cliam.petname}</strong></td>
                                    <td class="text-center"><strong>${cliam.mobile}</strong></td>
                                    <td class="text-center"><strong>${cliam.cliamName}</strong></td>
                                </tr>
                            </c:forEach>
                         </tbody>
                     </table>
                    <c:if test="${pageInfo.pages > 1}">
                        <ul id="pagination-demo" class="pagination pull-right"></ul>
                    </c:if>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../include/footer.jsp"/>
</div>
<jsp:include page="../include/js.jsp"/>
<script>
    $(function(){
        console.log(${pageInfo.pages});
        $('#pagination-demo').twbsPagination({
            totalPages: ${pageInfo.pages},
            visiblePage:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?username="+encodeURIComponent('${param.username}')+
            "&mobile="+encodeURIComponent('${param.mobile}')+
            "&state="+encodeURIComponent('${param.state}')+
            "&pageNo={{number}}"
        });
    })
</script>
</body>
</html>

