package com.wdzsj.mgr.controller.ecb;

import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.castor.util.ExcelHelper;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.ecb.EcardBalanceVo;
import com.wdzsj.mgr.service.ecb.EcardBalanceService;
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
 * 电子卡余额查询操作
 */
@Controller
@RequestMapping(value="/ecb")
@SuppressWarnings({"all", "rawtypes"})
public class EcardBalanceController extends BaseController {
    @Resource
    private EcardBalanceService ecardBalanceService;

    private final static String PAGE_INDEX = "ecb/index";

    /**
     * 获取电子卡余额查询表首页列表
     * /ecb
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
        Map<String, Object> param = pageRequest.getAllFilters();

        Page page = ecardBalanceService.findPage(pageRequest);

        model.addAttribute("page", page);
        model.addAttribute("pageRequest", pageRequest);//将搜索条件存入model
        return PAGE_INDEX;
    }

    /**
     * 电子卡余额奥比奥导出Excel
     * /ecb/exportExcel
     * @param model
     * @param request
     * @return PAGE_INDEX（搜索完后再返回本页面）
     */
    @RequestMapping(value="/exportExcel",method= RequestMethod.GET)
    public String exportExcel(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
        Map<String, Object> param = pageRequest.getAllFilters();

        List<EcardBalanceVo> list = ecardBalanceService.findExportExcelList(param);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        String msg = new String(("电子卡余额表查询报表_" + format.format(new Date()) + ".xlsx").getBytes(), "ISO-8859-1");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+msg);
        response.flushBuffer();

        boolean b = new ExcelHelper(EcardBalanceVo.class).exportExcel(list, "电子卡余额表查询报表", response.getOutputStream());

        model.addAttribute("pageRequest", pageRequest);

        return PAGE_INDEX;
    }
}
