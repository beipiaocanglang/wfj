package com.wdzsj.mgr.service.cnt;

import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.helper.ThreadLocalHelper;
import com.wdzsj.mgr.dao.acl.OrgUserDao;
import com.wdzsj.mgr.dao.cnt.StorSaleDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class StorSaleService {

	@Resource
	private StorSaleDao storSaleDao;

	@Resource
	private OrgUserDao orgUserDao;

	/**
	 * 获取饼状图数组
	 * @param plazaId
	 * @param creatTmBegin
	 * @param creatTmEnd
	 * @return
	 */
	public JSONObject findPieData(Long plazaId,String creatTmBegin,String creatTmEnd){
		Long[] plazaIds = new Long[1];
		if(null != plazaId) {
			plazaIds[0] = plazaId;
		} else {
			Long userId = ThreadLocalHelper.getUserId();
			List<JSONObject> plazaList = this.findPlazaDataByUserId(userId);
			plazaIds = this.jsonToLongArray(plazaList,"orgId");
		}
		List<JSONObject> list = storSaleDao.findList(plazaIds,creatTmBegin,creatTmEnd);

		Long totalValue = storSaleDao.getTotalValue(plazaIds,creatTmBegin,creatTmEnd);
		long otherTotalValue = getOtherTotalValue(list,totalValue);
		if(otherTotalValue > 0) {
			JSONObject other = new JSONObject();
			other.put("name","其他");
			other.put("value",otherTotalValue);
			list.add(other);
		}

		String[] legendDate = this.jsonToStringArray(list,"name");
		JSONObject result = new JSONObject();
		result.put("seriesDate",list);
		result.put("legendDate",legendDate);

		return result;
	}

	/**
	 * 获取柱状图数据
	 * @param plazaId
	 * @param creatTmBegin
	 * @param creatTmEnd
	 * @return
	 */
	public JSONObject findBarData( Long plazaId,String creatTmBegin,String creatTmEnd){
		Long[] plazaIds = {plazaId};
		List<JSONObject> list = storSaleDao.findList(plazaIds,creatTmBegin,creatTmEnd);

		String[] xAxisData = this.jsonToStringArray(list,"name");
		Long[] seriesData = this.jsonToLongArray(list,"value");
		JSONObject result = new JSONObject();
		result.put("xAxisData",xAxisData);
		result.put("seriesData",seriesData);

		return result;
	}

	/**
	 * 获取用户的广场列表
	 * @param userId
	 * @return
	 */
	public List<JSONObject> findPlazaDataByUserId(Long userId) {
		List<JSONObject> result = orgUserDao.findLeafOrgByUserId(userId);
		return result;
	}

	/**
	 * 将json对应key转化为数组
	 * @param list
	 * @param key
	 * @return
	 */
	private String[] jsonToStringArray(List<JSONObject> list,String key) {
		String[] result = new  String[list.size()];
		int i = 0;
		for(JSONObject object : list) {
			result[i] = object.getString(key);
			i++;
		}
		return result;
	}

	/**
	 * 将json对应key转化为数组
	 * @param list
	 * @param key
	 * @return
	 */
	private Long[] jsonToLongArray(List<JSONObject> list,String key) {
		Long[] result = new  Long[list.size()];
		int i = 0;
		for(JSONObject object : list) {
			result[i] = object.getLong(key);
			i++;
		}
		return result;
	}

	/**
	 * 获取其他总的数据
	 * @param list
	 * @param totalValue
	 * @return
	 */
	private Long getOtherTotalValue(List<JSONObject> list,Long totalValue) {
		if(null == totalValue) return 0L;
		long otherTotalValue = totalValue.longValue();
		for(JSONObject object : list) {
			otherTotalValue -= object.getLong("value");
		}
		return otherTotalValue;
	}
}
