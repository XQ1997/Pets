<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 用户管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="user"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <%--搜索表单--%>
            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" name="username" placeholder="用户姓名" class="form-control" value="${param.username}">
                        <input type="text" name="mobile" placeholder="联系电话" class="form-control" value="${param.mobile}">
                        <select name="role" class="form-control">
                            <option value="">全部</option>
                            <option value="管理员" ${param.role == "管理员" ? 'selected' : ''}>管理员</option>
                            <option value="领养人" ${param.role == "领养人" ? 'selected' : ''}>领养人</option>
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
                    <h3 class="box-title">用户信息</h3>
                    <div class="box-tools pull-right">
                        <a href="/user/regist" class="btn btn-success btn-sm pull-right"><i class="fa fa-plus"></i>新增用户</a>
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
                                 <th class="text-center">用户姓名</th>
                                 <th class="text-center">角色</th>
                                 <th class="text-center">年龄</th>
                                 <th class="text-center">性别</th>
                                 <th class="text-center">地址</th>
                                 <th class="text-center">工作</th>
                                 <th class="text-center">身份证号</th>
                                 <th class="text-center">联系电话</th>
                                 <th class="text-center">用户编号</th>
                                 <th class="text-center">#</th>
                             </tr>
                         </thead>
                         <tbody>
                            <c:forEach items="${pageInfo.list}" var="account">
                                <tr>
                                    <td class="text-center"><strong>${account.username}</strong></td>
                                    <td class="text-center"><strong>${account.role}</strong></td>
                                    <td class="text-center"><strong>${account.age}</strong></td>
                                    <td class="text-center"><strong>${account.sex}</strong></td>
                                    <td class="text-center"><strong>${account.address}</strong></td>
                                    <td class="text-center"><strong>${account.job}</strong></td>
                                    <td class="text-center"><strong>${account.cardnum}</strong></td>
                                    <td class="text-center"><strong>${account.mobile}</strong></td>
                                    <td class="text-center"><strong>${account.number}</strong></td>
                                    <td class="text-center">
                                        <a href="/user/${account.id}/edit"><i class="fa fa-edit"></i></a>
                                        <a href="javascript:;" rel="${account.id}" class="del"><i class="fa fa-trash"></i></a>
                                    </td>
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
            "&role="+encodeURIComponent('${param.role}')+
            "&pageNo={{number}}"
        });
        $(".del").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除该用户吗？",function (index) {
                layer.close(index);
                $.ajax({
                    url:'/user/'+id+'/del',
                    type:'get',
                    success:function (result) {
                        if(result.state == 'success') {
                            window.history.go(0);
                        } else {
                            layer.msg(result.message);
                        }
                    },
                    error:function () {
                        layer.msg("服务器忙");
                    }
                });
            })
        });

    })
</script>
</body>
</html>

