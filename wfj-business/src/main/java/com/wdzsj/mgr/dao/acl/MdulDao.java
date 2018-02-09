package com.wdzsj.mgr.dao.acl;

import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.acl.Mdul;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface MdulDao extends BaseDao<Mdul> {

    /**
     * 根据标示获取模块id
     * @param sn
     * @return
     */
    Long getBySn(String sn);
	
    /**
     * 获取模块列表
     * @param map
     * @return
     */
	List<Mdul> findMduls(Map<Object, Object> map);
	
	/**
	 * 获取模块父子列表
	 * @return
	 */
	List<Mdul> findByParent(Integer type);
	
	/**
	 * 拦截url使用
	 * @param aclUrl
	 * @return
	 */
	List<Map<Object,Object>> getByUrl(String aclUrl);

	List<Mdul> findAllMduls();
}
