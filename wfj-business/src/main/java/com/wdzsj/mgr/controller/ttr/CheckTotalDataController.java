package com.wdzsj.mgr.controller.ttr;

import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.entity.ttr.CardActivation;
import com.wdzsj.mgr.entity.ttr.CardActiveVo;
import com.wdzsj.mgr.entity.ttr.SecondLevelTotalTableVo;
import com.wdzsj.mgr.entity.ttr.TotalTableVo;
import com.wdzsj.mgr.service.ttr.CheckTotalDataService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.wdzsj.cmn.entity.PageRequest.newPageRequest;

@Controller
@RequestMapping(value="/ttr")
@SuppressWarnings({"all", "rawtypes"})
public class CheckTotalDataController {

    @Resource
    private CheckTotalDataService checkTotalDataService;

    private final static String PAGE_INDEX = "ttr/index";
    private final static String PAGE_SHOW = "ttr/show";
    private final static String PAGE_SHOWDETAIL = "ttr/showdetail";

    /**
     * 汇总表查询首页列表数据
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

        Map<String, Object> param = pageRequest.getAllFilters();
        String startDate = (String)param.get("startDate");
        String endDate = (String)param.get("endDate");

        Map<String,Object> map = new HashMap<>();

        //添加搜索条件
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            map.put("startDate",startDate);
            map.put("endDate",endDate);
        }/* else {//没有搜索条件时默认：一个月前的时间~~当前时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();

            //过去一月
            Date date = new Date();
            c.setTime(date);
            c.add(Calendar.MONTH, -1);
            Date monthDate = c.getTime();
            String beforeOneMonth = format.format(monthDate);//过去一个月时间
            String currentTime = format.format(date);//当前时间

            map.put("startDate",beforeOneMonth);
            map.put("endDate",currentTime);
        }*/

        //获取查询结果
        List<TotalTableVo> list = checkTotalDataService.findList(map);

        //总记录数
        Integer count = ((list==null)?0:list.size());
        //每一页的长度
        int pageSize = pageRequest.getPageSize();
        int pageNumber = pageRequest.getPageNumber() - 1;

        //创建新的集合封装数据
        List<TotalTableVo> finalList = new ArrayList<>();

        if (pageSize >= count){//判断每一页的长度是否大于总记录数，如果大于 就说明 只有一页数据 且长度是总记录数
            finalList.addAll(list);
        }else {//每页长度小于总记录数
            if (pageNumber == 0) {//第一页
                finalList.addAll(list.subList(pageNumber, pageSize));
            } else if (pageNumber > 0){//不是第一页
                int toIndex = 0;
                if(pageNumber*pageSize >= count){
                    toIndex = count;
                }else if(pageNumber*pageSize+pageSize >= count){
                    toIndex = count;
                } else {
                    toIndex = pageNumber*pageSize+pageSize;
                }
                finalList.addAll(list.subList(pageNumber*pageSize, toIndex));
            }
        }

        Page page = new Page(pageRequest,count,finalList);

        model.addAttribute("page", page);
        model.addAttribute("pageRequest", pageRequest);
        return PAGE_INDEX;
    }

    /**
     * 根据一级汇总表payFinishTime查看详情
     * /ttr/show/{payFinishTime}
     * @param model
     * @param request
     * @param payFinishTime
     * @return
     */
    @RequestMapping("/show/{payFinishTime}")
    public String show(ModelMap model,HttpServletRequest request,@PathVariable String payFinishTime){

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

        Map<String, Object> param = pageRequest.getAllFilters();

        if (StringUtils.isNotBlank(payFinishTime)) {
            param.put("payFinishTime",payFinishTime);
        }

        Integer count = checkTotalDataService.countAll(param);

        List<CardActivation> volist = checkTotalDataService.findTTRByPayFinishTime(param);

        Page page = new Page(pageRequest, count, volist);

        model.addAttribute("list", volist);
        model.addAttribute("page", page);
        model.addAttribute("payFinishTime", payFinishTime);

        return PAGE_SHOW;
    }

    /**
     * 二级汇总表详情页面搜索
     * /ttr/showindex
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/showindex")
    public String showindex(ModelMap model,HttpServletRequest request) {
        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

        Map<String, Object> param = pageRequest.getAllFilters();
        int pageSize = (int) param.get("pageSize");
        int offset = (int) param.get("offset");
        String startDate = (String) param.get("startDate");
        String endDate = (String) param.get("endDate");
        String payFinishTime = (String) param.get("payFinishTime");

        Map<String, Object> map = new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("offset",offset);

        if ((StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate))) {
            map.put("startDate", startDate);
            map.put("endDate", endDate);
        }else {
            if (StringUtils.isNotBlank(payFinishTime)) {
                map.put("startDate",payFinishTime);
                map.put("endDate",payFinishTime);
            } /*else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();

                //过去一月
                Date date = new Date();
                c.setTime(date);
                c.add(Calendar.MONTH, -1);
                Date monthDate = c.getTime();
                String beforeOneMonth = format.format(monthDate);//过去一个月时间
                String currentTime = format.format(date);//当前时间

                map.put("startDate",beforeOneMonth);
                map.put("endDate",currentTime);
            }*/
        }


        List<SecondLevelTotalTableVo> list = checkTotalDataService.searchTTRByTime(map);

        Integer count = 0;
        if (list!=null&&list.size()>0) {
            count = list.get(0).getTotalNum();
        }

        Page page = new Page(pageRequest, count, ((list==null)? new ArrayList<TotalTableVo>() : list));
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("payFinishTime", payFinishTime);
        model.addAttribute("pageRequest", pageRequest);
        return PAGE_SHOW;
    }

    /**
     * 根据二级汇总表的transId查看三级详情
     * /ttr/showDetail/{transId}
     * @param model
     * @param request
     * @param transId
     * @return
     */
    @RequestMapping("/showDetail/{transId}")
    public String showDetail(ModelMap model,HttpServletRequest request,@PathVariable String transId){

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
        Map<String, Object> param = pageRequest.getAllFilters();

        if (StringUtils.isNotBlank(transId)) {
            param.put("transId",transId);
        }

        List<CardActiveVo> volist = checkTotalDataService.findTTRByTransId(param);

        Integer count = 0;
        if (volist!=null && volist.size()>0) {
            count = volist.get(0).getTotalNum();
        }

        Page page = new Page(pageRequest, count, volist);

        model.addAttribute("list", volist);
        model.addAttribute("page", page);
        model.addAttribute("transId", transId);

        return PAGE_SHOWDETAIL;
    }

    /**
     * 三级汇总表详情页面搜索
     * /ttr/showDetailIndex
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/showDetailIndex")
    public String showDetailIndex(ModelMap model,HttpServletRequest request) {
        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

        Map<String, Object> paramp=pageRequest.getAllFilters();
        String amount = (String) paramp.get("amount");
        String order_number = (String) paramp.get("order_number");
        String transId = (String) paramp.get("transId");

        if (StringUtils.isNotBlank(amount)) {
            paramp.put("price", amount);
        }
        if (StringUtils.isNotBlank(order_number)) {
            paramp.put("transId", order_number);
        }

        List<CardActiveVo> list = checkTotalDataService.searchTTRByPriceAndTransId(paramp);

        Integer count = 0;
        if (list!=null && list.size()>0) {
            count = list.get(0).getTotalNum();
        }

        Page page = new Page(pageRequest, count, ((list==null)?new ArrayList():list));
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("transId", transId);
        model.addAttribute("pageRequest", pageRequest);

        return PAGE_SHOWDETAIL;
    }
}
