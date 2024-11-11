package com.soft.page.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft.admin.domain.Collect;
import com.soft.admin.domain.Evaluate;
import com.soft.admin.domain.Goods;
import com.soft.admin.domain.GoodsType;
import com.soft.admin.domain.Info;
import com.soft.admin.domain.Member;
import com.soft.admin.domain.Orders;
import com.soft.admin.domain.OrdersDetail;
import com.soft.admin.domain.Sblog;
import com.soft.admin.domain.SblogReply;
import com.soft.admin.domain.Uview;
import com.soft.util.Md5;
import com.soft.util.PaperUtil;
import com.soft.page.manager.IndexManager;
import com.soft.common.util.DateUtil;
import com.soft.common.util.JSONData;

@Controller
public class IndexAction{

	@Autowired
	IndexManager indexManager;
	public IndexManager getIndexManager() {
		return indexManager;
	}
	public void setIndexManager(IndexManager indexManager) {
		this.indexManager = indexManager;
	}

	String tip;
	
	/**
	 * @Title: index
	 * @Description: 首页
	 * @return String
	 */
	@RequestMapping(value="page_index.action",method=RequestMethod.GET)
	public String index(ModelMap model){
		//查询热销商品
		Goods goods = new Goods();
		goods.setStart(0);
		goods.setLimit(6);
		goods.setOrder_flag(1);
		List<Goods> goodss = indexManager.listGoodss(goods,null); 
		model.addAttribute("goodss1", goodss);
		
		//查询特价商品
		goods.setOrder_flag(2);
		List<Goods> goodss2 = indexManager.listGoodss(goods,null); 
		model.addAttribute("goodss2", goodss2);
		
		//查询商品类型
		GoodsType goodsType = new GoodsType();
		goodsType.setStart(-1);
		List<GoodsType> goodsTypes = indexManager.listGoodsTypes(goodsType, null);
		model.addAttribute("goodsTypes", goodsTypes);
		
		//查询公告
		Info info = indexManager.queryInfo(new Info());
		model.addAttribute("info", info);
		
		return "default"; 
	}
	
	/**
	 * @Title: index2
	 * @Description: 展示商品列表
	 * @return String
	 */
	@RequestMapping(value="page_index2.action")
	public String index2(Goods paramsGoods,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询商品信息集合
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//分页信息设置
			paperUtil.setPagination(paramsGoods);
			int[] sum={0};
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			model.addAttribute("goodss", goodss);
			paperUtil.setTotalCount(sum[0]);
			model.addAttribute("paramsGoods", paramsGoods);
			
			//查询商品类别
			GoodsType _goodsType1 = new GoodsType();
			_goodsType1.setStart(-1);
			List<GoodsType> goodsTypes1 = indexManager.listGoodsTypes(_goodsType1, null);
			if (goodsTypes1==null) {
				goodsTypes1 = new ArrayList<GoodsType>();
			}
			model.addAttribute("goodsTypes", goodsTypes1);

			
			//查询公告
			Info info = indexManager.queryInfo(new Info());
			model.addAttribute("info", info);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "default2";
	}
	
	/**
	 * @Title: goods
	 * @Description: 展示商品列表
	 * @return String
	 */
	@RequestMapping(value="page_goods.action")
	public String goods(Goods paramsGoods,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询商品信息集合
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//分页信息设置
			paperUtil.setPagination(paramsGoods);
			int[] sum={0};
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			model.addAttribute("goodss", goodss);
			paperUtil.setTotalCount(sum[0]);
			model.addAttribute("paramsGoods", paramsGoods);
			
			//查询商品类别
			GoodsType _goodsType1 = new GoodsType();
			_goodsType1.setStart(-1);
			List<GoodsType> goodsTypes1 = indexManager.listGoodsTypes(_goodsType1, null);
			if (goodsTypes1==null) {
				goodsTypes1 = new ArrayList<GoodsType>();
			}
			model.addAttribute("goodsTypes", goodsTypes1);

			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goods";
	}
	
	/**
	 * @Title: goodsHobby
	 * @Description: 展示商品推荐
	 * @return String
	 */
/**	@RequestMapping(value="page_goodsHobby.action")
	public String goodsHobby(Goods paramsGoods,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询商品信息集合
			List<Goods> goodss = indexManager.listGoodssHobby(httpSession); 
			model.addAttribute("goodss", goodss);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goodsHobby";
	}
	*/
	/**
	 * @Title: queryGoods
	 * @Description: 查询商品详情
	 * @return String
	 */
	@RequestMapping(value="page_queryGoods.action",method=RequestMethod.GET)
	public String queryGoods(Goods paramsGoods,Sblog paramsSblog,Evaluate paramsEvaluate,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			model.addAttribute("goods", goods);
			
			//查询评价信息
			paramsSblog.setStart(-1);
			List<Sblog> sblogs = indexManager.listSblogs(paramsSblog,null); 
			model.addAttribute("sblogs", sblogs);
			
			Member userFront = (Member)httpSession.getAttribute("userFront");
			if (userFront!=null) {
				Uview uview = new Uview();
				uview.setUser_id(userFront.getUser_id());
				uview.setGoods_id(goods.getGoods_id());
				Uview uview2 = indexManager.queryUview(uview);
				if (uview2==null) {
					uview.setUview_date(DateUtil.getCurDateTime());
					indexManager.addUview(uview);
				}
			}
			
			//查询评价集合
			paramsEvaluate.setStart(-1);
			List<Evaluate> evaluates = indexManager.listEvaluates(paramsEvaluate,null); 
			model.addAttribute("evaluates", evaluates);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goodsDetail";
	}
	
	/**
	 * @Title: sblog
	 * @Description: 查询留言板
	 * @return String
	 */
	@RequestMapping(value="page_sblog.action")
	public String sblog(Sblog paramsSblog,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询留言信息
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			//分页信息设置
			paperUtil.setPagination(paramsSblog);
			int[] sum={0};
			List<Sblog> sblogs = indexManager.listSblogs(paramsSblog,sum); 
			paperUtil.setTotalCount(sum[0]);
			model.addAttribute("sblogs", sblogs);
			model.addAttribute("paramsSblog", paramsSblog);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblog";
	}
	
	/**
	 * @Title: addSblog
	 * @Description: 新增留言
	 * @return String
	 */
	@RequestMapping(value="page_addSblog.action")
	@ResponseBody
	public JSONData addSblog(Sblog paramsSblog,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//新增留言
			indexManager.addSblog(paramsSblog);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("新增留言失败！");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: addSblogReply
	 * @Description: 回复留言
	 * @return String
	 */
	@RequestMapping(value="page_addSblogReply.action")
	@ResponseBody
	public JSONData addSblogReply(SblogReply paramsSblogReply,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//回复留言
			indexManager.addSblogReply(paramsSblogReply);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("回复留言失败！");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 查询公告
	 * @return String
	 */
	@RequestMapping(value="page_listInfos.action")
	public String listInfos(Info paramsInfo,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsInfo==null) {
				paramsInfo = new Info();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsInfo);
			//总的条数
			int[] sum={0};
			//查询公告列表
			List<Info> infos = indexManager.listInfos(paramsInfo,sum); 
			model.addAttribute("infos", infos);
			
			model.addAttribute("paramsInfo", paramsInfo);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "info";
	}
	
	/**
	 * @Title: queryInfo
	 * @Description: 查询公告
	 * @return String
	 */
	@RequestMapping(value="page_queryInfo.action",method=RequestMethod.GET)
	public String queryInfo(Info paramsInfo,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到公告
			Info info = indexManager.queryInfo(paramsInfo);
			model.addAttribute("info", info);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "infoDetail";
	}
	
	/**
	 * @Title: listCard
	 * @Description: 查询购物车
	 * @return String
	 */
	@RequestMapping(value="page_listCard.action")
	public String listCard(PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询购物车
			List<OrdersDetail> ordersDetails = indexManager.listCard(httpSession);
			model.addAttribute("ordersDetails", ordersDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "card";
	}
	
	/**
	 * @Title: addCard
	 * @Description: 添加到购物车
	 * @return String
	 */
	@RequestMapping(value="page_addCard.action")
	public String addCard(OrdersDetail paramsOrdersDetail,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//添加到购物车
			indexManager.addCard(paramsOrdersDetail, httpSession);
			//查询购物车
			List<OrdersDetail> ordersDetails = indexManager.listCard(httpSession);
			model.addAttribute("ordersDetails", ordersDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "card";
	}
	
	/**
	 * @Title: modifyCard
	 * @Description: 修改购物车
	 * @return String
	 */
	@RequestMapping(value="page_modifyCard.action")
	@ResponseBody
	public JSONData modifyCard(OrdersDetail paramsOrdersDetail,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//修改购物车
			indexManager.modifyCard(paramsOrdersDetail, httpSession);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("修改数量失败！");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: delGoodsFromCard
	 * @Description: 从购物车删除
	 * @return String
	 */
	@RequestMapping(value="page_delGoodsFromCard.action")
	public String delGoodsFromCard(OrdersDetail paramsOrdersDetail,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//从购物车删除
			indexManager.delGoodsFromCard(paramsOrdersDetail.getGoods_id(), httpSession);
			
			//查询购物车
			List<OrdersDetail> ordersDetails = indexManager.listCard(httpSession);
			model.addAttribute("ordersDetails", ordersDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "card";
	}
	
	/**
	 * @Title: clearCard
	 * @Description: 清空购物车
	 * @return String
	 */
	@RequestMapping(value="page_clearCard.action")
	public String clearCard(OrdersDetail paramsOrdersDetail,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//清空购物车
			indexManager.clearCard(httpSession);
			//查询购物车
			model.addAttribute("ordersDetails", null);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "card";
	}
	
	/**
	 * @Title: addOrdersShow
	 * @Description: 新增订单页面
	 * @return String
	 */
	@RequestMapping(value="page_addOrdersShow.action")
	public String addOrdersShow(OrdersDetail paramsOrdersDetail,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询购物车
			List<OrdersDetail> ordersDetails = indexManager.listCard(httpSession);
			model.addAttribute("ordersDetails", ordersDetails);
			
			//查询订单总额
			double orders_money=0;
			for (int i = 0; i < ordersDetails.size(); i++) {
				OrdersDetail ordersDetail = ordersDetails.get(i);
				orders_money+=ordersDetail.getGoods_money();//累计总金额
			}
			model.addAttribute("orders_money", Math.round(orders_money*10)/10.0);
			
			//查询折扣金额
			Member userFront = (Member)httpSession.getAttribute("userFront");
			model.addAttribute("user_discard", userFront.getMember_discard());
			double real_money = Math.round(orders_money*userFront.getMember_discard()*10)/10.0;
			model.addAttribute("real_money", real_money);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersAdd";
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 新增订单
	 * @return String
	 */
	@RequestMapping(value="page_addOrders.action")
	@ResponseBody
	public JSONData addOrders(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//新增订单
			String tip = indexManager.addOrders(paramsOrders,httpSession);
			
			if (tip!=null) {
				jsonData.setErrorReason(tip);
				return jsonData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("提交订单失败");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: addCollect
	 * @Description: 新增收藏
	 * @return String
	 */
	@RequestMapping(value="page_addCollect.action")
	@ResponseBody
	public JSONData addCollect(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//收藏验证
			Collect collect = indexManager.queryCollect(paramsCollect);
			if (collect==null) {
				//新增收藏
				indexManager.addCollect(paramsCollect);
			}else{
				jsonData.setErrorReason("您已经收藏过了！");
				return jsonData;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("收藏失败");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	@RequestMapping(value="page_saveUserFront.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData saveUserFront(Member paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			Member admin = new Member();
			admin.setUser_id(paramsUser.getUser_id());
			admin = indexManager.getUser(admin);
			httpSession.setAttribute("userFront", admin);
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	@RequestMapping(value="page_saveUserFrontPass.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData saveUserFrontPass(Member paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			Member userFront = (Member)httpSession.getAttribute("userFront");
			if (!userFront.getUser_pass().equals(Md5.makeMd5(paramsUser.getUser_passOld()))) {
				jsonData.setErrorReason("修改异常，原密码错误");
				return jsonData;
			}
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			if (userFront!=null) {
				userFront.setUser_pass(paramsUser.getUser_pass());
				httpSession.setAttribute("userFront", userFront);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: listMySblogs
	 * @Description: 查询留言信息
	 * @return String
	 */
	@RequestMapping(value="page_listMySblogs.action")
	public String listMySblogs(Sblog paramsSblog,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			//设置分页信息
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			int[] sum={0};
			//用户身份限制
			Member userFront = (Member)httpSession.getAttribute("userFront");
			paramsSblog.setUser_id(userFront.getUser_id());
			List<Sblog> sblogs = indexManager.listSblogs(paramsSblog,sum); 
			model.addAttribute("sblogs", sblogs);
			model.addAttribute("paramsSblog", paramsSblog);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogShow";
	}
	
	/**
	 * @Title: listMyCollects
	 * @Description: 查询我的藏品收藏
	 * @return String
	 */
	@RequestMapping(value="page_listMyCollects.action")
	public String listMyCollects(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsCollect==null) {
				paramsCollect = new Collect();
			}
			//获取用户,用户只能查询自己的藏品收藏
			Member userFront = (Member)httpSession.getAttribute("userFront");
			paramsCollect.setUser_id(userFront.getUser_id());
			//设置分页信息
			paperUtil.setLimit(7);
			paperUtil.setPagination(paramsCollect);
			//总的条数
			int[] sum={0};
			//查询藏品收藏
			List<Collect> collects = indexManager.listCollects(paramsCollect,sum); 
			model.addAttribute("collects", collects);
			paperUtil.setTotalCount(sum[0]);
			model.addAttribute("paramsCollect", paramsCollect);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "collectShow";
	}
	
	/**
	 * @Title: delCollects
	 * @Description: 删除藏品收藏
	 * @return String
	 */
	@RequestMapping(value="page_delCollects.action")
	@ResponseBody
	public JSONData delCollects(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //删除藏品收藏
			indexManager.delCollects(paramsCollect);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器繁忙！");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: listMyOrderss
	 * @Description: 查询我的商品订单
	 * @return String
	 */
	@RequestMapping(value="page_listMyOrderss.action")
	public String listMyOrderss(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//获取用户,用户只能查询自己的订单
			Member userFront = (Member)httpSession.getAttribute("userFront");
			paramsOrders.setUser_id(userFront.getUser_id());
			//设置分页信息
			paperUtil.setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询商品预约列表
			List<Orders> orderss = indexManager.listOrderss(paramsOrders,sum); 
			
			model.addAttribute("orderss", orderss);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersShow";
	}
	
	/**
	 * @Title: listMyOrdersDetails
	 * @Description: 查询我的订单明细
	 * @return String
	 */
	@RequestMapping(value="page_listMyOrdersDetails.action")
	public String listMyOrdersDetails(OrdersDetail paramsOrdersDetail,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsOrdersDetail==null) {
				paramsOrdersDetail = new OrdersDetail();
			}
			//设置分页信息
			paramsOrdersDetail.setStart(-1);
			//查询订单明细
			List<OrdersDetail> ordersDetails = indexManager.listOrdersDetails(paramsOrdersDetail,null); 
			model.addAttribute("ordersDetails", ordersDetails);
			
			//订单信息
			model.addAttribute("orders_no", paramsOrdersDetail.getOrders_no());
			if (ordersDetails!=null && ordersDetails.size()>0) {
				model.addAttribute("orders_money", ordersDetails.get(0).getOrders_money());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersDetailShow";
	}
	
	/**
	 * @Title: finishOrders
	 * @Description: 确认收货
	 * @return String
	 */
	@RequestMapping(value="page_finishOrders.action")
	@ResponseBody
	public JSONData finishOrders(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//确认收货
			indexManager.finishOrders(paramsOrders, httpSession);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("确认收货失败！");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: addEvaluateShow
	 * @Description: 评价商品界面
	 * @return String
	 */
	@RequestMapping(value="page_addEvaluateShow.action")
	public String addEvaluateShow(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询订单
			Orders orders = indexManager.queryOrders(paramsOrders);
			model.addAttribute("orders", orders);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "evaluateAdd";
	}

	/**
	 * @Title: addEvaluate
	 * @Description: 评价商品
	 * @return String
	 */
	@RequestMapping(value="page_addEvaluate.action")
	@ResponseBody
	public JSONData addEvaluate(Evaluate paramsEvaluate,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//新增商品评价
			indexManager.addEvaluate(paramsEvaluate);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("评价商品失败！");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: reg
	 * @Description: 跳转注册页面
	 * @return String
	 */
	@RequestMapping(value="page_reg.action")
	public String reg(){
		return "reg";
	}
	
	/**
	 * @Title: myInfo
	 * @Description: 跳转个人信息页面
	 * @return String
	 */
	@RequestMapping(value="page_myInfo.action")
	public String myInfo(){
		return "myInfo";
	}
	
	/**
	 * @Title: myPwd
	 * @Description: 跳转个人密码页面
	 * @return String
	 */
	@RequestMapping(value="page_myPwd.action")
	public String myPwd(){
		return "myPwd";
	}
}
