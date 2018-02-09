package com.wdzsj.mgr.service.ttr;

import com.wdzsj.mgr.castor.util.DoubleUtil;
import com.wdzsj.mgr.castor.util.SortUtils;
import com.wdzsj.mgr.dao.ttr.CheckTotalDataDao;
import com.wdzsj.mgr.entity.ttr.CardActivation;
import com.wdzsj.mgr.entity.ttr.CardActiveVo;
import com.wdzsj.mgr.entity.ttr.SecondLevelTotalTableVo;
import com.wdzsj.mgr.entity.ttr.TotalTableVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@SuppressWarnings({"all", "rawtypes"})
public class CheckTotalDataService{

    @Resource
    private CheckTotalDataDao checkTotalDataDao;

    @Resource
    private DoubleUtil doubleUtil;

    /**
     * 统计数量
     */
    public Integer countAll(Object obj){
        return checkTotalDataDao.countAll(obj);
    }

    /**
     * 获取汇总表的首页数据
     * @param obj
     * @return
     */
    public List<TotalTableVo> findList(Map<String, Object> map) {
        //Map<String, TotalTableVo> finalMap = new LinkedHashMap<String, TotalTableVo>();

        //购卡表数据统计
        List<TotalTableVo> activeCardList = checkTotalDataDao.findActiveCardList(map);
        /*if (activeCardList!=null) {
            for (TotalTableVo totalTableVo : activeCardList) {
                TotalTableVo ativeCardTotalTable = new TotalTableVo();
                ativeCardTotalTable.setPayTime(totalTableVo.getPayTime());
                ativeCardTotalTable.setActiveCardPrice(totalTableVo.getActiveCardPrice());
                ativeCardTotalTable.setBackCardPrice("0");
                ativeCardTotalTable.setDiffAmount("0");

                finalMap.put(ativeCardTotalTable.getPayTime(), ativeCardTotalTable);
            }
        }*/

        //退卡表数据统计
        List<TotalTableVo> backCardList = checkTotalDataDao.findBackCardList(map);
        /*if (backCardList!=null){
            for (TotalTableVo totalTableVo : backCardList) {
                TotalTableVo existTotalTableVo = finalMap.get(totalTableVo.getPayTime());
                if (existTotalTableVo != null) {
                    finalMap.get(totalTableVo.getPayTime()).setBackCardPrice(totalTableVo.getBackCardPrice());
                    Integer diffPrice = Integer.parseInt(existTotalTableVo.getActiveCardPrice())-Integer.parseInt(totalTableVo.getBackCardPrice());
                    finalMap.get(totalTableVo.getPayTime()).setDiffAmount(String.valueOf(diffPrice));
                } else {
                    TotalTableVo backCardTotalTable = new TotalTableVo();
                    backCardTotalTable.setPayTime(totalTableVo.getPayTime());
                    backCardTotalTable.setActiveCardPrice("0");
                    backCardTotalTable.setBackCardPrice(totalTableVo.getBackCardPrice());
                    backCardTotalTable.setDiffAmount(totalTableVo.getBackCardPrice());

                    finalMap.put(totalTableVo.getPayTime(), backCardTotalTable);
                }
            }
        }*/


        if (activeCardList!=null && backCardList!=null) {

            for(int x=0; x < activeCardList.size(); x++) {
                TotalTableVo activeCard = activeCardList.get(x);

                //目的是为了将两张表中的没有相同的数据的合计先赋初始值，如果在下面的判断中有时间相同的则替换，如果没有则是最终值
                if (activeCard.getDiffAmount() == 0) {
                    activeCardList.get(x).setDiffAmount(activeCard.getActiveCardPrice());
                }

                for(int y=0; y<backCardList.size();y++) {

                    TotalTableVo backCard = backCardList.get(y);

                    //目的是为了将两张表中的没有相同的数据的合计先赋初始值，如果在下面的判断中有时间相同的则替换，如果没有则是最终值
                    if (backCard.getDiffAmount() == 0) {
                        backCardList.get(y).setDiffAmount(backCard.getBackCardPrice());
                    }

                    if (activeCard.getPayTime().equals(backCard.getPayTime())){

                        activeCardList.get(x).setBackCardPrice(backCard.getBackCardPrice());

                        activeCardList.get(x).setDiffAmount(doubleUtil.sub(activeCard.getActiveCardPrice(),backCard.getBackCardPrice()));

                        backCardList.remove(y);
                    }
                }
            }
        }

        List<TotalTableVo> list = new ArrayList<>();

        if (activeCardList!=null && activeCardList.size()>0) {
            list.addAll(activeCardList);
        }
        if (backCardList!=null && backCardList.size()>0) {
            list.addAll(backCardList);
        }

        //排序
        if (list!=null) {
            SortUtils.sortByIsAvailable(list);
        }

        return list;
    }

    /**
     * 根据payFinishTime查询二级汇总表详情
     */
    public List<CardActivation> findTTRByPayFinishTime(Map<String, Object> param) {
        List<CardActivation> volist = checkTotalDataDao.findTTRByPayFinishTime(param);

        return volist;
    }

    /**
     * 二级汇总表页面搜索
     */
    public List<SecondLevelTotalTableVo> searchTTRByTime(Map<String, Object> param) {
        List<SecondLevelTotalTableVo> list = checkTotalDataDao.searchTTRByTime(param);

        return list;
    }

    /**
     * 二级汇总表的导出
     */
    public List<SecondLevelTotalTableVo> exportSecondLevelTTRByTime(Map<String, Object> param){
        List<SecondLevelTotalTableVo> secondLevelTotalTableVos = checkTotalDataDao.exportSecondLevelTTRByTime(param);
        return secondLevelTotalTableVos;
    }

    /**
     * 根据TransId查询三级汇总表详情
     */
    public List<CardActiveVo> findTTRByTransId(Map<String, Object> param) {
        List<CardActiveVo> volist = checkTotalDataDao.findTTRByTransId(param);
        return volist;
    }

    /**
     * 三级汇总表页面搜索
     */
    public List<CardActiveVo> searchTTRByPriceAndTransId(Map<String, Object> paramMap) {
        List<CardActiveVo> list = checkTotalDataDao.searchTTRByPriceAndTransId(paramMap);
        return list;
    }
}
