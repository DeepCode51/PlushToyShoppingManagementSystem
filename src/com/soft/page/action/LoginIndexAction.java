package com.soft.page.action;

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

import com.soft.admin.domain.Cache;
import com.soft.admin.domain.Member;
import com.soft.admin.domain.OrdersDetail;
import com.soft.common.util.DateUtil;
import com.soft.common.util.JSONData;
import com.soft.page.manager.IndexManager;
import com.soft.page.manager.LoginIndexManager;

@Controller
public class LoginIndexAction {

	@Autowired
	LoginIndexManager loginIndexManager;
	@Autowired
	IndexManager indexManager;
	public IndexManager getIndexManager() {
		return indexManager;
	}
	public void setIndexManager(IndexManager indexManager) {
		this.indexManager = indexManager;
	}

	public LoginIndexManager getLoginIndexManager() {
		return loginIndexManager;
	}
	public void setLoginIndexManager(LoginIndexManager loginIndexManager) {
		this.loginIndexManager = loginIndexManager;
	}
	/**
	 * @Title: InSystem
	 * @Description: 用户登录
	 * @return String
	 */
	@RequestMapping(value="LoginInSystem.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData InSystem(Member params,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
		//	String random = (String)httpSession.getAttribute("random");
			//if (!random.equals(params.getRandom())) {
				//jsonData.setErrorReason("验证码错误");
			//	return jsonData;
			//}

			//用户登录查询
			Member admin = loginIndexManager.getUser(params);
			if (admin!=null) {
				httpSession.setAttribute("userFront", admin);
				
				httpSession.removeAttribute("card");
				Cache cache = new Cache();
				cache.setUser_id(admin.getUser_id());
				cache = loginIndexManager.queryCache(cache);
				if (cache!=null) {
					List<OrdersDetail> card = cache.getUserCard();
					httpSession.setAttribute("card", card);
				}
			}else{
				jsonData.setErrorReason("用户名或密码错误");
				return jsonData;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("登录异常，请稍后重试");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: OutSystem
	 * @Description: 退出登录
	 * @return String
	 */
	@RequestMapping(value="LoginOutSystem.action")
	@ResponseBody
	public JSONData OutSystem(HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//用户查询
			Member user = (Member)httpSession.getAttribute("userFront");
			if (user!=null) {
				//退出登录
				httpSession.removeAttribute("userFront");
				httpSession.invalidate();
			}
			
		} catch (Exception e) {
			jsonData.setErrorReason("退出异常，请稍后重试");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: RegSystem
	 * @Description: 用户注册
	 * @return String
	 */
	@RequestMapping(value="LoginRegSystem.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData RegSystem(Member params,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//验证码验证
			//String random = (String)httpSession.getAttribute("random");
			//if (!random.equals(params.getRandom())) {
				//jsonData.setErrorReason("验证码错误");
				//return jsonData;
			//}
			
			//查询用户名是否被占用
			Member user = new Member();
			user.setUser_name(params.getUser_name());
			Member user_temp = loginIndexManager.getUser(user);
			if (user_temp!=null) {
				jsonData.setErrorReason("注册失败，用户名已被注册："+params.getUser_name());
				return jsonData;
			}
			
			//添加用户入库
			params.setReg_date(DateUtil.getCurDateTime());
			loginIndexManager.addUser(params);
			
		} catch (Exception e) {
			jsonData.setErrorReason("注册异常，请稍后重试");
			return jsonData;
		}
		
		return jsonData;
	}
	
}
