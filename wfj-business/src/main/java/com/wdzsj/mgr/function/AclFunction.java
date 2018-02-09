package com.wdzsj.mgr.function;

import com.wdzsj.cmn.base.BeanManager;
import com.wdzsj.cmn.helper.ThreadLocalHelper;
import com.wdzsj.mgr.service.acl.AclService;

/**
 * 安全认证
 * @author qy
 */
public class AclFunction {
	
	/**
	 * 安全认证
	 * @param resourceSn 模块标示
	 * @param level 权限
	 * @return
	 */
	public static boolean hasPermission(String resourceSn,int level){
		
		AclService aclService = BeanManager.getBean("aclService", AclService.class);
		
		if(resourceSn != null && !"".equals(resourceSn)) {
            boolean isAcl = aclService.hasPermissionByResourceSn(ThreadLocalHelper.getUserId(),
                resourceSn, level);
			return isAcl;
		}
		return true;	
	}
}
