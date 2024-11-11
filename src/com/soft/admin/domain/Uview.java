package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;

@SuppressWarnings("serial")
//用户浏览记录
public class Uview extends BaseDomain {
	private int uview_id; // 
	private int user_id; // 
	private int goods_id; // 
	private String uview_date; // 

	private String real_name; // 
	private String goods_name; // 
	private String ids;
	private String random;

	public void setUview_id(int uview_id){
		this.uview_id=uview_id;
	}

	public int getUview_id(){
		return uview_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setGoods_id(int goods_id){
		this.goods_id=goods_id;
	}

	public int getGoods_id(){
		return goods_id;
	}

	public void setUview_date(String uview_date){
		this.uview_date=uview_date;
	}

	public String getUview_date(){
		return uview_date;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

}
