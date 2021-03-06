<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 顶部导航栏部分 -->
  <header class="main-header">
    <!-- Logo -->
    <a href="index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>流浪宠物救助</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>流浪宠物</b>救助</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">  
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="/static/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs"><shiro:principal property="username"/></span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                <p>
                  <shiro:principal property="username"/>
                </p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="/repassword" class="btn btn-default btn-flat">个人设置</a>
                </div>
                <div class="pull-right">
                  <a href="/logout" class="btn btn-default btn-flat">安全退出</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>