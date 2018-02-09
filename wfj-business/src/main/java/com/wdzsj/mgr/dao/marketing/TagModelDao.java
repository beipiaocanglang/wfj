package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.TagModel;
import java.util.Map;

public interface TagModelDao {
    TagModel selectByPrimaryKey(String cid);

    int insert(TagModel tagModel);

    int insertSelective(TagModel tagModel);

    int countByMap(Map<String, Object> map);

    int updateByPrimaryKeySelective(TagModel tagModel);
}