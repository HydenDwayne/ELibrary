package model.DAOs.Reports;

public class DAOReturnBook {
	private String transactionID;
	private String callNumber;
	private String returnDate;

	public DAOReturnBook(String transactionID, String callNumber, String returnDate) {
		this.transactionID = transactionID;
		this.callNumber = callNumber;
		this.returnDate = returnDate;

	}

	public String getTransactionID() {
		return transactionID;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public String getReturnDate() {
		return returnDate;
	}

}
