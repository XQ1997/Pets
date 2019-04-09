<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>流浪宠物救助系统|留言板</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="../include/css.jsp"%>
  <style>
    .box1{
      width: 400px;
      height: 100%;
    }
  </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
  <%@ include file="../include/header.jsp"%>
  <jsp:include page="../include/sider.jsp">
    <jsp:param name="menu" value="words"/>
  </jsp:include>

  <!-- 右侧内容部分 -->
  <div class="content-wrapper">
    <section class="content">
      <div class="box">
        <c:if test="${not empty message}">
          <div class="alert alert-success text-center">${message}</div>
        </c:if>
        <div class="box-header with-border">
          <h3 class="box-title">欢迎访问留言板</h3>
          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        <div class="box-body">
          <form method="post" id="saveword" class="form-horizontal center-block">
            <div class="form-group">
              <label class="col-md-4 control-label">主题:</label>
              <div class="col-md-8">
                <input type="text" name="title" size=30 autofocus/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-4 control-label">留言:</label>
              <div class="col-md-8">
                <textarea name="content" rows=7 cols=30></textarea>
              </div>
            </div>
          </form>
        </div>
        <div class="box-footer">
          <button class="btn btn-primary center-block" id="saveBtn">留言</button>
        </div>
      </div>
      <c:if test="${not empty message}">
        <div class="box1 center-block">
          <table class="table">
            <tbody>
            <tr>
              <td class="text-muted text-center"><i>留言人:</i></td>
              <td><shiro:principal property="username"/></td>
            <tr>
              <td class="text-muted text-center"><i>留言主题:</i></td>
              <td>${words.title}</td>
            </tr>
            <tr>
              <td class="text-muted text-center"><i>留言内容:</i></td>
              <td><textarea disabled>${words.content}</textarea></td>
            </tr>
            </tbody>
          </table>
        </div>
      </c:if>
    </section>
  </div>
  <%@ include file="../include/footer.jsp"%>
</div>
<%@ include file="../include/js.jsp"%>
<script>
    $(function(){
        $("#saveBtn").click(function(){
            $("#saveword").submit();
        });
    })
</script>
</body>
</html>