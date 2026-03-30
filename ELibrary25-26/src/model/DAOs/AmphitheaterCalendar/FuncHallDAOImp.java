package model.DAOs.AmphitheaterCalendar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncHallDAOImp implements DAOInterfaceFuncHall {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    @Override
    public List<DAOFuncHall> checkDayForEvent(String facilityCode) {

        List<DAOFuncHall> events = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HALL_RESERVATION WHERE FunctionHallCode = ?")) {
        	
        	stmt.setString(1, facilityCode);
        	
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	events.add(new DAOFuncHall(
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

        return events;
    }
}
