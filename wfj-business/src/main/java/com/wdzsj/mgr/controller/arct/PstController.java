package com.wdzsj.mgr.controller.arct;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.annotation.AclAnnotation;
import com.wdzsj.cmn.constant.Status;
import com.wdzsj.cmn.entity.ResponseModel;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.arct.Pst;
import com.wdzsj.mgr.service.arct.PstService;

@Controller
@RequestMapping(value="/pst")
@SuppressWarnings({"unchecked", "rawtypes"})
public class PstController extends BaseController {
	
	@Resource
	private PstService pstService;

	private final static String PAGE_TREE     = "pst/tree";

    /**
     * 组织管理首页
     * @param model
     * @return
     */
	@AclAnnotation(mdul = "pst",level = 0)
	@RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
		return PAGE_TREE;
    }	
    /**
     * 获取岗位的根节点
     * @return
     */	
	@AclAnnotation(mdul = "pst",level = 0)
	@RequestMapping(value = "/list")
    @ResponseBody
    public ResponseModel<List<JSONObject>> tree (){
		List<JSONObject> result = pstService.findRoot();		
        return ResponseModel.build2Success(result, Status.SUCCESS_MSG);
    }	
	/**
	 * 异步获取机构的下级节点数据
	 * @param parId
	 * @param request
	 * @return
	 */
	@AclAnnotation(mdul = "pst",level = 0)
	@RequestMapping(value = "/findByParId")
    @ResponseBody
    public ResponseModel<List<JSONObject>> findByParId(@RequestParam(value="id",required=true,defaultValue="0")Long parId, HttpServletRequest request) {
					/*@RequestParam
					指定 Request 请求参数，在方法参数中定义，相当于传统的 request.getParameter()。
					*/
		List<JSONObject> result = pstService.findByParId(parId);
        return ResponseModel.build2Success(result, Status.SUCCESS_MSG);
    }
	/*
	*//**
	 * 保存结构节点
	 * @return
	 */
	@AclAnnotation(mdul = "pst",level = 1)
	@RequestMapping(value = "/save")
    @ResponseBody
    public ResponseModel<Pst>  save(@RequestBody Pst pst,
    		HttpServletRequest request) {
		Pst curPst = pstService.save(pst);
        return ResponseModel.build2Success(curPst, Status.SUCCESS_MSG);
    }
	/**
	 * 修改节点名称
	 * @param id
	 * @param name
	 * @return
	 */
	@AclAnnotation(mdul = "pst",level = 2)
	@RequestMapping(value = "/update")
    @ResponseBody
    public ResponseModel<Object>  update(
    		@RequestParam(value="id",required=true)Long id,
    		@RequestParam(value="name",required=true)String name,
    		HttpServletRequest request) {
		pstService.update(id,name);
        return ResponseModel.build2Success(null, Status.SUCCESS_MSG);
    }

	/**
	 * 删除用户所在的节点
	 * @param id
	 * @param request
	 * @return
	 */
	@AclAnnotation(mdul = "pst",level = 3)
	@RequestMapping(value = "/delete")
    @ResponseBody
    public ResponseModel<Object> delete(
    		@RequestParam(value="id",required=true)Long id, 
    		HttpServletRequest request) {
		pstService.delete(id);
        return ResponseModel.build2Success(null, Status.SUCCESS_MSG);
    }
}

