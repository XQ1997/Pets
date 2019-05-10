<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>修改个人信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Gato Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <%@ include file="../client/css.jsp"%>
</head>
<body>
<div id="home" class="header">
    <jsp:include page="../client/header.jsp">
        <jsp:param name="menu" value="user"/>
    </jsp:include>
    <div class="banner two">
        <div class="container">
            <div class="pag-nav">
                <ul class="p-list">
                    <li><a href="/index">首页</a></li> &nbsp;&nbsp;/&nbsp;
                    <li class="act">&nbsp;个人信息</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>个人信息</h3>
            <a href="/client/user" class="btn btn-sm btn-success pull-right">返回</a>
        </div>
        <br/>
        <div class="portfolio-bottom">
            <form method="post" class="form-horizontal" id="addCliam">
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">用户姓名:</label>
                    <div class="col-md-8">
                        <input type="text" name="username" class="form-control" value="${account.username}" autofocus required/>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">年龄:</label>
                    <div class="col-md-8">
                        <input type="text" name="age" class="form-control" value="${account.age}" required/>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">住址:</label>
                    <div class="col-md-8">
                        <input type="text" name="address" class="form-control" value="${account.address}" required/>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">从事工作:</label>
                    <div class="col-md-8">
                        <input type="text" name="job" class="form-control" value="${account.job}" required/>
                    </div>
                </div>
            </form>
            <div>
                <button class="btn pull-right btn-success" id="saveBtn"><i class="fa fa-save"></i> 修改</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="../client/footer.jsp"%>
<%@ include file="../client/js.jsp"%>
<script src="/static/plugins/jquery.validate.min.js"></script>
<script>
    $(function(){
        $("#saveBtn").click(function(){
            $("#addCliam").submit();
        });
        $("#addCliam").validate({
            errorClass : 'text-danger',
            errorElement : 'span',
            rules : {
                username :{
                    "required" : true,
                    "minlength" : 1
                },
                age :{
                    "required" : true,
                    "range":[18,70]
                },
                address : {
                    "required" : true,
                    "minlength" : 5
                },
                job :{
                    "required" : true,
                    "minlength" : 1
                }
            },
            messages :{
                username :{
                    "required" : "请输入用户名",
                    "minlength" : "用户名至少一位"
                },
                age :{
                    "required" : "请输入年龄",
                    "range":"年龄为大于18岁，不超过70岁"
                },
                address : {
                    "required" : "请输入住址",
                    "minlength" : "住址长度至少为5位"
                },
                job :{
                    "required" : "请输入工作",
                    "minlength" : "工作长度至少为1位"
                }
            }
        });
    })
</script>
</body>
</html>