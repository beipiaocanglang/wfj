package com.wdzsj.mgr.controller.cac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.cac.CheckAccountVo;
import com.wdzsj.mgr.service.cac.CheckAccountService;

/**
 * 这个是关于门店消费报表的操作
 * 1、获取门店消费报表首页列表
 * 2、门店二级消费报表根据首页posid查询详情
 * 3、门店二级消费报表页面搜索
 */
@Controller
@RequestMapping(value="/cac")
@SuppressWarnings({"all", "rawtypes"})
public class CheckAccountController extends BaseController {

	@Resource
	private CheckAccountService checkAccountService;

	private final static String LIST_ACTION = "redirect:/cac";
	private final static String PAGE_INDEX = "cac/index";
	private final static String PAGE_SHOW = "cac/show";

    /**
     * 获取门店消费报表首页列表
     * /cac
     * @param model
     * @param request
     * @return
     */
	@RequestMapping
	public String index(ModelMap model,HttpServletRequest request) {
		Map<String, Object> parammap=new HashedMap();
		PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
		Page page = checkAccountService.findPage(pageRequest);
		//Page page1 =checkAccountService.findPageBySelf(pageRequest);
		model.addAttribute("page", page);
		model.addAttribute("pageRequest", pageRequest);//将搜索条件存入model
		return PAGE_INDEX;
	}

    /**
     * 门店二级消费报表根据首页posid查询详情
     * /cac/show/{posid}
     * @param model
     * @param request
     * @param posid
     * @return
     */
	@RequestMapping(value="/show/{posid}")
	public String show(ModelMap model,HttpServletRequest request,@PathVariable String posid){

		//获取搜索条件和参数
		PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
		Map<String, Object> param = pageRequest.getAllFilters();

		if (StringUtils.isNotBlank(posid)) {
			param.put("posid",posid);
		}

		List<CheckAccountVo> volist = checkAccountService.findCheckAccountVoByPosid(param);

		Integer count = checkAccountService.countByPosId(param);

		Page page = new Page(pageRequest, count, volist);

		model.addAttribute("list", volist);
		model.addAttribute("page", page);
		model.addAttribute("posid", posid);

		return PAGE_SHOW;
	}

	/**
	 * 门店二级消费报表页面搜索
	 * /cac/showindex
	 * @param model
	 * @param request
	 * @return PAGE_SHOW（搜索完后再返回本页面）
	 */
	@RequestMapping(value="/showindex")
	public String showindex(ModelMap model,HttpServletRequest request) {

		PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);

		Map<String, Object> param = pageRequest.getAllFilters();
		int pageSize = (int) param.get("pageSize");
		int offset = (int) param.get("offset");
		String startPosid = (String) param.get("startPosid");
		String startDate = (String) param.get("startDate");
		String endDate = (String) param.get("endDate");
		String posid = (String) param.get("posid");

		Map<String, Object> map = new HashMap<>();
		map.put("pageSize",pageSize);
		map.put("offset",offset);

		if (StringUtils.isBlank(startPosid)) {
			map.put("startPosid", posid);
		}

		if (StringUtils.isNotBlank(startPosid) && (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate))) {
			map.put("startPosid", startPosid);
		}else if (StringUtils.isBlank(startPosid) && StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
			map.put("startDate", startDate);
			map.put("endDate", endDate);
		}else if (StringUtils.isNotBlank(startPosid) && StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
			map.put("startPosid", startPosid);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
		}

		List<CheckAccountVo> list = checkAccountService.findCheckAccountVoByPosidAndTime(map);

		Integer count = 0;
		if (list!=null && list.size()>0) {
			count = list.get(0).getTotalNum();
		}

		Page page = new Page(pageRequest, count, list==null?new ArrayList():list);

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("posid", posid);
		model.addAttribute("pageRequest", pageRequest);//将搜索条件存入model

		return PAGE_SHOW;
	}
}

