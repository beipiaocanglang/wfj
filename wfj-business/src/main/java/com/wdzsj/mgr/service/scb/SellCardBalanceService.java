package com.wdzsj.mgr.service.scb;

import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.castor.util.DoubleUtil;
import com.wdzsj.mgr.castor.util.JsonUtil;
import com.wdzsj.mgr.dao.scb.SellCardBalanceDao;
import com.wdzsj.mgr.entity.scb.SellCardBalanceVo;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@SuppressWarnings({"all", "rawtypes"})
public class SellCardBalanceService extends BaseService<SellCardBalanceVo> {
    @Resource
    private SellCardBalanceDao sellCardBalanceDao;

    @Resource
    private DoubleUtil doubleUtil;

    @Override
    protected BaseDao<SellCardBalanceVo> getEntityDao() {
        return sellCardBalanceDao;
    }

    /**
     * 查询售卡余额表首页数据
     * @param map
     * @return
     */
    public List<SellCardBalanceVo> findList1(Map<String, Object> map) {

        //定义最终封装数据的集合
        List<SellCardBalanceVo> finalList = new ArrayList<>();

        //购卡激活表
        List<SellCardBalanceVo> cardActivation = sellCardBalanceDao.findCardActivation(map);

        if (cardActivation!=null && cardActivation.size()>0) {
            for (SellCardBalanceVo scbv1 : cardActivation) {
                Double d = 0.0;
                for (SellCardBalanceVo scbv2 : cardActivation) {
                    if (scbv1.getTranstime().equals(scbv2.getTranstime())){
                        d = doubleUtil.add(d,scbv2.getGroupByTransTimePrice());
                    } else if (scbv1.getTranstime().after(scbv2.getTranstime())){
                        d = doubleUtil.add(d,scbv2.getGroupByTransTimePrice());
                    }
                }

                //初期余额的总金额
                scbv1.setTotalPrice(d);//初期余额的总金额

                SellCardBalanceVo sellCardBalanceVo = new SellCardBalanceVo();
                //设置初期余额 ( 10月1号之前所有sum(price)-sum(退卡金额)-sum(核销金额 price) )
                sellCardBalanceVo.setInitialBalance(d);

                //本期售卡 ( sum(price) 10.1当天 )
                sellCardBalanceVo.setCurrentSalesCardAmount(scbv1.getGroupByTransTimePrice());

                //期末余额 ( 期初余额+本期售卡-本期退卡-本期核销 )
                sellCardBalanceVo.setFinalBalance(doubleUtil.add(d,scbv1.getGroupByTransTimePrice()));

                //从数据库中查出的 根据时间排序后  sum 后 price
                sellCardBalanceVo.setGroupByTransTimePrice(scbv1.getGroupByTransTimePrice());

                sellCardBalanceVo.setTotalPrice(scbv1.getTotalPrice());
                sellCardBalanceVo.setTranstime(scbv1.getTranstime());

                finalList.add(sellCardBalanceVo);
            }
        }

        //核销对账表
        List<SellCardBalanceVo> checkAccount = sellCardBalanceDao.findCheckAccount(map);

        if (checkAccount!=null && checkAccount.size()>0) {
            for (int x=0;x<finalList.size();x++) {

                for (int y=0;y<checkAccount.size();y++) {
                    Double d = 0.0;
                    for (SellCardBalanceVo scbv2 : checkAccount) {
                        if (checkAccount.get(y).getTranstime().equals(scbv2.getTranstime())){
                            d = doubleUtil.add(d,scbv2.getGroupByTransTimePrice());
                        } else if (checkAccount.get(y).getTranstime().after(scbv2.getTranstime())){
                            d = doubleUtil.add(d,scbv2.getGroupByTransTimePrice());
                        }
                    }

                    //初期余额的总金额
                    checkAccount.get(y).setTotalPrice(d);

                    if (checkAccount.get(y).getTranstime().equals(finalList.get(x).getTranstime())) {

                        //设置初期余额 ( 10月1号之前所有sum(price)-sum(退卡金额)-sum(核销金额 price) )
                        finalList.get(x).setInitialBalance(doubleUtil.sub(finalList.get(x).getInitialBalance(),checkAccount.get(y).getTotalPrice()));

                        //期末余额 ( 期初余额+本期售卡-本期退卡-本期核销 )
                        finalList.get(x).setFinalBalance(doubleUtil.sub(finalList.get(x).getFinalBalance(),checkAccount.get(y).getTotalPrice()));

                        //从数据库中查出的 根据时间排序后  sum 后 price
                        finalList.get(x).setGroupByTransTimePrice(checkAccount.get(y).getGroupByTransTimePrice());

                        finalList.get(x).setTotalPrice(checkAccount.get(y).getTotalPrice());
                        finalList.get(x).setTranstime(checkAccount.get(y).getTranstime());

                        checkAccount.remove(y);
                    }
                }
            }
        }

        //退卡表
        List<SellCardBalanceVo> backCard = sellCardBalanceDao.findBackCard(map);

        if (backCard!=null && backCard.size()>0) {

            for (int x=0;x<finalList.size();x++) {

                for (int y=0;y<backCard.size();y++) {

                    Double d = 0.0;
                    for (SellCardBalanceVo scbv2 : backCard) {
                        if (backCard.get(y).getTranstime().equals(scbv2.getTranstime())){
                            d = doubleUtil.add(d,scbv2.getGroupByTransTimePrice());
                        } else if (backCard.get(y).getTranstime().after(scbv2.getTranstime())){
                            d = doubleUtil.add(d,scbv2.getGroupByTransTimePrice());
                        }
                    }

                    //初期余额的总金额
                    backCard.get(y).setTotalPrice(d);

                    if (backCard.get(y).getTranstime().equals(finalList.get(x).getTranstime())) {
                        //设置初期余额 ( 10月1号之前所有sum(price)-sum(退卡金额)-sum(核销金额 price) )
                        finalList.get(x).setInitialBalance(doubleUtil.sub(finalList.get(x).getInitialBalance(),backCard.get(y).getTotalPrice()));

                        //期末余额 ( 期初余额+本期售卡-本期退卡-本期核销 )
                        finalList.get(x).setFinalBalance(doubleUtil.sub(finalList.get(x).getFinalBalance(),backCard.get(y).getTotalPrice()));

                        //从数据库中查出的 根据时间排序后  sum 后 price
                        finalList.get(x).setGroupByTransTimePrice(backCard.get(y).getGroupByTransTimePrice());

                        //本期退卡
                        finalList.get(x).setCurrentBackCardAmount(backCard.get(y).getTotalPrice());

                        finalList.get(x).setTotalPrice(backCard.get(y).getTotalPrice());
                        finalList.get(x).setTranstime(backCard.get(y).getTranstime());

                        backCard.remove(y);
                    }
                }
            }
        }
        finalList.addAll(checkAccount);
        finalList.addAll(backCard);

        System.out.println(JsonUtil.toJson(finalList));

        return finalList;
    }

    //排序功能
    public static void sortByIsAvailable(List<? extends SellCardBalanceVo> list) {
        Comparator comparator = new Comparator<SellCardBalanceVo>() {
            @Override
            public int compare(SellCardBalanceVo t1, SellCardBalanceVo t2) {
                int num = t2.getTranstime().compareTo(t1.getTranstime());
                if (num == 1){

                }
                return t2.getTranstime().compareTo(t1.getTranstime());
            }
        };

        ComparatorChain comparatorChain = new ComparatorChain();
        comparatorChain.addComparator(comparator);
        Collections.sort(list, comparatorChain);//按支付完成时间的升序排序
    }

    /**
     * 售卡余额报表导出Excel表格
     * @param param
     * @return
     */
    public List<SellCardBalanceVo> findExportExcelList(Map<String, Object> param) {
        return sellCardBalanceDao.findExportExcelList(param);
    }
}
