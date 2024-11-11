package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.OrdersDetail;

public interface IOrdersDetailDao {

	public abstract int addOrdersDetail(OrdersDetail ordersDetail);

	public abstract int delOrdersDetail(String detail_id);

	public abstract int delOrdersDetails(String[] detail_ids);

	public abstract int updateOrdersDetail(OrdersDetail ordersDetail);

	public abstract OrdersDetail getOrdersDetail(OrdersDetail ordersDetail);

	public abstract List<OrdersDetail>  listOrdersDetails(OrdersDetail ordersDetail);

	public abstract int listOrdersDetailsCount(OrdersDetail ordersDetail);

	public abstract List<OrdersDetail>  listOrdersDetailsSum(OrdersDetail ordersDetail);

	public abstract int listOrdersDetailsSumCount(OrdersDetail ordersDetail);

}
