package com.wdzsj.mgr.service.ecd;

import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.dao.ecd.EcardConsumeDetailDao;
import com.wdzsj.mgr.entity.cac.CheckAccountVo;
import com.wdzsj.mgr.entity.ecd.EcardConsumeDetailVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings({"all", "rawtypes"})
public class EcardConsumeDetailService extends BaseService<EcardConsumeDetailVo> {

    @Resource
    private EcardConsumeDetailDao ecardConsumeDetailDao;

    @Override
    protected BaseDao<EcardConsumeDetailVo> getEntityDao() {
        return ecardConsumeDetailDao;
    }

    /**
     * 电子卡消费明细报表导出数据
     * @param param
     * @return
     */
    public List<EcardConsumeDetailVo> findExportExcelList(Map<String, Object> param) {
        return ecardConsumeDetailDao.findExportExcelList(param);
    }
}
