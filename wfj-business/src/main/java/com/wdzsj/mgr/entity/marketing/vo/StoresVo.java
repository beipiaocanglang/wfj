package com.wdzsj.mgr.entity.marketing.vo;

import com.wdzsj.mgr.entity.marketing.Stores;

import java.util.List;

public class StoresVo extends Stores{
    private List<Stores> stores;

    public List<Stores> getStores() {
        return stores;
    }

    public void setStores(List<Stores> stores) {
        this.stores = stores;
    }
}
