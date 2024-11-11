package com.soft.page.manager;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.admin.dao.ICacheDao;
import com.soft.admin.dao.IMemberDao;
import com.soft.admin.dao.IMemberTypeDao;
import com.soft.admin.domain.Cache;
import com.soft.admin.domain.Member;
import com.soft.admin.domain.MemberType;
import com.soft.admin.domain.OrdersDetail;
import com.soft.common.util.DateUtil;
import com.soft.common.util.StringUtil;
import com.soft.util.Md5;

@Service
public class LoginIndexManager {

	@Autowired
	IMemberDao userDao;
	@Autowired
	IMemberTypeDao memberTypeDao;
	@Autowired
	ICacheDao cacheDao;
	
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
	 * @Title: listUsers
	 * @Description: 查询用户集合
	 * @param user
	 * @return List<Picnews>
	 */
	public List<Member> listUsers(Member user){
		
		
		List<Member> users = userDao.listMembers(user);
		
		
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 查询用户
	 * @param user
	 * @return User
	 */
	public Member getUser(Member user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		Member _user = userDao.getMember(user);
		
		
		return _user;
	}
	
	/**
	 * @Title: addUser
	 * @Description: 用户注册
	 * @return void
	 */
	public void addUser(Member user) {
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		user.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		//设置用户等级
		MemberType memberType = new MemberType();
		memberType.setStart(-1);
		List<MemberType> memberTypes = memberTypeDao.listMemberTypes(memberType);
		if (memberTypes!=null && memberTypes.size()>0) {
			user.setMember_type_id(memberTypes.get(0).getMember_type_id());
		}
		
		userDao.addMember(user);
		
		
	}  

}
