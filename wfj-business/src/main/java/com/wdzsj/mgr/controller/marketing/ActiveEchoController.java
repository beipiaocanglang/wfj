package com.wdzsj.mgr.controller.marketing;

import com.wdzsj.cmn.util.HttpClientUtils;
import com.wdzsj.mgr.castor.util.JsonUtil;
import com.wdzsj.mgr.entity.marketing.ActiveTagRelation;
import com.wdzsj.mgr.entity.marketing.Category;
import com.wdzsj.mgr.entity.marketing.Stores;
import com.wdzsj.mgr.entity.marketing.vo.*;
import com.wdzsj.mgr.service.marketing.ActiveService;
import com.wdzsj.mgr.service.marketing.ActiveTagRelationService;
import com.wdzsj.mgr.service.marketing.CategoryService;
import com.wdzsj.mgr.service.marketing.StoresService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.util.*;

/**
 * 所有controller 回显时的公共查询 提取
 * author : sunpanhu
 * create time : 2018/2/6 上午10:20
 */
@Component
public class ActiveEchoController {
    //活动列表服务
    @Resource
    private ActiveService activeService;

    //门店所在城市服务
    @Resource
    private StoresService storesService;

    //品类服务
    @Resource
    private CategoryService categoryService;

    //标签服务
    @Resource
    private ActiveTagRelationService activeTagRelationService;

    /**
     * 用于所有页面的回显
     * @param storeId
     * @param actId
     * @param model
     * author : sunpanhu
     * createTime : 2018/2/9 下午1:27
     */
    public void selectAllCommon(String[] storeId, Long actId, ModelMap model){

        List<Map<String, Object>> categoryList = new ArrayList<>();
        //根据门店ID查询对应的一级品类信息
        if (storeId!=null&&storeId.length>0){
            categoryList = categoryService.selectCategoryByStoreId(storeId);
        }

        //回显保存的数据
        CreateEditActiveVo createEditActiveVo = activeService.selectCreateEditActiveDataByActId(actId);
        if (StringUtils.isNotBlank(createEditActiveVo.getKpiValue())){
            //封装 kpi 指标
            String editKpiValue = createEditActiveVo.getKpiValue();
            String[] split = editKpiValue.split(",");

            for (int x=0;x<split.length;x++) {
                String kpiKey = split[x].substring(0, 1);
                String Kpivalue = split[x].substring(2, split[x].length());
                if (kpiKey.equals("1")){
                    createEditActiveVo.setSaleKpiValue(Kpivalue);
                } else if (kpiKey.equals("2")){
                    createEditActiveVo.setKeliuKpiValue(Kpivalue);
                }else if (kpiKey.equals("3")){
                    createEditActiveVo.setMaoliKpiValue(Kpivalue);
                }
            }
        }

        //获取扩散标签数据
        String tagData = "";
        if (StringUtils.isNotBlank(createEditActiveVo.getTagData())){
            tagData = createEditActiveVo.getTagData();
        } else {
            Map<String, Object> map = this.labelData();
            map.put("province",null);
            map.put("county",null);
            map.put("city",null);
            tagData = JsonUtil.toJson(map);
        }
        Map<String, List<LabelVo>> map = JsonUtil.parseJson(tagData, Map.class);

        //获取所有门店名称及 所有信息
        List<StoresVo> list = storesService.findList();

        model.addAttribute("labelMapVo",map);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("list",list);
        model.addAttribute("createEditActiveVo",createEditActiveVo);
    }

    public void saveLabel(LabelSpreadVo labelSpreadVo){
        Map<String, Object> map = this.labelData();
        map.put("province",labelSpreadVo.getProvince());
        map.put("county",labelSpreadVo.getCounty());
        map.put("city",labelSpreadVo.getCity());
        map.put("alienBrandMemberNum",labelSpreadVo.getAlienBrandMemberNum());
        map.put("targetKpiNum",labelSpreadVo.getTargetKpiNum());
        map.put("alienBrandSeedMemberNum",labelSpreadVo.getAlienBrandSeedMemberNum());

        //年龄
        if (labelSpreadVo.getAge()!=null){
            String[] age = labelSpreadVo.getAge();
            List<LabelVo> ageList = (List<LabelVo>)map.get("age");
            this.setValue(age, ageList);
        }
        //消费定位
        if (labelSpreadVo.getConsumeLocation()!=null){
            String[] consumeLocation = labelSpreadVo.getConsumeLocation();
            List<LabelVo> ageList = (List<LabelVo>)map.get("consumeLocation");
            this.setValue(consumeLocation, ageList);

        }
        //是否已婚
        if (labelSpreadVo.getIsMarriage()!=null){
            String[] isMarriage = labelSpreadVo.getIsMarriage();
            List<LabelVo> ageList = (List<LabelVo>)map.get("isMarriage");
            this.setValue(isMarriage, ageList);
        }
        //性别
        if (labelSpreadVo.getGender()!=null){
            String[] gender = labelSpreadVo.getGender();
            List<LabelVo> ageList = (List<LabelVo>)map.get("gender");
            this.setValue(gender, ageList);
        }
        //客单价
        if (labelSpreadVo.getPerTicketSalesPrice()!=null){
            String[] perTicketSalesPrice = labelSpreadVo.getPerTicketSalesPrice();
            List<LabelVo> ageList = (List<LabelVo>)map.get("perTicketSalesPrice");
            this.setValue(perTicketSalesPrice, ageList);
        }
        //RFV标签
        if (labelSpreadVo.getRfvLabel()!=null){
            String[] rfvLabel = labelSpreadVo.getRfvLabel();
            List<LabelVo> ageList = (List<LabelVo>)map.get("rfvLabel");
            this.setValue(rfvLabel, ageList);
        }
        //购物频次
        if (labelSpreadVo.getShoppingFrequency()!=null){
            String[] shoppingFrequency = labelSpreadVo.getShoppingFrequency();
            List<LabelVo> ageList = (List<LabelVo>)map.get("shoppingFrequency");
            this.setValue(shoppingFrequency, ageList);
        }

        ActiveTagRelation activeTagRelation = new ActiveTagRelation();
        activeTagRelation.setActId(labelSpreadVo.getS_actId());
        activeTagRelation.setTagData(JsonUtil.toJson(map));

        //根据活动表的ID查询对应的活动标签关联表的数据
        ActiveTagRelation activeTag = activeTagRelationService.selectByactId(labelSpreadVo.getS_actId());

        if (activeTag == null){
            //为null 说明在种子人群页面时没有选择品牌/品类 根据保存的活动ID动态的插入对应的标签数据
            activeTagRelationService.insertSelective(activeTagRelation);
        } else {
            //不为null 说明在种子人群页面时选择品牌/品类 并且保存了数据  这时需要修改
            activeTagRelationService.updateByIdSelective(activeTagRelation);
        }
    }

    public void setValue(String[] array, List<LabelVo> list){
        for(int x=0;x<array.length;x++){
            for (LabelVo labelVo : list) {
                if (array[x].equals(labelVo.getLabelValue())){
                    labelVo.setIsChecked("checked");
                }
                labelVo.setCheckedNum(array.length);
            }
        }
    }

    //封装标签数据
    public Map<String,Object> labelData(){
        //年龄
        List<LabelVo> ageList  = new ArrayList();
        LabelVo labelVo1 = new LabelVo();
        labelVo1.setLabelValue("17岁及以下");
        LabelVo labelVo2 = new LabelVo();
        labelVo2.setLabelValue("18-25岁");
        LabelVo labelVo3 = new LabelVo();
        labelVo3.setLabelValue("26-40岁");
        LabelVo labelVo4 = new LabelVo();
        labelVo4.setLabelValue("41-55岁");
        LabelVo labelVo5 = new LabelVo();
        labelVo5.setLabelValue("56岁及以上");
        LabelVo labelVo6 = new LabelVo();
        labelVo6.setLabelValue("未知");
        ageList.add(labelVo1);
        ageList.add(labelVo2);
        ageList.add(labelVo3);
        ageList.add(labelVo4);
        ageList.add(labelVo5);
        ageList.add(labelVo6);

        //性别
        List<LabelVo> genderList  = new ArrayList();
        LabelVo labelVo7 = new LabelVo();
        labelVo7.setLabelValue("男");
        LabelVo labelVo8 = new LabelVo();
        labelVo8.setLabelValue("女");
        LabelVo labelVo9 = new LabelVo();
        labelVo9.setLabelValue("未知");
        genderList.add(labelVo7);
        genderList.add(labelVo8);
        genderList.add(labelVo9);

        //婚否
        List<LabelVo> isMarriageList  = new ArrayList();
        LabelVo labelVo10 = new LabelVo();
        labelVo10.setLabelValue("已婚");
        LabelVo labelVo11 = new LabelVo();
        labelVo11.setLabelValue("未婚");
        LabelVo labelVo12 = new LabelVo();
        labelVo12.setLabelValue("未知");
        isMarriageList.add(labelVo10);
        isMarriageList.add(labelVo11);
        isMarriageList.add(labelVo12);

        //消费定位
        List<LabelVo> consumeLocationList  = new ArrayList();
        LabelVo labelVo13 = new LabelVo();
        labelVo13.setLabelValue("大众品牌");
        LabelVo labelVo14 = new LabelVo();
        labelVo14.setLabelValue("时尚品牌");
        LabelVo labelVo15 = new LabelVo();
        labelVo15.setLabelValue("高端品牌");
        LabelVo labelVo16 = new LabelVo();
        labelVo16.setLabelValue("奢侈品牌");
        consumeLocationList.add(labelVo13);
        consumeLocationList.add(labelVo14);
        consumeLocationList.add(labelVo15);
        consumeLocationList.add(labelVo16);

        //购物频次
        List<LabelVo> shoppingFrequencyList  = new ArrayList();
        LabelVo labelVo17 = new LabelVo();
        labelVo17.setLabelValue("一周一次");
        LabelVo labelVo18 = new LabelVo();
        labelVo18.setLabelValue("一月一次");
        LabelVo labelVo19 = new LabelVo();
        labelVo19.setLabelValue("未知");
        shoppingFrequencyList.add(labelVo17);
        shoppingFrequencyList.add(labelVo18);
        shoppingFrequencyList.add(labelVo19);

        //客单价
        List<LabelVo> perTicketSalesPriceList  = new ArrayList();
        LabelVo labelVo20 = new LabelVo();
        labelVo20.setLabelValue("-37102-831元");
        LabelVo labelVo21 = new LabelVo();
        labelVo21.setLabelValue("832-1652元");
        LabelVo labelVo22 = new LabelVo();
        labelVo22.setLabelValue("1653-3016元");
        LabelVo labelVo23 = new LabelVo();
        labelVo23.setLabelValue("3017-6254元");
        LabelVo labelVo24 = new LabelVo();
        labelVo24.setLabelValue("6255-7161700元");
        perTicketSalesPriceList.add(labelVo20);
        perTicketSalesPriceList.add(labelVo21);
        perTicketSalesPriceList.add(labelVo22);
        perTicketSalesPriceList.add(labelVo23);
        perTicketSalesPriceList.add(labelVo24);

        //RFV标签
        List<LabelVo> rfvLabeList  = new ArrayList();
        LabelVo labelVo25 = new LabelVo();
        labelVo25.setLabelValue("重要价值客户");
        LabelVo labelVo26 = new LabelVo();
        labelVo26.setLabelValue("重要保持客户");
        LabelVo labelVo27 = new LabelVo();
        labelVo27.setLabelValue("重要发展客户");
        LabelVo labelVo28 = new LabelVo();
        labelVo28.setLabelValue("重要挽留客户");
        LabelVo labelVo29 = new LabelVo();
        labelVo29.setLabelValue("一般类客户");
        LabelVo labelVo30 = new LabelVo();
        labelVo30.setLabelValue("未知");
        rfvLabeList.add(labelVo25);
        rfvLabeList.add(labelVo26);
        rfvLabeList.add(labelVo27);
        rfvLabeList.add(labelVo28);
        rfvLabeList.add(labelVo29);
        rfvLabeList.add(labelVo30);

        Map<String,Object> map = new HashMap<>();
        map.put("age",ageList);
        map.put("gender",genderList);
        map.put("isMarriage",isMarriageList);
        map.put("consumeLocation",consumeLocationList);
        map.put("shoppingFrequency",shoppingFrequencyList);
        map.put("perTicketSalesPrice",perTicketSalesPriceList);
        map.put("rfvLabel",rfvLabeList);

        return map;
    }

    //智算是调用远程http
    public List<ZhiSuanVo> countSeedNum(Map<String,Object> dataMap){
        List<ZhiSuanVo> finalList = new ArrayList<>();

        //循环最外层map  得到用户信息以及对应的 用户交易记录信息
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {

            //获取map的key 对应的是交易信息或用户信
            String key = entry.getKey();

            //获取用户交易信息
            if (!key.equals("userinfo")){
                String[] split = key.split("\\_");

                String storeId = split[0];
                String categoryId = split[1];
                String userId = split[3];

                Map<String,String> map = new HashMap<>();
                map.put("storeId",storeId);
                map.put("categoryId",categoryId);
                Category category = categoryService.selectPpNameByPpAndStoreId(map);

                Stores stores = storesService.selectStoreNameByStoreId(storeId);

                //循环嘴外层map 获取用户信息
                for (Map.Entry<String, Object> userinfo : dataMap.entrySet()) {
                    String userKey = userinfo.getKey();
                    //获取用户信
                    if (userKey.equals("userinfo")){
                        //获取用户信集合
                        List<LinkedHashMap> userinfoValue = (List<LinkedHashMap>)userinfo.getValue();

                        for (LinkedHashMap m : userinfoValue) {
                            String rowkey = (String)m.get("rowkey");

                            if (rowkey.equals(userId)){//用户id 和 交易信息的最后一个相等  说明是同一个用户

                                //获取用对应的交易信息
                                List<LinkedHashMap> transactionValue = (List<LinkedHashMap>) entry.getValue();

                                for (LinkedHashMap nn : transactionValue) {
                                    ZhiSuanVo zhiSuanVo = new ZhiSuanVo();

                                    zhiSuanVo.setCityName(stores.getProvinceCity());//用户消费的城市名称
                                    zhiSuanVo.setCityId(storeId);//用户消费的门店id
                                    zhiSuanVo.setPpName(category.getStandardPpname());//消费的品类名称
                                    zhiSuanVo.setPpId(categoryId);//消费的品类id
                                    zhiSuanVo.setYear(split[2]);//消费的时间  哪一年
                                    zhiSuanVo.setRowkey(userId);//用户id

                                    zhiSuanVo.setRowkey(rowkey);
                                    zhiSuanVo.setOpenid((String) m.get("openid"));
                                    zhiSuanVo.setAge_group((String) m.get("age_group"));
                                    zhiSuanVo.setPhone((String) m.get("phone"));
                                    zhiSuanVo.setSex((String) m.get("sex"));
                                    zhiSuanVo.setProvince((String) m.get("province"));

                                    zhiSuanVo.setWeekenddays((String) nn.get("weekenddays"));
                                    zhiSuanVo.setWeekendfrequency((String) nn.get("weekendfrequency"));
                                    zhiSuanVo.setWeekendsalemoney((String) nn.get("weekendsalemoney"));

                                    finalList.add(zhiSuanVo);
                                }
                            }
                        }
                    }
                }
            }
        }

        return finalList;
    }

    //智算请求 经过controller
    public Map<String,Object> zhiSuanHttp(Map<String,Object> params){
        String url = "http://localhost:8082/marketing/seed/simulationZhiSuanData";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String s = HttpClientUtils.doPost(httpClient, url, params);

        Map<String,Object> ResultMap = JsonUtil.parseJson(s, Map.class);

        return ResultMap;
    }
}
