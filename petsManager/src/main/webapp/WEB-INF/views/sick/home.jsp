<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 宠物就医管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="sick"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <c:if test="${not empty message}">
                <div class="alert alert-success text-center">${message}</div>
            </c:if>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">宠物就医信息</h3>
                    <div class="box-tools pull-right">
                        <a href="/sick" class="btn btn-success btn-sm pull-right"><i class="fa fa-plus"></i>新建饲料库存数量</a>
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
                                 <th class="text-center">宠物编号</th>
                                 <th class="text-center">宠物名称</th>
                                 <th class="text-center">宠物类别</th>
                                 <th class="text-center">宠物生病类型</th>
                                 <th class="text-center">就医时间</th>
                                 <th class="text-center">花费</th>
                                 <th class="text-center">#</th>
                             </tr>
                         </thead>
                         <tbody>
                            <c:forEach items="${pageInfo.list}" var="sick">
                                <tr>
                                    <td class="text-center"><strong>${sick.petnum}</strong></td>
                                    <td class="text-center"><strong>${sick.petname}</strong></td>
                                    <td class="text-center"><strong>${sick.petType}</strong></td>
                                    <td class="text-center"><strong>${sick.sickType}</strong></td>
                                    <td class="text-center"><strong><fmt:formatDate value="${sick.createTime}"  pattern='yyyy年MM月dd日'/></strong></td>
                                    <td class="text-center"><strong>${sick.money}</strong></td>
                                    <td class="text-center">
                                        <a href="javascript:;" rel="${sick.id}" class="del"><i class="fa fa-trash"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                         </tbody>
                         <c:if test="${pageInfo.pages > 1}">
                             <ul id="pagination-demo" class="pagination pull-right"></ul>
                         </c:if>
                     </table>
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
            href:"?pageNo={{number}}"
        });

        $(".del").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除该宠物就医记录吗？",function (index) {
                layer.close(index);
                $.ajax({
                    url:'/sick/'+id+'/del',
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

