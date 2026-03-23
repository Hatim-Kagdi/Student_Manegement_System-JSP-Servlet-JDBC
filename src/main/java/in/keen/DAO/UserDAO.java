package in.keen.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import in.keen.Connection.DBconnection;
import in.keen.Model.User;

public class UserDAO {
	
	//Register User
	public int registerUser(User user) {
		int generatedId = 0;
		
		try {
			String query = "INSERT INTO users(user_name, user_email, user_password, user_role) VALUES (?,?,?,?)";
			Connection con = DBconnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserEmail());
			ps.setString(3, user.getUserPassword());
			ps.setString(4, user.getUserRole());
			
			int registerUser = ps.executeUpdate();
			
			try(ResultSet rs = ps.getGeneratedKeys()){
				if(rs.next()) {
					generatedId = rs.getInt(1);
				}
			}
			con.close();
			ps.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return generatedId;
	}
	
	
	//Login User
	public User loginUser(String email, String password) {
		User user = null;
		
		try {
			Connection con = DBconnection.getConnection();
			
			String query = "SELECT * FROM users WHERE user_email= ?";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new User();
				String storedHash = rs.getString("user_password");
				
				if(BCrypt.checkpw(password, storedHash)) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserEmail(rs.getString("user_email"));
				//user.setUserPassword(rs.getString("user_password"));
				user.setUserRole(rs.getString("user_role"));
			}
			}
			con.close();
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	//Update User Profile Details (Name and Email only)
	public boolean updateUserFields(int userId, String fieldName, String newValue) {
		String columnName = "";
		
		if("name".equals(fieldName)) {
			columnName = "user_name";
		}else if("email".equals(fieldName)) {
			columnName = "user_email";
		}
	boolean status = false;
	String query = "UPDATE users SET "+columnName+" = ? WHERE user_id = ?";
	
	try(Connection con = DBconnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query)){
		ps.setString(1, newValue);
		ps.setInt(2, userId);
		
		if(ps.executeUpdate() > 0) {
			status = true;
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return status;
	}
	
	//Get Hashed Password
	
	public String getStoredHashedPassword(int userId) {
		String currentPW = "";
		String query = "SELECT user_password FROM users WHERE user_id = ?";
		try(Connection con = DBconnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				currentPW = rs.getString("user_password");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return currentPW;
	}
	
	//Update User Password
	public boolean updateUserPassword(int userId, String newPass) {
		boolean status = false;
		String query = "UPDATE users SET user_password = ? WHERE user_id = ?";
		try(Connection con = DBconnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, newPass);
			ps.setInt(2, userId);
			
			if(ps.executeUpdate() > 0) {
				status = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}