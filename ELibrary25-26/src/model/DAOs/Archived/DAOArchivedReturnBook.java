package model.DAOs.Archived;

public class DAOArchivedReturnBook {
	private String transactionID;
	private String callNumber;
	private String returnDate;

	public DAOArchivedReturnBook(String transactionID, String callNumber, String returnDate) {
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
