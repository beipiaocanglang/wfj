package com.wdzsj.mgr.controller.mktactive;

import com.wdzsj.cmn.util.HttpClientUtils;
import com.wdzsj.mgr.castor.util.JsonUtil;
import com.wdzsj.mgr.castor.util.ResultDTOBuilder;
import com.wdzsj.mgr.controller.marketing.ActiveEchoController;
import com.wdzsj.mgr.entity.marketing.vo.ActiveSeedVo;
import com.wdzsj.mgr.entity.marketing.vo.ZhiSuanVo;
import com.wdzsj.mgr.service.marketing.ActiveService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 种子会员操作
 * author : sunpanhu
 * create time : 2018/2/8 下午5:34
 */
@Controller
@RequestMapping("/mktactive/seed")
@SuppressWarnings({"all", "rawtypes"})
public class MktactiveSeedController {
    //活动列表服务
    @Resource
    private ActiveService activeService;
    //公共部分
    @Resource
    private ActiveEchoController activeEchoController;

    /**
     * 根据时间获取数据(近3个月、近6个月、近一年)
     * /mktactive/seed/queryMemberBytime
     * @param buyTime
     *      取值：
     *          0：近3个月
     *          1：近6个月
     *          2：近一年
     * @param startTime 自定义时的开始时间
     * @param startTime 自定义时的结束时间
     * author : sunpanhu
     * createTime : 2018/2/8 下午5:57
     */
    @ResponseBody
    @RequestMapping(value = "/queryMemberBytime", method= RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String queryMemberBytime(@RequestParam(required = false)Integer buyTime,
                                    @RequestParam(required = false)String startTime,
                                    @RequestParam(required = false)String endTime,
                                    @RequestParam(required = false)String buyRate,/*购买频次*/
                                    @RequestParam(required = false)String buyPerTicketSales,/*客单价*/
                                    @RequestParam(required = false)String buyTotalAmount,/*累计购买金额*/
                                    Long actId){

        if (buyTime == null){
            if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)){
                return JsonUtil.toJson(ResultDTOBuilder.failure("003","请选择时间"));
            }
        } else {
            if (StringUtils.isNotBlank(startTime) || StringUtils.isNotBlank(endTime)){
                return JsonUtil.toJson(ResultDTOBuilder.failure("003","只能选择一个时间"));
            }
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //当前时间
        Date date = new Date();
        c.setTime(date);
        String currentTime = format.format(date);//当前时间

        //时间数组
        String[] time = null;
        if (buyTime != null){
            switch (buyTime){
                case 0:
                    c.add(Calendar.MONTH, -3);
                    Date threeMonthMDate = c.getTime();
                    String threeMonthBefore = format.format(threeMonthMDate);//近3个月的时间
                    time = new String[]{threeMonthBefore,currentTime};
                    break;
                case 1:
                    c.add(Calendar.MONTH, -6);
                    Date fourMonthMDate = c.getTime();
                    String fourMonthBefore = format.format(fourMonthMDate);//近6个月的时间
                    time = new String[]{fourMonthBefore,currentTime};
                    break;
                case 2:
                    c.add(Calendar.MONTH, -12);
                    Date twelveMonthMDate = c.getTime();
                    String twelveMonthBefore = format.format(twelveMonthMDate);//近一年的时间
                    time = new String[]{twelveMonthBefore,currentTime};
                    break;
            }
        } else {
            time = new String[]{startTime,endTime};
        }

        String[] storeArray = new String[0];
        String[] seedCategoryIdArray = new String[0];
        try {
            ActiveSeedVo activeSeedVo = activeService.selectEditActiveSeedByActId(actId);

            String storeId = activeSeedVo.getStoreId();
            String seedCategoryData = activeSeedVo.getSeedCategoryData();

            //门店数组
            storeArray = storeId.split(",");
            //品类数组
            seedCategoryIdArray = seedCategoryData.split("_")[1].split(",");
        } catch (Exception e) {
            return JsonUtil.toJson(ResultDTOBuilder.failure("005","获取活动信息失败，请稍后重试"));
        }

        //调用获取种子会员数据接口

        Map<String,Object> params = new HashMap();
        params.put("cityids",storeArray);
        params.put("ppids",seedCategoryIdArray);
        params.put("years",time);
        if (StringUtils.isNotBlank(buyRate)){
            params.put("buyRate",buyRate);
        }
        if (StringUtils.isNotBlank(buyPerTicketSales)){
            params.put("buyPerTicketSales",buyPerTicketSales);
        }
        if (StringUtils.isNotBlank(buyTotalAmount)){
            params.put("buyTotalAmount",buyTotalAmount);
        }

        try {
            Map<String, Object> resultMap = activeEchoController.zhiSuanHttp(params);

            if (resultMap.get("msg").equals("success")){
                String result = (String) resultMap.get("data");
                Map<String,Object> memberMap = JsonUtil.parseJson(result, Map.class);

                List<ZhiSuanVo> finalList = activeEchoController.countSeedNum(memberMap);
                return JsonUtil.toJson(ResultDTOBuilder.success(finalList));
            }

            return JsonUtil.toJson(ResultDTOBuilder.failure((String) resultMap.get("code"),(String) resultMap.get("success")));
        } catch (Exception e) {
            return JsonUtil.toJson(ResultDTOBuilder.failure("004","筛选失败，请稍后重试"));
        }
    }
}
