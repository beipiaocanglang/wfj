package com.wdzsj.mgr.service.card;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.cmn.util.HttpClientUtils;
import com.wdzsj.mgr.entity.card.CardOrder;

@Service("cardOrderService")
public class CardOrderService extends BaseService<CardOrder> {
	
	private static final String VERSION = "1.0.0";
	
	@Value("${interface.card.order}")
	private String card_order_url;
	

	protected static Logger log = Logger.getLogger(CardOrderService.class);

	@Override
	protected BaseDao<CardOrder> getEntityDao() {
		return null;
	}
	
	public CardOrder loadCardOrder(Map<String,Object> param){
		CardOrder result = null;
		try {
			if(StringUtils.isNotBlank(card_order_url) && null != param){

				CloseableHttpClient dfClient = HttpClientUtils.createDefaultHttpClient();
				Object orderNoObj = param.get("order_no");

				if(null != orderNoObj){
					String appid = (null==param.get("appid"))?"":param.get("appid").toString();
					String sign = (null==param.get("sign"))?"":param.get("sign").toString();

					String orderNo = orderNoObj.toString().trim();

					String url = card_order_url+"?version="+VERSION+"&appid="+appid+"&sign="+sign;

					Map<String,Object> params = new HashMap<String,Object>();
					params.put("order_no",orderNo);

					String postResult = HttpClientUtils.doPost(dfClient, url, params);
					
					if(!HttpClientUtils.ERROR.equals(postResult)) 
						result = JSONObject.parseObject(postResult, CardOrder.class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ERROR:"+e.getMessage());
		}
		return result;
	}
}
