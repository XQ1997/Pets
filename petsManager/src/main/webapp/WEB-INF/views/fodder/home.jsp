<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 宠物饲料管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="fodder"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <c:if test="${not empty message}">
                <div class="alert alert-success text-center">${message}</div>
            </c:if>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">宠物饲料信息</h3>
                    <div class="box-tools pull-right">
                        <a href="/fodder/new" class="btn btn-success btn-sm pull-right"><i class="fa fa-plus"></i>新建饲料库存数量</a>
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
                                 <th class="text-center">宠物类别</th>
                                 <th class="text-center">使用数量</th>
                                 <th class="text-center">支出/花费</th>
                                 <th class="text-center">单袋饲料单价</th>
                                 <th class="text-center">#</th>
                             </tr>
                         </thead>
                         <tbody>
                            <c:forEach items="${fodderList}" var="fodder">
                                <tr>
                                    <td class="text-center"><strong>${fodder.type}</strong></td>
                                    <td class="text-center"><strong>${fodder.number}</strong></td>
                                    <td class="text-center"><strong>${fodder.totalnum}</strong></td>
                                    <td class="text-center"><strong>${fodder.price}</strong></td>
                                    <td class="text-center">
                                        <a href="javascript:;" rel="${fodder.id}" class="add"><i class="glyphicon glyphicon-plus"></i></a>
                                        <a href="javascript:;" rel="${fodder.id}" class="reduce"><i class="glyphicon glyphicon-minus"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                         </tbody>
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
        $(".add").click(function () {
            var id = $(this).attr("rel");
            layer.prompt("添加库存数量",function (val, index) {
                layer.close(index);
                $.ajax({
                    url: '/fodder/' + id + '/add?val='+ val,
                    type: 'get',
                    success: function (result) {
                        if (result.state == 'success') {
                            window.history.go(0);
                        } else {
                            layer.msg(result.message);
                        }
                    },
                    error: function () {
                        layer.msg("服务器忙");
                    }
                });
            })
        });
        $(".reduce").click(function () {
            var id = $(this).attr("rel");
            layer.prompt("减少饲料库存",function(val, index) {
                layer.close(index);
                $.ajax({
                    url:'/fodder/'+id+'/reduce?val='+val,
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

