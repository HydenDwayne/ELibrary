package model.DAOs.Reports;

public class DAOEquipmentLoan {
	private String loanID;
	private String facilityCode;
	private String serialNumber;
	private String patronID;
	private String venue;
	private String borrowDate;
	private String loanStatus;

	public DAOEquipmentLoan(String loanID, String facilityCode, String serialNumber, String patronID, String venue,
			String borrowDate, String loanStatus) {
		this.loanID = loanID;
		this.facilityCode = facilityCode;
		this.serialNumber = serialNumber;
		this.patronID = patronID;
		this.venue = venue;
		this.borrowDate = borrowDate;
		this.loanStatus = loanStatus;
	}

	public String getLoanID() {
		return loanID;
	}

	public String getFacilityCode() {
		return facilityCode;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public String getPatronID() {
		return patronID;
	}

	public String getVenue() {
		return venue;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public String getLoanStatus() {
		return loanStatus;
	}
}
