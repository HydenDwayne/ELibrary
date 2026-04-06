package model.DAOs.Archived;



import java.time.LocalDate;
import java.time.LocalTime;

public class DAOArchivedFunctionHall {
	private String functionHallCode;
	private String hallReservationNumber;
	private String librarianID;
	private String patronID;
	private String eventName;
	private String dateOfEvent;
	private String startTime;
	private String endTime;

	public DAOArchivedFunctionHall(String functionHallCode, String hallReservationNumber, String librarianID, String patronID,
			String eventName, String dateOfEvent, String startTime, String endTime) {
		this.functionHallCode = functionHallCode;
		this.hallReservationNumber = hallReservationNumber;
		this.librarianID = librarianID;
		this.patronID = patronID;
		this.eventName = eventName;
		this.dateOfEvent = dateOfEvent;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	// Getters and Setters
	public String getFunctionHallCode() {
		return functionHallCode;
	}

	public String getHallReservationNumber() {
		return hallReservationNumber;
	}

	public String getLibrarianID() {
		return librarianID;
	}

	public String getPatronID() {
		return patronID;
	}

	public String getEventName() {
		return eventName;
	}

	public String getDateOfEvent() {
		return dateOfEvent;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

}