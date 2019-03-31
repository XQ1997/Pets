/**
 * 
 */
package com.fdy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdy.entity.Account;
import com.fdy.exception.ServiceException;
import com.fdy.service.AccountService;
import com.fdy.util.AjaxResult;
import com.fdy.util.Qiniustore;

/**用户servlet
 * @author fdy
 * @date 2019年3月30日
 * @Version
 */
@WebServlet("/user/regist")
public class UserServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	AccountService  accountService = new AccountService();
	
	/*  用于跳转到用户注册页面
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Qiniustore qiniustore = new Qiniustore();//创建工具类对象
		//将七牛云凭证token传至前端
		req.setAttribute("token", qiniustore.getUploadToken()); 
		forward("user/regist_user", req, resp);
	}
	
	/* 提交用户注册信息
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String  role = req.getParameter("role");
		String  username = req.getParameter("username");
		String  age = req.getParameter("age");
		String  sex = req.getParameter("sex");
		String  address = req.getParameter("address");
		String  cardnum = req.getParameter("cardnum");
		String  job = req.getParameter("job");
		String  password = req.getParameter("password");
		String  userPhoto = req.getParameter("userPhoto");
		String  cardInPhoto = req.getParameter("cardInPhoto");
		String  cardOutPhoto = req.getParameter("cardOutPhoto");
		String  mobile = req.getParameter("mobile");
		
		Account account = new Account(role, username, Integer.parseInt(age), sex, address, cardnum, job, password, userPhoto, cardInPhoto, cardOutPhoto, mobile);
		try {
			//保存注册用户
			accountService.saveAccount(account);
			AjaxResult result = AjaxResult.success();
			sendJson(result, resp);
		}catch (ServiceException e) {
			AjaxResult result = AjaxResult.error(e.getMessage());
			sendJson(result, resp);
		}
	}
	
}
