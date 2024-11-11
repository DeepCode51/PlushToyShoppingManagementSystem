package com.soft.admin.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.soft.common.domain.BaseDomain;
import com.soft.common.util.StringUtil;

public class Cache extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -1519892304924888265L;
	private int cache_id; // 
	private String cache_con; // 
	private int user_id; // 

	private String ids;
	private String random;

	public List<OrdersDetail> getUserCard(){
		try {
			if (!StringUtil.isEmptyString(cache_con)) {
				 GsonBuilder gsonBuilder = new GsonBuilder();
		         gsonBuilder.serializeSpecialFloatingPointValues();
		         Gson gson = gsonBuilder.create();
				 return gson.fromJson(cache_con, new TypeToken<List<OrdersDetail>>(){}.getType());
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<OrdersDetail>();
		}
		return new ArrayList<OrdersDetail>();
	}
	
	public void setCache_id(int cache_id){
		this.cache_id=cache_id;
	}

	public int getCache_id(){
		return cache_id;
	}
	
	public void setCache_con(List<OrdersDetail> card){
		 GsonBuilder gsonBuilder = new GsonBuilder();
         gsonBuilder.serializeSpecialFloatingPointValues();
         Gson gson = gsonBuilder.create();
		 this.cache_con = gson.toJson(card);
	}

	public void setCache_con(String cache_con){
		this.cache_con=cache_con;
	}

	public String getCache_con(){
		return cache_con;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
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

}
