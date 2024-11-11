package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;

public class Info extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -674161960515333295L;
	private int info_id; // 
	private String info_content; //
	
	private String random; // 
	private String ids; // 

	public void setInfo_id(int info_id){
		this.info_id=info_id;
	}

	public int getInfo_id(){
		return info_id;
	}

	public void setInfo_content(String info_content){
		this.info_content=info_content;
	}

	public String getInfo_contentShow(){
		if (!StringUtil.isEmptyString(info_content)) {
			return Transcode.htmlDiscode(info_content);
		}
		return info_content;
	}
	
	public String getInfo_content(){
		return info_content;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
