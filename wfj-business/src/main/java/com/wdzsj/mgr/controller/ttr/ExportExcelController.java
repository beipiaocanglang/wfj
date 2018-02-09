package com.wdzsj.mgr.controller.ttr;

import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.castor.util.ExcelHelper;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.ttr.CardActiveVo;
import com.wdzsj.mgr.entity.ttr.SecondLevelTotalTableVo;
import com.wdzsj.mgr.entity.ttr.TotalTableVo;
import com.wdzsj.mgr.service.ttr.CheckTotalDataService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.wdzsj.cmn.entity.PageRequest.newPageRequest;

@Controller
@RequestMapping(value="/ttr/export")
@SuppressWarnings({"all", "rawtypes"})
public class ExportExcelController extends BaseController {

    @Resource
    private CheckTotalDataService checkTotalDataService;

    private final static String PAGE_INDEX = "ttr/index";
    private final static String PAGE_SHOW = "ttr/show";
    private final static String PAGE_SHOWDETAIL = "ttr/showdetail";

    /**
     * 门店汇总一级报表导出Excel表格
     * /ttr/export/exportIndexExcel
     * @param model
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/exportIndexExcel",method= RequestMethod.GET)
    @ResponseBody
    public String export(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException{

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

        Map<String, Object> param = pageRequest.getAllFilters();
        int size=(int) param.get("pageSize");//每一页的条数
        String startDate = (String)param.get("startDate");
        String endDate = (String)param.get("endDate");

        Map<String,Object> map = new HashMap<>();

        //添加搜索条件
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            map.put("startDate",startDate);
            map.put("endDate",endDate);
        } /*else {//没有搜索条件时默认：一个月前的时间~~当前时间
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
        }
*/
        //获取查询结果
        List<TotalTableVo> list = checkTotalDataService.findList(map);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String msg = new String(("汇总表查询列表_" + format.format(new Date()) + ".xlsx").getBytes(), "ISO-8859-1");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+msg);
        response.flushBuffer();

        new ExcelHelper(TotalTableVo.class).exportExcel(list,"门店消费查询列表",response.getOutputStream()) ;

        return PAGE_INDEX;
    }

    /**
     * 门店汇总二级报表导出Excel表格
     * /ttr/export/exporSecondLevelExcel
     * @param model
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/exporSecondLevelExcel",method= RequestMethod.GET)
    @ResponseBody
    public String exporSecondLevelExcel(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException{

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

        Map<String, Object> param = pageRequest.getAllFilters();
        String startDate = (String) param.get("startDate");
        String endDate = (String) param.get("endDate");
        String payFinishTime = (String) param.get("payFinishTime");

        Map<String, Object> map = new HashMap<>();

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

        //获取查询结果
        List<SecondLevelTotalTableVo> list = checkTotalDataService.exportSecondLevelTTRByTime(map);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String msg = new String(("汇总表二级报表查询_" + format.format(new Date()) + ".xlsx").getBytes(), "ISO-8859-1");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+msg);
        response.flushBuffer();

        new ExcelHelper(SecondLevelTotalTableVo.class).exportExcel(list,"汇总表二级报表查询",response.getOutputStream()) ;

        model.addAttribute("payFinishTime",payFinishTime);
        return PAGE_SHOW;
    }

    /**
     * 门店汇总三级报表导出Excel表格
     * /ttr/export/exporThreeLevelExcel
     * @param model
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/exporThreeLevelExcel",method= RequestMethod.GET)
    @ResponseBody
    public String exporThreeLevelExcel(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException{

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

        Map<String, Object> paramp=pageRequest.getAllFilters();
        String amount = (String) paramp.get("amount");
        String order_number = (String) paramp.get("order_number");
        String transId = (String) paramp.get("transId");

        if (StringUtils.isNotBlank(transId)) {
            paramp.put("transId", transId);
        } else {
            if (StringUtils.isNotBlank(amount)) {
                paramp.put("price", amount);
            }
            if (StringUtils.isNotBlank(order_number)) {
                paramp.put("transId", order_number);
            }
        }

        List<CardActiveVo> list = checkTotalDataService.searchTTRByPriceAndTransId(paramp);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String msg = new String(("汇总表三级报表查询_" + format.format(new Date()) + ".xlsx").getBytes(), "ISO-8859-1");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+msg);
        response.flushBuffer();

        new ExcelHelper(CardActiveVo.class).exportExcel(list,"汇总表三级报表查询",response.getOutputStream()) ;

        model.addAttribute("transId",transId);
        return PAGE_SHOWDETAIL;
    }
}
