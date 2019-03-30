/**
 * 
 */
package com.fdy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdy.util.Qiniustore;

/**用户servlet
 * @author fdy
 * @date 2019年3月30日
 * @Version
 */
@WebServlet("/user/regist")
public class UserServlet extends BaseServlet{

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
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
