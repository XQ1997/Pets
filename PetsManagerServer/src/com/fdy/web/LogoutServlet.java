/**
 * 
 */
package com.fdy.web;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** 注销当前账户servlet类
 * @author fdy
 * @date 2019年3月28日
 * @Version
 */
@WebServlet("/logout")
public class LogoutServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 强制销毁session
		req.getSession().invalidate();
		
		//记住我功能，设置cookie
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies) {
			if("mobile".equals(cookie.getName())) {
				req.setAttribute("mobile", cookie.getValue());
			}
		}
		
		forward("login", req, resp);
	}
	
}
