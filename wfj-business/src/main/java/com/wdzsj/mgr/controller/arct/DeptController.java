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
import com.wdzsj.mgr.entity.arct.Dept;
import com.wdzsj.mgr.service.arct.DeptService;

//@Controller 在类上面定义，表明该类为控制器，返回字符串与 redirect:xxx
@Controller
//在类或方法上面使用此注解，设臵 URL 访问地址。它有两个属性，value 指定访问路径，method 指定指定请
//求方式，请求方式在 RequestMethod 这个类中，全部以常量形式定义，它默认使用 GET 请求。
@RequestMapping(value="/dept")
@SuppressWarnings({"unchecked", "rawtypes"})
public class DeptController extends BaseController {
	
	@Resource
	private DeptService deptService;
	
	private final static String PAGE_TREE = "dept/tree";

    /**
     * 组织管理首页
     * @param model
     * @return
     */
	@AclAnnotation(mdul = "dept",level = 0)
	@RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
		return PAGE_TREE;
    }

    /**
     * 获取岗位的根节点
     * @return
     */	
	@AclAnnotation(mdul = "dept",level = 0)
	@RequestMapping(value = "/list")
    @ResponseBody
    public ResponseModel<List<JSONObject>> tree (){

		List<JSONObject> result = deptService.findRoot();

        return ResponseModel.build2Success(result, Status.SUCCESS_MSG);
    }

	/**
	 * 异步获取机构的下级节点数据
	 * @param parId
	 * @param request
	 * @return
	 */
	@AclAnnotation(mdul = "dept",level = 0)
	@RequestMapping(value = "/findByParId")
    @ResponseBody
    //@RequestParam 指定 Request 请求参数，在方法参数中定义，相当于传统的 request.getParameter()。
    public ResponseModel<List<JSONObject>> findByParId(@RequestParam(value="id",required=true,defaultValue="0")Long parId, HttpServletRequest request) {

		List<JSONObject> result = deptService.findByParId(parId);

        return ResponseModel.build2Success(result, Status.SUCCESS_MSG);
    }

    /**
	 * 保存结构节点	
	 * @param dept
	 * @param request
	 * @return
	 */
	@AclAnnotation(mdul = "dept",level = 1)
	@RequestMapping(value = "/save")
    @ResponseBody
    public ResponseModel<Dept>  save(@RequestBody Dept dept, HttpServletRequest request) {

		Dept curDept = deptService.save(dept);//直接接收返回的对象

        return ResponseModel.build2Success(curDept, Status.SUCCESS_MSG);
    }
	/**
	 * 修改节点名称
	 * @param id
	 * @param name
	 * @param name
	 * @return
	 */
	@AclAnnotation(mdul = "dept",level = 2)
	@RequestMapping(value = "/update")
    @ResponseBody
    //@ResponseBody 定义在方法上，Ajax 调用声明，指定方法返回结果为 Ajax 回调函数结果。这是 Spring MVC 3.0 框架中增加的一个新特性。
    public ResponseModel<Object> update(HttpServletRequest request, @RequestParam(value="id",required=true)Long id,
    		@RequestParam(value="name",required=true)String name) {

		deptService.update(id,name);

        return ResponseModel.build2Success(null, Status.SUCCESS_MSG);
    }

	/**
	 * 删除用户所在的节点
	 * @param id
	 * @param request
	 * @return
	 */
	@AclAnnotation(mdul = "dept",level = 3)
	@RequestMapping(value = "/delete")
    @ResponseBody
    public ResponseModel<Object> delete(@RequestParam(value="id",required=true)Long id, HttpServletRequest request) {

	    deptService.delete(id);

        return ResponseModel.build2Success(null, Status.SUCCESS_MSG);
    }
}

