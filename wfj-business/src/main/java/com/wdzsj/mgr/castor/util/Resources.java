package com.wdzsj.mgr.castor.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resources implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<EntityResource> entityResourceList = new ArrayList<EntityResource>();

	public List<EntityResource> getEntityResourceList() {
		return entityResourceList;
	}
	public void setEntityResourceList(List<EntityResource> entityResourceList) {
		this.entityResourceList = entityResourceList;
	}
}
