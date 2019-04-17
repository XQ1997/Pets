<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Gato Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <%@ include file="include/css.jsp"%>
</head>
<body>
<div id="home" class="header">
    <%@ include file="include/header.jsp"%>
    <div class="banner">
        <div class="container">
            <ul id="flexiselDemo3">
                <li>
                    <div class="biseller-column">
                        <a class="lightbox" href="#goofy">
                            <img src="/static/plugins/pet/images/s1.jpg"/>
                        </a>
                        <div class="lightbox-target" id="goofy">
                            <img src="/static/plugins/pet/images/s1.jpg"/>
                            <a class="lightbox-close" href="#"> </a>
                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
<!--about-->
<div class="about">
    <div class="container">
        <div class="about-head">
            <h3>关于</h3>
            <p>宠物介绍</p>
        </div>
        <div class="cat-info-section">
            <div class="cat-info-left">
                <img src="/static/plugins/pet/images/cat.jpg" alt="" />
            </div>
            <div class="cat-info-middle">
                <h4>Information</h4>
            </div>
            <div class="cat-info-right">
                <h3>宠物的名字</h3>
                <p>介绍XXXX</p>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<%--宠物列表--%>
<div class="gallery">
    <div class="container">
        <div class="gallery-head">
            <h3>宠物的领养</h3>
            <p>XXXX</p>
        </div>
        <div class="portfolio-bottom">
            <div class="gallery-one">
                <div class="col-md-3 gallery-left">
                    <a href="/static/plugins/pet/images/g1.jpg" class=" mask b-link-stripe b-animate-go   swipebox"  title="Image Title">
                        <img src="/static/plugins/pet/images/g1.jpg" alt="" class="img-responsive zoom-img"/>
                    </a>
                </div>
                <div class="clearfix"> </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="include/footer.jsp"%>
<%@ include file="include/js.jsp"%>
<script type="text/javascript" src="/static/plugins/pet/js/jquery.flexisel.js"></script>
<script type="text/javascript">
    $(window).load(function() {
        $("#flexiselDemo3").flexisel({
            visibleItems:3,
            animationSpeed: 1000,
            autoPlay: true,
            autoPlaySpeed: 3000,
            pauseOnHover: true,
            enableResponsiveBreakpoints: true,
            responsiveBreakpoints: {
                portrait: {
                    changePoint:480,
                    visibleItems:3
                },
                landscape: {
                    changePoint:640,
                    visibleItems:3
                },
                tablet: {
                    changePoint:768,
                    visibleItems:3
                }
            }
        });
    });
</script>
</body>
</html>
