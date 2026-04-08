package model.DAOs.LostAndFound;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Connect;


public class LNFDAOImp {

	Connect connect = new Connect();
    private final String URL = connect.getURL();
    private final String USER = connect.getUSER();
    private final String PASSWORD = connect.getPASSWORD();

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
                        rs.getString("LastSeen"),
                        rs.getString("Lost_ItemNum")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ims;
    }
    
    public boolean markAsClaimed(String patronID, String itemNum) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		PreparedStatement stmt = conn.prepareStatement("UPDATE LOST_AND_FOUND SET ClaimedByPatronID = ?, Status = 'Found' WHERE Lost_ItemNum = ?")) {
        	
        	stmt.setString(1, patronID);
        	stmt.setString(2, itemNum);
        	
        	
            stmt.executeUpdate();

            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean addReport(String[] reportDetails) {
    	







        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		CallableStatement stmt = conn.prepareCall("{CALL addNewRecord_LostAndFound(?,?,?,?,?,?)}")) {
        	
        	stmt.setString(1, reportDetails[0]);
        	stmt.setString(2, reportDetails[1]);
        	stmt.setString(3, reportDetails[2]);
        	stmt.setString(4, reportDetails[3]);
        	stmt.setString(5, reportDetails[4]);
        	stmt.setString(6, reportDetails[5]);
        	
        	
            stmt.executeUpdate();

            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
