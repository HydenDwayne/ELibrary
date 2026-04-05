package model.DAOs.ActiveRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActiveRequestDAOImp {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    public List<DAOActiveRequest> getAllRequests() {

        List<DAOActiveRequest> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		PreparedStatement stmt = conn.prepareStatement("SELECT a.*, e.LoanStatus FROM getActiveRequest as a, EQUIPMENT_LOAN as e WHERE a.LoanID = e.LoanID")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(new DAOActiveRequest(
                        rs.getString("LoanID"),
                        rs.getString("SerialNumber"),
                        rs.getString("PatronName"),
                        rs.getString("Venue"),
                        rs.getString("BorrowDate"),
                        rs.getString("EquipmentName"),
                        rs.getString("LoanStatus")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
    
    public String[] getEquipmentLoanDetails(String loanID) {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		PreparedStatement stmt = conn.prepareStatement("select * from EQUIPMENT_LOAN  where LoanID = ?")) {

    		stmt.setString(1, loanID);
    		
            ResultSet rs = stmt.executeQuery();

            rs.next();
            
            String[] loanDetails = {
            		rs.getString("LoanID"),
                    rs.getString("SerialNumber"),
                    rs.getString("PatronID"),
                    rs.getString("Venue"),
                    rs.getString("BorrowDate"),
                    rs.getString("LoanStatus")
            };
            
            return loanDetails;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	return null;
    }
    
    public boolean updateStatus(String loanID, String status) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		PreparedStatement stmt = conn.prepareStatement("update EQUIPMENT_LOAN set LoanStatus = ? where LoanID = ?")) {
        	
        	stmt.setString(1, status);
        	stmt.setString(2, loanID);
        	
            stmt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean addRequest(String[] equipmentDetails) {
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		CallableStatement stmt = conn.prepareCall("{CALL addNewRecord_EquipmentLoan(?,?,?,?,?)}")) {
        	
        	stmt.setString(1, equipmentDetails[0]);
        	stmt.setString(2, equipmentDetails[1]);
        	stmt.setString(3, equipmentDetails[2]);
        	stmt.setString(4, equipmentDetails[3]);
        	stmt.setString(5, "Pending");
        	
            stmt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
