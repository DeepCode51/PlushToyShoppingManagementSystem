package com.soft.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

/**
 * 字符串加密工具类
 * @author cc
 *
 */
public class Md5 {
	/**
	 * 密码加密
	 * @param password
	 * @return
	 */
	public static String makeMd5(String password){
		//***先 MD5 加密***
		//***再 BASE64 加密***
		MessageDigest md;
		String pwd="";
		boolean seccess = true;
		try {
			// 生成一个MD5加密计算摘要
			md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(password.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			//pwd = new BigInteger(1, md.digest()).toString(16);
			pwd = (new BASE64Encoder()).encodeBuffer(md.digest());
		} catch (Exception e) {
			seccess = false;
			e.printStackTrace();
		}
		//***最后 SHA 加密***
		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA");
			sha.update(pwd.getBytes());
			pwd = new BigInteger(1, sha.digest()).toString(16);
		} catch (Exception e) {
			seccess = false;
			e.printStackTrace();
		}
		if(seccess){
			return pwd;
		}
		return password;
	}
	
}
