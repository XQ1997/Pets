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

/**�û�servlet
 * @author fdy
 * @date 2019��3��30��
 * @Version
 */
@WebServlet("/user/regist")
public class UserServlet extends BaseServlet{

	/*  ������ת���û�ע��ҳ��
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Qiniustore qiniustore = new Qiniustore();//�������������
		//����ţ��ƾ֤token����ǰ��
		req.setAttribute("token", qiniustore.getUploadToken()); 		
		forward("user/regist_user", req, resp);
	}
	
	/* �ύ�û�ע����Ϣ
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
