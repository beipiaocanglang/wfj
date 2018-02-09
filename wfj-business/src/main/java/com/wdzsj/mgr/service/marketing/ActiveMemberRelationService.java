package com.wdzsj.mgr.service.marketing;

import com.wdzsj.mgr.dao.marketing.ActiveMemberRelationDao;
import com.wdzsj.mgr.entity.marketing.ActiveMemberRelation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 活动会员关联表
 * 活动表 与 活动会员关联表  是一对多关系
 */
@Service
public class ActiveMemberRelationService {
    @Resource
    private ActiveMemberRelationDao activeMemberRelationDao;

    /**
     * 插入
     * @param activeMemberRelation
     * @return
     */
    public int insert(ActiveMemberRelation activeMemberRelation){
        return activeMemberRelationDao.insert(activeMemberRelation);
    }

    /**
     * 查询
     * @param actId
     * @param cid
     * @return
     */
    public ActiveMemberRelation selectByActIdAndCid(Long actId,String cid){
        ActiveMemberRelation activeMemberRelation = activeMemberRelationDao.selectByActIdAndCid(actId, cid);
        return activeMemberRelation;
    }
}
