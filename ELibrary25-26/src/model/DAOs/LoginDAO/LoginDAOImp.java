package model.DAOs.LoginDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Connect;

public class LoginDAOImp implements DAOInterfaceLogin {

	Connect connect = new Connect();
    private final String URL = connect.getURL();
    private final String USER = connect.getUSER();
    private final String PASSWORD = connect.getPASSWORD();

	@Override
	public boolean checkCredentials(String username, String password) {

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(
						"select count(*) as AccountFound from ADMINLOGIN where username = ? AND userPassword = ?")) {

			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			rs.next();

			if (rs.getInt("AccountFound") == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String[] getLibrarianDetails(String username) {

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("select * from ADMINLOGIN where username = ?")) {

			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();

			rs.next();
			String[] details = { rs.getString("FullName"), rs.getString("emailAddress"), rs.getString("username"),
					rs.getString("userPassword") };

			return details;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateLibrarianDetails(String[] details) {

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement stmt = conn.prepareStatement("{CALL editAdminLogin(?,?,?,?,?,?,?,?)}")) {

	        stmt.setString(1, details[0]); 
	        stmt.setString(2, details[1]); 
	        stmt.setString(3, details[2]); 
	        stmt.setString(4, details[3]); 
	        stmt.setString(5, details[4]); 
	        stmt.setString(6, details[5]); 
	        stmt.setString(7, details[6]); 
	        stmt.setBoolean(8, Boolean.parseBoolean(details[7])); 

	        boolean hasResults = stmt.execute();

	        while (true) {
	            if (hasResults) {
	                ResultSet rs = stmt.getResultSet();

	                if (rs.next()) {
	                    int success = rs.getInt("Success");
	                    
	                    return success == 1;
	                }
	            } else {
	                int updateCount = stmt.getUpdateCount();
	                if (updateCount == -1) break;
	            }

	            hasResults = stmt.getMoreResults();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}

}
