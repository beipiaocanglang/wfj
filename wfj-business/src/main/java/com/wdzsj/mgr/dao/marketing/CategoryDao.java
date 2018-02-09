package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.Category;

import java.util.List;
import java.util.Map;

public interface CategoryDao {
    int insert(Category category);

    int insertSelective(Category category);

    int countByMap(Map<String, Object> map);

    Category selectPpNameByPpAndStoreId(Map<String, String> paramMap);


    //******************************** 以下是页面重构后的服务 ********************************

    List<Category> selectCategorySelective(String[] storeId);

    List<Category> selectCategoryByPid(Map<String, Object> paramMap);
}