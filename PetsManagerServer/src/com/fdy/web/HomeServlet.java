package com.fdy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**��ҳservlet
 * @author fdy
 * @date 2019��3��26��
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
