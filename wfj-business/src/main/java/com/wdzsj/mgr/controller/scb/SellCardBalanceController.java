package com.wdzsj.mgr.controller.scb;

import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.castor.util.ExcelHelper;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.scb.SellCardBalanceVo;
import com.wdzsj.mgr.service.scb.SellCardBalanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 售卡余额查询操作
 */
@Controller
@RequestMapping(value="/scb")
@SuppressWarnings({"all", "rawtypes"})
public class SellCardBalanceController extends BaseController {
    @Resource
    private SellCardBalanceService sellCardBalanceService;

    private final static String PAGE_INDEX = "scb/index";

    /**
     * 获取售卡余额报表首页列表
     * /scb
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
        Map<String, Object> param = pageRequest.getAllFilters();

        List<SellCardBalanceVo> list1 = sellCardBalanceService.findList1(param);

        Page page = sellCardBalanceService.findPage(pageRequest);

        model.addAttribute("page", page);
        model.addAttribute("pageRequest", pageRequest);//将搜索条件存入model
        return PAGE_INDEX;
    }

    /**
     * 售卡余额报表导出Excel表格
     * /scb/exportExcel
     * @param model
     * @param request
     * @param response
     * @return PAGE_INDEX（搜索完后再返回本页面）
     */
    @RequestMapping(value="/exportExcel",method= RequestMethod.GET)
    public String exportExcel(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
        Map<String, Object> param = pageRequest.getAllFilters();

        List<SellCardBalanceVo> list = sellCardBalanceService.findExportExcelList(param);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String msg = new String(("售卡余额报表_" + format.format(new Date()) + ".xlsx").getBytes(), "ISO-8859-1");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+msg);
        response.flushBuffer();

        boolean b = new ExcelHelper(SellCardBalanceVo.class).exportExcel(list, "售卡余额报表", response.getOutputStream());

        model.addAttribute("pageRequest", pageRequest);

        return PAGE_INDEX;
    }
}
