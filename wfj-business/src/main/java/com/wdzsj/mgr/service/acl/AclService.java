package com.wdzsj.mgr.service.acl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.wdzsj.cmn.constant.Status;
import com.wdzsj.cmn.exception.WDException;
import com.wdzsj.cmn.helper.CacheHelper;
import com.wdzsj.cmn.util.MD5;
import com.wdzsj.mgr.entity.acl.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.dao.acl.AclDao;
import com.wdzsj.mgr.dao.acl.MdulDao;
import com.wdzsj.mgr.dao.acl.SysUserDao;

@Service("aclService")
@SuppressWarnings({"unchecked", "rawtypes"})
public class AclService extends BaseService<Acl> {

	protected static Logger log = Logger.getLogger(AclService.class);
	
	@Resource
	private AclDao aclDao;
	
	@Resource
	private MdulDao mdulDao;
	
	@Resource
	private SysUserDao sysUserDao;

	@Override
	protected BaseDao<Acl> getEntityDao() {
		return aclDao;
	}
	
	/**
	 * 用户登录
	 */
	public SysUser login(LoginDto loginDto, HttpServletRequest request) {
		String userName = loginDto.getUserName();
		String password = loginDto.getPassword();
		String code = loginDto.getCode();

		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(userName)) {
			throw new WDException(Status.EXCEPTION_ERROR,"参数不合法");
		}

		SysUser sysUser = sysUserDao.getByUserName(userName);
		
		if(null == sysUser) {
			throw new WDException(Status.EXCEPTION_ERROR,"用户名不存在");
		}

		if(!(MD5.encrypt(password)).equals(sysUser.getPswd())) {
			throw new WDException(Status.EXCEPTION_ERROR,"密码不正确");
		}

		if(sysUser.getStatus().intValue() == 0) {
			throw new WDException(Status.EXCEPTION_ERROR,"用户被禁用");
		}

		if(sysUser.getEnabFlag() == 0) {
			throw new WDException(Status.EXCEPTION_ERROR,"用户被删除");
		}
		
		return sysUser;
	}
	
	/**
	 * 设置或更新授权
	 * @param prinType  主体类型
	 * @param prinId  主体标识
	 * @param mdulId  模块标识
	 * @param permission 操作标识
	 * @param yes 是否允许该操作
	 * @param top
	 */
	public void addOrUpdatePermission(String prinType, Long prinId, Long mdulId, int permission, boolean yes, String top) {

		String key = "Acl_" + prinType + "_" + prinId + "_" + mdulId;

		//查找ACL对象
		Acl acl = getACL(prinType, prinId, mdulId);
		
		//能够找到ACL对象，更新permission
		if(acl != null){
			acl.setPermission(permission, yes);
			if(acl.getAclstate().intValue() == 0) {
				aclDao.delete(acl.getId());
			} else {
				aclDao.update(acl);
				CacheHelper.put(key,acl);

				log.error("更改权限");
			}
			return;
		}
		
		//找不到ACL对象，则创建ACL对象，并更新permission
		acl = new Acl();
		acl.setPrinType(prinType);
		acl.setPrinId(prinId);
		acl.setMdulId(mdulId);
		acl.setPermission(permission, yes);
		//2010.01.04
		if("top".equals(top)) {
			acl.setAcltristate(0);
		}
		if(acl.getAclstate().intValue() == 0) {
			return;
		}
		aclDao.insert(acl);
		CacheHelper.put(key,acl);
		
		log.error("添加权限");
	}

	/**
	 * 
	 * @param userId 用户标识
	 * @param mdulId 模块标识
	 * @param yes 是否继承
	 */
	public void addOrUpdateUserExtends(Long userId, Long mdulId, boolean yes) {
		Acl acl = getACL(Acl.TYPE_USER,userId,mdulId);
		if(acl != null){
			acl.setExtends(yes);
			aclDao.update(acl);
			log.error("更改继承权限");
			return;
		}
		acl = new Acl();
		acl.setPrinType(Acl.TYPE_USER);
		acl.setPrinId(userId);
		acl.setMdulId(mdulId);
		acl.setExtends(yes);
		aclDao.insert(acl);
		log.error("添加继承权限");
	}

	/**
	 * 删除权限
	 * @param prinType
	 * @param prinId
	 * @param mdulId
	 */
	public void delPermission(String prinType, Long prinId, Long mdulId) {
		Acl acl = getACL(prinType, prinId, mdulId);
		aclDao.delete(acl.getId());

		String key = "Acl_" + prinType + "_" + prinId + "_" + mdulId;
		CacheHelper.remove(key);
		log.error("删除权限");
	}

	/**
	 * 查看具体权限
	 * @param userId
	 * @param mdulId
	 * @param permission
	 * @return
	 */
	public boolean gethasPermission(Long userId, Long mdulId, int permission) {
		
		//根据用户标识和模块标识查找授权记录
		Acl acl = null;//getACL(Acl.TYPE_USER,userId,mdulId);
		
		//有授权记录
//		if(acl != null){
//			int yesOrNo = acl.getPermission(permission);
//			
//			//如果是确定的授权
//			if(yesOrNo != Acl.ACL_NEUTRAL){
//				
//				//立刻返回，无需继续查找
//				return yesOrNo == Acl.ACL_YES ? true : false; 
//			}
//		}
		
		//继续查找用户拥有的角色的授权
		//查找分配给用户的角色，按优先级从高到低排序
		List list = this.findRoleids(userId);
		List roleIds = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Map map = (Map) iter.next();
            Long rid = (Long) map.get("rid");
            String code = (String) map.get("code");
            if ("SYSTEM".equals(code)) {
				return true;
			}
			roleIds.add(rid);
		}
		
		//依次根据角色标识和模块标识查找授权记录
		for (Iterator iterator = roleIds.iterator(); iterator.hasNext();) {
			Long rid = (Long)iterator.next();
			acl = getACL(Acl.TYPE_ROLE, rid, mdulId);
			boolean isAcl = false;
			if(acl != null){
				isAcl =  acl.getPermission(permission) == Acl.ACL_YES ? true : false;
			}
			if(isAcl) return true;
		}
		
		return false;
	}

	/**
	 * 获取用户角色
	 * @param userId
	 * @return
	 */
	private List findRoleids(Long userId) {
		String key = "Role_" + userId;
		List list = (List)CacheHelper.get(key);
		if(null == list) {
			list = aclDao.findRoleids(userId);

			CacheHelper.put(key,list);
		}
		return list;
	}
	
	/**
	 * 查看权限
	 * @param userId
	 * @param resourceSn
	 * @param permission
	 * @return
	 */
	public boolean hasPermissionByResourceSn(Long userId, String resourceSn, int permission) {

		return gethasPermission(userId,getMdulIdBySn(resourceSn),permission);
	}

	/**
	 * 根据模块标识获取模块id
	 * @param resourceSn
	 * @return
	 */
	private Long getMdulIdBySn(String resourceSn) {
		String key = "Mdul_sn_" + resourceSn;
		Long mdulId = (Long) CacheHelper.get(key);
		if(null == mdulId) {
			mdulId = mdulDao.getBySn(resourceSn);
			CacheHelper.put(key,mdulId );
		}
		return mdulId;
	}

	public List<Mdul> searchMyMduls(Long userId, String system) {
		List<Mdul> result = new ArrayList<Mdul>();

		List<Mdul> list = this.searchMduls(userId,system);
		for(Mdul mdul : list) {
			if(mdul.getParId().longValue() == 0) {
				List<Mdul> children = new ArrayList<Mdul>();
				for(Mdul m : list) {
					if(mdul.getId().longValue() == m.getParId().longValue()) {
						children.add(m);
					}
				}
				mdul.setChildren(children);
				result.add(mdul);
			}
		}
		return result;
	}

	/**
	 * 查找功能模块
	 * @param userId
	 * @param system
	 * @return
	 */
	public List<Mdul> searchMduls(Long userId, String system) {
		
		//查找用户拥有的角色，并按优先级从低到高排序
		List list = this.findRoleids(userId);
		List roleIds = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Map map = (Map) iter.next();
			long a = (Long)map.get("rid");
			String code = (String)map.get("code");

			if ("SYSTEM".equals(code)) {
				return mdulDao.findAllMduls();
			}

			roleIds.add(a);
		}
		
		Map<Object,Object> temp = new HashMap<Object,Object>();
		
		//依次查找角色的授权（ACL对象）
		for (Iterator iterator = roleIds.iterator(); iterator.hasNext();) {
			Long rid = (Long) iterator.next();
			List acls = findRoleAcls(rid);
			for (Iterator iterator2 = acls.iterator(); iterator2.hasNext();) {
				Acl acl = (Acl) iterator2.next();
				temp.put(acl.getMdulId(), acl);
			}
		}
		
		//针对用户查找有效的用户授权
		List acls = findUserAcls(userId);
		for (Iterator iterator = acls.iterator(); iterator.hasNext();) {
			Acl acl = (Acl) iterator.next();
			temp.put(acl.getMdulId(), acl);
		}
		
		//去除掉那些没有读取权限的授权记录
		Set entries = temp.entrySet();
		for (Iterator iterator = entries.iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			Acl acl = (Acl)entry.getValue();
			if(acl.getPermission(0) != Acl.ACL_YES){
				//iterator.remove();
			}
		}
		
		if(temp.isEmpty()){
			return new ArrayList();
		}
		
		StringBuffer ids = new StringBuffer();
		for(Iterator iter = temp.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			ids.append(entry.getKey()).append(",");
		}
		String id = ids.substring(0, ids.length()-1);
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("id", id);
		map.put("system", system);
		return mdulDao.findMduls(map);
	}
	
	/**
	 * 获取我的模块
	 * @param prinType
	 * @param prinId
	 * @return
	 */
	public List<AclView> searchAclRecord(String prinType,Long prinId){
		List listRecords = new ArrayList();
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("prinType", prinType);
		map.put("prinId", prinId);
		List list = aclDao.findAcl(map);
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Acl acl = (Acl) iter.next();

			AclView aclView = new AclView();
			aclView.setMdulId(acl.getMdulId());
			aclView.setAcltristate(acl.getAcltristate());
			
			List<Integer> aclList = new ArrayList<Integer>();
			for(int i=0; i<50; i++) {
				aclList.add(acl.getAclstate()&(int)Math.pow(2, i));
			}
			aclView.setAclList(aclList);
			
			listRecords.add(aclView);
		}
				
		return listRecords;
	}
	
	/**
	 * 获取一个ACL实例
	 * @param prinType 主体类型
	 * @param prinId  主体id
	 * @param mdulId  模块标示
	 * @return
	 */
	private Acl getACL(String prinType,Long prinId,Long mdulId){

		String key = "Acl_" + prinType + "_" + prinId + "_" + mdulId;

		Acl acl = (Acl) CacheHelper.get(key);

		if(null == acl) {
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("prinType", prinType);
			map.put("prinId", prinId);
			map.put("mdulId", mdulId);

			acl = aclDao.getByMap(map);

			CacheHelper.put(key,acl);
		}
		return acl;
	}
	
	/**
	 * 根据角色id获取一个ACL实例列表
	 * @param roleId
	 * @return
	 */
	private List findRoleAcls(Long roleId){
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("prinType", Acl.TYPE_ROLE);
		map.put("prinId", roleId);
		
		return aclDao.findAcl(map);
	}
	
	/**
	 * 根据用户id获取一个ACL实例列表
	 * @param userId
	 * @return
	 */
	private List findUserAcls(Long userId){
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("prinType", Acl.TYPE_USER);
		map.put("prinId", userId);
		map.put("acltristate", Acl.ACL_TRI_STATE_UNEXTENDS);
		
		return aclDao.findAcl(map);
	}
}
