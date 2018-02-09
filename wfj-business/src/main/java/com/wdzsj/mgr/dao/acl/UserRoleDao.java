package com.wdzsj.mgr.dao.acl;

import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.acl.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRoleDao extends BaseDao<UserRole> {
	
    /**
     * 删除我的角色
     * @param userId
     */
    void deleteByUserId(long userId);
    
    List<UserRole> findByRoleId(Long roleId);

    /**
     * 根据用户ID获取他的角色
     * @param userId
     * @return
     */
    List<JSONObject> findRoleByUserId(Long userId);

    Integer countByRoleAndUserId(@Param("userId") Long userId, @Param("roleId") Long roleId);

    void deleteByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
