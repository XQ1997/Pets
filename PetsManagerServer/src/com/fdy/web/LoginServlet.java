package com.fdy.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fdy.entity.Account;
import com.fdy.exception.ServiceException;
import com.fdy.service.AccountService;

/**µÇÂ¼servlet
 * @author fdy
 * @date 2019Äê3ÔÂ26ÈÕ
 * @Version
 */
@WebServlet("/login")
public class LoginServlet extends BaseServlet{
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(LoginServlet.class); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies) {
			if("mobile".equals(cookie.getName())) {
				req.setAttribute("mobile", cookie.getValue());
			} 
		}
		forward("login", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String mobile = req.getParameter("mobile");
		String password = req.getParameter("password");
		String remember = req.getParameter("rememberMe");

		
		AccountService service = new AccountService();
		Map<String,Object> res = new HashMap<>();
		try { 
			Account account = service.login(mobile, password);
			
			HttpSession session = req.getSession();
			session.setAttribute("account", account);
			
			if(StringUtils.isNotEmpty(remember)) {
				Cookie cookie = new Cookie("mobile",mobile);
				cookie.setDomain("localhost");
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 30);
				cookie.setHttpOnly(true);
				
				resp.addCookie(cookie);
			} else {
				Cookie[] cookies = req.getCookies();
				for(Cookie cookie : cookies) {
					if("mobile".equals(cookie.getName())) {
						cookie.setDomain("localhost");
						cookie.setPath("/");
						cookie.setMaxAge(0);
						
						resp.addCookie(cookie);
					}
				}
			}
			
			res.put("state", "success");
			sendJson(res,resp);
			
		} catch(ServiceException e) {
			res.put("state", "error");
			res.put("message", e.getMessage());
			sendJson(res,resp);
		}
		
	}
}
