package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.Uview;

public interface IUviewDao {

	public abstract int addUview(Uview uview);

	public abstract int delUview(String uview_id);

	public abstract int delUviews(String[] uview_ids);

	public abstract int updateUview(Uview uview);

	public abstract Uview getUview(Uview uview);

	public abstract List<Uview>  listUviews(Uview uview);

	public abstract int listUviewsCount(Uview uview);

}
