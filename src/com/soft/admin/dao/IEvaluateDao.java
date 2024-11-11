package com.soft.admin.dao;

import java.util.List;

import com.soft.admin.domain.Evaluate;

public interface IEvaluateDao {

	public abstract int addEvaluate(Evaluate evaluate);

	public abstract int addEvaluateBatch(Evaluate evaluate);
	
	public abstract int delEvaluate(String evaluate_id);

	public abstract int delEvaluates(String[] evaluate_ids);

	public abstract int updateEvaluate(Evaluate evaluate);

	public abstract Evaluate getEvaluate(Evaluate evaluate);

	public abstract List<Evaluate>  listEvaluates(Evaluate evaluate);

	public abstract int listEvaluatesCount(Evaluate evaluate);

}
