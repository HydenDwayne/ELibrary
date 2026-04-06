package model.DAOs.Archived;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import model.DAOs.ActiveRequest.DAOActiveRequest;
import model.DAOs.Reports.DAOBook;
import model.DAOs.Reports.DAOBookLoan;
import model.DAOs.Reports.DAOEquipmentLoan;
import model.DAOs.Reports.DAOFunctionHall;
import model.DAOs.Reports.DAOIMS;
import model.DAOs.Reports.DAOLoginFacility;
import model.DAOs.Reports.DAOPatron;
import model.DAOs.Reports.DAOReturnBook;

public class ArchivedDAOImp {
	private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    public List<DAOArchivedBook> getBooksArchives() {

        List<DAOArchivedBook> archives = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		PreparedStatement stmt = conn.prepareStatement("SELECT * from Book WHERE isArchived = 1")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Map the correct columns from the stored procedure to the DAO
            	archives.add(new DAOArchivedBook(
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

        return archives;
    }
    
    public List<DAOArchivedBookLoan> getBookLoanArchives() {

        List<DAOArchivedBookLoan> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * from Book_Loan WHERE isArchived = 1")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Map the correct columns from the stored procedure to the DAO
            	reports.add(new DAOArchivedBookLoan(
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
    
    public List<DAOArchivedEquipmentLoan> getEquipmentLoanArchives() {

        List<DAOArchivedEquipmentLoan> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * from EQUIPMENT_LOAN WHERE isArchived = 1")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Map the correct columns from the stored procedure to the DAO
            	reports.add(new DAOArchivedEquipmentLoan(
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
    
    public List<DAOArchivedFunctionHall> getFunctionHallArchives(String facilityCode) {

        List<DAOArchivedFunctionHall> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * from HALL_RESERVATION WHERE FunctionHallCode = ? and isArchived = 1")) {

            stmt.setString(1, facilityCode);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Map the correct columns from the stored procedure to the DAO
                reports.add(new DAOArchivedFunctionHall(
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
    
    public List<DAOArchivedIMS> getIMSArchives() {

        List<DAOArchivedIMS> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select * from INSTRUCTIONAL_MEDIA_SERVICES where isArchived = 1")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Map the correct columns from the stored procedure to the DAO
            	reports.add(new DAOArchivedIMS(
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
    
    public List<DAOArchivedLoginFacility> getLoginFacilityArchives(String facilityCode) {

        List<DAOArchivedLoginFacility> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * from FACILITY_LOGIN WHERE FacilityCode = ? and isArchived = 1")) {

            stmt.setString(1, facilityCode);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Map the correct columns from the stored procedure to the DAO
            	reports.add(new DAOArchivedLoginFacility(
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
    
    public List<DAOArchivedPatron> getPatronArchives() {

        List<DAOArchivedPatron> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select * from PATRON where IsArchived = 1")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Map the correct columns from the stored procedure to the DAO
            	reports.add(new DAOArchivedPatron(
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
    
    public List<DAOArchivedReturnBook> getReturnBookArchives() {

        List<DAOArchivedReturnBook> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select * from return_book where isArchived = 1")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Map the correct columns from the stored procedure to the DAO
            	reports.add(new DAOArchivedReturnBook(
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
    
    public List<DAOArchivedLostAndFound> getLostAndFoundArchives() {

        List<DAOArchivedLostAndFound> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * from LOST_AND_FOUND WHERE isArchived = 1")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Map the correct columns from the stored procedure to the DAO
            	reports.add(new DAOArchivedLostAndFound(
            			 rs.getString("Lost_ItemNum"),
                         rs.getString("ItemLost"),
                         rs.getString("NameOfOwner"),
                         rs.getString("Status"),
                         rs.getString("ClaimedByPatronID")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
}
