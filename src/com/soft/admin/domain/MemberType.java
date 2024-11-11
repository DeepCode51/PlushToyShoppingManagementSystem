package com.soft.admin.domain;

import com.soft.common.domain.BaseDomain;

public class MemberType extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1770185824735782580L;
	private int member_type_id; // 
	private String member_type_name; // 
	private int credit_start; // 
	private int credit_end; // 
	private double member_discard; // 

	private String ids;

	public double getMember_discard2() {
		return Math.round(member_discard*100)/10.0;
	}

	public int getMember_type_id() {
		return member_type_id;
	}

	public void setMember_type_id(int member_type_id) {
		this.member_type_id = member_type_id;
	}

	public String getMember_type_name() {
		return member_type_name;
	}

	public void setMember_type_name(String member_type_name) {
		this.member_type_name = member_type_name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getCredit_start() {
		return credit_start;
	}

	public void setCredit_start(int credit_start) {
		this.credit_start = credit_start;
	}

	public int getCredit_end() {
		return credit_end;
	}

	public void setCredit_end(int credit_end) {
		this.credit_end = credit_end;
	}

	public double getMember_discard() {
		return member_discard;
	}

	public void setMember_discard(double member_discard) {
		this.member_discard = member_discard;
	}

}
