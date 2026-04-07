package controller;

import java.util.List;

import model.DAOs.Reports.*;

public class ReportsController {

	ReportDAOImp daoReport = new ReportDAOImp();

	String facilityCode = "";

	public ReportsController(String facilityCode) {
		this.facilityCode = facilityCode;
	}

	public Object[][] getTableData() {
		List<DAOFunctionHall> records = daoReport.getFunctionHallReports(facilityCode);

		return convertToTableDataFH(records);
	}

	public Object[][] getTableDataFL() {
		List<DAOLoginFacility> records = daoReport.getLoginFacilityReports(facilityCode);

		return convertToTableDataFL(records);
	}

	public static Object[][] convertToTableDataFH(List<DAOFunctionHall> reservations) {
		Object[][] data = new Object[reservations.size()][8]; // 8 columns

		for (int i = 0; i < reservations.size(); i++) {
			DAOFunctionHall r = reservations.get(i);
			data[i][0] = r.getFunctionHallCode();
			data[i][1] = r.getHallReservationNumber();
			data[i][2] = r.getLibrarianID();
			data[i][3] = r.getPatronID();
			data[i][4] = r.getEventName();
			data[i][5] = r.getDateOfEvent();
			data[i][6] = r.getStartTime();
			data[i][7] = r.getEndTime();
		}

		return data;
	}

	public static Object[][] convertToTableDataFL(List<DAOLoginFacility> logs) {
		Object[][] data = new Object[logs.size()][6]; // 6 columns

		for (int i = 0; i < logs.size(); i++) {
			DAOLoginFacility log = logs.get(i);
			data[i][0] = log.getFacilityCode();
			data[i][1] = log.getLogID();
			data[i][2] = log.getPatronTimeIn();
			data[i][3] = log.getPatronTimeOut();
			data[i][4] = log.getPatronID();
			data[i][5] = log.getCardNo();
		}

		return data;
	}

	public Object[][] getBookLoanData() {
		List<DAOBookLoan> records = daoReport.getBookLoanReports();

		return convertToTableDataBL(records);
	}

	public static Object[][] convertToTableDataBL(List<DAOBookLoan> transactions) {
		Object[][] data = new Object[transactions.size()][6]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			DAOBookLoan t = transactions.get(i);
			data[i][0] = t.getTransactionID();
			data[i][1] = t.getCallNumber();
			data[i][2] = t.getBorrowDate();
			data[i][3] = t.getDueDate();
			data[i][4] = t.getPatronID();
			data[i][5] = t.getCirculationCode();
		}

		return data;
	}
	
	public Object[][] getReturnBookData() {
		List<DAOReturnBook> records = daoReport.getReturnBookReports();
		
		return convertToTableDataRB(records);
	}
	
	public static Object[][] convertToTableDataRB(List<DAOReturnBook> transactions) {
		Object[][] data = new Object[transactions.size()][6]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			DAOReturnBook t = transactions.get(i);
			data[i][0] = t.getTransactionID();
			data[i][1] = t.getCallNumber();
			data[i][2] = t.getReturnDate();
		}

		return data;
	}
	
	public Object[][] getIMSData() {
		List<DAOIMS> records = daoReport.getIMSReports();
		
		return convertToTableDataIMS(records);
	}
	
	public static Object[][] convertToTableDataIMS(List<DAOIMS> transactions) {
		Object[][] data = new Object[transactions.size()][3]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			DAOIMS t = transactions.get(i);
			data[i][0] = t.getSerialNumber();
			data[i][1] = t.getEquipmentName();
			data[i][2] = t.getItemType();
		}

		return data;
	}
	
	public Object[][] getEquipmentLoanData() {
		List<DAOEquipmentLoan> records = daoReport.getEquipmentLoanReports();
		
		return convertToTableDataEL(records);
	}
	
	public static Object[][] convertToTableDataEL(List<DAOEquipmentLoan> transactions) {
		Object[][] data = new Object[transactions.size()][7]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			DAOEquipmentLoan t = transactions.get(i);
			data[i][0] = t.getLoanID();
			data[i][1] = t.getFacilityCode();
			data[i][2] = t.getSerialNumber();
			data[i][3] = t.getPatronID();
			data[i][4] = t.getVenue();
			data[i][5] = t.getBorrowDate();
			data[i][6] = t.getLoanStatus();
		}

		return data;
	}
	
	public Object[][] getPatronData() {
		List<DAOPatron> records = daoReport.getPatronReports();
		
		return convertToTableDatPat(records);
	}
	
	public static Object[][] convertToTableDatPat(List<DAOPatron> transactions) {
		Object[][] data = new Object[transactions.size()][9]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			
			DAOPatron t = transactions.get(i);
			data[i][0] = t.getPatronID();
			data[i][1] = t.getLastName();
			data[i][2] = t.getFirstName();
			data[i][3] = t.getMiddleInitial();
			data[i][4] = t.getEmailAddress();
			data[i][5] = t.getContactNumber();
			data[i][6] = t.getHomeAddress();
			data[i][7] = t.getCampus();
			data[i][8] = t.getPatronType();
		}

		return data;
	}
	
	public Object[][] getBookData() {
		List<DAOBook> records = daoReport.getBookReports();
		
		return convertToTableDatBook(records);
	}
	
	public static Object[][] convertToTableDatBook(List<DAOBook> transactions) {
		Object[][] data = new Object[transactions.size()][7]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			
			DAOBook t = transactions.get(i);
			data[i][0] = t.getCallNumber();
			data[i][1] = t.getTitle();
			data[i][2] = t.getAuthor();
			data[i][3] = t.getPublicationYear().substring(0,4);
			data[i][4] = t.getBookType();
			data[i][5] = t.getCollectionCode();
			data[i][6] = t.getClassificationCode();
			
		}

		return data;
	}
	
	public Object[][] getBorrowedBooksData() {
		List<DAOBorrowedBooks> records = daoReport.getBorrowedBookReports();

		return convertToTableDataBB(records);
	}

	public static Object[][] convertToTableDataBB(List<DAOBorrowedBooks> transactions) {
		Object[][] data = new Object[transactions.size()][6]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			DAOBorrowedBooks t = transactions.get(i);
			data[i][0] = t.getTransactionID();
			data[i][1] = t.getCallNumber();
			data[i][2] = t.getBorrowDate();
			data[i][3] = t.getDueDate();
			data[i][4] = t.getPatronID();
			data[i][5] = t.getCirculationCode();
		}

		return data;
	}
	
	public Object[][] getOverdueBooksData() {
		List<DAOOverdueBooks> records = daoReport.getOverdueBookReports();

		return convertToTableDataOB(records);
	}

	public static Object[][] convertToTableDataOB(List<DAOOverdueBooks> transactions) {
		Object[][] data = new Object[transactions.size()][6]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			DAOOverdueBooks t = transactions.get(i);
			data[i][0] = t.getTransactionID();
			data[i][1] = t.getCallNumber();
			data[i][2] = t.getBorrowDate();
			data[i][3] = t.getDueDate();
			data[i][4] = t.getPatronID();
			data[i][5] = t.getCirculationCode();
		}

		return data;
	}
	
}
