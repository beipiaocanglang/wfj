package com.wdzsj.mgr.service.marketing;

import com.wdzsj.mgr.dao.marketing.AttachmentDao;
import com.wdzsj.mgr.entity.marketing.Attachment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 附件操作
 */
@Service
public class AttachmentService {

    @Resource
    private AttachmentDao attachmentDao;

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public Attachment selectByPrimaryKey(Long id){
        return attachmentDao.selectByPrimaryKey(id);
    }


    //******************************** 以下是页面重构后的服务 ********************************
    /**
     * 根据主键动态修改
     * @param attachment
     * @return
     */
    public int updateByPrimaryKeySelective(Attachment attachment){

        return attachmentDao.updateByPrimaryKeySelective(attachment);
    }

    /**
     * 根据活动表对应的二维码动态插入数据
     * @param attachment
     * @return
     */
    public int insertSelective(Attachment attachment){

        return attachmentDao.insertSelective(attachment);
    }
}
