package com.wdzsj.mgr.controller.acl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.wdzsj.mgr.entity.acl.SysUser;
import com.wdzsj.mgr.service.acl.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.annotation.AclAnnotation;
import com.wdzsj.cmn.constant.Status;
import com.wdzsj.cmn.entity.ResponseModel;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.acl.LoginDto;
import com.wdzsj.mgr.service.acl.OrgService;

@Controller
@RequestMapping(value="/org")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrgController extends BaseController {
	
	@Resource
	private OrgService orgService;

	@Resource
	private SysUserService sysUserService;
	
	private final static String PAGE_TREE     = "org/tree";

    /**
     * 组织管理首页
     * @param model
     * @param userId
     * @return
     */
	@AclAnnotation(mdul = "sysUser",level = 6)
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model,@RequestParam(value="userId",required=true)Long userId) {
    	model.addAttribute("userId", userId);

		SysUser sysUser = sysUserService.getById(userId);
		model.addAttribute("sysUser",sysUser);
        return PAGE_TREE;
    }
	
    /**
     * 获取用户的组织节点
     * @param userId
     * @param request
     * @return
     */
	@AclAnnotation(mdul = "sysUser",level = 6)
	@RequestMapping(value = "/list")
    @ResponseBody
    public ResponseModel<List<JSONObject>> tree(@RequestParam(value="userId",required=true)Long userId,HttpServletRequest request) {
		List<JSONObject> result = orgService.findAll(userId);
        return ResponseModel.build2Success(result, Status.SUCCESS_MSG);
    }
	
	/**
	 * 异步获取机构的下级节点数据
	 * @param parId
	 * @param request
	 * @return
	 */
	@AclAnnotation(mdul = "sysUser",level = 6)
	@RequestMapping(value = "/findByParId")
    @ResponseBody
    public ResponseModel<List<JSONObject>> findByParId(@RequestParam(value="id",required=true,defaultValue="0")Long parId, HttpServletRequest request) {
		List<JSONObject> result = orgService.findByParId(parId);
        return ResponseModel.build2Success(result, Status.SUCCESS_MSG);
    }
	
	/**
	 * 保存用户所在的结构节点
	 * @param orgIds
	 * @param userId
	 * @param request
	 * @return
	 */
	@AclAnnotation(mdul = "sysUser",level = 6)
	@RequestMapping(value = "/save")
    @ResponseBody
    public ResponseModel<Object> save(@RequestParam(value="orgIds",required=true)String orgIds, 
    		@RequestParam(value="userId",required=true)Long userId,
    		HttpServletRequest request) {
		orgService.save(orgIds,userId);
        return ResponseModel.build2Success(null, Status.SUCCESS_MSG);
    }
	
	/**
	 * 删除用户所在的节点
	 * @param orgIds
	 * @param userId
	 * @param request
	 * @return
	 */
	@AclAnnotation(mdul = "sysUser",level = 6)
	@RequestMapping(value = "/delete")
    @ResponseBody
    public ResponseModel<Object> delete(@RequestParam(value="orgIds",required=true)String orgIds, 
    		@RequestParam(value="userId",required=true)Long userId,
    		HttpServletRequest request) {
		orgService.delete(orgIds,userId);
        return ResponseModel.build2Success(null, Status.SUCCESS_MSG);
    }
}

