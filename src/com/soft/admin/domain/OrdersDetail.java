package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;

public class OrdersDetail extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int detail_id; // 
	private String orders_no; // 
	private int goods_id; // 
	private String goods_name;
	private double goods_price; //
	private int goods_count;  
	private double goods_money;  

	private int user_id;
	private String real_name;
	private String orders_money;
	private String orders_date_min;
	private String orders_date_max;
	private String ids;
	
	private int goods_count_real;  

	public int getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}

	public String getOrders_no() {
		return orders_no;
	}

	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public double getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}

	public int getGoods_count() {
		return goods_count;
	}

	public void setGoods_count(int goods_count) {
		this.goods_count = goods_count;
	}

	public double getGoods_money() {
		return goods_money;
	}

	public void setGoods_money(double goods_money) {
		this.goods_money = goods_money;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getOrders_money() {
		return orders_money;
	}

	public void setOrders_money(String orders_money) {
		this.orders_money = orders_money;
	}

	public int getGoods_count_real() {
		return goods_count_real;
	}

	public void setGoods_count_real(int goods_count_real) {
		this.goods_count_real = goods_count_real;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOrders_date_min() {
		return orders_date_min;
	}

	public void setOrders_date_min(String orders_date_min) {
		this.orders_date_min = orders_date_min;
	}

	public String getOrders_date_max() {
		return orders_date_max;
	}

	public void setOrders_date_max(String orders_date_max) {
		this.orders_date_max = orders_date_max;
	}
	
}
