package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.ActiveMemberRelation;

public interface ActiveMemberRelationDao {

    int insert(ActiveMemberRelation activeMemberRelation);

    ActiveMemberRelation selectByActIdAndCid(Long actId,String cid);
}