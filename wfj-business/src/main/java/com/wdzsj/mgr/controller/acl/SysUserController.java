package com.wdzsj.mgr.controller.acl;

import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.annotation.AclAnnotation;
import com.wdzsj.cmn.constant.Const;
import com.wdzsj.cmn.constant.Status;
import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.cmn.entity.ResponseModel;
import com.wdzsj.cmn.exception.WDException;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.acl.Role;
import com.wdzsj.mgr.entity.acl.SysUser;
import com.wdzsj.mgr.service.acl.RoleService;
import com.wdzsj.mgr.service.acl.SysUserService;
import com.wdzsj.mgr.service.acl.UserRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/sysUser")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SysUserController extends BaseController {

    @Resource
    private SysUserService      sysUserService;

    @Resource
    private UserRoleService     userRoleService;

    @Resource
    private RoleService         roleService;

    private final static String LIST_ACTION             = "redirect:/sysUser";

    private final static String PAGE_INDEX              = "sysUser/index";
    private final static String PAGE_SHOW               = "sysUser/show";
    private final static String PAGE_CREATE             = "sysUser/create";
    private final static String PAGE_EDIT               = "sysUser/edit";
    private final static String PAGE_ASSGIN_SHOW   = "sysUser/assginShow";
    private final static String PAGE_PSWD_SHOW   = "sysUser/pswdShow";
    private final static String PAGE_RESET_PSWD_SHOW = "sysUser/resetPswdShow";

    /**
     * 列表
     * @param model
     * @param request
     * @return
     */
    @AclAnnotation(mdul = "sysUser",level = 0)
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        PageRequest<Map<String, Object>> pageRequest = newPageRequest(request);
        Page page = sysUserService.findPage(pageRequest);

        model.addAttribute("page", page);
        model.addAttribute("pageRequest", pageRequest);
        return PAGE_INDEX;
    }

    //	
    /** 
     * 显示
     * @param model
     * @param id
     * @return
     */
    @AclAnnotation(mdul = "sysUser",level = 0)
    @RequestMapping(value = "/{id}")
    public String show(ModelMap model, @PathVariable Integer id) {
        SysUser sysUser = sysUserService.getById(id);
        model.addAttribute("sysUser", sysUser);
        return PAGE_SHOW;
    }

    /** 
     * 进入新增
     * @param model
     * @param sysUser
     * @return
     */
    @AclAnnotation(mdul = "sysUser",level = 1)
    @RequestMapping(value = "/create")
    public String create(ModelMap model, SysUser sysUser) {
        model.addAttribute("sysUser", sysUser);
        return PAGE_CREATE;
    }

    /**
     * 保存新增
     * @param model
     * @param sysUser
     * @return
     */
    @AclAnnotation(mdul = "sysUser",level = 1)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ModelMap model, SysUser sysUser, RedirectAttributes redirectAttributes) {
        try {
            sysUserService.addSysUser(sysUser);
        } catch (WDException e) {
            this.failureMessage(e.getMessage(),redirectAttributes);
            return LIST_ACTION;
        }

        this.successMessage(Const.MESSAGE_SUCCESS, redirectAttributes);
        return LIST_ACTION;
    }

    /** 
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @AclAnnotation(mdul = "sysUser",level = 2)
    @RequestMapping(value = "/edit/{id}")
    public String edit(ModelMap model, @PathVariable Integer id) {
        SysUser sysUser = sysUserService.getById(id);

        model.addAttribute("sysUser", sysUser);
        return PAGE_EDIT;
    }

    /** 
     * 保存更新
     * @param model
     * @param id
     * @return
     * @throws Exception 
     */
    @AclAnnotation(mdul = "sysUser",level = 2)
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(ModelMap model, @PathVariable Integer id, Integer[] roleIds,
                         HttpServletRequest request, RedirectAttributes redirectAttributes)
                                                                                           throws Exception {
        SysUser sysUser = sysUserService.getById(id);
        this.bind(request, sysUser);
        sysUserService.updateSysUser(sysUser);

        this.successMessage(Const.MESSAGE_SUCCESS, redirectAttributes);
        return LIST_ACTION;
    }

    /**
     * 删除
     * @param model
     * @param id
     * @return
     */
    @AclAnnotation(mdul = "sysUser",level = 3)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(ModelMap model, @PathVariable Long id,
                         RedirectAttributes redirectAttributes) {
        this.sysUserService.delete(id);

        this.successMessage(Const.MESSAGE_SUCCESS, redirectAttributes);
        return LIST_ACTION;
    }



    /**
     * 检查用户账号
     * @param uname
     * @return
     */
    @RequestMapping(value = "/checkUserName")
    @ResponseBody
    public ResponseModel checkUserName(@RequestParam(value = "uname") String uname) {
        int count = sysUserService.countByUserName(uname);
        if (count > 0) {
            return ResponseModel.build(null,Status.EXCEPTION_ERROR,"用户账号已经存在");
        }
        return ResponseModel.build2Success(null,"");
    }

    /**
     * 分配角色
     * @param model
     * @return
     */
    @AclAnnotation(mdul = "sysUser",level = 5)
    @RequestMapping(value = "/assignShow/{userId}", method = RequestMethod.GET)
    public String assignShow(ModelMap model, @PathVariable Long userId,
                                 HttpServletRequest request) {
        model.addAttribute("userId", userId);

        //安全组
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList", roleList);

        List<JSONObject> userRoleList = userRoleService.findRoleByUserId(userId);
        model.addAttribute("userRoleList", userRoleList);

        return PAGE_ASSGIN_SHOW;
    }

    /**
     * 分配角色
     * @param model
     * @return
     */
    @AclAnnotation(mdul = "sysUser",level = 5)
    @RequestMapping(value = "/assignRole", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel<Object> assignRole(ModelMap model, @RequestParam(value = "userId") Long userId,
                             @RequestParam(value = "roleIds") String roleIds,
                             @RequestParam(value = "level", defaultValue = "1") Integer level,
                             HttpServletRequest request) {
        sysUserService.assignRole(userId, roleIds, level);
        return ResponseModel.build2Success(null,Status.SUCCESS_MSG);
    }

    /**
     * 删除角色
     * @param model
     * @param userId
     * @param roleId
     * @return
     */
    @AclAnnotation(mdul = "sysUser",level = 5)
    @RequestMapping(value = "/delete/role", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel<Object> deleteRole(ModelMap model,@RequestParam(value = "userId") Long userId,
                                            @RequestParam(value = "roleId") Long roleId) {
        userRoleService.deleteByUserIdAndRoleId(userId, roleId);

        return ResponseModel.build2Success(null,Status.SUCCESS_MSG);
    }

    /**
     * 修改密码
     * @param model
     * @return
     */
    @RequestMapping(value = "/pswd", method = RequestMethod.GET)
    public String pswdShow(ModelMap model) {
        return PAGE_PSWD_SHOW;
    }

    /**
     * 修改密码
     * @param model
     * @return
     */
    
    @RequestMapping(value = "/pswd", method = RequestMethod.POST)
    public String pswd(ModelMap model, @RequestParam(value = "pswd") String pswd,
                           @RequestParam(value = "newPswd") String newPswd,
                           HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            SysUser user = this.currentSysUser(request);
            sysUserService.updatePswd(user.getId(),pswd,newPswd);
        } catch (WDException e) {
            return this.failurePage(e.getMessage(),request);
        }
        return this.successPage(Const.MESSAGE_SUCCESS,request);
    }

    /**
     * 重置密码
     * @param model
     * @return
     */
    @AclAnnotation(mdul = "sysUser",level = 7)
    @RequestMapping(value = "/resetPswd", method = RequestMethod.GET)
    public String resetPswdShow(ModelMap model,@RequestParam(value = "userId") Long userId) {
        model.addAttribute("userId",userId);
        return PAGE_RESET_PSWD_SHOW;
    }

    /**
     * 重置密码
     * @param model
     * @return
     */
    @AclAnnotation(mdul = "sysUser",level = 7)
    @RequestMapping(value = "/resetPswd", method = RequestMethod.POST)
    public String resetPswd(ModelMap model, @RequestParam(value = "userId") Long userId,
                       @RequestParam(value = "newPswd") String newPswd,
                       HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            sysUserService.resetPswd(userId,newPswd);
        } catch (WDException e) {
            return this.failurePage(e.getMessage(),request);
        }
        return this.successPage(Const.MESSAGE_SUCCESS,request);
    }
}
