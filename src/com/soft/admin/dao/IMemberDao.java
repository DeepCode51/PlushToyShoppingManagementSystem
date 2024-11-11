package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.Member;

public interface IMemberDao {

	public abstract int addMember(Member member);

	public abstract int delMember(String user_id);

	public abstract int delMembers(String[] user_ids);

	public abstract int updateMember(Member member);

	public abstract Member getMember(Member member);

	public abstract List<Member>  listMembers(Member member);

	public abstract int listMembersCount(Member member);

}
