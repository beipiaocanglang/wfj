package com.wdzsj.mgr.dao.cnt;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorSaleDao {
	
	List<JSONObject> findList(@Param("plazaIds") Long[] plazaIds,@Param("creatTmBegin") String creatTmBegin,@Param("creatTmEnd") String creatTmEnd);

	Long getTotalValue(@Param("plazaIds") Long[] plazaIds,@Param("creatTmBegin") String creatTmBegin,@Param("creatTmEnd") String creatTmEnd);
}
