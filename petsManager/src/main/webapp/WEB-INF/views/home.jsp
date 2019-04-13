<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>流浪宠物救助系统|首页</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="include/css.jsp"%>
  <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
	<%@ include file="include/header.jsp"%> 
  	<jsp:include page="include/sider.jsp">
  		<jsp:param value="home" name="param"/>
  	</jsp:include>
  <div class="content-wrapper">
    <section class="content">
      <%--搜索表单--%>
      <div class="box no-border">
        <div class="box-body">
          <form class="form-inline">
            <input type="text" name="title" placeholder="公告主题" class="form-control" value="${param.petname}">
            <input type="text" name="createTime" placeholder="公告发布时间" id="datepicker" class="form-control" value="${param.age}">
            <button class="btn btn-flat"><i class="fa fa-search"></i></button>
          </form>
        </div>
      </div>
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">公告展示</h3>
          <div class="box-tools pull-right">
            <a href="/notice/new" class="btn btn-success btn-sm pull-right"><i class="fa fa-plus"></i>新增公告</a>
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                    title="Collapse">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        <c:if test="${not empty message}">
          <div class="alert alert-success text-center">${message}</div>
        </c:if>
        <div class="box-body">
          <table class="table">
            <thead>
            <tr>
              <th class="text-center">公告主题</th>
              <th class="text-center">公告内容</th>
              <th class="text-center">发布时间</th>
              <th class="text-center">#</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="notice">
              <tr>
                <td class="text-center"><strong>${notice.title}</strong></td>
                <td class="text-center"><strong>${notice.content}</strong></td>
                <td class="text-center"><strong><fmt:formatDate value="${notice.createTime}"  pattern='yyyy年MM月dd日'/></strong></td>
                <td class="text-center">
                  <a href="/notice/${notice.id}/edit"><i class="fa fa-edit"></i></a>
                  <a href="javascript:;" rel="${notice.id}" class="del"><i class="fa fa-trash"></i></a>
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
  <%@ include file="include/footer.jsp"%> 
</div> 
<%@ include file="include/js.jsp"%>
<script src="/static/plugins/jquery.twbsPagination.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script>
    $(function(){
        $('#pagination-demo').twbsPagination({
            totalPages: ${pageInfo.pages},
            visiblePage:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?title="+encodeURIComponent('${param.title}')+
            "&createTime="+encodeURIComponent('${param.createTime}')+
            "&pageNo={{number}}"
        });
        //datatime显示时间弹框
        var picker = $('#datepicker').datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true,//为true时，选择完时间，弹框消失，反之，不消失
            todayHighlight: true,//常量显示
        });
        $(".del").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除该公告吗？",function (index) {
                layer.close(index);
                $.ajax({
                    url:'/notice/'+id+'/del',
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