package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.TagData;
import java.util.Map;

public interface TagDataDao {
    TagData selectByPrimaryKey(Long tagDataId);

    int insert(TagData tagData);

    int insertSelective(TagData tagData);

    int countByMap(Map<String, Object> map);

    int updateByPrimaryKeySelective(TagData tagData);
}