package com.wdzsj.mgr.service.marketing;

import com.wdzsj.mgr.dao.marketing.ActiveTagRelationDao;
import com.wdzsj.mgr.entity.marketing.ActiveTagRelation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 扩散标签服务
 */
@Service
public class ActiveTagRelationService {
    @Resource
    private ActiveTagRelationDao activeTagRelationDao;

    /**
     * 根据活动表中的数据查询对用的标签数据
     * @param actId
     * @return
     */
    public ActiveTagRelation selectByactId(Long actId){
        return activeTagRelationDao.selectByActId(actId);
    }

    /**
     * 根据保存的活动ID 动态插入对应的数据
     * @param activeTagRelation
     * @return
     */
    public int insertSelective(ActiveTagRelation activeTagRelation){
        return activeTagRelationDao.insertSelective(activeTagRelation);
    }

    /**
     * 根据活动表的ID动态修改活动标签关联表的数据
     * @param activeTagRelation
     * @return
     */
    public int updateByIdSelective(ActiveTagRelation activeTagRelation){
        return activeTagRelationDao.updateByIdSelective(activeTagRelation);
    }
}
