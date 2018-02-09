package com.wdzsj.mgr.dao.ecb;

import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.ecb.EcardBalanceVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Repository
public interface EcardBalanceDao extends BaseDao<EcardBalanceVo>{

    /**
     * 电子卡余额查询报表导出数据
     * @param param
     * @return
     */
    List<EcardBalanceVo> findExportExcelList(Map<String, Object> param);
}
