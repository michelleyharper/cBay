package dao;

import java.util.List;

import exception.ApplicationException;
import pojo.UserPojo;

public interface UserDao {
	
	UserPojo register(UserPojo userPojo) throws ApplicationException;
	UserPojo validateUser(UserPojo userPojo) throws ApplicationException;
	boolean deleteUser(int userId) throws ApplicationException;
	List<UserPojo> getAllUsers() throws ApplicationException;
	UserPojo getAUser(int userId) throws ApplicationException;
	void exitApplication();
	
}