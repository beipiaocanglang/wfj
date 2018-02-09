package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.Attachment;
import java.util.Map;

public interface AttachmentDao {
    Attachment selectByPrimaryKey(Long attachmentId);

    int insert(Attachment attachment);

    /**
     * 根据活动表对应的二维码动态插入数据
     * @param attachment
     * @return
     */
    int insertSelective(Attachment attachment);

    int countByMap(Map<String, Object> map);

    int updateByPrimaryKeySelective(Attachment attachment);
}