package com.soft.admin.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.admin.dao.IGoodsDao;
import com.soft.admin.dao.IGoodsTypeDao;
import com.soft.admin.dao.IInfoDao;
import com.soft.admin.dao.IMemberDao;
import com.soft.admin.dao.IMemberTypeDao;
import com.soft.admin.dao.IOrdersDao;
import com.soft.admin.dao.IOrdersDetailDao;
import com.soft.admin.dao.ISblogDao;
import com.soft.admin.dao.ISblogReplyDao;
import com.soft.admin.dao.IUserDao;
import com.soft.admin.domain.Goods;
import com.soft.admin.domain.GoodsType;
import com.soft.admin.domain.Info;
import com.soft.admin.domain.Member;
import com.soft.admin.domain.MemberType;
import com.soft.admin.domain.Orders;
import com.soft.admin.domain.OrdersDetail;
import com.soft.admin.domain.Sblog;
import com.soft.admin.domain.SblogReply;
import com.soft.admin.domain.User;
import com.soft.common.util.DateUtil;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;
import com.soft.util.Md5;

@Service
public class AdminManager {

	@Autowired
	IUserDao userDao;
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
	IInfoDao infoDao;
	
	/**
	 * @Title: getManager
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getManager(User user){
		
		User _user = userDao.getUser(user);
		
		return _user;
	}
	 
	/**
	 * @Title: updateManager
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateManager(User user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user);
		
	}
	
	/**
	 * @Title: listMemberTypes
	 * @Description: 用户类型查询
	 * @param memberType
	 * @return List<MemberType>
	 */
	public List<MemberType> listMemberTypes(MemberType memberType, int[] sum) {
		
		if (sum != null) {
			sum[0] = memberTypeDao.listMemberTypesCount(memberType);
		}
		List<MemberType> memberTypes = memberTypeDao.listMemberTypes(memberType);

		
		return memberTypes;
	}

	/**
	 * @Title: queryMemberType
	 * @Description: 用户类型查询
	 * @param memberType
	 * @return MemberType
	 */
	public MemberType queryMemberType(MemberType memberType) {
		
		MemberType _memberType = memberTypeDao.getMemberType(memberType);
		
		return _memberType;
	}

	/**
	 * @Title: addMemberType
	 * @Description: 添加用户类型
	 * @param memberType
	 * @return void
	 */
	public void addMemberType(MemberType memberType) {
		
		memberTypeDao.addMemberType(memberType);
		
	}

	/**
	 * @Title: updateMemberType
	 * @Description: 更新用户类型信息
	 * @param memberType
	 * @return void
	 */
	public void updateMemberType(MemberType memberType) {
		
		memberTypeDao.updateMemberType(memberType);
		
	}

	/**
	 * @Title: delMemberType
	 * @Description: 删除用户类型信息
	 * @param memberType
	 * @return void
	 */
	public void delMemberTypes(MemberType memberType) {
		
		memberTypeDao.delMemberTypes(memberType.getIds().split(","));
		
	}
	
	
	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<Member>  listUsers(Member user,int[] sum){
		
		if (sum!=null) {
			sum[0] = memberDao.listMembersCount(user);
		}
		List<Member> users = memberDao.listMembers(user);
		
		
		return users;
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
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(Member user){
		
		user.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		//设置用户等级
		MemberType memberType = new MemberType();
		memberType.setStart(-1);
		List<MemberType> memberTypes = memberTypeDao.listMemberTypes(memberType);
		if (memberTypes!=null && memberTypes.size()>0) {
			user.setMember_type_id(memberTypes.get(0).getMember_type_id());
		}
		memberDao.addMember(user);
		
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
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(Member user){
		
		memberDao.delMembers(user.getIds().split(","));
		
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
	 * @Title: queryGoodsType
	 * @Description: 商品类型查询
	 * @param goodsType
	 * @return GoodsType
	 */
	public GoodsType queryGoodsType(GoodsType goodsType) {
		
		GoodsType _goodsType = goodsTypeDao.getGoodsType(goodsType);
		
		return _goodsType;
	}

	/**
	 * @Title: addGoodsType
	 * @Description: 添加商品类型
	 * @param goodsType
	 * @return void
	 */
	public void addGoodsType(GoodsType goodsType) {
		
		goodsTypeDao.addGoodsType(goodsType);
		
	}

	/**
	 * @Title: updateGoodsType
	 * @Description: 更新商品类型信息
	 * @param goodsType
	 * @return void
	 */
	public void updateGoodsType(GoodsType goodsType) {
		
		goodsTypeDao.updateGoodsType(goodsType);
		
	}

	/**
	 * @Title: delGoodsType
	 * @Description: 删除商品类型信息
	 * @param goodsType
	 * @return void
	 */
	public void delGoodsTypes(GoodsType goodsType) {
		
		goodsTypeDao.delGoodsTypes(goodsType.getIds().split(","));
		
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 商品查询
	 * @param goods
	 * @return List<Goods>
	 */
	public List<Goods> listGoodss(Goods goods, int[] sum) {
		
		if (sum != null) {
			sum[0] = goodsDao.listGoodssCount(goods);
		}
		List<Goods> goodss = goodsDao.listGoodss(goods);

		
		return goodss;
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
	 * @Title: addGoods
	 * @Description: 添加商品
	 * @param goods
	 * @return void
	 */
	public void addGoods(Goods goods) {
		
		if (!StringUtil.isEmptyString(goods.getGoods_desc())) {
			goods.setGoods_desc(Transcode.htmlEncode(goods.getGoods_desc()));
		}
		goods.setGoods_date(DateUtil.dateToDateString(new Date()));
		goodsDao.addGoods(goods);
		
	}

	/**
	 * @Title: updateGoods
	 * @Description: 更新商品信息
	 * @param goods
	 * @return void
	 */
	public void updateGoods(Goods goods) {
		
		if (!StringUtil.isEmptyString(goods.getGoods_desc())) {
			goods.setGoods_desc(Transcode.htmlEncode(goods.getGoods_desc()));
		}
		goodsDao.updateGoods(goods);
		
	}

	/**
	 * @Title: delGoods
	 * @Description: 删除商品信息
	 * @param goods
	 * @return void
	 */
	public void delGoodss(Goods goods) {
		
		goodsDao.delGoodss(goods.getIds().split(","));
		
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 留言板查询
	 * @param sblog
	 * @return List<Sblog>
	 */
	public List<Sblog>  listSblogs(Sblog sblog,int[] sum){
		
		if (sum!=null) {
			sum[0] = sblogDao.listSblogsCount(sblog);
		}
		List<Sblog> sblogs = sblogDao.listSblogs(sblog);
		
		//查询回复信息
		if (sblogs!=null && sblogs.size()>0) {
			for (Sblog sblog2 : sblogs) {
				SblogReply sblogReply = new SblogReply();
				sblogReply.setSblog_id(sblog2.getSblog_id());
				List<SblogReply> sblogReplys = sblogReplyDao.listSblogReplys(sblogReply);
				sblog2.setReplys(sblogReplys);
			}
		}
		
		
		return sblogs;
	}
	
	/**
	 * @Title: querySblog
	 * @Description: 留言信息查询
	 * @param sblog
	 * @return Sblog
	 */
	public Sblog querySblog(Sblog sblog) {
		
		Sblog _sblog = sblogDao.getSblog(sblog);
		
		//查询回复信息
		SblogReply sblogReply = new SblogReply();
		sblogReply.setSblog_id(_sblog.getSblog_id());
		List<SblogReply> sblogReplys = sblogReplyDao.listSblogReplys(sblogReply);
		_sblog.setReplys(sblogReplys);
		
		
		return _sblog;
	}

	/**
	 * @Title: delSblogs
	 * @Description: 删除留言板
	 * @param sblog
	 * @return void
	 */
	public void  delSblogs(Sblog sblog){
		
		sblogDao.delSblogs(sblog.getIds().split(","));
		
	}
	
	/**
	 * @Title: assessSblog
	 * @Description: 审核留言板
	 * @param sblog
	 * @return void
	 */
	public void  assessSblog(Sblog sblog){
		//sblog.setSblog_flag(2);
		sblogDao.updateSblog(sblog);
		
	}
	
	/**
	 * @Title: addSblogReply
	 * @Description: 新增留言回复
	 * @param SblogReply
	 * @return void
	 */
	public void  addSblogReply(SblogReply sblogReply){
		
		String reply_content = sblogReply.getReply_content();
		//内容编码
		sblogReply.setReply_content(Transcode.htmlEncode(reply_content));
		//回复时间
		sblogReply.setReply_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		sblogReplyDao.addSblogReply(sblogReply);
		
		
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
	 * @Title: listOrderssSum
	 * @Description: 商品销售统计
	 * @param orders
	 * @return List<Orders>
	 */
	public List<Orders>  listOrderssSum(Orders orders,int[] sum){
		
		if (sum!=null) {
			sum[0] = ordersDao.listOrderssSumCount(orders);
		}
		List<Orders> orderss = ordersDao.listOrderssSum(orders);
		
		
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
	 * @Title: delOrderss
	 * @Description: 删除商品订单信息
	 * @param orders
	 * @return void
	 */
	public void  delOrderss(Orders orders){
		
		ordersDao.delOrderss(orders.getIds().split(","));
		
	}
	
	/**
	 * @Title: sendOrders
	 * @Description: 订单发货
	 * @param orders
	 * @return void
	 */
	public void sendOrders(Orders orders) {
		
		//确认订单信息
		orders.setOrders_flag(2);//2-已发货 
		ordersDao.updateOrders(orders);
		
		
	}
	
	/**
	 * @Title: listOrdersDetails
	 * @Description: 商品订单明细查询
	 * @param ordersDetail
	 * @return List<OrdersDetail>
	 */
	public List<OrdersDetail> listOrdersDetails(OrdersDetail ordersDetail, int[] sum) {
		
		if (sum != null) {
			sum[0] = ordersDetailDao.listOrdersDetailsCount(ordersDetail);
		}
		List<OrdersDetail> ordersDetails = ordersDetailDao.listOrdersDetails(ordersDetail);

		
		return ordersDetails;
	}
	
	/**
	 * @Title: listOrdersDetailsSum
	 * @Description: 商品销售统计
	 * @param ordersDetail
	 * @return List<OrdersDetail>
	 */
	public List<OrdersDetail> listOrdersDetailsSum(OrdersDetail ordersDetail, int[] sum) {
		
		if (sum != null) {
			sum[0] = ordersDetailDao.listOrdersDetailsSumCount(ordersDetail);
		}
		List<OrdersDetail> ordersDetails = ordersDetailDao.listOrdersDetailsSum(ordersDetail);

		
		return ordersDetails;
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
