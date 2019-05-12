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
        <jsp:param name="menu" value="pet"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <%--搜索表单--%>
            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" name="petname" placeholder="流浪宠物名称" class="form-control" value="${param.petname}">
                        <select name="type" class="form-control">
                            <option value="哺乳动物类" ${param.type == "哺乳动物类" ? 'selected' : ''}>哺乳动物类</option>
                            <option value="鸟类"${param.type == "鸟类" ? 'selected' : ''}>鸟类</option>
                            <option value="爬行动物类" ${param.type == "爬行动物类" ? 'selected' : ''}>爬行动物类</option>
                            <option value="两栖动物类" ${param.type == "两栖动物类" ? 'selected' : ''}>两栖动物类</option>
                            <option value="鱼类" ${param.type == "鱼类" ? 'selected' : ''}>鱼类</option>
                            <option value="昆虫动物类"${param.type == "昆虫动物类" ? 'selected' : ''}>昆虫动物类</option>
                            <option value="植物宠物类" ${param.type == "植物宠物类" ? 'selected' : ''}>植物宠物类</option>
                            <option value="茶宠宠物" ${param.type == "茶宠宠物" ? 'selected' : ''}>茶宠宠物</option>
                            <option value="另类宠物" ${param.type == "另类宠物" ? 'selected' : ''}>另类宠物</option>
                            <option value="其他" ${param.type == "其他" ? 'selected' : ''}>其他</option>
                        </select>
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
                    <h3 class="box-title">宠物审核信息</h3>
                    <div class="box-tools pull-right">
                        <a href="/pet/new" class="btn btn-success btn-sm pull-right"><i class="fa fa-plus"></i>新增流浪宠物</a>
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
                                 <th class="text-center">宠物编号</th>
                                 <th class="text-center">类别</th>
                                 <th class="text-center">当前状态</th>
                                 <th class="text-center">年龄</th>
                                 <th class="text-center">#</th>
                             </tr>
                         </thead>
                         <tbody>
                            <c:forEach items="${pageInfo.list}" var="pets">
                                <tr>
                                    <td class="text-center"><strong><a href="/pet/${pets.id}">${pets.petname}</a></strong></td>
                                    <td class="text-center"><strong>${pets.num}</strong></td>
                                    <td class="text-center"><strong>${pets.type}</strong></td>
                                    <td class="text-center"><strong>${pets.state}</strong></td>
                                    <td class="text-center"><strong>${pets.age}</strong></td>
                                    <td class="text-center">
                                        <a href="/pet/${pets.id}/edit"><i class="fa fa-edit"></i></a>
                                        <a href="javascript:;" rel="${pets.id}" class="del"><i class="fa fa-trash"></i></a>
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
            "&type="+encodeURIComponent('${param.type}')+
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

