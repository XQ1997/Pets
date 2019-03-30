package com.fdy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**首页servlet
 * @author fdy
 * @date 2019年3月26日
 * @Version
 */
@WebServlet("/home")
public class HomeServlet extends BaseServlet{
	
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		forward("home",req,resp);
		
	}
}
