package com.wdzsj.mgr.controller.card;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.card.CardActivation;
import com.wdzsj.mgr.entity.card.CardDetail;
import com.wdzsj.mgr.entity.card.CardOrder;
import com.wdzsj.mgr.service.card.CardActivationService;
import com.wdzsj.mgr.service.card.CardDetailService;
import com.wdzsj.mgr.service.card.CardOrderService;

/**
 * 关于购物卡的操作
 */
@Controller
@RequestMapping(value="/card")
public class CardController extends BaseController{

	//购卡激活查询
	@Resource
	private  CardActivationService cardActivationService;
	//订单查询
	@Resource
	private  CardOrderService cardOrderService;
	//卡交易明细查询
	@Resource
	private  CardDetailService cardDetailService;
	
	private static final String APPID = "xxxxx";
	private static final String SIGN = "xxxxx";
	 
	private static final String ACTIVATION_INDEX = "card/activation_index";
	private static final String ORDER_INDEX = "card/order_index";
	private static final String DETAIL_INDEX = "card/detail_index";
	private static final String DETAIL_TRASNDETAIL = "card/transdetail_index";
	private static final String ATRASNDETAIL_SHOW = "card/transdetail_show";

 	/**
     * 购卡激活首页       
     * @return
     * @throws IOException
     */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/activation")
    public String index(ModelMap model, HttpServletRequest request) {

        PageRequest<Map<String, Object>> pageRequest = newPageRequest(request);
        Page page = cardActivationService.findPage(pageRequest);

        model.addAttribute("page", page);
        model.addAttribute("pageRequest", pageRequest);

        return ACTIVATION_INDEX;
    }
 	
 	/**
     * 订单查询   
     * @return
     * @throws IOException
     */
	@RequestMapping(value = "/order")
    public String order(ModelMap model, HttpServletRequest request) {

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("appid",APPID);
		param.put("sign",SIGN);

		PageRequest<Map<String, Object>> pageRequest = newPageRequest(request);
		Object orderNo = pageRequest.getAllFilters().get("order_no");

		List<CardOrder> cardOrderList = new ArrayList<CardOrder>();

		if(null != orderNo && StringUtils.isNotBlank(orderNo.toString())){
			param.put("order_no",orderNo);
			CardOrder cardOrder = cardOrderService.loadCardOrder(param);
			if(null != cardOrder) {
				cardOrderList.add(cardOrder);
			}
		}

		model.addAttribute("cardOrderList", cardOrderList);
		model.addAttribute("pageRequest", pageRequest);

        return ORDER_INDEX;
    }
 	
	/**
     * 订单明细查询 
     * @return
     * @throws IOException
     */
	@RequestMapping(value = "/detail")
    public String detail(ModelMap model, HttpServletRequest request) {

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("appid",APPID);
		param.put("sign",SIGN);

		PageRequest<Map<String, Object>> pageRequest = newPageRequest(request);
		param.put("openid",pageRequest.getAllFilters().get("openid"));
		param.put("cardId",pageRequest.getAllFilters().get("cardId"));

		Object cardCode = pageRequest.getAllFilters().get("cardCode");

		List<CardDetail> cardDetailList = new ArrayList<CardDetail>();

		if(null != cardCode && StringUtils.isNotBlank(cardCode.toString())){
			param.put("cardCode",cardCode);
			CardDetail cardDetail = cardDetailService.loadCardDetail(param);
			if(null != cardDetail){
				cardDetailList.add(cardDetail);
			}
		}
		model.addAttribute("cardDetailList", cardDetailList);
		//model.addAttribute("pageRequest", pageRequest);

        return DETAIL_INDEX;
    }

	/**
	 * 交易明细
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/transDetail")
	public String transDetail(ModelMap model, HttpServletRequest request) {

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("appid",APPID);
		param.put("sign",SIGN);

		PageRequest<Map<String, Object>> pageRequest = newPageRequest(request);
		param.put("openid",pageRequest.getAllFilters().get("openid"));
		param.put("cardId",pageRequest.getAllFilters().get("cardId"));

		Object cardCode = pageRequest.getAllFilters().get("cardCode");

		List<CardDetail> cardDetailList = new ArrayList<CardDetail>();

		if(null != cardCode && StringUtils.isNotBlank(cardCode.toString())){
			param.put("cardCode",cardCode);
			CardDetail cardDetail = cardDetailService.loadCardDetail(param);
			if(null != cardDetail){
				cardDetailList.add(cardDetail);
			}
		}
		model.addAttribute("cardDetailList", cardDetailList);
		//model.addAttribute("pageRequest", pageRequest);

		return DETAIL_TRASNDETAIL;
	}

	/**
	 * 购卡激活查看详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/activation/{id}")
	public String show(ModelMap model, @PathVariable Long id) {

		CardActivation cardActivation = cardActivationService.getById(id);

		model.addAttribute("cardActivation", cardActivation);

		return ATRASNDETAIL_SHOW;
	}
}
