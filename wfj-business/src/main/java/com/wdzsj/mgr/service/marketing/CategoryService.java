package com.wdzsj.mgr.service.marketing;

import com.wdzsj.mgr.dao.marketing.CategoryDao;
import com.wdzsj.mgr.dao.marketing.StoresDao;
import com.wdzsj.mgr.entity.marketing.Category;
import com.wdzsj.mgr.entity.marketing.Stores;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings({"all", "rawtypes"})
public class CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Resource
    private StoresDao storesDao;

    /**
     * 智算完成后的下载种子数据时根据品类id和门店名称查询 品类名称
     * @param map
     * @return
     */
    public Category selectPpNameByPpAndStoreId(Map<String,String> map){
        return categoryDao.selectPpNameByPpAndStoreId(map);
    }


    //******************************** 以下是页面重构后的服务 ********************************
    /**
     * 根据页面选择的门店 ID(数组形式) 查询对应的一级品类
     * author : sunpanhu
     * createTime : 2018/2/7 下午4:57
     */
    public List<Map<String, Object>> selectCategoryByStoreId(String[] storeId){

        List<Category> categories = categoryDao.selectCategorySelective(storeId);

        List<Map<String, Object>> finalList = new ArrayList<>();

        for (int x=0; x<storeId.length; x++) {
            Stores store = storesDao.selectCityByStoreId(storeId[x]);

            Map<String, Object> map = new HashMap<>();

            List<Category> list = new ArrayList<>();

            for (Category category : categories) {
                if (category.getStoreId().equals(storeId[x])){
                    list.add(category);
                }
            }

            if (list.size()>0){
                map.put("storeId", list.get(0).getStoreId());
                map.put("currentCategoryLeve", list.get(0).getClassHierarchy());
                map.put("storeName", store.getStoreName());
                map.put("categroyList", list);
                finalList.add(map);
            }
        }
        return finalList;
    }

    /**
     * 根据上级(一级)品牌编码的ppid查询二级分类信息
     * @param paramMap（pid，storesId、brandName）
     * author : sunpanhu
     * createTime : 2018/2/8 下午12:42
     */
    public List<Category> selectCategoryByPid(Map<String, Object> paramMap){
        List<Category> secondLevelCategory = categoryDao.selectCategoryByPid(paramMap);
        return secondLevelCategory;
    }
}
