package com.fdy.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 拦截器父类，使用适配器模式
 * @author fdy
 * @date 2019年3月27日
 * @Version
 */
public abstract class AbstractFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public abstract void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException ;

	@Override
	public void destroy() {}

}