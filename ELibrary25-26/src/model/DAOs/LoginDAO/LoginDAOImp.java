package model.DAOs.LoginDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImp implements DAOInterfaceLogin {

	private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
	private final String USER = "Pia";
	private final String PASSWORD = "passwordPia";

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

	        stmt.setString(1, details[0]); // current username
	        stmt.setString(2, details[1]); // new username
	        stmt.setString(3, details[2]); // full name
	        stmt.setString(4, details[3]); // email
	        stmt.setString(5, details[4]); // old password
	        stmt.setString(6, details[5]); // new password
	        stmt.setString(7, details[6]); // confirm password
	        stmt.setBoolean(8, Boolean.parseBoolean(details[7])); // updatePassword flag

	        boolean hasResults = stmt.execute();

	        while (true) {
	            if (hasResults) {
	                ResultSet rs = stmt.getResultSet();

	                if (rs.next()) {
	                    int success = rs.getInt("Success");
	                    // You can show this in UI instead
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
