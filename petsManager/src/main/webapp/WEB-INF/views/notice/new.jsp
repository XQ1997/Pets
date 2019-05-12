<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>流浪宠物救助系统|新增公告</title>
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
    <jsp:param name="menu" value="home"/>
  </jsp:include>

  <!-- 右侧内容部分 -->
  <div class="content-wrapper">
    <section class="content">
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">新增公告</h3>
          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        <div class="box-body">
          <form method="post" id="saveword" class="form-horizontal">
            <div class="form-group">
              <label class="col-md-3 control-label">公告主题:</label>
              <div class="col-md-9">
                <input type="text" name="title" size=80 autofocus/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label">公告内容:</label>
              <div class="col-md-9">
                <textarea name="content" rows=7 cols=80></textarea>
              </div>
            </div>
          </form>
        </div>
        <div class="box-footer">
          <button class="btn btn-primary center-block" id="saveBtn">保存</button>
        </div>
      </div>
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