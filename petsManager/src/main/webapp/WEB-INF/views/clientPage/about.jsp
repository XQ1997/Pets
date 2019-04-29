<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>关于我们</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Gato Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <%@ include file="../client/css.jsp"%>
    <style>
        p{
            line-height: 2em;
            text-indent: 2em;
            font-style: normal;
        }
        li{
            text-indent: 2em;
            font-style: normal;
        }
    </style>
</head>
<body>
<div id="home" class="header">
    <jsp:include page="../client/header.jsp">
        <jsp:param name="menu" value="about"/>
    </jsp:include>
    <div class="banner two">
        <div class="container">
            <div class="pag-nav">
                <ul class="p-list">
                    <li><a href="/index">首页</a></li> &nbsp;&nbsp;/&nbsp;
                    <li class="act">&nbsp;关于我们</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<div class="gallery services">
    <div class="container">
        <div class="port-head">
            <h3>关于我们</h3>
        </div>
        <div class="portfolio-bottom">
            <div class="content">
                <h2><img src="/static/dist/img/picture/adoption.png"  width="333" height="115" /></h2>
                <div class="contentitem">
                    <div class="row">
                        <div class="col-md-6">
                            <h3 style="padding-top:30px"><img src="/static/dist/img/picture/cat.png" width="41" height="28" class="animalicon"/>领养资格</h3>
                            <p>领养前，领养家庭必须认真考虑清楚是否有能力和条件领养动物，和负责任一生。</p>
                            <ul>
                                <li>有固定住所和稳定收入者，能为被领养动物提供安全、稳定的生存的环境。</li>
                                <li>家人愿意接受被领养动物，并一同照顾。</li>
                                <li>定期体内外驱虫，每年按时打疫苗。</li>
                                <li>添加领养回访电话，能配合我们对领养动物的定期回访工作。</li>
                                <li>同意给被领养的动物采取必要的节育措施，避免其过度繁殖。</li>
                                <li>谢绝未成年人、学生和猫狗贩。</li>
                            </ul>
                        </div>
                        <div class="col-md-6">
                            <img src="/static/dist/img/picture/a1.jpg" class="floatrightimage" style="border:none;box-shadow:none"/>
                        </div>
                    </div>
                </div>
                <div class="contentitem">
                    <div class="row">
                        <div class="col-md-6">
                            <img src="/static/dist/img/picture/a2.jpg" class="floatleftimage"/>
                        </div>
                        <div class="col-md-6">
                            <h3><img src="/static/dist/img/picture/dog.png" width="41" height="28" class="animalicon"/>领养步骤</h3>
                            <p>我们的领养是免费的！</p>
                            <ol>
                                <li>检查自己是否符合领养资格，与家人沟通，做好准备一同照顾被领养动物。</li>
                                <li>下载并填写：<span class="textcolor1"><a href="http://pan.baidu.com/s/13SFXO" target="_blank">领养申请表</a></span></li>
                                <li>联系协会（电话：<span class="textcolor1">027-12345679871</span>）预约办理领养手续，或关注协会领养日活动信息</li>
                            </ol>
                        </div>
                    </div>
                </div>
                <div class="contentitem">
                    <h3><img src="/static/dist/img/picture/dog.png" width="41" height="28" class="animalicon"/>领养后续</h3>
                    <ul>
                        <li>请做到科学喂养、支持绝育、定期疫苗和驱虫，生病及时就医，不散养不笼养，出门牵绳和清理大小便。</li>
                        <li>希望你能像家人一样看待被领养的小动物，一辈子不离不弃，不要因工作、怀孕和其他原因放弃领养。当然，在你是在无能力继续养下去时，请将它们送回协会，决不允许没有经过我们的同意私自将领养的动物转让他人。</li>
                        <li>协会会对您进行定期回访工作，领养后请主人配合我们的工作，添加领养电话(1298299191)</li>
                    </ul>
                </div>
            </div>
            <br/>
            <br/>
            <div class="content">
                <div class="contentitem">
                    <div class="row">
                        <div class="col-md-6">
                            <h3 style="padding-top:30px"><img src="/static/dist/img/picture/dog_1.png" width="41" height="28" class="animalicon"/>关于我们</h3>
                            <p class="text-danger">流浪宠物救助系统 成立于<span class="textcolor1">2019年3月28号</span>，于<span class="textcolor1">2019年5月</span>是一家纯公益性质的流浪宠物救助保护青年自发组成的组织。</p>
                            <p style="padding-top:20px" class="text-success">历年来，协会成员先后救助流浪动物达<span class="textcolor1">7000</span>余只(截止至2015年5月)，目前滞留基地的流浪动物有<span class="textcolor1">300</span>多只，在救助流浪动物的同时，我们也积极宣扬保护动物、科学喂养的知识。我们希望通过自己的实际行动倡导<span class="textcolor1">&quot;保护动物、关爱生命&quot;</span>的社会习惯，为社会文明进步做出贡献，同时也给流浪的猫猫狗狗一个温暖的家，结束它们的流浪生涯。</p>
                        </div>
                        <div class="col-md-6">
                            <img src="/static/dist/img/picture/dogss_1.jpg" class="floatrightimage" width="300" height="350" style="margin-top:30px;border:0px;box-shadow:none"/>
                        </div>
                    </div>
                </div>
                <div class="contentitem" style="position:relative;height:420px;">
                    <img src="/static/dist/img/picture/us02_1.jpg" width="250" height="166" class="bordernshadowimage" style="position:absolute;top:20px;left:190px" />
                    <img src="/static/dist/img/picture/hanadese_1.png" width="164" height="117" style="position:absolute;top:160px;left:80px" />
                    <img src="/static/dist/img/picture/bling_1.png" width="49" height="49" style="position:absolute;top:40px;left:490px" />
                    <img src="/static/dist/img/picture/us01_1.jpg" width="250" height="187" class="bordernshadowimage" style="position:absolute;top:220px;left:270px" />
                    <img src="/static/dist/img/picture/us03_1.jpg" width="250" height="166" class="bordernshadowimage" style="position:absolute;top:100px;left:490px" />
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../client/footer.jsp"%>
<%@ include file="../client/js.jsp"%>
</body>
</html>