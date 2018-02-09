package com.wdzsj.mgr.controller.tca;

import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.castor.util.ExcelHelper;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.cac.ConsumeCheckThreeLevelDetailVo;
import com.wdzsj.mgr.entity.cac.ConsumeCheckVo;
import com.wdzsj.mgr.service.cac.CheckAccountService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.wdzsj.cmn.entity.PageRequest.newPageRequest;

@Controller
@RequestMapping(value="/tca/export")
@SuppressWarnings({"all", "rawtypes"})
public class ConsumeCheckExportController extends BaseController {

    @Resource
    private CheckAccountService checkAccountService;

    private final static String PAGE_INDEX = "cac/index";
    private final static String PAGE_SHOWDETAIL = "cac/showdetail";

    /**
     * 核销对账表一级报表导出
     * /tca/export/exportIndexExcel
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/exportIndexExcel",method= RequestMethod.GET)
    @ResponseBody
    public String export(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

        Map<String, Object> param = pageRequest.getAllFilters();

        List<ConsumeCheckVo> consumeList = checkAccountService.exportIndexExcel(param);

        if (consumeList != null && consumeList.size() > 0) {
            for (ConsumeCheckVo consumeCheckVo : consumeList) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String transtime = format.format(consumeCheckVo.getTranstime());
                consumeCheckVo.setTime(transtime);
            }
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String msg = new String(("消费核对报表_" + format.format(new Date()) + ".xlsx").getBytes(), "ISO-8859-1");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+msg);
        response.flushBuffer();

        new ExcelHelper(ConsumeCheckVo.class).exportExcel(consumeList, "消费核对报表", response.getOutputStream()) ;

        return PAGE_INDEX;
    }

    /**
     * 核销对账表三级报表导出
     * /tca/export/exportSecondExcel
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/exportSecondExcel",method= RequestMethod.GET)
    @ResponseBody
    public String exportSecondExcel(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
        Map<String, Object> param = pageRequest.getAllFilters();
        String storename = (String)param.get("storename");

        List<ConsumeCheckThreeLevelDetailVo> volist = checkAccountService.findCheckAccountVoByStorename(param);

        if (volist != null && volist.size() > 0) {
            for (ConsumeCheckThreeLevelDetailVo consumeCheckThreeLevelDetailVo : volist) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String transtime = format.format(consumeCheckThreeLevelDetailVo.getTranstime());
                consumeCheckThreeLevelDetailVo.setTime(transtime);
            }
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String msg = new String(("三级消费核对报表_" + format.format(new Date()) + ".xlsx").getBytes(), "ISO-8859-1");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+msg);
        response.flushBuffer();

        new ExcelHelper(ConsumeCheckThreeLevelDetailVo.class).exportExcel(volist, "三级消费核对报表", response.getOutputStream()) ;

        model.addAttribute("storename",storename);

        return PAGE_SHOWDETAIL;
    }
}
