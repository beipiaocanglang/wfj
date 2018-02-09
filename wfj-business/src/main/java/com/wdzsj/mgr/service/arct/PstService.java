package com.wdzsj.mgr.service.arct;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.dao.arct.PstDao;
import com.wdzsj.mgr.entity.arct.Pst;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class PstService extends BaseService<Pst>{

	@Resource
	private PstDao pstDao;
	
	@Override
	protected BaseDao<Pst> getEntityDao() {
		// TODO Auto-generated method stub
		return null;
	}
		
	/**
	 * 获取所有根节点
	 * @return
	 */
	public List<JSONObject> findRoot() {
		List<JSONObject> result = pstDao.findRoot();
		return result;
	}
	
	/**
	 * 根据上级ID获取数据列表
	 * @param parId
	 * @return
	 */
	public List<JSONObject> findByParId(Long parId) {
		List<JSONObject> result = pstDao.findByParId(parId);
		for(JSONObject object : result) {
			object.put(" nocheck", true);
		}
		return result;
	}
	
	/**
	 * 保存结构节点
	 * @param pst
	 */
	public Pst save(Pst pst) {		
		pstDao.updateIsParent(pst.getParId());	
			
		pstDao.insert(pst);

		return pst;
	}

	/**
	 * 保存结构节点
	 * @param id
	 * @param name
	 */
	public void update(Long id,String name) {
		//	Pst pst=new Pst();	
		/*	int count = pstDao.countByParIdAndName(parId,name);
			if(count == 0) {*/
				Pst pst = new Pst();
				pst.setId(id);
				pst.setName(name);
			//	pst.setSeq(seq);
				pstDao.update(pst);					
				/*}*/
	}
	
	public void delete(Long id) {
		 pstDao.delete(id);		 
		}
}




