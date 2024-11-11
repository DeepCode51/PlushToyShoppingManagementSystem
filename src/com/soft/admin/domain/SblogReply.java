package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;
import com.soft.common.util.StringUtil;
import com.soft.common.util.Transcode;

public class SblogReply extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5974537055144250656L;
	private int sblog_reply_id; // 
	private int sblog_id; // 
	private int user_id; // 
	private String reply_content; // 
	private String reply_date; // 

	private String user_name; // 
	private String user_photo; // 
	private String ids;
	private String random;
	
	public String getReply_contentShow(){
		if (!StringUtil.isEmptyString(reply_content)) {
			return Transcode.htmlDiscode(reply_content);
		}
		return reply_content;
	}

	public void setSblog_reply_id(int sblog_reply_id){
		this.sblog_reply_id=sblog_reply_id;
	}

	public int getSblog_reply_id(){
		return sblog_reply_id;
	}

	public void setSblog_id(int sblog_id){
		this.sblog_id=sblog_id;
	}

	public int getSblog_id(){
		return sblog_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setReply_content(String reply_content){
		this.reply_content=reply_content;
	}

	public String getReply_content(){
		return reply_content;
	}

	public void setReply_date(String reply_date){
		this.reply_date=reply_date;
	}

	public String getReply_date(){
		return reply_date;
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

	public String getUser_photo() {
		return user_photo;
	}

	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
