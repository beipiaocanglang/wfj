package com.wdzsj.mgr.controller.ecd;

import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.castor.util.ExcelHelper;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.ecd.EcardConsumeDetailVo;
import com.wdzsj.mgr.service.ecd.EcardConsumeDetailService;
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
 * 电子卡消费明细报表操作
 */
@Controller
@RequestMapping(value="/ecd")
@SuppressWarnings({"all", "rawtypes"})
public class EcardConsumeDetailController extends BaseController {
    @Resource
    private EcardConsumeDetailService ecardConsumeDetailService;

    private final static String PAGE_INDEX = "ecd/index";

    /**
     * 电子卡消费明细报表首页列表
     * /ecd
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

        Page page = ecardConsumeDetailService.findPage(pageRequest);

        model.addAttribute("page", page);
        model.addAttribute("pageRequest", pageRequest);//将搜索条件存入model
        return PAGE_INDEX;
    }
    /**
     * 电子卡消费明细报表页面搜索
     * /ecd/exportExcel
     * @param model
     * @param request
     * @return PAGE_SHOW（搜索完后再返回本页面）
     */
    @RequestMapping(value="/exportExcel",method= RequestMethod.GET)
    public String showindex(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {

        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
        Map<String, Object> param = pageRequest.getAllFilters();

        List<EcardConsumeDetailVo> list = ecardConsumeDetailService.findExportExcelList(param);

        //将日期格式化导出到Excel
        if (list != null && list.size() > 0) {
            for (EcardConsumeDetailVo ecardConsumeDetailVo : list) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String transtime = format.format(ecardConsumeDetailVo.getTranstime());
                ecardConsumeDetailVo.setTime(transtime);
            }
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        String msg = new String(("电子卡消费明细报表_" + format.format(new Date()) + ".xlsx").getBytes(), "ISO-8859-1");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+msg);
        response.flushBuffer();

        boolean b = new ExcelHelper(EcardConsumeDetailVo.class).exportExcel(list, "电子卡消费明细报表", response.getOutputStream());

        model.addAttribute("pageRequest", pageRequest);

        return PAGE_INDEX;
    }
}
