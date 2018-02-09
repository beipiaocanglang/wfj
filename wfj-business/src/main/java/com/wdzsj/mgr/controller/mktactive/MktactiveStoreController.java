package com.wdzsj.mgr.controller.mktactive;

import com.wdzsj.mgr.castor.util.JsonUtil;
import com.wdzsj.mgr.castor.util.ResultDTOBuilder;
import com.wdzsj.mgr.entity.marketing.Stores;
import com.wdzsj.mgr.entity.marketing.vo.StoresVo;
import com.wdzsj.mgr.service.marketing.StoresService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 门店查询
 * author : sunpanhu
 * create time : 2018/2/7 下午4:06
 */
@Controller
@ResponseBody
@RequestMapping(value = "/store", method= RequestMethod.GET, produces = "text/html;charset=UTF-8")
@SuppressWarnings({"all", "rawtypes"})
public class MktactiveStoreController {

    //门店服务
    @Resource
    private StoresService storesService;

    /**
     * 获取所有门店信息
     * /store/queryAllStore
     * author : sunpanhu
     * createTime : 2018/2/7 下午4:07
     */
    @RequestMapping("/queryAllStore")
    public String queryAllStore(){

        try {
            List<StoresVo> storesVos = storesService.queryAllStore();

            if (storesVos==null){
                storesVos = new ArrayList<>();
            }

            return JsonUtil.toJson(ResultDTOBuilder.success(storesVos));
        } catch (Exception e) {
            return JsonUtil.toJson(ResultDTOBuilder.failure("002","数据异常，请稍后重试"));
        }
    }

    /**
     * 根据门店ID查询二级门店列表
     * /store/querySecondLevelStoreListByStoreId
     * author : sunpanhu
     * createTime : 2018/2/7 下午4:46
     */
    @RequestMapping("/querySecondLevelStoreListByStoreId")
    public String querySecondLevelStoreListByStoreId(Integer storeId){
        try {
            List<Stores> storeList = storesService.querySecondLevelStoreListByStoreId(storeId);

            if (storeList==null){
                storeList = new ArrayList<>();
            }

            return JsonUtil.toJson(ResultDTOBuilder.success(storeList));
        } catch (Exception e) {
            return JsonUtil.toJson(ResultDTOBuilder.failure("002","数据异常，请稍后重试"));
        }
    }
}
