/**
 * 
 */
package com.fdy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdy.entity.Account;
import com.fdy.exception.ServiceException;
import com.fdy.service.AccountService;
import com.fdy.util.AjaxResult;

/**
 * @author fdy
 * @date 2019��3��31��
 * @Version
 */
@WebServlet("/password")
public class RePassWordServlet extends BaseServlet{
	
	private AccountService service = new AccountService();
	
	/* ��ת���޸������ҳ�� 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 forward("repassword", req, resp);
	}
	
	/* �޸�����
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String oldpass = req.getParameter("oldpass");
		 String newpass = req.getParameter("newpass");
		 
		 HttpSession session = req.getSession();
		 Account account = (Account)session.getAttribute("account");
		 
		try {
			//�޸�����
			service.repass(account,oldpass,newpass);			
			AjaxResult result = AjaxResult.success();
			sendJson(result, resp);
		}catch (ServiceException e) {
			AjaxResult result = AjaxResult.error(e.getMessage());
			sendJson(result, resp);
		}
	}
}
