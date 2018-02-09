package com.wdzsj.mgr.controller.cnt;

import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.annotation.AclAnnotation;
import com.wdzsj.cmn.constant.Status;
import com.wdzsj.cmn.entity.ResponseModel;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.acl.SysUser;
import com.wdzsj.mgr.service.cnt.StorSaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value="/storSale")
@SuppressWarnings({"unchecked", "rawtypes"})
public class StorSaleController extends BaseController {
	
	@Resource
	private StorSaleService storSaleService;

	private final static String PAGE_INDEX = "storSale/index";

	/**
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	@AclAnnotation(mdul = "storSale",level = 0)
	@RequestMapping
	public String index(ModelMap model,HttpServletRequest request) {
		SysUser sysUser = this.currentSysUser(request);
		List<JSONObject> plazaDataList = storSaleService.findPlazaDataByUserId(sysUser.getId());
		model.addAttribute("plazaDataList",plazaDataList);
		return PAGE_INDEX;
	}

	/**
	 *
	 * @param plazaId
	 * @param creatTmBegin
	 * @param creatTmEnd
	 * @return
	 */
	@RequestMapping(value = "pie")
	@ResponseBody
	public ResponseModel<JSONObject> pie(@RequestParam(value = "plazaId",required = false)Long plazaId,
										 @RequestParam(value = "creatTmBegin",required = false)String creatTmBegin,
										 @RequestParam(value = "creatTmEnd",required = false)String creatTmEnd) {
		JSONObject result = storSaleService.findPieData(plazaId,creatTmBegin,creatTmEnd);
		return ResponseModel.build2Success(result, Status.SUCCESS_MSG);
	}

	/**
	 *
	 * @param plazaId
	 * @param creatTmBegin
	 * @param creatTmEnd
	 * @return
	 */
	@RequestMapping(value = "bar")
	@ResponseBody
	public ResponseModel<JSONObject> bar(@RequestParam(value = "plazaId",required = false)Long plazaId,
										 @RequestParam(value = "creatTmBegin",required = false)String creatTmBegin,
										 @RequestParam(value = "creatTmEnd",required = false)String creatTmEnd) {
		JSONObject result = storSaleService.findBarData(plazaId,creatTmBegin,creatTmEnd);
		return ResponseModel.build2Success(result, Status.SUCCESS_MSG);
	}
}

