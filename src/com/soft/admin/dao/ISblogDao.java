package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.Sblog;

public interface ISblogDao {

	public abstract int addSblog(Sblog sblog);

	public abstract int delSblog(String sblog_id);

	public abstract int delSblogs(String[] sblog_ids);

	public abstract int updateSblog(Sblog sblog);

	public abstract Sblog getSblog(Sblog sblog);

	public abstract List<Sblog>  listSblogs(Sblog sblog);

	public abstract int listSblogsCount(Sblog sblog);

}
