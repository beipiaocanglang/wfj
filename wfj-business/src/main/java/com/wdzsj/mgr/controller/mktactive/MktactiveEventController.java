package com.wdzsj.mgr.controller.mktactive;

import com.wdzsj.mgr.castor.util.JsonUtil;
import com.wdzsj.mgr.castor.util.ResultDTOBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动计划中的活动事件
 * author : sunpanhu
 * create time : 2018/2/8 下午1:05
 */
@Controller
@RequestMapping("/active")
@SuppressWarnings({"all", "rawtypes"})
public class MktactiveEventController {

    /**
     * 获取活动计划中的活动事件(固定的事件)
     * /active
     * author : sunpanhu
     * createTime : 2018/2/8 下午1:07
     */
    @ResponseBody
    @RequestMapping(value = "/queryActiveEvent", method= RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String queryActiveEvent(){

        Map<String,Object> map1 = new HashMap<>();
        map1.put("id",1);
        map1.put("eventName","事件名称1");
        Map<String,Object> map2 = new HashMap<>();
        map2.put("id",2);
        map2.put("eventName","事件名称2");
        Map<String,Object> map3 = new HashMap<>();
        map3.put("id",3);
        map3.put("eventName","事件名称3");
        Map<String,Object> map4 = new HashMap<>();
        map4.put("id",4);
        map4.put("eventName","事件名称4");
        Map<String,Object> map5 = new HashMap<>();
        map5.put("id",5);
        map5.put("eventName","事件名称5");
        Map<String,Object> map6 = new HashMap<>();
        map6.put("id",6);
        map6.put("eventName","事件名称6");
        Map<String,Object> map7 = new HashMap<>();
        map7.put("id",7);
        map7.put("eventName","事件名称7");
        Map<String,Object> map8 = new HashMap<>();
        map8.put("id",8);
        map8.put("eventName","事件名称8");

        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);

        return JsonUtil.toJson(ResultDTOBuilder.success(list));
    }
}
