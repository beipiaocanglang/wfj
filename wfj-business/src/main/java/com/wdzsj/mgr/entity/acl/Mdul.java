package com.wdzsj.mgr.entity.acl;

import com.wdzsj.cmn.base.BaseEntity;
import java.util.List;

public class Mdul extends BaseEntity {
	
    //parId       
	private Long parId;
    //模块名称      
	private String name;
    //模块地址       
	private String url;
    //模块类型       
	private String sn;
    //图片路径       
	private String path;
    //状态      
	private int status;
	//模块类型
	private int type;
    //所属系统       
	private String system;
    //排序      
	private String seq;
	
	private Mdul parent;
	private List children;
	private List functions;

	public Mdul(){
	}

	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setUrl(String value) {
		this.url = value;
	}
	
	public String getUrl() {
		return this.url;
	}
	public void setSn(String value) {
		this.sn = value;
	}
	
	public String getSn() {
		return this.sn;
	}
	public void setPath(String value) {
		this.path = value;
	}
	
	public String getPath() {
		return this.path;
	}
	public void setStatus(int value) {
		this.status = value;
	}
	
	public int getStatus() {
		return this.status;
	}
	public void setSystem(String value) {
		this.system = value;
	}
	
	public String getSystem() {
		return this.system;
	}

	public Mdul getParent() {
		return parent;
	}

	public void setParent(Mdul parent) {
		this.parent = parent;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public List getFunctions() {
		return functions;
	}

	public void setFunctions(List functions) {
		this.functions = functions;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getParId() {
		return parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}
}

