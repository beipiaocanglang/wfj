package com.wdzsj.cmn.constant;

public interface Status {
	
	/**
	 * 服务器端出问题
	 */
    static final int SERVER_ERROR = 500;
    static final String SERVER_ERROR_MSG = "服务器繁忙,请稍后";
	/**
	 * 请求成功
	 */
    static final int SUCCESS = 200;
    static final String SUCCESS_MSG = "请求成功";
  
    /**
	 * 用户尚未登录
	 */	
    static final int UNLOGIN_USER = 208;
    static final String UNLOGIN_USER_MSG = "用户尚未登录，请重新登录后在操作";
    
    /**
	 * 参数不合法
	 */
    static final int PARAMETER_ILLEGAL = 209;
    static final String PARAMETER_ILLEGAL_MSG = "参数不合法";
    
	/**
  	 * 没有权限
  	 */
	static final int SIGN_NO = 600;
	static final String SIGN_NO_MSG = "签名不合法";

	/**
	 * 服务器自定义异常
	 */
	static final int EXCEPTION_ERROR = 700;
	static final String EXCEPTION_ERROR_MSG = "服务器自定义异常";
	
}
