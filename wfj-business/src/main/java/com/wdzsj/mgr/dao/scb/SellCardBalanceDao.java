package com.wdzsj.mgr.dao.scb;

import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.ecb.EcardBalanceVo;
import com.wdzsj.mgr.entity.scb.SellCardBalanceVo;

import java.util.List;
import java.util.Map;

public interface SellCardBalanceDao extends BaseDao<SellCardBalanceVo> {

    /**
     * 核销对账表数据 tcac_check_account
     * @param o
     * @return
     */
    List<SellCardBalanceVo> findCheckAccount(Object o);
    /**
     * 购卡激活表数据 tcac_card_activation
     * @param o
     * @return
     */
    List<SellCardBalanceVo> findCardActivation(Object o);
    /**
     * 退卡表数据 tcac_back_card
     * @param o
     * @return
     */
    List<SellCardBalanceVo> findBackCard(Object o);

    /**
     * 售卡余额报表导出Excel表格
     * @param param
     * @return
     */
    List<SellCardBalanceVo> findExportExcelList(Map<String, Object> param);
}
