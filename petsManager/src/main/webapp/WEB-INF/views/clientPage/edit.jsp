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
        </div>
        <br/>
        <div class="portfolio-bottom">
            <form method="post" class="form-horizontal" id="addCliam">
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">用户姓名:</label>
                    <div class="col-md-8">
                        <input type="text" name="username" class="form-control" value="${account.username}" autofocus/>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">联系电话:</label>
                    <div class="col-md-8">
                        <input type="text" name="mobile" class="form-control" value="${account.mobile}"/>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">年龄:</label>
                    <div class="col-md-8">
                        <input type="text" name="age" class="form-control" value="${account.age}"/>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">角色</label>
                    <div class="col-md-8">
                        <select name="role" class="form-control">
                            <option value="管理员" ${account.role == "管理员" ? 'selected' : ''}>管理员</option>
                            <option value="领养人" ${account.role == "领养人" ? 'selected' : ''}>领养人</option>
                        </select>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">住址:</label>
                    <div class="col-md-8">
                        <input type="text" name="address" class="form-control" value="${account.address}"/>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">从事工作:</label>
                    <div class="col-md-8">
                        <input type="text" name="job" class="form-control" value="${account.job}"/>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label class="col-md-3 control-label">身份证号:</label>
                    <div class="col-md-8">
                        <input type="text" name="cardnum" class="form-control" value="${account.cardnum}"/>
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
<script>
    $(function(){
        $("#saveBtn").click(function(){
            $("#addCliam").submit();
        });
    })
</script>
</body>
</html>