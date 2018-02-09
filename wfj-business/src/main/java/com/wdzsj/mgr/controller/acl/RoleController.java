package com.wdzsj.mgr.controller.acl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.wdzsj.cmn.annotation.AclAnnotation;
import com.wdzsj.cmn.constant.Status;
import com.wdzsj.cmn.entity.ResponseModel;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.service.acl.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wdzsj.cmn.constant.Const;
import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.entity.acl.Role;

/**
 * 
 * @author qy
 *
 */
@Controller
@RequestMapping(value="/role")
@SuppressWarnings({"unchecked", "rawtypes"})
public class RoleController extends BaseController {
	
	@Resource
	private RoleService roleService;
	
	private final String LIST_ACTION = "redirect:/role";
	
	private final static String PAGE_INDEX = "role/index";
	private final static String PAGE_SHOW = "role/show";
	private final static String PAGE_CREATE = "role/create";
	private final static String PAGE_EDIT = "role/edit";
	
	
	/** 
	 * 列表
	 * @param model
	 * @param request
	 * @return
	 */
	@AclAnnotation(mdul = "Role",level = 0)
	@RequestMapping
	public String index(ModelMap model,HttpServletRequest request) {
		PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
		Page page = roleService.findPage(pageRequest);
		
		model.addAttribute("page", page);
		model.addAttribute("pageRequest", pageRequest);
		return PAGE_INDEX;
	}
	
	/** 
	 * 显示
	 * @param model
	 * @param id
	 * @return
	 */
	@AclAnnotation(mdul = "Role",level = 0)
	@RequestMapping(value="/{id}")
	public String show(ModelMap model,@PathVariable Long id) {
		Role role = roleService.getById(id);
		model.addAttribute("role",role);
		return PAGE_SHOW;
	}

	/** 
	 * 进入新增
	 * @param model
	 * @param role
	 * @return
	 */
	@AclAnnotation(mdul = "Role",level = 1)
	@RequestMapping(value="/create")
	public String create(ModelMap model,Role role) {
		
		model.addAttribute("role",role);
		return PAGE_CREATE;
	}
	
	/**
	 * 保存新增
	 * @param model
	 * @param role
	 * @return
	 */
	@AclAnnotation(mdul = "Role",level = 1)
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(ModelMap model,Role role,RedirectAttributes redirectAttributes) {
		roleService.insert(role);
		
		this.successMessage(Const.MESSAGE_SUCCESS, redirectAttributes);
		return LIST_ACTION;
	}
	
	/**
	 * 编辑
	 * @param model
	 * @param id
	 * @return
	 */
	@AclAnnotation(mdul = "Role",level = 2)
	@RequestMapping(value="/edit/{id}")
	public String edit(ModelMap model,@PathVariable Long id) {
		Role role = roleService.getById(id);
		model.addAttribute("role",role);
		return PAGE_EDIT;
	}
	
	/** 
	 * 保存更新
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@AclAnnotation(mdul = "Role",level = 2)
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(ModelMap model,@PathVariable Long id,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		Role role = roleService.getById(id);
		this.bind(request, role);
		
		roleService.update(role);
		this.successMessage(Const.MESSAGE_SUCCESS, redirectAttributes);
		return LIST_ACTION;
	}
	
	/**
	 * 删除
	 * @param model
	 * @param id
	 * @return
	 */
	@AclAnnotation(mdul = "Role",level = 3)
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(ModelMap model,@PathVariable Long id,RedirectAttributes redirectAttributes) {
		roleService.delete(id);
		
		this.successMessage(Const.MESSAGE_SUCCESS, redirectAttributes);
		return LIST_ACTION;
	}
}

