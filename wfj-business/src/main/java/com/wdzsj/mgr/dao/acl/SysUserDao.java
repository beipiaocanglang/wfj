package com.wdzsj.mgr.dao.acl;

import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.acl.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao extends BaseDao<SysUser> {
	
	/**
	 * 根据用户名获取用户
	 * @param uname
	 * @return
	 */
	SysUser getByUserName(@Param("uname")String uname);
	
	/**
     * 查询用户名是否重复
     * @param uname
     * @return
     */
    Integer countByUserName(@Param("uname")String uname);
}
