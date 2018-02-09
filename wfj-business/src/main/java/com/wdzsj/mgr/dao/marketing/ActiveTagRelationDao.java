package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.ActiveTagRelation;

public interface ActiveTagRelationDao {

    int insert(ActiveTagRelation activeTagRelation);

    int insertSelective(ActiveTagRelation activeTagRelation);

    ActiveTagRelation selectByActId(Long actId);

    int updateByIdSelective(ActiveTagRelation activeTagRelation);
}