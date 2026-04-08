package model.DAOs.ActiveRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Connect;

public class ActiveRequestDAOImp {

	Connect connect = new Connect();
    private final String URL = connect.getURL();
    private final String USER = connect.getUSER();
    private final String PASSWORD = connect.getPASSWORD();

    public List<DAOActiveRequest> getAllRequests() {

        List<DAOActiveRequest> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		PreparedStatement stmt = conn.prepareStatement("SELECT a.*, e.LoanStatus FROM getActiveRequest as a, EQUIPMENT_LOAN as e WHERE a.LoanID = e.LoanID AND e.LoanStatus != 'Returned'")) {

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
        		CallableStatement stmt = conn.prepareCall("{CALL setStatus(?,?)}")) {
        	
        	stmt.setString(1, loanID);
        	stmt.setString(2, status);
        	
            stmt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
    
    public boolean addRequest(String[] equipmentDetails) {
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		CallableStatement stmt = conn.prepareCall("{CALL addNewRecord_EquipmentLoan(?,?,?,?,?,?)}")) {
        	
    		System.out.println(getLoanID()); 
    		
    		stmt.setString(1, getLoanID());
        	stmt.setString(2, equipmentDetails[0]);
        	stmt.setString(3, equipmentDetails[1]);
        	stmt.setString(4, equipmentDetails[2]);
        	stmt.setDate(5, java.sql.Date.valueOf(equipmentDetails[3].trim()));
        	stmt.setString(6, "Pending");
        	
            stmt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean checkEquipmentExists(String serialNumber) {
		String sql = "SELECT COUNT(*) FROM INSTRUCTIONAL_MEDIA_SERVICES WHERE SerialNumber = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, serialNumber);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0; 
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
    
    public boolean checkIfAvailable(String serialNumber) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement stmt = conn.prepareCall("{CALL checkIfAvailable(?)}")) {

			stmt.setString(1, serialNumber);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0; 
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
    
    public String getLoanID() {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn
						.prepareStatement("select top 1 LoanID from EQUIPMENT_LOAN order by LoanID desc")) {

			ResultSet rs = stmt.executeQuery();

			String loanID;
			if (rs.next()) {
				loanID = rs.getString("LoanID");
			} else {
				loanID = "LN00000000"; 
			}

			
			int splitIndex = 0;
			
			for (int i = 0; i < loanID.length(); i++) {
				if (Character.isDigit(loanID.charAt(i))) {
					splitIndex = i;
					break;
				}
			}
			String prefix = loanID.substring(0, splitIndex);
			String numberPart = loanID.substring(splitIndex);

			
			long number = Long.parseLong(numberPart);
			number += 1;

			
			String newNumberPart = String.format("%0" + numberPart.length() + "d", number);

			return prefix + newNumberPart;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
