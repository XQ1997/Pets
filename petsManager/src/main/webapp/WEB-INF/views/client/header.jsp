<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header-bottom">
    <div class="container">
        <div class="logo">
            <a href="/index"><h1>Pe<span>t</span></h1></a>
        </div>
        <span class="menu"></span>
        <div class="top-menu">
            <ul>
                <nav>
                    <li><a class="${param.menu == 'home' ? 'active' : ''}" href="/index">首页</a></li>
                    <li><a class="${param.menu == 'pet' ? 'active' : ''}" href="/client/pet">宠物管理</a></li>
                    <li><a class="${param.menu == 'publish' ? 'active' : ''}" href="/client/publish">宠物发布</a></li>
                    <li><a class="${param.menu == 'cliam' ? 'active' : ''}" href="/client/cliam">领养申请</a></li>
                    <li><a class="${param.menu == 'user' ? 'active' : ''}" href="/client/user">个人信息</a></li>
                    <li><a class="${param.menu == 'about' ? 'active' : ''}" href="/client/about">关于我们</a></li>
                    <li><a class="${param.menu == 'words' ? 'active' : ''}" href="/client/words">留言</a></li>
                    <li><a href="/logout" class="pull-right">切换账号</a></li>
                </nav>
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
