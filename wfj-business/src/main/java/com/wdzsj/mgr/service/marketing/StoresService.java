package com.wdzsj.mgr.service.marketing;

import com.wdzsj.mgr.dao.marketing.StoresDao;
import com.wdzsj.mgr.entity.marketing.Stores;
import com.wdzsj.mgr.entity.marketing.vo.StoresVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings({"all", "rawtypes"})
public class StoresService {

    @Resource
    private StoresDao storesDao;

    //获取所有门店所在城市列表
    public List<StoresVo> findList(){

        List<StoresVo> lists = storesDao.findList();
        for (StoresVo stores : lists) {
            List<Stores> sList = storesDao.selectCityByPrimaryKey(stores.getId());
            stores.setStores(sList);
        }

        return lists;
    }

    public List<Stores> selectCityByPrimaryKey(Integer id){
        return storesDao.selectCityByPrimaryKey(id);
    }

    /**
     * 智算完成后根据智算结果返回的ctiyId查询城市名称
     * @param storeId
     * @return
     */
    public Stores selectStoreNameByStoreId(String storeId){
        return storesDao.selectStoreNameByStoreId(storeId);
    }


    //******************************** 以下是页面重构后的服务 ********************************

    /**
     * 获取所有门店信息
     * author : sunpanhu
     * createTime : 2018/2/7 下午4:14
     */
    public List<StoresVo> queryAllStore(){

        List<StoresVo> lists = storesDao.findList();

        return lists;
    }

    /**
     * 根据门店ID查询二级门店列表
     * author : sunpanhu
     * createTime : 2018/2/7 下午4:47
     */
    public List<Stores> querySecondLevelStoreListByStoreId(Integer storeId) {

        List<Stores> stores = storesDao.selectCityByPrimaryKey(storeId);

        return stores;
    }
}
