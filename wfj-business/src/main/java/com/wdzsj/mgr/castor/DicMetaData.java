package com.wdzsj.mgr.castor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.wdzsj.mgr.entity.sys.Dict;

public class DicMetaData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Dict> dictList = new ArrayList<Dict>();

	public List<Dict> getDictList() {
		return dictList;
	}

	public void setDictList(List<Dict> dictList) {
		this.dictList = dictList;
	}
}
