package com.wdzsj.mgr.controller.pvuv;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.annotation.AclAnnotation;
import com.wdzsj.cmn.constant.Status;
import com.wdzsj.cmn.entity.ResponseModel;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.service.pvuv.PvuvService;

@Controller
@RequestMapping(value="/pvuv")
public class PvuvController extends BaseController{
	 @Resource
	 private  PvuvService pvuvService;
	 
	 private final String PAGE_INDEX = "pvuv/index";

	 @AclAnnotation(mdul = "pvuv",level = 0)
	 @RequestMapping
	 public String index(ModelMap model,HttpServletRequest request){
		 return PAGE_INDEX ; 
	 }
	 
	@RequestMapping(value = "tsk")
	@ResponseBody
	public ResponseModel<JSONObject> pie() {
		//System.out.println("图表");
		JSONObject list= pvuvService.findData();
		return ResponseModel.build2Success(list, Status.SUCCESS_MSG);
	}
}
