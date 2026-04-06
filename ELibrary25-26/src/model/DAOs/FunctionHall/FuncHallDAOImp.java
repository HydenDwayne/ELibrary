package model.DAOs.FunctionHall;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FuncHallDAOImp {

	private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
	private final String USER = "Pia";
	private final String PASSWORD = "passwordPia";

	public List<DAOFuncHall> checkDayForEvent(String facilityCode) {

		List<DAOFuncHall> events = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM HALL_RESERVATION WHERE FunctionHallCode = ? AND isArchived = 0")) {

			stmt.setString(1, facilityCode);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				events.add(new DAOFuncHall(rs.getString("HallReservationNumber"), rs.getString("LibrarianID"),
						rs.getString("PatronID"), rs.getString("EventName"), rs.getString("DateOfEvent"),
						rs.getString("StartTime"), rs.getString("EndTime")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return events;
	}

	public List<DAOFuncHall> checkDayForEvent() {

		List<DAOFuncHall> events = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HALL_RESERVATION WHERE isArchived = 0")) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				events.add(new DAOFuncHall(rs.getString("HallReservationNumber"), rs.getString("LibrarianID"),
						rs.getString("PatronID"), rs.getString("EventName"), rs.getString("DateOfEvent"),
						rs.getString("StartTime"), rs.getString("EndTime")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return events;
	}

	public String getHallReservationNumber() {

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(
						"select top 1 HallReservationNumber from HALL_RESERVATION order by HallReservationNumber desc")) {
			ResultSet rs = stmt.executeQuery();

			rs.next();

			String latestNumber = rs.getString("HallReservationNumber");
			String hallReservationNumber = LocalDate.now().getYear() + "-";

			if (latestNumber == null) {
				hallReservationNumber = hallReservationNumber + "0001";
			} else {
				int extensionNumber = Integer.parseInt(latestNumber.split("-")[1]);

				extensionNumber++;

				if (extensionNumber > 999) {
					hallReservationNumber = hallReservationNumber + "" + extensionNumber;
				}

				else if (extensionNumber > 99) {
					hallReservationNumber = hallReservationNumber + "0" + extensionNumber;
				}

				else if (extensionNumber > 9) {
					hallReservationNumber = hallReservationNumber + "00" + extensionNumber;
				}
			}

			return hallReservationNumber;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addNewReservation(String[] reservationDetails) {

	    String reservationNumber = getHallReservationNumber();

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         CallableStatement stmt = conn.prepareCall("{CALL addNewRecord_HallReservation(?,?,?,?,?,?,?,?)}")) {

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

	        // Set parameters
	        stmt.setString(1, reservationDetails[0]);               // FunctionHallCode
	        stmt.setString(2, reservationNumber);                 // HallReservationNumber
	        stmt.setString(3, reservationDetails[1]);             // LibrarianID
	        stmt.setString(4, reservationDetails[2]);             // PatronID
	        stmt.setString(5, reservationDetails[3]);             // EventName

	        // Convert date string to java.sql.Date
	        stmt.setDate(6, Date.valueOf(LocalDate.parse(reservationDetails[4])));

	        // Normalize time strings (remove .00 if present)
	        String startTimeStr = reservationDetails[5].split("\\.")[0];
	        String endTimeStr   = reservationDetails[6].split("\\.")[0];

	        // Convert to java.sql.Time
	        stmt.setTime(7, Time.valueOf(LocalTime.parse(startTimeStr, formatter)));
	        stmt.setTime(8, Time.valueOf(LocalTime.parse(endTimeStr, formatter)));

	        // Execute stored procedure
	        stmt.executeUpdate();  // ✅ only this is needed

	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean archiveReservation(String reservationNumber) {

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement stmt = conn.prepareStatement("UPDATE HALL_RESERVATION SET isArchived = 1 WHERE HallReservationNumber = ?")) {

	        stmt.setString(1, reservationNumber);

	        stmt.executeUpdate();  // ✅ only this is needed

	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
}
