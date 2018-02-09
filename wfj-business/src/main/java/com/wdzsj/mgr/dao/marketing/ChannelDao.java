package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.Channel;

import java.util.List;

public interface ChannelDao {

    int insert(Channel channel);

    Channel selectById(Long channelId);

    //页面回显时的查询
    List<Channel> selectByIds(String[] channelId);

    int insertSelective(Channel channel);

    int updateSelective(Channel record);
}