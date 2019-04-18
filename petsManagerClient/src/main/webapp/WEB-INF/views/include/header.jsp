<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header-bottom">
    <div class="container">
        <div class="logo">
            <a href="/home"><h1>Pe<span>t</span></h1></a>
        </div>
        <span class="menu"></span>
        <div class="top-menu">
            <ul>
                <nav>
                    <li><a class="${param.menu == 'home' ? 'active' : ''}" href="/home">首页</a></li>
                    <li><a class="${param.menu == 'pet' ? 'active' : ''}" href="/pet">宠物管理</a></li>
                    <li><a class="${param.menu == 'cliam' ? 'active' : ''}" href="/cliam">领养申请</a></li>
                    <li><a class="${param.menu == 'user' ? 'active' : ''}" href="/user">个人信息</a></li>
                    <li><a class="${param.menu == 'about' ? 'active' : ''}" href="/about">关于我们</a></li>
                    <li><a class="${param.menu == 'words' ? 'active' : ''}" href="/words">留言</a></li>
                </nav>
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
