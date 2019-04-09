<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 左侧菜单栏 -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- 菜单 -->
      <ul class="sidebar-menu">
      	<!-- 首页 -->
        <li class="treeview ${param.param == 'home' ? 'active' : ''}">
          <a href="/home">
            <i class="fa fa-home"></i> <span>公告</span>
          </a>
        </li>
        <li class="header">系统功能</li> 
        <li class="${param.menu == 'pet' ? 'active' : ''}"><a href="/pet"><i class="fa fa-calendar-check-o"></i> <span>流浪宠物管理</span></a></li>
		<c:if test="${role == '管理员'}">
	        <li class="${param.menu == 'user' ? 'active' : ''}"><a href="/user"><i class="fa fa-calendar-plus-o"></i> <span>用户管理</span></a></li>
	        <li class="${param.menu == 'cliam' ? 'active' : ''}"><a href="/cliam"><i class="fa fa-circle-o"></i> <span>认领申请管理</span></a></li>
	        <li class="${param.menu == 'auditing' ? 'active' : ''}"><a href="/auditing"><i class="fa fa-circle-o"></i> <span>宠物认领审核</span></a></li>
		</c:if>
		<c:if test="${role == '领养人'}">
	        <li class="${param.menu == 'regist' ? 'active' : ''}"><a href="/user/regist"><i class="fa fa-calendar-plus-o"></i> <span>用户注册</span></a></li>
	        <li class="${param.menu == 'cliam' ? 'active' : ''}"><a href="/user/cliam"><i class="fa fa-calendar-check-o"></i> <span>宠物认领申请</span></a></li>
	        <li class="${param.menu == 'state' ? 'active' : ''}"><a href="/user/state"><i class="fa fa-circle-o"></i> <span>查看申请结果</span></a></li>
	        <li class="${param.menu == 'words' ? 'active' : ''}"><a href="/words"><i class="fa fa-circle-o"></i> <span>留言板</span></a></li>
		</c:if>
      </ul>
    </section>
  </aside>