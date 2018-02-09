package com.wdzsj.mgr.service.acl;

import java.util.List;
import javax.annotation.Resource;
import com.wdzsj.cmn.helper.CacheHelper;
import org.springframework.stereotype.Service;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.dao.acl.MdulDao;
import com.wdzsj.mgr.entity.acl.Mdul;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class MdulService extends BaseService<Mdul> {

	@Resource
	private MdulDao mdulDao;
	
	@Override
	protected BaseDao<Mdul> getEntityDao() {
		return mdulDao;
	}
	
	public List<Mdul> findByParent(Integer type) {
		String key = "Mdule_" + type;
		List<Mdul> list = (List<Mdul>)CacheHelper.get(key);
		if(null == list) {
			list = mdulDao.findByParent(type);

			CacheHelper.put(key,list);
		}
		return list;
	}
}
