package com.wdzsj.mgr.service.ecb;

import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.dao.ecb.EcardBalanceDao;
import com.wdzsj.mgr.entity.ecb.EcardBalanceVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings({"all", "rawtypes"})
public class EcardBalanceService extends BaseService<EcardBalanceVo> {
    @Resource
    private EcardBalanceDao ecardBalanceDao;

    @Override
    protected BaseDao<EcardBalanceVo> getEntityDao() {
        return ecardBalanceDao;
    }

    /**
     * 电子卡余额查询表导出数据
     * @param param
     * @return
     */
    public List<EcardBalanceVo> findExportExcelList(Map<String, Object> param) {
        return ecardBalanceDao.findExportExcelList(param);
    }
}
