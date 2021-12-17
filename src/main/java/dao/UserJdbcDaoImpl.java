package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.ApplicationException;
import pojo.UserPojo;

public class UserJdbcDaoImpl implements UserDao {

	private static final Logger logger = LogManager.getLogger(UserJdbcDaoImpl.class);
	
	// CREATE AN ACCOUNT
	public UserPojo register(UserPojo userPojo) throws ApplicationException {
		logger.info("Entered register() in dao.");

		Connection conn = DBUtil.makeConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "insert into user_details(user_password, user_first_name, user_last_name, user_type, user_removed)"
					+ "values('" + userPojo.getUserPassword() + "','" + userPojo.getUserFirstName() + "','"
					+ userPojo.getUserLastName() + "','" + userPojo.getUserType() + "'," + userPojo.isUserRemoved()
					+ ") returning user_id";

			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			userPojo.setUserId(rs.getInt(1));
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		logger.info("Exited register() in dao.");
		return userPojo;
	}
	// USER LOGIN
	public UserPojo validateUser(UserPojo userPojo) throws ApplicationException {
		logger.info("Entered validateUser() in dao.");

		Connection conn = DBUtil.makeConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from user_details where user_id=" + userPojo.getUserId() + " and user_password='"
					+ userPojo.getUserPassword() + "' and user_removed=false";

			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				userPojo.setUserFirstName(rs.getString(3));
				userPojo.setUserLastName(rs.getString(4));
				userPojo.setUserType(rs.getString(5));
				userPojo.setUserRemoved(rs.getBoolean(6));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		logger.info("Exited validateUser() in dao.");
		return userPojo;
	}
	
	// DELETE USER ACCOUNT
	public boolean deleteUser(int userId) throws ApplicationException {
		logger.info("Entered deleteUser() in dao.");
		
		boolean flag = true;
		Connection conn = DBUtil.makeConnection();
		int rowsAffected = 0;
		try {
			Statement stmt = conn.createStatement();
			// here we are not going to do a hard delete, we are going 
			// for a soft delete.
			String query = "update user_details set user_removed=true where user_id="+userId;
			rowsAffected = stmt.executeUpdate(query);
			System.out.println(rowsAffected);
			
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		if(rowsAffected == 0)
			flag = false;
		
		logger.info("Exited deleteUser() in dao.");
		return flag;
	}
	// LIST ALL USERS
	public List<UserPojo> getAllUsers() throws ApplicationException {
		logger.info("Entered getAllUsers() in dao.");
		
		List<UserPojo> allUsers = new ArrayList<UserPojo>();
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from user_details where user_removed=false";
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				UserPojo userPojo = new UserPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getBoolean(6));
				
				allUsers.add(userPojo);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getAllUsers() in dao.");
		return allUsers;
	}
	
	// GET A USER
	public UserPojo getAUser(int userId) throws ApplicationException {
		logger.info("Entered getAUser() in dao.");
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		UserPojo userPojo = null;
		try {
			stmt = conn.createStatement();
			String query = "select * from user_details where user_id="+userId
							+ "and user_removed=false";
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				userPojo = new UserPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getBoolean(6));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getAUser() in dao.");
		return userPojo;
	}

	public void exitApplication() {
		logger.info("Entered exitApplication() in dao.");
		DBUtil.closeConnection();

	}
}