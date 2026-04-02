package model.DAOs.FacilityLogin;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FacilityLoginDAOImp implements DAOInterfaceFacilityLogin {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    @Override
    public List<DAOFacilityLogin> getLoggedIn(String facilityCode) {

        List<DAOFacilityLogin> cards = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); CallableStatement stmt = conn.prepareCall("{CALL showLoggedIn_FacilityLogin(?)}")) {

        	stmt.setString(1, facilityCode);
        	
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cards.add(new DAOFacilityLogin(
                        rs.getString("FacilityCode"),
                        rs.getString("PatronTimeIn"),
                        rs.getString("PatronTimeOut"),
                        rs.getString("PatronID"),
                        rs.getInt("CardNo")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }
    
    public String[] timeInTimeOutPatron(String patronID, String facilityCode, String cardNo) {

        patronID = patronID.trim();     // ✅ important
        facilityCode = facilityCode.trim();

        boolean withCard = !(facilityCode.equals("PWD") 
                          || facilityCode.equals("LSect") 
                          || facilityCode.equals("LOGIN"));

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt =
                 conn.prepareStatement("EXEC checkPatronTimeIn_Out ?, ?, ?")) {

            stmt.setString(1, facilityCode);
            stmt.setString(2, patronID);

            if (withCard) {
                stmt.setString(3, cardNo);
            } else {
                stmt.setNull(3, java.sql.Types.VARCHAR);
            }

            // ✅ Correct method for INSERT/UPDATE
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return getPatronData(patronID, facilityCode, cardNo);
    }

    
    public String[] getPatronData(String patronID, String facilityCode, String cardNo) {
    	
    	DateTimeFormatter timeFormatter =
    	        DateTimeFormatter.ofPattern("hh:mm:ss a");
    	
    	
    	List<String> list = new ArrayList<>();
    	
    	if (cardNo != null) {
    		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement stmt = conn.prepareStatement("exec getPatronLoginInfo ?, ?, ?")) {
	
	    		stmt.setString(1, patronID);
				stmt.setString(2, facilityCode);
				stmt.setString(3, cardNo);
	
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					list.add(rs.getString("PatronID"));
					list.add(rs.getString("Full Name"));
					list.add(rs.getString("CampColl"));
					list.add(rs.getString("PatronType"));
					Timestamp timeIn = rs.getTimestamp("PatronTimeIn");
    				list.add(timeIn == null
    				        ? "--"
    				        : timeIn.toLocalDateTime().format(timeFormatter));
    				Timestamp timeOut = rs.getTimestamp("PatronTimeOut");
    				list.add(timeOut == null
    				        ? "--"
    				        : timeOut.toLocalDateTime().format(timeFormatter));
				}
	
			} catch (SQLException e) {
				return null;
			}
    	} else {
    		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
    				PreparedStatement stmt = conn.prepareStatement("exec getPatronLoginInfoNoCard ?, ?")) {

        		stmt.setString(1, patronID);
    			stmt.setString(2, facilityCode);

    			ResultSet rs = stmt.executeQuery();
    			while (rs.next()) {
    				list.add(rs.getString("PatronID"));
    				list.add(rs.getString("Full Name"));
    				list.add(rs.getString("CampColl"));
    				list.add(rs.getString("PatronType"));
    				Timestamp timeIn = rs.getTimestamp("PatronTimeIn");
    				list.add(timeIn == null
    				        ? "--"
    				        : timeIn.toLocalDateTime().format(timeFormatter));
    				Timestamp timeOut = rs.getTimestamp("PatronTimeOut");
    				list.add(timeOut == null
    				        ? "--"
    				        : timeOut.toLocalDateTime().format(timeFormatter));
    			}

    		} catch (SQLException e) {
    			return null;
    		}
    	}
    	
    	

		return list.toArray(new String[0]);
    }
}
