package model.DAOs.Reports;

public class DAOBookLoan {
	private String transactionID;
	private String callNumber;
	private String borrowDate;
	private String dueDate;
	private String patronID;
	private String circulationCode;

	public DAOBookLoan(String transactionID, String callNumber, String borrowDate, String dueDate, String patronID,
			String circulationCode) {
		this.transactionID = transactionID;
		this.callNumber = callNumber;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.patronID = patronID;
		this.circulationCode = circulationCode;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getPatronID() {
		return patronID;
	}

	public String getCirculationCode() {
		return circulationCode;
	}
}
