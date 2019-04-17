<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Gato Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href="/static/css/bootstrap.css" rel='stylesheet' type='text/css' />
    <!-- Custom Theme files -->
    <link href="/static/css/style.css" rel='stylesheet' type='text/css' />
    <script src="/static/js/jquery.min.js"> </script>
    <!--webfonts-->
    <link href='http://fonts.useso.com/css?family=Raleway:400,100,200,300,500,600,700|Cinzel+Decorative:400,700' rel='stylesheet' type='text/css'>
    <!--//webfonts-->
</head>
<body>
<!--start-home-->
<div id="home" class="header">
    <div class="header-bottom">
        <div class="container">
            <div class="logo">
                <a href="index.html"><h1>Pe<span>t</span></h1></a>
            </div>
            <span class="menu"></span>
            <div class="top-menu">
                <ul>
                    <nav>
                        <li><a class="active" href="index.html">首页</a></li>
                        <li><a href="portfolio.html">宠物管理</a></li>
                        <li><a href="ly.html">领养申请</a></li>
                        <li><a href="ifo.html">个人信息</a></li>
                        <li><a href="about.html">关于我们</a></li>
                        <li><a href="contact.html">留言</a></li>
                    </nav>
                </ul>
            </div>

            <!-- script for menu -->
            <script>
                $( "span.menu" ).click(function() {
                    $( ".top-menu" ).slideToggle( "slow", function() {
                        // Animation complete.
                    });
                });
            </script>
            <!-- script for menu -->
            <div class="clearfix"></div>
        </div>
    </div>

    <div class="banner">
        <div class="container">
            <ul id="flexiselDemo3">
                <li>
                    <div class="biseller-column">
                        <a class="lightbox" href="#goofy">
                            <img src="/static/images/s1.jpg"/>
                        </a>
                        <div class="lightbox-target" id="goofy">
                            <img src="/static/images/s1.jpg"/>
                            <a class="lightbox-close" href="#"> </a>

                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="biseller-column">
                        <a class="lightbox" href="#goofy">
                            <img src="/static/images/s4.jpg"/>
                        </a>
                        <div class="lightbox-target" id="goofy">
                            <img src="/static/images/s4.jpg"/>
                            <a class="lightbox-close" href="#"> </a>

                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="biseller-column">
                        <a class="lightbox" href="#goofy">
                            <img src="/static/images/s3.jpg"/>
                        </a>
                        <div class="lightbox-target" id="goofy">
                            <img src="/static/images/s3.jpg"/>
                            <a class="lightbox-close" href="#"> </a>

                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="biseller-column">
                        <a class="lightbox" href="#goofy">
                            <img src="/static/images/s1.jpg"/>
                        </a>
                        <div class="lightbox-target" id="goofy">
                            <img src="/static/images/s1.jpg"/>
                            <a class="lightbox-close" href="#"> </a>

                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="biseller-column">
                        <a class="lightbox" href="#goofy">
                            <img src="/static/images/s4.jpg"/>
                        </a>
                        <div class="lightbox-target" id="goofy">
                            <img src="/static/images/s4.jpg"/>
                            <a class="lightbox-close" href="#"> </a>

                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="biseller-column">
                        <a class="lightbox" href="#goofy">
                            <img src="/static/images/s1.jpg"/>
                        </a>
                        <div class="lightbox-target" id="goofy">
                            <img src="/static/images/s1.jpg"/>
                            <a class="lightbox-close" href="#"> </a>

                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="biseller-column">
                        <a class="lightbox" href="#goofy">
                            <img src="/static/images/s4.jpg"/>
                        </a>
                        <div class="lightbox-target" id="goofy">
                            <img src="/static/images/s4.jpg"/>
                            <a class="lightbox-close" href="#"> </a>

                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
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
        <script type="text/javascript" src="/static/js/jquery.flexisel.js"></script>
    </div>
</div>
<!--about-->
<div class="about">
    <div class="container">
        <div class="about-head">
            <h3>关于</h3>
            <p>宠物介绍xxxxx</p>
        </div>
        <div class="cat-info-section">
            <div class="cat-info-left">
                <img src="/static/images/cat.jpg" alt="" />
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
<!--//about-->
<!--gallery-->
<div class="gallery">
    <div class="container">
        <div class="gallery-head">
            <h3>宠物的领养</h3>
            <p>XXXX</p>
        </div>
        <div class="portfolio-bottom">
            <div class="gallery-one">
                <div class="col-md-3 gallery-left">
                    <a href="/static/images/g1.jpg" class=" mask b-link-stripe b-animate-go   swipebox"  title="Image Title">
                        <img src="/static/images/g1.jpg" alt="" class="img-responsive zoom-img"/>
                    </a>
                </div>
                <div class="col-md-3 gallery-left">
                    <a href="/static/images/g2.jpg" class=" mask b-link-stripe b-animate-go   swipebox"  title="Image Title">
                        <img src="/static/images/g2.jpg" alt="" class="img-responsive zoom-img"/>
                    </a>
                </div>
                <div class="col-md-3 gallery-left">
                    <a href="/static/images/g4.jpg" class=" mask b-link-stripe b-animate-go   swipebox"  title="Image Title">
                        <img src="images/g4.jpg" alt="" class="img-responsive zoom-img"/>
                    </a>
                </div>
                <div class="col-md-3 gallery-left">
                    <a href="images/g3.jpg" class=" mask b-link-stripe b-animate-go   swipebox"  title="Image Title">
                        <img src="/static/images/g3.jpg" alt="" class="img-responsive zoom-img"/>
                    </a>
                </div>
                <div class="clearfix"> </div>
            </div>
        </div>
    </div>
</div>
<!--swipebox -->
<link rel="stylesheet" href="/static/css/swipebox.css">
<script src="/static/js/jquery.swipebox.min.js"></script>
<script type="text/javascript">
    jQuery(function($) {
        $(".swipebox").swipebox();
    });
</script>

<div class="c-family">
    <div class="container">
        <div class="family-top">
            <h3>宠物之家 !</h3>
            <a class="read two" href="#">查看更多</a>
        </div>
    </div>
</div>
<!---- footer --->

<div class="copy">
    <p>Copyright &copy; 2019. All rights reserved.</p>
</div>
<!--start-smoth-scrolling-->
<script type="text/javascript">
    jQuery(document).ready(function($) {
        $(".scroll").click(function(event){
            event.preventDefault();
            $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
        });
    });
</script>
<!--start-smoth-scrolling-->
<script type="text/javascript">
    $(document).ready(function() {
        var defaults = {
              containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
         };

        $().UItoTop({ easingType: 'easeOutQuart' });

    });
</script>
<a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
</body>
</html>
