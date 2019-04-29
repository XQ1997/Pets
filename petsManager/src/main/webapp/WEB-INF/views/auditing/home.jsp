<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 流浪宠物管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="auditing"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <%--搜索表单--%>
            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" name="petname" placeholder="流浪宠物名称" class="form-control" value="${param.petname}">
                        <input type="text" name="age" placeholder="宠物年龄" class="form-control" value="${param.age}">
                        <select name="state" class="form-control">
                            <option value="">全部</option>
                            <option value="未认领" ${param.state == "未认领" ? 'selected' : ''}>未认领</option>
                            <option value="已认领" ${param.state == "已认领" ? 'selected' : ''}>已认领</option>
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
                    <h3 class="box-title">流浪宠物信息</h3>
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
                                 <th class="text-center">宠物名称</th>
                                 <th class="text-center">当前状态</th>
                                 <th class="text-center">年龄</th>
                                 <th class="text-center">救助地点</th>
                                 <th class="text-center">描述</th>
                                 <th class="text-center">#</th>
                             </tr>
                         </thead>
                         <tbody>
                            <c:forEach items="${pageInfo.list}" var="pets">
                                <tr>
                                    <td class="text-center"><strong><a href="/pet/${pets.id}">${pets.petname}</a></strong></td>
                                    <td class="text-center">
                                        <span class="label ${pets.state == '未认领' ? 'label-danger' : 'label-success'}">${pets.state}</span>
                                    </td>
                                    <td class="text-center"><strong>${pets.age}</strong></td>
                                    <td class="text-center"><strong>${pets.place}</strong></td>
                                    <td class="text-center"><strong>${pets.content}</strong></td>
                                    <td class="text-center">
                                        <c:if test="${pets.state == '未认领'}">
                                            <a href="/auditing/${pets.id}" class="btn btn-sm btn-success">认领审核</a>
                                        </c:if>
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
            href:"?petname="+encodeURIComponent('${param.petname}')+
            "&age="+encodeURIComponent('${param.age}')+
            "&state="+encodeURIComponent('${param.state}')+
            "&pageNo={{number}}"
        });
        $(".del").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除该流浪宠物吗？",function (index) {
                layer.close(index);
                $.ajax({
                    url:'/pet/'+id+'/del',
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

