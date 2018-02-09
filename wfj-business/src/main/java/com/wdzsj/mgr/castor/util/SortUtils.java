package com.wdzsj.mgr.castor.util;

import com.wdzsj.mgr.entity.ttr.TotalTableVo;
import org.apache.commons.collections.comparators.ComparatorChain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtils {
    //排序功能
    public static void sortByIsAvailable(List<? extends TotalTableVo> list) {
        Comparator comparator = new Comparator<TotalTableVo>() {
            @Override
            public int compare(TotalTableVo t1, TotalTableVo t2) {
                return t2.getPayTime().compareTo(t1.getPayTime());
            }
        };

        ComparatorChain comparatorChain = new ComparatorChain();
        comparatorChain.addComparator(comparator);
        Collections.sort(list, comparatorChain);//按支付完成时间的升序排序
    }
}
