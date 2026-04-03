package model.DAOs.IMS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class IMSDAOImp {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    public List<DAOIMS> getAllItems(String searchQuery) {

        List<DAOIMS> ims = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		CallableStatement stmt = conn.prepareCall("{CALL searchIMS(?)}")) {
        	
        	if (searchQuery == null) {
				stmt.setString(1, "");
			} else {
				stmt.setString(1, searchQuery);
			}
        	
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ims.add(new DAOIMS(
                        rs.getString("FacilityCode"),
                        rs.getString("SerialNumber"),
                        rs.getString("EquipmentName")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ims;
    }
    
}
