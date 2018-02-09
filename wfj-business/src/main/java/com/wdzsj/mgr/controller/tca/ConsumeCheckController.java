package com.wdzsj.mgr.controller.tca;

import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.entity.cac.*;
import com.wdzsj.mgr.service.cac.CheckAccountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wdzsj.cmn.entity.PageRequest.newPageRequest;

/**
 * 消费核对报表操作
 */
@Controller
@RequestMapping(value="/tca")
@SuppressWarnings({"all", "rawtypes"})
public class ConsumeCheckController {

    @Resource
    private CheckAccountService checkAccountService;

    private final static String PAGE_INDEX = "tca/index";
    private final static String PAGE_SHOW = "tca/show";
    private final static String PAGE_SHOWDETAIL = "tca/showdetail";

    /**
     * 消费核对表首页列表数据
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

        Map<String, Object> param = pageRequest.getAllFilters();

        List<ConsumeCheckVo> consumeList = checkAccountService.findConsumeList(param);

        if (consumeList != null && consumeList.size() > 0) {
            for (ConsumeCheckVo consumeCheckVo : consumeList) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String transtime = format.format(consumeCheckVo.getTranstime());
                consumeCheckVo.setTime(transtime);
            }
        }

        Integer count = 0;
        if (consumeList!=null && consumeList.size()>0) {
            count = consumeList.get(0).getTotalNum();
        }

        Page page = new Page(pageRequest,count,consumeList);

        model.addAttribute("page", page);
        model.addAttribute("pageRequest", pageRequest);

        return PAGE_INDEX;
    }

    /**
     * 消费核对表根据首页transtime查询二级消费核对表列表
     * /tca/show/{transtime}
     * @param model
     * @param request
     * @param transtime
     * @return
     */
    @RequestMapping("/show/{transtime}")
    public String show(ModelMap model,HttpServletRequest request,@PathVariable String transtime){

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
        Map<String, Object> param = pageRequest.getAllFilters();

        if (StringUtils.isNotBlank(transtime)) {
            param.put("transtime",transtime);
        }

        List<ConsumeCheckSecondLevelDetailVo> volist = checkAccountService.findCheckAccountVoByTransTime(param);

        Integer count = 0;
        if (volist!=null && volist.size()>0) {
            count = volist.get(0).getTotalNum();
        }

        Page page = new Page(pageRequest, count, volist);

        model.addAttribute("page", page);
        model.addAttribute("transtime", transtime);
        model.addAttribute("pageRequest", pageRequest);

        return PAGE_SHOW;
    }

    /**
     * 二级消费核对表详情页面搜索
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/showindex")
    public String showindex(ModelMap model,HttpServletRequest request) {
        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

        Map<String, Object> param = pageRequest.getAllFilters();
        String transtime = (String)param.get("transtime");

        List<ConsumeCheckSecondLevelDetailVo> list = checkAccountService.findCheckAccountVoByTransTime(param);

        Integer count = 0;
        if (list!=null && list.size()>0) {
            count = list.get(0).getTotalNum();
        }

        Page page = new Page(pageRequest, count, list==null?new ArrayList():list);

        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("transtime", transtime);
        model.addAttribute("pageRequest", pageRequest);
        return PAGE_SHOW;
    }

    /**
     * 根据storename查询三级消费核销对账列表
     * @param model
     * @param request
     * @param storename
     * @return
     */
    @RequestMapping("/showdetail/{storename}")
    public String showDetail(ModelMap model,HttpServletRequest request,@PathVariable String storename){

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
        Map<String, Object> param = pageRequest.getAllFilters();

        if (StringUtils.isNotBlank(storename)) {
            param.put("storename",storename);
        }

        List<ConsumeCheckThreeLevelDetailVo> volist = checkAccountService.findCheckAccountVoByStorename(param);

        int count = 0;
        if (volist!=null && volist.size()>0) {
            count = volist.get(0).getTotaleCount();
        }

        Page page = new Page(pageRequest, count, volist);

        model.addAttribute("page", page);
        model.addAttribute("storename", storename);
        model.addAttribute("pageRequest", pageRequest);

        return PAGE_SHOWDETAIL;
    }

    /**
     * 三级消费核销对账搜索
     * @param model
     * @param request
     * @param storename
     * @return
     */
    @RequestMapping("/showThreeDetail")
    public String showThreeDetail(ModelMap model,HttpServletRequest request){

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
        Map<String, Object> param = pageRequest.getAllFilters();
        String storename = (String)param.get("storename");

        List<ConsumeCheckThreeLevelDetailVo> volist = checkAccountService.findCheckAccountVoByStorename(param);

        int count = 0;
        if (volist!=null && volist.size()>0) {
            count = volist.get(0).getTotaleCount();
        }

        Page page = new Page(pageRequest, count, volist);

        model.addAttribute("page", page);
        model.addAttribute("storename", storename);
        model.addAttribute("pageRequest", pageRequest);

        return PAGE_SHOWDETAIL;
    }
}
