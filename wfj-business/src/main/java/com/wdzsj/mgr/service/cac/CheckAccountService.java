package com.wdzsj.mgr.service.cac;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.wdzsj.mgr.entity.cac.*;
import org.springframework.stereotype.Service;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.dao.cac.CheckAccountDao;

@Service
@SuppressWarnings({"all", "rawtypes"})
public class CheckAccountService extends BaseService<CheckAccountVo> {

	@Resource
	private CheckAccountDao checkAccountDao;
	
	@Override
	protected BaseDao<CheckAccountVo> getEntityDao() {
		return checkAccountDao;
	}
	public Page<CheckAccountVo> findPageBySelf(PageRequest<Map<String,Object>> pageRequest){
		Page page = findPage(pageRequest);
		return page;
	}

	/**
	 * 根据posId统计
	 * @param obj
	 * @return
	 */
	public Integer countByPosId(Object obj){
		return checkAccountDao.countByPosId(obj);
	}

	/**
	 * 查看详情
	 * @param posid
	 * @return
	 */
	public List<CheckAccountVo> findCheckAccountVoByPosid(Map<String, Object> param) {
		List<CheckAccountVo> volist=checkAccountDao.findCheckAccountVoByPosid(param);
		return volist;
	}

	/**
	 * 详情页面搜索
	 * @param paramMap
	 * @return
	 */
	public List<CheckAccountVo> findCheckAccountVoByPosidAndTime(Map<String, Object> paramMap) {
		List<CheckAccountVo> list=this.checkAccountDao.findCheckAccountVoByPosidAndTime(paramMap);

		 return list;
	}

	/**
	 * 门店消费报表导出Excel表格
	 * @param param
	 * @return
	 */
	public List<CheckAccountVo> findCheckAccountVoByPosidAndTime02(Map<String, Object> param) {
		List<CheckAccountVo> checkAccountVoByPosidAndTime02 = checkAccountDao.findCheckAccountVoByPosidAndTime02(param);
		return checkAccountVoByPosidAndTime02;
	}

	/**
	 * 门店消费二级报表导出Excel表格
	 * @param param
	 * @return
	 */
	public List<CheckAccountDetailVo> findCheckAccountVoByPosidAndTime03(Map<String, Object> param) {
		List<CheckAccountDetailVo> checkAccountVoByPosidAndTime02 = checkAccountDao.findCheckAccountVoByPosidAndTime03(param);
		return checkAccountVoByPosidAndTime02;
	}


	//**************************以下是消费核对表的数据操作**************start***********
	/**
	 * 核对消费表首页获取数据
	 * @param param
	 * @return
	 */
	public List<ConsumeCheckVo> findConsumeList(Map<String, Object> param){
		List<ConsumeCheckVo> consumeList = checkAccountDao.findConsumeList(param);
		return consumeList;
	}

	/**
	 * 消费核对表导出数据到Excel
	 * @param param
	 * @return
	 */
	public List<ConsumeCheckVo> exportIndexExcel(Map<String, Object> param) {
		List<ConsumeCheckVo> exportIndexExcel = checkAccountDao.exportIndexExcel(param);
		return exportIndexExcel;
	}

	/**
	 * 消费核对表根据首页transtime查询二级消费核对表列表
	 * @param model
	 * @param request
	 * @param transtime
	 * @return
	 */
	public List<ConsumeCheckSecondLevelDetailVo> findCheckAccountVoByTransTime(Map<String, Object> param) {
		List<ConsumeCheckSecondLevelDetailVo> consumeCheckVos = checkAccountDao.findCheckAccountVoByTransTime(param);
		return consumeCheckVos;
	}

	/**
	 * 根据storename查询三级消费核销对账列表
	 * @param model
	 * @param request
	 * @param storename
	 * @return
	 */
	public List<ConsumeCheckThreeLevelDetailVo> findCheckAccountVoByStorename(Map<String, Object> param) {
		List<ConsumeCheckThreeLevelDetailVo> consumeCheckThreeLevelDetailVo = checkAccountDao.findCheckAccountVoByStorename(param);
		return consumeCheckThreeLevelDetailVo;
	}

	//**************************以下是消费核对表的数据操作**************end***********
}
