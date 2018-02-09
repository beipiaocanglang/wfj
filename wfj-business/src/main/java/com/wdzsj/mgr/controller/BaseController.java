package com.wdzsj.mgr.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.wdzsj.mgr.entity.acl.SysUser;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import com.wdzsj.cmn.constant.Const;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.cmn.util.CastUtil;
import com.wdzsj.cmn.util.StringUtil;

public class BaseController extends MultiActionController {
	
	private final static int DEFAULT_PAGE_SIZE = 10;
	
	protected SysUser currentSysUser(HttpServletRequest request) {
		return (SysUser)request.getSession().getAttribute(Const.USER_SESSION_KEY);
	}

	/**
	 * 登录设置用户
	 * @param sysUser
	 * @param request
	 */
    protected void setCurrentUser(SysUser sysUser, HttpServletRequest request) {
        request.getSession().setAttribute(Const.USER_SESSION_KEY, sysUser);
    }
    
	/**
	 * 注销用户
	 * @param request
	 */
    protected void logoutCurrentUser(HttpServletRequest request){
    	request.getSession().removeAttribute(Const.USER_SESSION_KEY);
    }
	
	/**
	 * 成功提示
	 * @param message
	 * @param redirectAttributes
	 */
	protected void successMessage(String message,RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", StringUtil.isEmpty(message) ? Const.MESSAGE_SUCCESS : message);
		redirectAttributes.addFlashAttribute("messageType", 1);
	}

	/**
	 * 失败提示
	 * @param message
	 * @param redirectAttributes
	 */
	protected void failureMessage(String message,RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", StringUtil.isEmpty(message) ? Const.MESSAGE_FAILURE : message);
		redirectAttributes.addFlashAttribute("messageType", 0);
	}
	
	/**
	 * 成功页
	 * @param message
	 * @param request 
	 */
	protected String successPage(String message,HttpServletRequest request) {
		request.setAttribute("messagePage", StringUtil.isEmpty(message) ? Const.MESSAGE_SUCCESS : message);
		return "common/successPage";
	}

	/**
	 * 失败页
	 * @param message
	 * @param request
	 * @return
	 */
	protected String failurePage(String message,HttpServletRequest request) {
		request.setAttribute("messagePage", StringUtil.isEmpty(message) ? Const.MESSAGE_FAILURE : message);
		return "common/failurePage";
	}
	
	protected PageRequest<Map<String,Object>> newPageRequest(HttpServletRequest request){
		return newPageRequest(request,DEFAULT_PAGE_SIZE);
    }
	
	protected PageRequest<Map<String,Object>> newPageRequest(HttpServletRequest request,int defaultPageSize){
		PageRequest<Map<String,Object>> result = new PageRequest<Map<String,Object>>(new HashMap<String, Object>());
		
		Map<String,Object> autoIncludeFilters = WebUtils.getParametersStartingWith(request, "s_");
		result.getFilters().putAll(autoIncludeFilters);
		
		//当前页数
		int pageNumber = CastUtil.castInt(request.getParameter("pageNumber"), 1);
		//每页显示的记录条数 
		int pageSize = CastUtil.castInt(request.getParameter("pageSize"), DEFAULT_PAGE_SIZE);

		result.setPageNumber(pageNumber);
		result.setPageSize(pageSize);

    	return result;
    }
}
