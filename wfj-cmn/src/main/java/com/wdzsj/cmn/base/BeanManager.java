package com.wdzsj.cmn.base;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;


public final class BeanManager {
	
	private static ApplicationContext applicationCtx;
	private static ServletContext servletCtx;
	
	public static ApplicationContext getApplicationCtx() {
		return applicationCtx;
	}
	public static void setApplicationCtx(ApplicationContext applicationCtx) {
		BeanManager.applicationCtx = applicationCtx;
	}
	
	public static ServletContext getServletCtx() {
		return servletCtx;
	}
	public static void setServletCtx(ServletContext servletCtx) {
		BeanManager.servletCtx = servletCtx;
	}
	
	public static <T> T getBean(String beanName, Class<T> clazz){
		return applicationCtx.getBean(beanName, clazz);
	}
	
	public static Object getBean(String beanName){
		return applicationCtx.getBean(beanName);
	}
	
}
