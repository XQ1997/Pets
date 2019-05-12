<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 客户托管宠物信息管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="pet"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <%--搜索表单--%>
            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" name="petname" placeholder="宠物名称" class="form-control" value="${param.petname}">
                        <select name="state" class="form-control">
                            <option value="">全部</option>
                            <option value="未送养" ${param.state == "未送养" ? 'selected' : ''}>未送养</option>
                            <option value="未托管" ${param.state == "未托管" ? 'selected' : ''}>未托管</option>
                            <option value="已送养" ${param.state == "已送养" ? 'selected' : ''}>已送养</option>
                            <option value="已托管" ${param.state == "已托管" ? 'selected' : ''}>已托管</option>
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
                    <h3 class="box-title">宠物信息</h3>
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
                                 <th class="text-center">宠物学名</th>
                                 <th class="text-center">宠物年龄</th>
                                 <th class="text-center">发布类型</th>
                                 <th class="text-center">当前状态</th>
                                 <th class="text-center">联系电话</th>
                                 <th class="text-center">发布人</th>
                                 <th class="text-center">#</th>
                             </tr>
                         </thead>
                         <tbody>
                            <c:forEach items="${pageInfo.list}" var="userpet">
                                <tr>
                                    <td class="text-center"><strong><a href="/userpet/${userpet.id}">${userpet.petname}</a></strong></td>
                                    <td class="text-center"><strong>${userpet.petLanguageName}</strong></td>
                                    <td class="text-center"><strong>${userpet.age}</strong></td>
                                    <td class="text-center"><strong>${userpet.sendtype}</strong></td>
                                    <td class="text-center"><strong>${userpet.state}</strong></td>
                                    <td class="text-center"><strong>${userpet.mobile}</strong></td>
                                    <td class="text-center"><strong>${userpet.username}</strong></td>
                                    <td class="text-center">
                                        <a href="javascript:;" rel="${userpet.id}" class="del"><i class="fa fa-trash"></i></a>
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
        $('#pagination-demo').twbsPagination({
            totalPages: ${pageInfo.pages},
            visiblePage:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?petname="+encodeURIComponent('${param.petname}')+
            "&state="+encodeURIComponent('${param.state}')+
            "&pageNo={{number}}"
        });
        $(".del").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除该宠物吗？",function (index) {
                layer.close(index);
                $.ajax({
                    url:'/userpet/'+id+'/del',
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

