package com.wdzsj.mgr.controller.mktactive;

import com.wdzsj.mgr.castor.util.JsonUtil;
import com.wdzsj.mgr.castor.util.ResultDTOBuilder;
import com.wdzsj.mgr.entity.marketing.Category;
import com.wdzsj.mgr.service.marketing.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : sunpanhu
 * create time : 2018/2/7 下午3:11
 */
@Controller
@ResponseBody
@RequestMapping(value = "/category", method= RequestMethod.GET, produces = "text/html;charset=UTF-8")
@SuppressWarnings({"all", "rawtypes"})
public class MktactiveCategoryController {

    //品类服务
    @Resource
    private CategoryService categoryService;

    /**
     * 根据页面选择的门店 ID(数组形式) 查询对应的一级品类
     * /category/queryCategoryByArrayStore
     * author : sunpanhu
     * createTime : 2018/2/7 下午4:53
     */
    @RequestMapping(value = "queryCategoryByArrayStore")
    public String queryCategoryByArrayStore(String[] storeId){

        try {
            List<Map<String, Object>> maps = categoryService.selectCategoryByStoreId(storeId);

            if (maps == null){
                maps = new ArrayList<>();
            }

            return JsonUtil.toJson(ResultDTOBuilder.success(maps));
        } catch (Exception e) {
            return JsonUtil.toJson(ResultDTOBuilder.failure("001","数据异常，请稍后重试"));
        }
    }

    /**
     * 根据一级分类的ID查询二级分类的数据
     * /category/selectSecondLevelCategoryByPid
     * author : sunpanhu
     * createTime : 2018/2/8 下午12:48
     */
    @RequestMapping("selectSecondLevelCategoryByPid")
    public String selectSecondLevelCategoryByPid(String storesId, String pid, @RequestParam(required = false) String brandName){
        Map<String,Object> map = new HashMap<>();
        map.put("storesId",storesId);
        map.put("pid",pid);

        if (StringUtils.isNotBlank(brandName)){
            map.put("brandName",brandName);
        }

        try {
            List<Category> categories = categoryService.selectCategoryByPid(map);

            if (categories == null){
                categories = new ArrayList<>();
            }

            return JsonUtil.toJson(ResultDTOBuilder.success(categories));
        } catch (Exception e) {
            return JsonUtil.toJson(ResultDTOBuilder.failure("001","数据异常，请稍后重试"));
        }
    }
}
