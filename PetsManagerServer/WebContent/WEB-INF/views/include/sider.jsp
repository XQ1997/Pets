<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
        <li class="${param.menu == 'Continuation_fee' ? 'active' : ''}"><a href="/ticket/money"><i class="fa fa-calendar-check-o"></i> <span>流浪宠物管理</span></a></li>
        <!-- <c:if> -->	       
	        <li class="${param.menu == 'sales' ? 'active' : ''}"><a href="/ticket/sales"><i class="fa fa-calendar-plus-o"></i> <span>用户管理</span></a></li>      
	        <li class="${param.menu == 'lose' ? 'active' : ''}"><a href="/ticket/lose"><i class="fa fa-circle-o"></i> <span>宠物认领管理</span></a></li>
	        <li class="${param.menu == 'ticket_unhook' ? 'active' : ''}"><a href="/ticket/unhook"><i class="fa fa-circle-o"></i> <span>认领申请管理</span></a></li>
	        <li class="${param.menu == 'ticket_unhook' ? 'active' : ''}"><a href="/ticket/unhook"><i class="fa fa-circle-o"></i> <span>宠物认领审核</span></a></li>
	        <li class="treeview ${fn:startsWith(param.menu, 'search') ? 'active' : ''}">
	            <a href="javascript:;">
	                <i class="fa fa-search"></i> <span>查询</span>
	                <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
	            </a>
	            <ul class="treeview-menu">
	                <li class="${param.menu == 'search_ticket' ? 'active' : ''}"><a href="/search/ticket"><i class="fa fa-circle-o"></i> 查询宠物</a></li>
	                <li class="${param.menu == 'search_customer' ? 'active' : ''}"><a href="/search/customer"><i class="fa fa-circle-o"></i> 查询用户</a></li>
	            </ul>
	        </li>
        <!-- </c:if>
        <c:if> -->
	        <li class="${param.menu == 'regist' ? 'active' : ''}"><a href="/user/regist"><i class="fa fa-calendar-plus-o"></i> <span>用户注册</span></a></li>
	        <li class="${param.menu == 'Continuation_fee' ? 'active' : ''}"><a href="/ticket/money"><i class="fa fa-calendar-check-o"></i> <span>宠物认领申请</span></a></li>
	        <li class="${param.menu == 'ticket_unhook' ? 'active' : ''}"><a href="/ticket/unhook"><i class="fa fa-circle-o"></i> <span>查看申请结果</span></a></li>
	        <li class="${param.menu == 'ticket_unhook' ? 'active' : ''}"><a href="/ticket/unhook"><i class="fa fa-circle-o"></i> <span>留言板</span></a></li>
       <!--  </c:if> -->
      </ul>
    </section>
  </aside>