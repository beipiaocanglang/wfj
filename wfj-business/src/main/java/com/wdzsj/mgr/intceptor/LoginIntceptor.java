package com.wdzsj.mgr.intceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wdzsj.cmn.annotation.AclAnnotation;
import com.wdzsj.cmn.constant.Const;
import com.wdzsj.cmn.helper.ThreadLocalHelper;
import com.wdzsj.mgr.entity.acl.SysUser;
import com.wdzsj.mgr.function.AclFunction;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class LoginIntceptor implements HandlerInterceptor {

    protected static final Logger log = Logger.getLogger(LoginIntceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {

        SysUser sysUser = (SysUser) request.getSession().getAttribute(Const.USER_SESSION_KEY);

        if (sysUser == null) {
            response.sendRedirect(request.getContextPath() + "/acl/login");
            return false;
        }

        ThreadLocalHelper.setUserId(sysUser.getId());

        try {
            HandlerMethod method = (HandlerMethod) handler;

            AclAnnotation aclAnnotation = method.getMethod().getAnnotation(AclAnnotation.class);
            if (null == aclAnnotation) {
                aclAnnotation = method.getBeanType().getAnnotation(AclAnnotation.class);
            }
            if (null != aclAnnotation) {
                String mdul = aclAnnotation.mdul();
                int level = aclAnnotation.level();

                boolean isAcl = AclFunction.hasPermission(mdul, level);
                if (!isAcl) {
                    response.sendRedirect(request.getContextPath() + "/acl/auth");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
