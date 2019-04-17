<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
