package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.Stores;
import com.wdzsj.mgr.entity.marketing.vo.StoresVo;

import java.util.List;
import java.util.Map;

public interface StoresDao {
    Stores selectByPrimaryKey(String storeId);

    //智算完成后根据智算结果返回的ctiyId查询城市名称
    Stores selectStoreNameByStoreId(String storeId);

    //根据ID查询城市名称对应的ID
    List<Stores> selectCityByPrimaryKey(Integer id);

    // 暂时没有用到 根据城市对应的门店ID 查询对应的省份名称 根据省份名称查询所有相同的省份名称的数据
    List<Stores> selectStoreByCityStoreId(String cityStoreId);

    int insert(Stores stores);

    int insertSelective(Stores stores);

    int countByMap(Map<String, Object> map);

    int updateByPrimaryKeySelective(Stores stores);



    //******************************** 以下是页面重构后的服务 ********************************

    List<StoresVo> findList();

    Stores selectCityByStoreId(String storeId);
}