package com.fdy.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdy.entity.Account;

/**
 * ��¼���������Ծ�̬��Դ���й���,������ֱ֤��ͨ��
 * @author fdy
 * @date 2019��3��27��
 * @Version
 */
public class LoginFilter extends AbstractFilter{

	List<String> uriList = null;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String validate = config.getInitParameter("validate"); // /account,/cust,/sale
		String[] validateUri = validate.split(","); 
		uriList = Arrays.asList(validateUri); // ������ת���ɼ���  [/account,/cust]
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		// 1.�������·��
		String uri = req.getRequestURI();
		// ���uri����uriList�е�ĳ��Ԫ�ؿ�ͷ����ô��uri��Ҫ�������ˣ�����ֱ��ͨ��  eg: /account/list  /cust/add
		if(validate(uri)) {
			// У���Ƿ��Ѿ���¼
			HttpSession session = req.getSession();
			Account account = (Account)session.getAttribute("account");
			// ����Ѿ���¼��ֱ��ͨ��
			if(account != null) {
				chain.doFilter(req, resp);
			} else {
				// ƴװuri�����uri�д��в���������  uri : customer/my/detail
				uri = getUriWithParam(uri,req); // customer/my/detail?id=12&name=jack
				// ���δ��¼����ת��¼ҳ��
				resp.sendRedirect("/login?callback=" + uri);
			}
			
		} else {
			chain.doFilter(req, resp);
		}
		
	}

	
	/**
	 * ��ô�������uri
	 * @param req 
	 * @param uri ԭ����uri
	 * @return ��Ӳ������uri
	 */
	private String getUriWithParam(String uri, HttpServletRequest req) {
		Map<String,String[]> params = req.getParameterMap();
		Set<String> keys = params.keySet();
		Iterator<String> it = keys.iterator();
		if(it.hasNext()) {
			uri += "?";
			while(it.hasNext()) {
				String key = it.next();
				String[] values = params.get(key);
				for(String value : values) {
					String param = key + "=" + value +"&"; // id=1&id=2&
					uri += param;
				}
			}
			uri = uri.substring(0, uri.length()-1); // uri?id=12&name=jack  12345  (0,4)
		}
		return uri;
	}

	/**
	 * ���uri��uriList�е�ĳ��Ԫ�ؿ�ͷ,��ô����true
	 * @param uri ��Ҫ�жϵ�uri
	 * @return true����Ҫ����  false������Ҫ����
	 */
	private boolean validate(String uri) {
		// 1.�ų�/
		if(uriList == null) {
			return false;
		}
		
		for(String str : uriList) {
			if(uri.startsWith(str)) {
				return true;
			}
		}
		
		return false;
	}

}