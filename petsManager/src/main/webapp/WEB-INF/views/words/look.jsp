<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>流浪宠物救助系统| 留言板 | 留言详情</title>
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
      <div class="box box1 center-block">
        <div class="box-header with-border">
          <h3 class="box-title">查看留言</h3>
          <div class="box-tools pull-right">
            <a href="/words" class="btn btn-success btn-sm pull-right"><i class="fa fa-backward"></i>返回</a>
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
              <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
              <i class="fa fa-times"></i></button>
          </div>
        </div>
        <div>
          <table class="table">
            <tbody>
              <tr>
                <td class="text-muted text-center"><i>${words.username}留言标题为：--${words.title}的内容为:</i></td>
                <td><textarea disabled>${words.content}</textarea></td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="col-md-offset-2">
          <table class="table">
            <tbody>
            <c:forEach items="${replyList}" var="reply">
              <tr>
                <td class="text-muted text-center"><i>${reply.replyname}回复${words.username}的内容:</i></td>
                <td><textarea disabled>${reply.content}</textarea></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </section>
  </div>
  <%@ include file="../include/footer.jsp"%>
</div>
<%@ include file="../include/js.jsp"%>
</body>
</html>