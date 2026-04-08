package model.DAOs.FunctionHall;

public class DAOFuncHall {

	

	private String functionHallCode;
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
	private String hallReservationNumber;
	private String librarianID;
	private String patronID;
	private String eventName;
	private String dateOfEvent;
	private String startTime;
	private String endTime;


	public DAOFuncHall(String hallReservationNumber, String librarianID, String patronID, String eventName, String dateOfEvent, String startTime, String endTime) {
		this.hallReservationNumber = hallReservationNumber;
		this.librarianID = librarianID;
		this.patronID = patronID;
		this.eventName = eventName;
		this.dateOfEvent = dateOfEvent;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
