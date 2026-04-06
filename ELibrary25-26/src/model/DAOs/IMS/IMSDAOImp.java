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
    
    public String[] getEquipmentInfo(String serialNumber) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		PreparedStatement stmt = conn.prepareStatement("select * from INSTRUCTIONAL_MEDIA_SERVICES where SerialNumber = ? and IsArchived = 0")) {
        	
        	stmt.setString(1, serialNumber);
        	
            ResultSet rs = stmt.executeQuery();

            rs.next();
            
            String[] equipmentInfo = {
            		rs.getString("EquipmentName"),
            		rs.getString("ItemType")
            };
            
            return equipmentInfo;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean updateEquipmentInfo(String[] equipmentInfo) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		CallableStatement stmt = conn.prepareCall("{CALL updateIMS(?,?,?,?)}")) {
        	
        	stmt.setString(1, equipmentInfo[0]);
        	stmt.setString(2, equipmentInfo[1]);
        	stmt.setString(3, equipmentInfo[2]);
        	stmt.setBoolean(4, false);
        	
            stmt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
    public boolean checkSerialNumber(String serialNumber) {
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM INSTRUCTIONAL_MEDIA_SERVICES WHERE SerialNumber = ? and isArchived = 0")) {

			pstmt.setString(1, serialNumber);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0; // If count > 0, record exists
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
    }
    
    public boolean addNewEquipment(String[] equipmentDetails) {
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		PreparedStatement stmt = conn.prepareStatement("insert into INSTRUCTIONAL_MEDIA_SERVICES VALUES (?,?,?,?,?)")) {
        	
        	stmt.setString(1, "IMS");
        	stmt.setString(2, equipmentDetails[0]);
        	stmt.setString(3, equipmentDetails[1]);
        	stmt.setString(4, equipmentDetails[2]);
        	stmt.setBoolean(5, false);
        	
            stmt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
    
    
    
}
