package model.DAOs.LostAndFound;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LNFDAOImp {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    public List<DAOLNF> getAllItems(String searchQuery) {

        List<DAOLNF> ims = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		CallableStatement stmt = conn.prepareCall("{CALL searchLNF(?)}")) {
        	
        	if (searchQuery == null) {
				stmt.setString(1, "");
			} else {
				stmt.setString(1, searchQuery);
			}
        	
        	
            ResultSet rs = stmt.executeQuery();

            
            while (rs.next()) {
                ims.add(new DAOLNF(
                        rs.getString("ItemLost"),
                        rs.getString("NameOfOwner"),
                        rs.getString("ItemDescription"),
                        rs.getString("FloorLevel"),
                        rs.getString("Status"),
                        rs.getString("LastSeen")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ims;
    }
    
}
