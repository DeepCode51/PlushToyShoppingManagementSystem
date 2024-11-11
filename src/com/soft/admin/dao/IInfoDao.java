package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.Info;

public interface IInfoDao {

	public abstract int addInfo(Info info);

	public abstract int delInfo(String info_id);

	public abstract int delInfos(String[] info_ids);

	public abstract int updateInfo(Info info);

	public abstract Info getInfo(Info info);

	public abstract List<Info>  listInfos(Info info);

	public abstract int listInfosCount(Info info);

}
