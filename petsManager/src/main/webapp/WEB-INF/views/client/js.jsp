<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--swipebox -->
<link rel="stylesheet" href="/static/plugins/pet/css/swipebox.css">
<script src="/static/plugins/pet/js/jquery.swipebox.min.js"></script>
<script>
    $( "span.menu" ).click(function() {
        $( ".top-menu" ).slideToggle( "slow", function() {});
    });
</script>