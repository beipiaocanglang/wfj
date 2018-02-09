package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.TradingData;

import java.util.List;
import java.util.Map;

public interface TradingDataDao {
    List<TradingData> selectByPrimaryKey(String cid);

    int deleteByCid(String cid);

    int insert(TradingData tradingData);

    int insertSelective(TradingData tradingData);

    int countByMap(Map<String, Object> map);

    int updateByPrimaryKeySelective(TradingData tradingData);
}