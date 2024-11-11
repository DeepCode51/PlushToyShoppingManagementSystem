package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.User;

public interface IUserDao {

	public abstract int addUser(User user);

	public abstract int delUser(String user_id);

	public abstract int delUsers(String[] user_ids);

	public abstract int updateUser(User user);

	public abstract User getUser(User user);

	public abstract List<User>  listUsers(User user);

	public abstract int listUsersCount(User user);

}
