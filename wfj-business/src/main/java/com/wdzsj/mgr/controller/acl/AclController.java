package com.wdzsj.mgr.controller.acl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.wdzsj.cmn.exception.WDException;
import com.wdzsj.mgr.entity.acl.AclView;
import com.wdzsj.mgr.entity.acl.Mdul;
import com.wdzsj.mgr.service.acl.AclService;
import com.wdzsj.mgr.service.acl.MdulService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.wdzsj.cmn.annotation.AclAnnotation;
import com.wdzsj.cmn.constant.Const;
import com.wdzsj.cmn.constant.Status;
import com.wdzsj.cmn.entity.ResponseModel;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.acl.LoginDto;
import com.wdzsj.mgr.entity.acl.SysUser;
import com.wdzsj.mgr.service.acl.RoleService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/acl")
@SuppressWarnings({"unchecked", "rawtypes"})
public class AclController extends BaseController {

    @Resource
    private AclService aclService;

    @Resource
    private MdulService mdulService;

    @Resource
    private RoleService         roleService;

    private final static String LOGIN_ACTION = "redirect:/acl";

    private final static String PAGE_LOGIN = "../frame/login";
    private final static String PAGE_INDEX = "../frame/index";
    private final static String PAGE_MAIN = "../frame/main";
    private final static String PAGE_AUTH = "../frame/auth";
    private final static String PAGE_ACL     = "acl/index";

    /**
     * 首页       
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model,HttpServletRequest request) {
        SysUser sysUser = this.currentSysUser(request);
        List<Mdul> mdulList = aclService.searchMyMduls(sysUser.getId(),null);
        model.addAttribute("mdulList",mdulList);
        model.addAttribute("sysUser",sysUser);         //得到当前登录用户
        return PAGE_INDEX;            //将数据返回PAGE_INDEX
    }

    /**
     * 登录页
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index() {
        return PAGE_LOGIN;
    }

    /**
     * 框架主页
     *
     * @return
     */
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth() {
        return PAGE_AUTH;
    }

    /**
     * 框架主页
     *
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return PAGE_MAIN;
    }


    /**
     * 用户登录
     * @param loginDto
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel<Object> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        try {
            SysUser sysUser = aclService.login(loginDto, request);
            this.setCurrentUser(sysUser, request);
        } catch (WDException e) {
            return ResponseModel.build(null,e.getCode(),e.getMessage());
        }
        return ResponseModel.build2Success(null, Status.SUCCESS_MSG);
    }

    /**
     * 注销
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        this.logoutCurrentUser(request);
        return LOGIN_ACTION;
    }

    /**
     * 打开角色授权界面
     * @param model
     * @param id
     * @param redirectAttributes
     * @return
     */
    @AclAnnotation(mdul = "Role",level = 4)
    @RequestMapping(value = "/role/{id}")
    public String aclRole(ModelMap model, @PathVariable java.lang.Long id,
                          RedirectAttributes redirectAttributes) {
        List modulesFun = mdulService.findByParent(1);
        List modulesData = mdulService.findByParent(2);

        model.addAttribute("modulesFun", modulesFun);
        model.addAttribute("modulesData", modulesData);
        model.addAttribute("prinType", "role");
        model.addAttribute("prinId", id);
        model.addAttribute("role", roleService.getById(id));
        return PAGE_ACL;
    }

    /**
     * 授权
     * @param model
     * @param prinType
     * @param prinId
     * @param mdulId
     * @param permission
     * @param checked
     * @param top
     * @param redirectAttributes
     * @return
     */
    @AclAnnotation(mdul = "Role",level = 4)
    @RequestMapping(value = "/addOrUpdatePermission")
    @ResponseBody
    public ResponseModel<Object> addOrUpdatePermission(ModelMap model,
                                        @RequestParam(value = "prinType") String prinType,
                                        @RequestParam(value = "prinId") Long prinId,
                                        @RequestParam(value = "mdulId") Long mdulId,
                                        @RequestParam(value = "permission") Integer permission,
                                        @RequestParam(value = "checked") Boolean checked,
                                        @RequestParam(value = "top", required = false) String top,
                                        RedirectAttributes redirectAttributes) {
        aclService.addOrUpdatePermission(prinType, prinId, mdulId, permission, checked, top);

        return ResponseModel.build2Success(null,Status.SUCCESS_MSG);
    }

    /**
     * 启用
     * @param model
     * @param prinId
     * @param mdulId
     * @param checked
     * @param redirectAttributes
     * @return
     */
    @AclAnnotation(mdul = "Role",level = 4)
    @RequestMapping(value = "/addOrUpdateUserExtends")
    @ResponseBody
    public ResponseModel<Object> addOrUpdateUserExtends(ModelMap model,
                                         @RequestParam(value = "prinId") Long prinId,
                                         @RequestParam(value = "mdulId") Long mdulId,
                                         @RequestParam(value = "checked") boolean checked,
                                         RedirectAttributes redirectAttributes) {
        aclService.addOrUpdateUserExtends(prinId, mdulId, checked);
        return ResponseModel.build2Success(null,Status.SUCCESS_MSG);
    }

    /**
     * 删除权限
     * @param model
     * @param prinType
     * @param prinId
     * @param mdulId
     * @param redirectAttributes
     * @return
     */
    @AclAnnotation(mdul = "Role",level = 4)
    @RequestMapping(value = "/delPermission")
    @ResponseBody
    public ResponseModel<Object> delPermission(ModelMap model, @RequestParam(value = "prinType") String prinType,
                                @RequestParam(value = "prinId") Long prinId,
                                @RequestParam(value = "mdulId") Long mdulId,
                                RedirectAttributes redirectAttributes) {
        aclService.delPermission(prinType, prinId, mdulId);

        return ResponseModel.build2Success(null,Status.SUCCESS_MSG);
    }

    /**
     * 查询我的权限
     * @param model
     * @param prinType
     * @param prinId
     * @param redirectAttributes
     * @return
     */
    @AclAnnotation(mdul = "Role",level = 4)
    @RequestMapping(value = "/searchAclRecord")
    @ResponseBody
    public ResponseModel<List<AclView>> searchAclRecord(ModelMap model,
                                         @RequestParam(value = "prinType") String prinType,
                                         @RequestParam(value = "prinId") Long prinId,
                                         RedirectAttributes redirectAttributes) {
        List<AclView> list = aclService.searchAclRecord(prinType, prinId);

        return ResponseModel.build2Success(list,Status.SUCCESS_MSG);
    }

}
