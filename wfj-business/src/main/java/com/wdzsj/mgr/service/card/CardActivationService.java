package com.wdzsj.mgr.service.card;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.dao.card.CardActivationDao;
import com.wdzsj.mgr.entity.card.CardActivation;

@Service("cardActivationService")
public class CardActivationService extends BaseService<CardActivation> {

	protected static Logger log = Logger.getLogger(CardActivationService.class);
	
	@Resource
	private CardActivationDao cardActivationDao;

	@Override
	protected BaseDao<CardActivation> getEntityDao() {
		return cardActivationDao;
	}
}
