package com.wdzsj.mgr.controller.marketing;

import com.wdzsj.mgr.castor.util.JsonUtil;
import com.wdzsj.mgr.entity.marketing.Category;
import com.wdzsj.mgr.service.marketing.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/marketing/category/")
@SuppressWarnings({"all", "rawtypes"})
public class ActiveCategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 根据pid查询二级分类数据
     * /marketing/category/selectCategoryByPid?storesId=&pid=
     * @param storesId
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectCategoryByPid", method= RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public Object selectCategoryByPid(String storesId, String pid){

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("errMsg", "接口调用成功");
        map.put("data", new ArrayList<>());

        try {
            if (StringUtils.isBlank(pid)){
                map.put("success", false);
                map.put("errMsg", "品类ID不能为空");
                JsonUtil.toJson(map);
            }

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("pid",pid);
            paramMap.put("storesId", storesId);

            List<Category> categories = categoryService.selectCategoryByPid(paramMap);
            map.put("data", categories);
            return JsonUtil.toJson(map);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", "系统异常，请稍后重试");
            return JsonUtil.toJson(map);
        }
    }

    /**
     * 根据用户输入的品牌名称模糊查询
     * /marketing/category/selectCategoryByBrandName?brandName=
     * @param storesId
     * @param pid
     * @param brandName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectCategoryByBrandName", method= RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public Object selectCategoryByBrandName(String storesId,String pid,String brandName){

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("errMsg", "接口调用成功");
        map.put("data", new ArrayList<>());

        try {
            if (StringUtils.isBlank(pid)){
                map.put("success", false);
                map.put("errMsg", "品类ID不能为空");
                JsonUtil.toJson(map);
            }
            Map<String, Object> paramMap = new HashMap<>();

            if (StringUtils.isNotBlank(brandName) && brandName!= "undefined"){
                paramMap.put("brandName",brandName.trim());
            }
            paramMap.put("pid",pid);
            paramMap.put("storesId", storesId);

            List<Category> categories = categoryService.selectCategoryByPid(paramMap);
            map.put("data", categories);
            return JsonUtil.toJson(map);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", "系统异常，请稍后重试");
            return JsonUtil.toJson(map);
        }
    }
}
