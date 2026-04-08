package model.DAOs.Reports;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Connect;
import model.Login;
import model.DAOs.Reports.DAOPatron;

public class ReportDAOImp {

	Connect connect = new Connect();
    private final String URL = connect.getURL();
    private final String USER = connect.getUSER();
    private final String PASSWORD = connect.getPASSWORD();

    public List<DAOFunctionHall> getFunctionHallReports(String facilityCode) {

        List<DAOFunctionHall> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement stmt = conn.prepareCall("{CALL viewReports_FunctionHall(?)}")) {

            stmt.setString(1, facilityCode);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                reports.add(new DAOFunctionHall(
                        rs.getString("FunctionHallCode"),
                        rs.getString("HallReservationNumber"),
                        rs.getString("LibrarianID"),
                        rs.getString("PatronID"),
                        rs.getString("EventName"),
                        rs.getString("DateOfEvent"),
                        rs.getString("StartTime"),
                        rs.getString("EndTime")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
    
    public List<DAOLoginFacility> getLoginFacilityReports(String facilityCode) {

        List<DAOLoginFacility> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement stmt = conn.prepareCall("{CALL viewReports_Facilities(?)}")) {

            stmt.setString(1, facilityCode);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
            	reports.add(new DAOLoginFacility(
                        rs.getString("FacilityCode"),
                        rs.getString("LogID"),
                        rs.getString("PatronTimeIn"),
                        rs.getString("PatronTimeOut"),
                        rs.getString("PatronID"),
                        rs.getString("CardNo")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    public List<DAOBookLoan> getBookLoanReports() {

        List<DAOBookLoan> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select * from viewReports_BookLoan order by TransactionID asc")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
            	reports.add(new DAOBookLoan(
            			rs.getString("TransactionID"),
                        rs.getString("CallNumber"),
                        rs.getString("BorrowDate"),
                        rs.getString("DueDate"),
                        rs.getString("PatronID"),
                        rs.getString("CirculationCode")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
    
    public List<DAOReturnBook> getReturnBookReports() {

        List<DAOReturnBook> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select * from viewReports_ReturnBook order by TransactionID asc")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
            	reports.add(new DAOReturnBook(
            			rs.getString("TransactionID"),
                        rs.getString("CallNumber"),
                        rs.getString("ReturnDate")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
    
    public List<DAOIMS> getIMSReports() {

        List<DAOIMS> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select * from viewReports_IMS")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
            	reports.add(new DAOIMS(
            			rs.getString("SerialNumber"),
                        rs.getString("EquipmentName"),
                        rs.getString("ItemType")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
    
    public List<DAOEquipmentLoan> getEquipmentLoanReports() {

        List<DAOEquipmentLoan> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select * from viewReports_EquipmentLoan")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
            	reports.add(new DAOEquipmentLoan(
            			 rs.getString("LoanID"),
                         rs.getString("FacilityCode"),
                         rs.getString("SerialNumber"),
                         rs.getString("PatronID"),
                         rs.getString("Venue"),
                         rs.getString("BorrowDate"),
                         rs.getString("LoanStatus")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
    
    public List<DAOPatron> getPatronReports() {

        List<DAOPatron> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select * from PATRON where IsArchived = 0")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
            	reports.add(new DAOPatron(
            			rs.getString("PatronID"), 
            			rs.getString("LastName"),
            			rs.getString("FirstName"),
            			rs.getString("MiddleInitial"),
						rs.getString("EmailAddress"), 
						rs.getString("ContactNumber"), 
						rs.getString("HomeAddress"),
						rs.getString("CampCode"), 
						rs.getString("PatronType")
						));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
    
    public List<DAOBook> getBookReports() {

        List<DAOBook> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select * from book where IsArchived = 0")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
            	reports.add(new DAOBook(
            			rs.getString("CallNumber"), 
            			rs.getString("Title"),
            			rs.getString("Author"),
            			rs.getString("PublicationYear"),
						rs.getString("BookType"), 
						rs.getString("CollectionCode"), 
						rs.getString("ClassificationCode")
						));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
    
    public List<DAOBorrowedBooks> getBorrowedBookReports() {

        List<DAOBorrowedBooks> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select * from viewReports_BorrowedBooks")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
            	reports.add(new DAOBorrowedBooks(
            			rs.getString("TransactionID"),
                        rs.getString("CallNumber"),
                        rs.getString("BorrowDate"),
                        rs.getString("DueDate"),
                        rs.getString("PatronID"),
                        rs.getString("CirculationCode")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
    
    public List<DAOOverdueBooks> getOverdueBookReports() {

        List<DAOOverdueBooks> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select * from viewReports_Overduebooks")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
            	reports.add(new DAOOverdueBooks(
            			rs.getString("TransactionID"),
                        rs.getString("CallNumber"),
                        rs.getString("BorrowDate"),
                        rs.getString("DueDate"),
                        rs.getString("PatronID"),
                        rs.getString("CirculationCode")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
    
    
    
}