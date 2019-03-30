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
/** ע����ǰ�˻�servlet��
 * @author fdy
 * @date 2019��3��28��
 * @Version
 */
@WebServlet("/logout")
public class LogoutServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ǿ������session
		req.getSession().invalidate();
		
		//��ס�ҹ��ܣ�����cookie
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies) {
			if("mobile".equals(cookie.getName())) {
				req.setAttribute("mobile", cookie.getValue());
			}
		}
		
		forward("login", req, resp);
	}
	
}
