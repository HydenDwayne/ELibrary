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

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("select count(*) as AccountFound from ADMINLOGIN where username = ? AND userPassword = ?")) {
            

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
    
}
