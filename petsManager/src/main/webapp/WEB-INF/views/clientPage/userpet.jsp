<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>宠物管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Gato Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <%@ include file="../client/css.jsp"%>
</head>
<body>
<div id="home" class="header">
    <jsp:include page="../client/header.jsp">
        <jsp:param name="menu" value="pet"/>
    </jsp:include>
    <div class="banner two">
        <div class="container">
            <div class="pag-nav">
                <ul class="p-list">
                    <li><a href="/index">首页</a></li> &nbsp;&nbsp;/&nbsp;
                    <li class="act">&nbsp;宠物信息</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>宠物详细信息</h3>
            <c:if test="${pets.mobile == mobile}">
                <a href="javascript:;" rel="${pets.id}" class="confirm btn btn-sm btn-success pull-right">确认领养</a>
            </c:if>
            <a href="/client/pet" class="btn btn-sm btn-info pull-right">返回</a>
        </div>
        <div class="portfolio-bottom">
            <table class="table">
                <tbody>
                <tr>
                    <td class="text-muted text-center"><i>宠物名称:</i></td>
                    <td>${pets.petname}</td>
                    <td colspan="2"></td>
                    <td class="text-muted text-center"><i>地点:</i></td>
                    <td>${pets.place}</td>
                </tr>
                <tr>
                    <td class="text-muted text-center"><i>宠物种类:</i></td>
                    <td>${pets.type}</td>
                    <td class="text-muted text-center"><i>发送类型:</i></td>
                    <td>${pets.sendtype}</td>
                    <td class="text-muted text-center"><i>联系电话:</i></td>
                    <td>${pets.mobile}</td>
                </tr>
                <tr>
                    <td class="text-muted text-center"><i>发布人:</i></td>
                    <td>${cliam.cliamName}</td>
                    <td colspan="2"></td>
                    <td class="text-muted text-center"><i>领养人:</i></td>
                    <td>${cliam.username}</td>
                </tr>
                <tr>
                    <td class="text-muted text-center"><i>年龄:</i></td>
                    <td>${pets.age}</td>
                    <td colspan="2"></td>
                    <td class="text-muted text-center"><i>创建时间:</i></td>
                    <td><fmt:formatDate value="${pets.createTime}"  pattern='yyyy年MM月dd日'/></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="port-head">
            <h3>注意事项</h3>
        </div>
        <div class="portfolio-bottom">
             <textarea  rows=7 cols=75 disabled>${pets.content}</textarea>
        </div>
    </div>
</div>
<%@ include file="../client/footer.jsp"%>
<%@ include file="../client/js.jsp"%>
<!-- layer -->
<script src="/static/dist/js/layer/layer.js"></script>
<script>
    $(function(){
        $(".confirm").click(function () {
            var id = $(this).attr("rel");
            layer.prompt({title: '输入领养/托管人姓名'},function (val, index) {
                layer.close(index);
                $.ajax({
                    url: '/client/pet/' + id + '/confirm?val='+ val,
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
    })
</script>
</body>
</html>
