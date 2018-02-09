package com.wdzsj.mgr.service.marketing;

import com.wdzsj.mgr.dao.marketing.TradingDataDao;
import com.wdzsj.mgr.entity.marketing.TradingData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 交易记录服务
 */
@Service
public class TradingDataService {
    @Resource
    private TradingDataDao tradingDataDao;

    /**
     * 保存交易记录数据
     * @param tradingData
     * @return
     */
    public int insertSelective(TradingData tradingData){
        return tradingDataDao.insertSelective(tradingData);
    }

    public int deleteByCid(String cid){
        return tradingDataDao.deleteByCid(cid);
    }

    public List<TradingData> selectByPrimaryKey(String cid){
        return tradingDataDao.selectByPrimaryKey(cid);
    }
}
