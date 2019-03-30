package com.fdy.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
/**
 * 字符过滤器
 * @author fdy
 * @date 2019年3月27日
 * @Version
 */
public class EncodingFilter extends AbstractFilter{

	String encoding = "UTF-8";
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String encoding = config.getInitParameter("encoding");
		if(StringUtils.isNotEmpty(encoding)) {
			this.encoding = encoding;
		}
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		chain.doFilter(req, resp);
	}


}
