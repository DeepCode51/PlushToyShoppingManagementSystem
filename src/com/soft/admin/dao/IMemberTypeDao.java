package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.MemberType;

public interface IMemberTypeDao {

	public abstract int addMemberType(MemberType memberType);

	public abstract int delMemberType(String member_type_id);

	public abstract int delMemberTypes(String[] member_type_ids);

	public abstract int updateMemberType(MemberType memberType);

	public abstract MemberType getMemberType(MemberType memberType);

	public abstract List<MemberType>  listMemberTypes(MemberType memberType);

	public abstract int listMemberTypesCount(MemberType memberType);

}
