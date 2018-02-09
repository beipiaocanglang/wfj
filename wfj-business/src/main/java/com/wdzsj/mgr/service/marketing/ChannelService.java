package com.wdzsj.mgr.service.marketing;

import com.wdzsj.mgr.dao.marketing.ChannelDao;
import com.wdzsj.mgr.entity.marketing.Channel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 渠道表服务
 */
@Service
public class ChannelService {
    @Resource
    private ChannelDao channelDao;

    /**
     * 动态插入数据
     * @param channel
     * @return
     */
    public int insertSelective(Channel channel){
        return channelDao.insertSelective(channel);
    }

    /**
     * 根据ID查询
     * @param channelId
     * @return
     */
    public Channel selectById(Long channelId){
        return channelDao.selectById(channelId);
    }

    /**
     * 动态修改
     * @param channel
     * @return
     */
    public int updateSelective(Channel channel){
        return channelDao.updateSelective(channel);
    }

    /**
     * 页面回显
     * @param channelId
     * @return
     */
    public List<Channel> selectByIds(String[] channelId){
        return channelDao.selectByIds(channelId);
    }
}
