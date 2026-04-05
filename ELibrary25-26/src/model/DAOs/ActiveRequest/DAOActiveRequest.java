package model.DAOs.ActiveRequest;

public class DAOActiveRequest {

	// book columns

	private String loanID;
	private String serialNumber;
	private String patronName;
	private String venue;
	private String borrowDate;
	private String equipmentName;
	private String loanStatus;

	public String getLoanID() {
		return loanID;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public String getPatronName() {
		return patronName;
	}

	public String getVenue() {
		return venue;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public String getEquipmentName() {
		return equipmentName;
	}
	
	public String getLoanStatus() {
		return loanStatus;
	}

	public DAOActiveRequest(String loanID, String serialNumber, String patronName, String venue, String borrowDate, String equipmentName, String loanStatus) {
		this.loanID = loanID;
		this.serialNumber = serialNumber;
		this.patronName = patronName;
		this.venue = venue;
		this.borrowDate = borrowDate;
		this.equipmentName = equipmentName;
		this.loanStatus = loanStatus;
	}
}
