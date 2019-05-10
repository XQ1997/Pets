<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>流浪宠物救助系统 | 用户注册</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="../include/css.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../include/header.jsp"/>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="user"/>
    </jsp:include>
    <div class="content-wrapper"> 
        <section class="content"> 
            <c:if test="${not empty message}">
                <div class="alert alert-success text-center">${message}</div>
            </c:if>
            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">注册信息</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-3 control-label">用户姓名:</label>
                            <div class="col-md-8">
                                <input type="text" name="username" class="form-control" autofocus required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">登录密码:</label>
                            <div class="col-md-8">
                                <input type="password" name="password" class="form-control" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">联系电话:</label>
                            <div class="col-md-8">
                                <input type="text" name="mobile" class="form-control" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">年龄:</label>
                            <div class="col-md-8">
                                <input type="text" name="age" class="form-control" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">性别</label>
                            <div class="col-md-8">
                                <input type="radio" name="sex" value="男" checked>男
                                <input type="radio" name="sex" value="女">女
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">角色</label>
                            <div class="col-md-8">
                                <select name="role" class="form-control">
                                    <option value="管理员" selected>管理员</option>
                                    <option value="会员">会员</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">住址:</label>
                            <div class="col-md-8">
                                <input type="text" name="address" class="form-control" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">从事工作:</label>
                            <div class="col-md-8">
                                <input type="text" name="job" class="form-control" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">身份证号:</label>
                            <div class="col-md-8">
                                <input type="text" name="cardnum" class="form-control" required/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary pull-right" id="saveBtn">注册</button>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../include/footer.jsp"></jsp:include>
</div>
<jsp:include page="../include/js.jsp"></jsp:include>
<script src="/static/plugins/jquery.validate.min.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });
        $("#saveForm").validate({
            errorClass : 'text-danger',
            errorElement : 'span',
            rules : {
                mobile :{
                    "required" : true,
                    "minlength": 11
                },
                username :{
                    "required" : true,
                    "minlength" : 1
                },
                password : {
                    "required" : true,
                    "rangelength":[5,17]
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
                },
                cardnum : {
                    "required" : true,
                    "minlength": 18
                }
            },
            messages :{
                mobile :{
                    "required" : "请输入联系电话",
                    "minlength": "联系电话长度不得少于11位"
                },
                username :{
                    "required" : "请输入用户名",
                    "minlength" : "用户名至少一位"
                },
                password : {
                    "required" : "请输入密码",
                    "rangelength":"密码长度为5-17位"
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
                },
                cardnum : {
                    "required" : "请输入身份证号",
                    "minlength": "身份证号长度不得少于18位"
                }
            }
        });
    })
</script>
</body>
</html>

