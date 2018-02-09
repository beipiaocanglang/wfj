package com.wdzsj.mgr.controller.cac;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wdzsj.mgr.entity.cac.CheckAccountDetailVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.castor.util.ExcelHelper;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.cac.CheckAccountVo;
import com.wdzsj.mgr.service.cac.CheckAccountService;

/**
 * 关于门店消费报表导出Excle表格的操作
 * 1、门店消费报表导出Excel表格
 * 2、门店消费二级表查询导出Excel表格
 */
@Controller
@RequestMapping(value="/report")
@SuppressWarnings({"all", "rawtypes"})
public class ReportController  extends BaseController{
	@Resource
	private CheckAccountService checkAccountService;

	private final static String PAGE_INDEX = "cac/index";
	private final static String PAGE_SHOW = "cac/show";

	/**
	 * 门店消费报表导出Excel表格
	 * /report/export
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/export",method=RequestMethod.GET)
	@ResponseBody
	public String export(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException{

		PageRequest<Map<String, Object>> pageRequest = newPageRequest(request);

		Map<String, Object> param=pageRequest.getAllFilters();

		String startPosid=(String) param.get("startPosid");
		String endPosid=(String) param.get("endPosid");
		String startDate=(String) param.get("startDate");
		String endDate=(String) param.get("endDate");

		if (StringUtils.isNotBlank(startPosid) && StringUtils.isNotBlank(endPosid) && StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)){
			param.put("startPosid", startPosid);
			param.put("endPosid", endPosid);
		}else if(StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate) && StringUtils.isBlank(startPosid) && StringUtils.isBlank(endPosid)){
			param.put("startDate", startDate);
			param.put("endDate", endDate);
		}else if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate) && StringUtils.isNotBlank(startPosid) && StringUtils.isNotBlank(endPosid)) {
			param.put("startPosid", startPosid);
			param.put("endPosid", endPosid);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
		}

		List<CheckAccountVo> alllist= checkAccountService.findCheckAccountVoByPosidAndTime02(param);

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); 
		String msg = new String(("门店消费查询列表_" + format.format(new Date()) + ".xlsx").getBytes(), "ISO-8859-1");

		response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+msg);
        response.flushBuffer();

        new ExcelHelper(CheckAccountVo.class).exportExcel(alllist,"门店消费查询列表",response.getOutputStream()) ;

        return PAGE_INDEX;
	}

	/**
	 * 门店消费二级表查询导出Excel表格
	 * /report/export/detail
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/export/detail",method=RequestMethod.GET)
	@ResponseBody
	public String exportDetail(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {

		try {
			PageRequest<Map<String, Object>> pageRequest = newPageRequest(request);

			Map<String, Object> param = pageRequest.getAllFilters();
			String startPosid = (String) param.get("startPosid");
			String startDate = (String) param.get("startDate");
			String endDate = (String) param.get("endDate");
			String posid = (String) param.get("posid");

			Map<String, Object> map = new HashMap<>();

			if (StringUtils.isBlank(startPosid)) {
				map.put("startPosid", posid);
			}

			if (StringUtils.isNotBlank(startPosid) && StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)){
				map.put("startPosid", startPosid);
			}else if(StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate) && StringUtils.isBlank(startPosid)){
				map.put("startDate", startDate);
				map.put("endDate", endDate);
			}else if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate) && StringUtils.isNotBlank(startPosid)) {
				map.put("startPosid", startPosid);
				map.put("startDate", startDate);
				map.put("endDate", endDate);
			}

			List<CheckAccountDetailVo> alllist= checkAccountService.findCheckAccountVoByPosidAndTime03(map);

			//将日期格式化导出到Excel
			if (alllist != null && alllist.size() > 0) {
				for (CheckAccountDetailVo accountDetail : alllist) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String transactionDate = format.format(accountDetail.getTransactionDate());
					accountDetail.setTransactionD(transactionDate);
				}
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			String msg = new String(("门店消费二级报表列表_" + format.format(new Date()) + ".xlsx").getBytes(), "ISO-8859-1");

			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;filename="+msg);
			response.flushBuffer();

			boolean b = new ExcelHelper(CheckAccountDetailVo.class).exportExcel(alllist, "门店消费二级报表列表", response.getOutputStream());

			model.addAttribute("posid",posid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return PAGE_SHOW;
	}
}
