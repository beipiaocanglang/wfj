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
import com.wdzsj.mgr.entity.card.CardDetail;

@Service("cardDetailService")
public class CardDetailService extends BaseService<CardDetail> {
	
	private static final String VERSION = "1.0.0";
	
	@Value("${interface.card.detail}")
	private String card_detail_url;
	
	protected static Logger log = Logger.getLogger(CardDetailService.class);

	@Override
	protected BaseDao<CardDetail> getEntityDao() {
		return null;
	}
	
	public CardDetail loadCardDetail(Map<String,Object> param){
		CardDetail result = null;
		try {
			if(StringUtils.isNotBlank(card_detail_url) && null != param){
				CloseableHttpClient dfClient = HttpClientUtils.createDefaultHttpClient();
				Object openidObj = param.get("openid");
				Object cardIdObj = param.get("cardId");
				Object cardCodeObj = param.get("cardCode");
				if(null != cardCodeObj){
					String appid = (null==param.get("appid"))?"":param.get("appid").toString();
					String sign = (null==param.get("sign"))?"":param.get("sign").toString();
					String openid = (null==openidObj)?"":openidObj.toString();
					String cardId = (null==cardIdObj)?"":cardIdObj.toString();
					String cardCode = cardCodeObj.toString().trim();
					String url = card_detail_url+"?version="+VERSION+"&appid="+appid+"&sign="+sign;
					Map<String,Object> params = new HashMap<String,Object>();
					params.put("openid",openid);
					params.put("cardId",cardId);
					params.put("cardCode",cardCode);
					String postResult = HttpClientUtils.doPost(dfClient, url, params);
					if(!HttpClientUtils.ERROR.equals(postResult)) 
						result = JSONObject.parseObject(postResult, CardDetail.class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ERROR:"+e.getMessage());
		}
		return result;
	}
}
