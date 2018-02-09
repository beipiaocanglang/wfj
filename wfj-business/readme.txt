项目结构说明：
    报表管理：
        cac:门店消费报表查询
            Controller：com.wdzsj.mgr.controller.tca.ConsumeCheckController/com.wdzsj.mgr.controller.tca.ConsumeCheckExportController
            Service：com.wdzsj.mgr.service.cac.CheckAccountService
            Dao：com.wdzsj.mgr.dao.cac.CheckAccountDao

            一级消费报表查询:
                列表：/cac
                多条件搜索(posId~posId/transtime~transtime):/cac
                导出：/report/export

            二级消费报表查询:
                列表：/cac/show/{posid}
                多条件搜索(posId/transtime~transtime):/cac/showindex
                导出：/report/export/detail

        ttr:汇总表查询
            Controller：com.wdzsj.mgr.controller.ttr.CheckTotalDataController/com.wdzsj.mgr.controller.ttr.ExportExcelController
            Service：com.wdzsj.mgr.service.ttr.CheckTotalDataService
            Dao：com.wdzsj.mgr.dao.ttr.CheckTotalDataDao

            一级消费报表查询:
                列表：/ttr
                多条件搜索(payFinishTime~payFinishTime):/ttr
                导出:/ttr/export/exportIndexExcel

            二级消费报表查询:
                列表：/ttr/show/{payFinishTime}
                多条件搜索(payFinishTime~payFinishTime):/ttr/showindex
                导出:/ttr/export/exporSecondLevelExcel

            三级报表查询：
                列表：/ttr/showDetail/{transId}
                多条件搜索(price/transId):/ttr/showDetailIndex
                导出:/ttr/export/exporThreeLevelExcel

        tca:消费核对表查询
            Controller：com.wdzsj.mgr.controller.tca.ConsumeCheckController/com.wdzsj.mgr.controller.tca.ConsumeCheckExportController
            Service：com.wdzsj.mgr.service.tca.CheckAccountService
            Dao：com.wdzsj.mgr.dao.cac.CheckAccountDao

            一级报表查询：
                列表：/tca
                搜索、分页：/tca
                导出：/tca/export/exportIndexExcel

            二级报表查询：/tca/show/{transtime}
                列表：/tca/show/{transtime}
                搜索、分页：/tca/showindex
                导出：无

            三级报表查询：
                列表：/tca//showdetail/{storename}
                搜索、分页：/tca/showThreeDetail
                导出：/tca/export/exportSecondExcel

        ecd:电子卡消费明细  待完成  差sql的封装
            Controller：com.wdzsj.mgr.controller.ecd.EcardConsumeDetailController
            Service：com.wdzsj.mgr.service.ecd.EcardConsumeDetailService
            Dao：com.wdzsj.mgr.dao.ecd.EcardConsumeDetailDao

            列表：/ecd
            搜索、分页：/ecd
            导出：/ecd/exportExcel

        ecb:电子卡余额查询  待完成  差sql的封装
            Controller：com.wdzsj.mgr.controller.ecb.EcardBalanceController
            Service：com.wdzsj.mgr.service.ecb.EcardBalanceService
            Dao：com.wdzsj.mgr.dao.ecb.EcardBalanceDao

            列表：/ecb
            搜索、分页：/ecb
            导出：/ecb/exportExcel

        scb:售卡余额查询  待完成  差sql的封装
            Controller：com.wdzsj.mgr.controller.scb.SellCardBalanceController
            Service：com.wdzsj.mgr.service.scb.SellCardBalanceService
            Dao：com.wdzsj.mgr.dao.scb.SellCardBalanceDao

            列表：/scb
            搜索、分页：/scb
            导出：/scb/exportExcel

    营销活动：
        已有的活动列表查询

        新建：
            活动计划：

            种子人群：

            标签扩散：

            选择渠道：

            效果分析

        编辑：
            活动计划：

            种子人群：

            标签扩散：

            选择渠道：

            效果分析

    现金卡管理：
        订单查询：
            一级报表查询

            二级报表查询(根据一级报表中的订单号)

        购卡激活查询：
            一级报表查询(根据用户输入的code)；

            二级报表查询(根据上一级的code  或者上一级根据code查询出的数据中包含object对象 传到下一级)：

        交易明细查询：
