package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.SblogReply;

public interface ISblogReplyDao {

	public abstract int addSblogReply(SblogReply sblogReply);

	public abstract int delSblogReply(String sblog_reply_id);

	public abstract int delSblogReplys(String[] sblog_reply_ids);

	public abstract int updateSblogReply(SblogReply sblogReply);

	public abstract SblogReply getSblogReply(SblogReply sblogReply);

	public abstract List<SblogReply>  listSblogReplys(SblogReply sblogReply);

	public abstract int listSblogReplysCount(SblogReply sblogReply);

}
