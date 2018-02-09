package com.wdzsj.mgr.dao.ecd;

import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.ecd.EcardConsumeDetailVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Repository
public interface EcardConsumeDetailDao extends BaseDao<EcardConsumeDetailVo> {
    /**
     * 电子卡消费明细报表导出数据
     * @param param
     * @return
     */
    List<EcardConsumeDetailVo> findExportExcelList(Map<String, Object> param);
}
