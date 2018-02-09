package com.wdzsj.mgr.dao.sys;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.sys.Dict;
import org.springframework.stereotype.Repository;

/**
 * 数据字典访问数据类
 * @author qy
 */
@Repository
public interface DictDao extends BaseDao<Dict> {
	
	/**
	 * 根据上级id获取下级列表
	 * @param parId
	 * @return
	 */
    List<Dict> findByParId(@Param("parId") long parId);
    
    /**
	 * 根据上级code获取下级列表
	 * @param code
	 * @return
	 */
    List<Dict> findByCode(@Param("code") String code);
	
    /**
	 * 根据id获取数据字典对象
	 * @param id
	 * @return
	 */
    Dict getById(@Param("id") long id);
    
    /**
	 * 根据id获取名称
	 * @param code
	 * @return
	 */
    Dict getByCode(@Param("code") String code);
    
    
    Integer countByParId(@Param("parId") long parId);
    
    Integer countByCode(@Param("code") String code);
}
