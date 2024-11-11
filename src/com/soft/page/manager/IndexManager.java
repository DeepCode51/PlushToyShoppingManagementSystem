package com.soft.page.manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.admin.dao.ICacheDao;
import com.soft.admin.dao.ICollectDao;
import com.soft.admin.dao.IEvaluateDao;
import com.soft.admin.dao.IGoodsDao;
import com.soft.admin.dao.IGoodsTypeDao;
import com.soft.admin.dao.IInfoDao;
import com.soft.admin.dao.IMemberDao;
import com.soft.admin.dao.IMemberTypeDao;
import com.soft.admin.dao.IOrdersDao;
import com.soft.admin.dao.IOrdersDetailDao;
import com.soft.admin.dao.ISblogDao;
import com.soft.admin.dao.ISblogReplyDao;
import com.soft.admin.dao.IUviewDao;
import com.soft.admin.domain.Cache;
import com.soft.admin.domain.Collect;
import com.soft.admin.domain.Evaluate;
import com.soft.admin.domain.Goods;
import com.soft.admin.domain.GoodsType;
import com.soft.admin.domain.Info;
import com.soft.admin.domain.Member;
import com.soft.admin.domain.MemberType;
import com.soft.admin.domain.Orders;
import com.soft.admin.domain.OrdersDetail;
import com.soft.admin.domain.Sblog;
import com.soft.admin.domain.SblogReply;
import com.soft.admin.domain.Uview;
import com.soft.common.util.DateUtil;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;
import com.soft.util.Md5;

@Service
public class IndexManager {

	@Autowired
	IMemberDao memberDao;
	@Autowired
	IMemberTypeDao memberTypeDao;
	@Autowired
	IGoodsTypeDao goodsTypeDao;
	@Autowired
	IGoodsDao goodsDao;
	@Autowired
	ISblogDao sblogDao;
	@Autowired
	ISblogReplyDao sblogReplyDao;
	@Autowired
	IOrdersDao ordersDao;
	@Autowired
	IOrdersDetailDao ordersDetailDao;
	@Autowired
	ICollectDao collectDao;
	@Autowired
	IInfoDao infoDao;
	@Autowired
	IUviewDao uviewDao;
	@Autowired
	ICacheDao cacheDao;
	@Autowired
	IEvaluateDao evaluateDao;
	
	/**
	 * @Title: listEvaluates
	 * @Description: 查询商品评价信息
	 * @param evaluate
	 * @return List<Evaluate>
	 */
	public List<Evaluate>  listEvaluates(Evaluate evaluate,int[] sum){
		if (sum!=null) {
			sum[0] = evaluateDao.listEvaluatesCount(evaluate);
		}
		List<Evaluate> evaluates = evaluateDao.listEvaluates(evaluate);
		return evaluates;
	}
	
	 
	/**
	 * @Title: addEvaluate
	 * @Description: 添加商品评价
	 * @param evaluate
	 * @return void
	 */
	public void addEvaluate(Evaluate evaluate) {
		//添加商品评价
		evaluate.setEvaluate_date(DateUtil.getCurDateTime());
		evaluateDao.addEvaluateBatch(evaluate);
		
		//更新订单为已评价
		Orders orders = new Orders();
		orders.setOrders_no(evaluate.getOrders_no());
		orders.setOrders_flag(4);
		ordersDao.updateOrders(orders);
	}
	
	/**
	 * @Title: listCaches
	 * @Description: 用户缓存查询
	 * @param cache
	 * @return List<Cache>
	 */
	public List<Cache> listCaches(Cache cache, int[] sum) {
		
		if (sum != null) {
			sum[0] = cacheDao.listCachesCount(cache);
		}
		List<Cache> caches = cacheDao.listCaches(cache);

		
		return caches;
	}

	/**
	 * @Title: queryCache
	 * @Description: 用户缓存查询
	 * @param cache
	 * @return Cache
	 */
	public Cache queryCache(Cache cache) {
		
		Cache _cache = cacheDao.getCache(cache);
		
		return _cache;
	}

	/**
	 * @Title: addCache
	 * @Description: 添加用户缓存
	 * @param cache
	 * @return void
	 */
	public void addCache(Cache cache) {
		
		cacheDao.addCache(cache);
		
	}

	/**
	 * @Title: updateCache
	 * @Description: 更新用户缓存信息
	 * @param cache
	 * @return void
	 */
	public void updateCache(Cache cache) {
		
		cacheDao.updateCache(cache);
		
	}
	
	/**
	 * @Title: refreshCache
	 * @Description: 更新用户缓存信息
	 * @param cache
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void refreshCache(int user_id,HttpSession httpSession) {
		List<OrdersDetail> card = (List<OrdersDetail>)httpSession.getAttribute("card");
		if (card==null) {
			cacheDao.delCacheByUserId(user_id);
		}else{
			Cache cache = new Cache();
			cache.setUser_id(user_id);
			cache = cacheDao.getCache(cache);
			if (cache!=null) {
				cache.setCache_con(card);
				cacheDao.updateCache(cache);
			}else {
				cache = new Cache();
				cache.setUser_id(user_id);
				cache.setCache_con(card);
				cacheDao.addCache(cache);
			}
		}
	}

	/**
	 * @Title: delCache
	 * @Description: 删除用户缓存信息
	 * @param cache
	 * @return void
	 */
	public void delCaches(Cache cache) {
		
		cacheDao.delCaches(cache.getIds().split(","));
		
	}
	
	/**
	 * @Title: listCollects
	 * @Description: 用户收藏查询
	 * @param collect
	 * @return List<Collect>
	 */
	public List<Collect> listCollects(Collect collect, int[] sum) {
		if (sum != null) {
			sum[0] = collectDao.listCollectsCount(collect);
		}
		List<Collect> collects = collectDao.listCollects(collect);

		return collects;
	}

	/**
	 * @Title: queryCollect
	 * @Description: 用户收藏查询
	 * @param collect
	 * @return Collect
	 */
	public Collect queryCollect(Collect collect) {
		Collect _collect = collectDao.getCollect(collect);
		return _collect;
	}

	/**
	 * @Title: addCollect
	 * @Description: 添加用户收藏
	 * @param collect
	 * @return void
	 */
	public void addCollect(Collect collect) {
		collect.setCollect_date(DateUtil.getCurDateTime());
		collectDao.addCollect(collect);
	}

	/**
	 * @Title: updateCollect
	 * @Description: 更新用户收藏信息
	 * @param collect
	 * @return void
	 */
	public void updateCollect(Collect collect) {
		collectDao.updateCollect(collect);
	}

	/**
	 * @Title: delCollect
	 * @Description: 删除用户收藏信息
	 * @param collect
	 * @return void
	 */
	public void delCollects(Collect collect) {
		collectDao.delCollects(collect.getIds().split(","));
	}
	
	/**
	 * @Title: listUviews
	 * @Description: 浏览记录查询
	 * @param uview
	 * @return List<Uview>
	 */
	public List<Uview> listUviews(Uview uview, int[] sum) {
		if (sum != null) {
			sum[0] = uviewDao.listUviewsCount(uview);
		}
		List<Uview> uviews = uviewDao.listUviews(uview);

		return uviews;
	}

	/**
	 * @Title: queryUview
	 * @Description: 浏览记录查询
	 * @param uview
	 * @return Uview
	 */
	public Uview queryUview(Uview uview) {
		Uview _uview = uviewDao.getUview(uview);
		return _uview;
	}

	/**
	 * @Title: addUview
	 * @Description: 添加浏览记录
	 * @param uview
	 * @return void
	 */
	public void addUview(Uview uview) {
		uview.setUview_date(DateUtil.getCurDateTime());
		uviewDao.addUview(uview);
	}

	/**
	 * @Title: updateUview
	 * @Description: 更新浏览记录信息
	 * @param uview
	 * @return void
	 */
	public void updateUview(Uview uview) {
		uviewDao.updateUview(uview);
	}

	/**
	 * @Title: delUview
	 * @Description: 删除浏览记录信息
	 * @param uview
	 * @return void
	 */
	public void delUviews(Uview uview) {
		uviewDao.delUviews(uview.getIds().split(","));
	}

	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public Member getUser(Member user){
		
		Member _user = memberDao.getMember(user);
		
		return _user;
	}
	 
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(Member user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		memberDao.updateMember(user);
		
	}
	
	/**
	 * @Title: listGoodsTypes
	 * @Description: 商品类型查询
	 * @param goodsType
	 * @return List<GoodsType>
	 */
	public List<GoodsType> listGoodsTypes(GoodsType goodsType, int[] sum) {
		
		if (sum != null) {
			sum[0] = goodsTypeDao.listGoodsTypesCount(goodsType);
		}
		List<GoodsType> goodsTypes = goodsTypeDao.listGoodsTypes(goodsType);

		
		return goodsTypes;
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 查询商品信息
	 * @param goods
	 * @return List<Goods>
	 */
	public List<Goods>  listGoodss(Goods goods,int[] sum){
		
		if (sum!=null) {
			sum[0] = goodsDao.listGoodssCount(goods);
		}
		List<Goods> goodss = goodsDao.listGoodss(goods);
		
		return goodss;
	}
	
	/**
	 * @Title: findLikesByUser   
	 * @Description: 查询所有用户的购买记录 
	 * @param: @return      
	 * @return: Map<Integer,List<OrdersDetail>>      
	 * @throws
	 */
	public Map<Integer, List<OrdersDetail>> findLikesByUser(){
		Map<Integer, List<OrdersDetail>> ulikeMap = new HashMap<Integer, List<OrdersDetail>>();
		//查询购买记录
		OrdersDetail uhis = new OrdersDetail();
		uhis.setStart(-1);
		//uhis.setInfo_idflag(1);
		List<OrdersDetail> uhisList = ordersDetailDao.listOrdersDetails(uhis);
		List<String> uhisList2 = new ArrayList<>();
		if (uhisList!=null) {
			for (OrdersDetail uhis2 : uhisList) {
				String key = uhis2.getUser_id()+"_"+uhis2.getGoods_id();
				List<OrdersDetail> ulikeList = ulikeMap.get(uhis2.getUser_id());
				if (ulikeList==null) {
					ulikeList = new ArrayList<>();
					ulikeMap.put(uhis2.getUser_id(), ulikeList);
				}
				if (!uhisList2.contains(key)) {
					ulikeList.add(uhis2);
					uhisList2.add(key);
				}
				
			}
		}
		
		//查询浏览记录
		Uview uview = new Uview();
		uview.setStart(-1);
		List<Uview> uviews = uviewDao.listUviews(uview);
		if (uviews!=null) {
			for (Uview _uview : uviews) {
				String key = _uview.getUser_id()+"_"+_uview.getGoods_id();
				List<OrdersDetail> ulikeList = ulikeMap.get(_uview.getUser_id());
				if (ulikeList==null) {
					ulikeList = new ArrayList<>();
					ulikeMap.put(_uview.getUser_id(), ulikeList);
				}
				if (!uhisList2.contains(key)) {
					OrdersDetail uhis2 = new OrdersDetail();
					uhis2.setUser_id(_uview.getUser_id());
					uhis2.setGoods_id(_uview.getGoods_id());
					ulikeList.add(uhis2);
					uhisList2.add(key);
				}
				
			}
		}
		
		return ulikeMap;
	}
	
	/**
	 * @Title: listGoodssHobby
	 * @Description: 商品推荐查询
	 * @param goods
	 * @return List<Goods>
	 */
	public List<Goods> listGoodssHobby(HttpSession session) {
		List<Goods> recomLists = new ArrayList<Goods>();
		Member userFront = (Member)session.getAttribute("userFront");//当前用户
		int uid = userFront.getUser_id();//当前用户ID
		
		Map<Integer, List<OrdersDetail>> ulikeMap = findLikesByUser();//所有用户购买的商品集合
		List<OrdersDetail> likeLists;  //其他用户购买的商品列表
		Goods goods = new Goods();
		goods.setStart(-1);
		goods.setOrder_flag(1);
		List<Goods> goodss = goodsDao.listGoodss(goods);   //所有商品列表
		Map<Integer, Integer> goodsIdIndexMap = new HashMap<>(); //所有商品ID的索引值{商品ID: 索引值}
		if (goodss==null || goodss.size()==0) {
			return recomLists;
		}
		int index=0;
		for (Goods goods2 : goodss) {
			goodsIdIndexMap.put(goods2.getGoods_id(), index++);
		}
        int[][] comMatrix = new int[goodss.size()][goodss.size()];   //共现矩阵
        int[] N = new int[goodss.size()];                              //购买每个商品的人数
		
        for (Integer curID : ulikeMap.keySet()) {
        	if(curID==uid) continue;//当前用户则跳过
        	likeLists = ulikeMap.get(curID); //当前用户的购买列表
            for(int i = 0; i < likeLists.size(); i++){
				if(!goodsIdIndexMap.containsKey(likeLists.get(i).getGoods_id())){
					continue;
				}
                int pid1 = goodsIdIndexMap.get(likeLists.get(i).getGoods_id());
                ++N[pid1];
                for(int j = i+1; j < likeLists.size(); j++){
					if(!goodsIdIndexMap.containsKey(likeLists.get(j).getGoods_id())){
						continue;
					}
                    int pid2 = goodsIdIndexMap.get(likeLists.get(j).getGoods_id());
                    ++comMatrix[pid1][pid2];
                    ++comMatrix[pid2][pid1]; //两两加一
                }
            }
        	
		}
        
        TreeSet<Goods> preList = new TreeSet<Goods>(new Comparator<Goods>() {
            public int compare(Goods o1, Goods o2) {
                if(o1.getW()!=o2.getW()) {
                	 return (int) (o1.getW()-o2.getW())*100;
                }else if(o1.getOrders_count()!=o2.getOrders_count()) {
                	return o1.getOrders_count() > o2.getOrders_count() ? 1 : -1;
                }else {
					return 0;
				}
            }
        }); //预处理的列表
        
        likeLists = ulikeMap.get(uid);       //当前用户喜欢的商品列表
        Map<Integer, Goods> used = new HashMap<>();//判重数组
        if (likeLists!=null && likeLists.size()>0) {
        	for(OrdersDetail like: likeLists){
                int Nij = 0;// 既喜欢i又喜欢j的人数
                double Wij;  //相似度
                Goods tmp;  //当前的商品

				if(!goodsIdIndexMap.containsKey(like.getGoods_id())){
					continue;
				}

                int i = goodsIdIndexMap.get(like.getGoods_id());
                for(Goods goodsCur: goodss){
                    if(like.getGoods_id() == goodsCur.getGoods_id()) continue;
					if(!goodsIdIndexMap.containsKey(goodsCur.getGoods_id())){
						continue;
					}
                    int j = goodsIdIndexMap.get(goodsCur.getGoods_id());

                    if(comMatrix[i][j]!=0 && (N[i]*N[j]!=0)) {
                    	Nij = comMatrix[i][j];
                        Wij = (double)Nij/Math.sqrt(N[i]*N[j]); //计算余弦相似度

                        tmp = goodsDao.getGoods(goodsCur);
                        tmp.setW(Wij);

                        if(used.containsKey(j)) {
                        	tmp.setW(Math.max(Wij, used.get(j).getW()));
                        }
                        used.put(j, tmp);
                    }
                }
            }
		}
        for (Integer key : used.keySet()) {
        	preList.add(used.get(key));
		}

        for(int i = 0; preList.size()>0 && i<6; i++){
            recomLists.add(preList.pollLast());
            preList.pollLast();
        }
        if(recomLists.size()<5){
            //推荐数量不满5个, 补足评分数最高的商品
        	for (int i = 0; i < goodss.size(); i++) {
        		 int j = goodsIdIndexMap.get(goodss.get(i).getGoods_id());
        		 if(!used.containsKey(j)){
        			 recomLists.add(goodss.get(i));
        			 if (recomLists.size()>=6) {
						break;
					}
        		 }
			}
        }

        return recomLists;
	}
	
	
	/**
	 * @Title: queryGoods
	 * @Description: 商品查询
	 * @param goods
	 * @return Goods
	 */
	public Goods queryGoods(Goods goods) {
		
		Goods _goods = goodsDao.getGoods(goods);
		
		return _goods;
	}
	
	/**
	 * @Title: addCard
	 * @Description: 添加购物车
	 * @param ordersDetail
	 */
	@SuppressWarnings("unchecked")
	public void addCard(OrdersDetail ordersDetail, HttpSession httpSession) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) httpSession.getAttribute("card");
		if (card==null) {
			card = new ArrayList<OrdersDetail>();
		}
		OrdersDetail oldDetail = getGoodsFromCard(ordersDetail.getGoods_id(), httpSession);
		if (oldDetail==null) {//新增商品
			//计算总额
			double goods_money = ordersDetail.getGoods_price()*ordersDetail.getGoods_count();
			ordersDetail.setGoods_money(goods_money);
			card.add(ordersDetail);
		}else {//修改购物车商品
			card.remove(oldDetail);
			oldDetail.setGoods_count(oldDetail.getGoods_count()+ordersDetail.getGoods_count());
			double goods_money = oldDetail.getGoods_price()*oldDetail.getGoods_count();
			oldDetail.setGoods_money(goods_money);
			card.add(oldDetail);
		}
		httpSession.setAttribute("card", card);
		
		Member userFront = (Member)httpSession.getAttribute("userFront");
		refreshCache(userFront.getUser_id(), httpSession);
		
	}
	
	/**
	 * @Title: modifyCard
	 * @Description: 修改购物车商品
	 * @param ordersDetail
	 */
	@SuppressWarnings("unchecked")
	public void modifyCard(OrdersDetail ordersDetail, HttpSession httpSession) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) httpSession.getAttribute("card");
		OrdersDetail oldDetail = getGoodsFromCard(ordersDetail.getGoods_id(), httpSession);
		//修改购物车商品
		card.remove(oldDetail);
		oldDetail.setGoods_count(ordersDetail.getGoods_count());
		double goods_money = oldDetail.getGoods_price()*oldDetail.getGoods_count();
		oldDetail.setGoods_money(goods_money);
		card.add(oldDetail);
		httpSession.setAttribute("card", card);
		
		Member userFront = (Member)httpSession.getAttribute("userFront");
		refreshCache(userFront.getUser_id(), httpSession);
	}
	
	/**
	 * @Title: delGoodsFromCard
	 * @Description: 从购物车删除商品
	 * @param goods_id
	 */
	@SuppressWarnings("unchecked")
	public void delGoodsFromCard(int goods_id, HttpSession httpSession) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) httpSession.getAttribute("card");
		if (card!=null) {
			for (OrdersDetail ordersDetail : card) {
				if (ordersDetail.getGoods_id()==goods_id) {
					card.remove(ordersDetail);
					break;
				}
			}
		}
		httpSession.setAttribute("card", card);
		
		Member userFront = (Member)httpSession.getAttribute("userFront");
		refreshCache(userFront.getUser_id(), httpSession);
		
	}
	
	/**
	 * @Title: clearCard
	 * @Description: 清空购物车
	 */
	public void clearCard(HttpSession httpSession) {
		//清空购物车
		httpSession.removeAttribute("card");
		
		Member userFront = (Member)httpSession.getAttribute("userFront");
		refreshCache(userFront.getUser_id(), httpSession);
	}
	
	@SuppressWarnings("unchecked")
	private OrdersDetail getGoodsFromCard(int goods_id, HttpSession httpSession) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) httpSession.getAttribute("card");
		if (card!=null) {
			for (OrdersDetail ordersDetail : card) {
				if (ordersDetail.getGoods_id()==goods_id) {
					return ordersDetail;
				}
			}
		}else {
			return null;
		}
		return null;
	}
	
	/**
	 * @Title: listCard
	 * @Description: 查询购物车
	 * @return List<OrdersDetail>
	 */
	@SuppressWarnings("unchecked")
	public List<OrdersDetail> listCard(HttpSession httpSession) {
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) httpSession.getAttribute("card");
		if (card==null) {
			card = new ArrayList<OrdersDetail>();
		}
		for (OrdersDetail ordersDetail : card) {
			Goods goods = new Goods();
			goods.setGoods_id(ordersDetail.getGoods_id());
			goods = goodsDao.getGoods(goods);
			ordersDetail.setGoods_count_real(goods.getGoods_count());
		}
		return card;
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 添加商品订单
	 * @param orders
	 * @return Orders
	 */
	@SuppressWarnings("unchecked")
	public String addOrders(Orders orders, HttpSession httpSession) {
		
		//生成订单号
		String orders_no = DateUtil.dateToDateString(new Date(), "yyyyMMddHHmmss")+orders.getUser_id();
		orders.setOrders_no(orders_no);
		//订单日期
		orders.setOrders_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd"));
		//1：待发货
		orders.setOrders_flag(1);
		//查询购物车
		List<OrdersDetail> card = (List<OrdersDetail>) httpSession.getAttribute("card");
		List<Goods> goodsList = new ArrayList<>();
		double orders_money=0;
		for (int i = 0; i < card.size(); i++) {
			OrdersDetail ordersDetail = card.get(i);
			orders_money+=ordersDetail.getGoods_money();//累计总金额
			ordersDetail.setOrders_no(orders_no);//设置订单号
			//保存订单明细
			//ordersDetailDao.addOrdersDetail(ordersDetail);
			
			//更新库存数量
			Goods goods = new Goods();
			goods.setGoods_id(ordersDetail.getGoods_id());
			goods = goodsDao.getGoods(goods);
			if (goods.getGoods_count() < ordersDetail.getGoods_count()) {
				return goods.getGoods_name()+" 库存数量不足！";
			}
			goods.setGoods_count(goods.getGoods_count() - ordersDetail.getGoods_count());
			goodsList.add(goods);
			//goodsDao.updateGoodsCount(goods);
		}
		for (int i = 0; i < card.size(); i++) {
			OrdersDetail ordersDetail = card.get(i);
			ordersDetailDao.addOrdersDetail(ordersDetail);
		}
		for (Goods goods : goodsList) {
			goodsDao.updateGoodsCount(goods);
		}
		//设置总额
		orders.setOrders_money(orders_money);
		//保存订单
		ordersDao.addOrders(orders);
		
		//清空购物车
		httpSession.removeAttribute("card");
		refreshCache(orders.getUser_id(), httpSession);
		
		return null;
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 商品订单查询
	 * @param orders
	 * @return List<Orders>
	 */
	public List<Orders>  listOrderss(Orders orders,int[] sum){
		
		if (sum!=null) {
			sum[0] = ordersDao.listOrderssCount(orders);
		}
		List<Orders> orderss = ordersDao.listOrderss(orders);
		
		
		return orderss;
	}
	
	/**
	 * @Title: queryOrders
	 * @Description: 商品订单查询
	 * @param orders
	 * @return Orders
	 */
	public Orders  queryOrders(Orders orders){
		
		Orders _orders = ordersDao.getOrders(orders);
		
		return _orders;
	}
	
	/**
	 * @Title: listOrdersDetails
	 * @Description: 订单明细查询
	 * @param ordersDetail
	 * @return List<Borrow>
	 */
	public List<OrdersDetail> listOrdersDetails(OrdersDetail ordersDetail, int[] sum) {
		
		if (sum != null) {
			sum[0] = ordersDetailDao.listOrdersDetailsCount(ordersDetail);
		}
		List<OrdersDetail> ordersDetails = ordersDetailDao.listOrdersDetails(ordersDetail);

		
		return ordersDetails;
	}
	
	/**
	 * @Title: finishOrders
	 * @Description: 确认收货
	 * @param Orders
	 * @return void
	 */
	public void finishOrders(Orders orders, HttpSession httpSession) {
		
		//确认收货
		orders.setOrders_flag(3);
		ordersDao.updateOrders(orders);
		
		//更新用户
		orders = ordersDao.getOrders(orders);
		Member userFront = (Member)httpSession.getAttribute("userFront");
		userFront.setUser_credit(userFront.getUser_credit() + (int)(Math.ceil(orders.getReal_money())));
		MemberType memberType = new MemberType();
		memberType.setStart(-1);
		List<MemberType> memberTypes = memberTypeDao.listMemberTypes(memberType);
		if (memberTypes!=null && memberTypes.size()>0) {
			for (MemberType memberType2 : memberTypes) {
				if (userFront.getUser_credit() >= memberType2.getCredit_start() && userFront.getUser_credit() <= memberType2.getCredit_end()) {
					userFront.setMember_type_id(memberType2.getMember_type_id());
				}
			}
		}
		memberDao.updateMember(userFront);
		httpSession.setAttribute("userFront", userFront);
		
		
		
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询留言等-首页
	 * @param sblog
	 * @return List<Sblog>
	 */
	public List<Sblog>  listSblogsIndex(Sblog sblog){
		
		List<Sblog> sblogs = sblogDao.listSblogs(sblog);
		
		return sblogs;
	}
	
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询留言
	 * @param sblog
	 * @return List<Sblog>
	 */
	public List<Sblog>  listSblogs(Sblog sblog,int[] sum){
		
		if (sum!=null) {
			sum[0] = sblogDao.listSblogsCount(sblog);
		}
		List<Sblog> sblogs = sblogDao.listSblogs(sblog);
		//查询回复内容
		if (sblogs!=null) {
			for (int i = 0; i < sblogs.size(); i++) {
				SblogReply sblogReply = new SblogReply();
				sblogReply.setSblog_id(sblogs.get(i).getSblog_id());
				sblogs.get(i).setReplys(sblogReplyDao.listSblogReplys(sblogReply));
			}
		}
	
		
		return sblogs;
	}
	
	/**
	 * @Title: addSblog
	 * @Description: 新增留言
	 * @param sblog
	 * @return void
	 */
	public void  addSblog(Sblog sblog){
		
		sblog.setSblog_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		//sblog.setSblog_flag(1);
		sblogDao.addSblog(sblog);
		
	}
	
	/**
	 * @Title: addSblogReply
	 * @Description: 新增留言回复
	 * @param sblogReply
	 * @return void
	 */
	public void  addSblogReply(SblogReply sblogReply){
		
		sblogReply.setReply_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		sblogReplyDao.addSblogReply(sblogReply);
		
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 网站公告列表查询
	 * @param info
	 * @return List<Info>
	 */
	public List<Info> listInfos(Info info, int[] sum) {
		
		if (sum != null) {
			sum[0] = infoDao.listInfosCount(info);
		}
		List<Info> infos = infoDao.listInfos(info);

		
		return infos;
	}

	/**
	 * @Title: queryInfo
	 * @Description: 网站公告查询
	 * @param info
	 * @return Info
	 */
	public Info queryInfo(Info info) {
		
		Info _info = infoDao.getInfo(new Info());
		
		return _info;
	}

	/**
	 * @Title: addInfo
	 * @Description: 新增公告信息
	 * @param info
	 * @return void
	 */
	public void addInfo(Info info) {
		if (!StringUtil.isEmptyString(info.getInfo_content())) {
			info.setInfo_content(Transcode.htmlEncode(info.getInfo_content()));
		}
		infoDao.addInfo(info);
		
	}

	/**
	 * @Title: updateInfo
	 * @Description: 更新公告信息
	 * @param info
	 * @return void
	 */
	public void updateInfo(Info info) {
		
		if (!StringUtil.isEmptyString(info.getInfo_content())) {
			info.setInfo_content(Transcode.htmlEncode(info.getInfo_content()));
		}
		infoDao.updateInfo(info);
		
	}
	
	/**
	 * @Title: delInfos
	 * @Description: 删除网站公告信息
	 * @param info
	 * @return void
	 */
	public void  delInfos(Info info){
		
		infoDao.delInfos(info.getIds().split(","));
		
	}
	
}
