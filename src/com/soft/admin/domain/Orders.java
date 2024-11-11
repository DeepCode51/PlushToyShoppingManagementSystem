package com.soft.admin.domain;

import java.util.ArrayList;
import java.util.List;

import com.soft.common.domain.BaseDomain;

public class Orders extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int orders_id; // 
	private String orders_no;
	private String orders_date;
	private int user_id; // 
	private String real_name;
	private String user_address; // 
	private String user_phone; // 
	private double orders_money;
	private double user_discard;
	private double real_money;
	private int orders_flag; // 1：待发货 2:已发货 3:已收货4:已评价
	private String orders_note;
	
	private String orders_date_min;
	private String orders_date_max;
	
	private String orders_month;
	private String orders_month_min;
	private String orders_month_max;
	private String goods_name;
	private int goods_count;  
	private double goods_money;  
	
	private List<OrdersDetail> ordersDetails = new ArrayList<OrdersDetail>();
	
	private String ids;
	
	public String getOrders_flagDesc() {
		switch (orders_flag) {
			case 1:
				return "待发货";
			case 2:
				return "已发货";
			case 3:
				return "已收货"; 
			case 4:
				return "已评价";
			default:
				return "";
		}
	}
	
	public int getOrders_flag() {
		return orders_flag;
	}

	public void setOrders_flag(int orders_flag) {
		this.orders_flag = orders_flag;
	}

	public int getOrders_id() {
		return orders_id;
	}

	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOrders_date() {
		return orders_date;
	}

	public void setOrders_date(String orders_date) {
		this.orders_date = orders_date;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOrders_no() {
		return orders_no;
	}

	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public double getOrders_money() {
		return orders_money;
	}

	public void setOrders_money(double orders_money) {
		this.orders_money = orders_money;
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

	public List<OrdersDetail> getOrdersDetails() {
		return ordersDetails;
	}

	public void setOrdersDetails(List<OrdersDetail> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public double getUser_discard() {
		return user_discard;
	}

	public void setUser_discard(double user_discard) {
		this.user_discard = user_discard;
	}

	public double getReal_money() {
		return real_money;
	}

	public void setReal_money(double real_money) {
		this.real_money = real_money;
	}

	public String getOrders_month_min() {
		return orders_month_min;
	}

	public void setOrders_month_min(String orders_month_min) {
		this.orders_month_min = orders_month_min;
	}

	public String getOrders_month_max() {
		return orders_month_max;
	}

	public void setOrders_month_max(String orders_month_max) {
		this.orders_month_max = orders_month_max;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
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

	public String getOrders_month() {
		return orders_month;
	}

	public void setOrders_month(String orders_month) {
		this.orders_month = orders_month;
	}

	public String getOrders_note() {
		return orders_note;
	}

	public void setOrders_note(String orders_note) {
		this.orders_note = orders_note;
	}

}
