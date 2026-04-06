package controller;

import java.util.List;

import javax.swing.JOptionPane;

import model.DAOs.Archived.*;

public class ArchiveController {

	ArchivedDAOImp daoArchives = new ArchivedDAOImp();

	String facilityCode;
	String pk;
	String tableName;

	public ArchiveController(String facilityCode) {
		this.facilityCode = facilityCode;
	}

	public Object[][] getTableData() {
		List<DAOArchivedFunctionHall> records = daoArchives.getFunctionHallArchives(facilityCode);

		return convertToTableDataFH(records);
	}

	public Object[][] getTableDataFL() {
		List<DAOArchivedLoginFacility> records = daoArchives.getLoginFacilityArchives(facilityCode);

		return convertToTableDataFL(records);
	}

	public static Object[][] convertToTableDataFH(List<DAOArchivedFunctionHall> reservations) {
		Object[][] data = new Object[reservations.size()][4]; // 8 columns

		for (int i = 0; i < reservations.size(); i++) {
			DAOArchivedFunctionHall r = reservations.get(i);

			data[i][0] = r.getHallReservationNumber();
			data[i][1] = r.getFunctionHallCode();
			data[i][2] = r.getEventName();
			data[i][3] = "Archived";
		}

		return data;
	}

	public static Object[][] convertToTableDataFL(List<DAOArchivedLoginFacility> logs) {
		Object[][] data = new Object[logs.size()][4]; // 6 columns

		for (int i = 0; i < logs.size(); i++) {
			DAOArchivedLoginFacility log = logs.get(i);
			data[i][0] = log.getLogID();
			data[i][1] = log.getFacilityCode();
			data[i][2] = log.getPatronID();
			data[i][3] = "Archived";
		}

		return data;
	}

	public Object[][] getBookLoanData() {
		List<DAOArchivedBookLoan> records = daoArchives.getBookLoanArchives();

		return convertToTableDataBL(records);
	}

	public static Object[][] convertToTableDataBL(List<DAOArchivedBookLoan> transactions) {
		Object[][] data = new Object[transactions.size()][5]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			DAOArchivedBookLoan t = transactions.get(i);
			data[i][0] = t.getTransactionID();
			data[i][1] = t.getCallNumber();
			data[i][2] = t.getBorrowDate();
			data[i][3] = t.getPatronID();
			data[i][4] = "Archived";
		}

		return data;
	}

	public Object[][] getReturnBookData() {
		List<DAOArchivedReturnBook> records = daoArchives.getReturnBookArchives();

		return convertToTableDataRB(records);
	}

	public static Object[][] convertToTableDataRB(List<DAOArchivedReturnBook> transactions) {
		Object[][] data = new Object[transactions.size()][4]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			DAOArchivedReturnBook t = transactions.get(i);
			data[i][0] = t.getTransactionID();
			data[i][1] = t.getCallNumber();
			data[i][2] = t.getReturnDate();
			data[i][3] = "Archived";
		}

		return data;
	}

	public Object[][] getIMSData() {
		List<DAOArchivedIMS> records = daoArchives.getIMSArchives();

		return convertToTableDataIMS(records);
	}

	public static Object[][] convertToTableDataIMS(List<DAOArchivedIMS> transactions) {
		Object[][] data = new Object[transactions.size()][4]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			DAOArchivedIMS t = transactions.get(i);
			data[i][0] = t.getSerialNumber();
			data[i][1] = t.getEquipmentName();
			data[i][2] = t.getItemType();
			data[i][3] = "Archived";
		}

		return data;
	}

	public Object[][] getEquipmentLoanData() {
		List<DAOArchivedEquipmentLoan> records = daoArchives.getEquipmentLoanArchives();

		return convertToTableDataEL(records);
	}

	public static Object[][] convertToTableDataEL(List<DAOArchivedEquipmentLoan> transactions) {
		Object[][] data = new Object[transactions.size()][5]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {
			DAOArchivedEquipmentLoan t = transactions.get(i);
			data[i][0] = t.getLoanID();
			data[i][1] = t.getSerialNumber();
			data[i][2] = t.getPatronID();
			data[i][3] = t.getLoanStatus();
			data[i][4] = "Archived";
		}

		return data;
	}

	public Object[][] getPatronData() {
		List<DAOArchivedPatron> records = daoArchives.getPatronArchives();

		return convertToTableDatPat(records);
	}

	public static Object[][] convertToTableDatPat(List<DAOArchivedPatron> transactions) {
		Object[][] data = new Object[transactions.size()][5]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {

			DAOArchivedPatron t = transactions.get(i);
			data[i][0] = t.getPatronID();
			data[i][1] = t.getLastName();
			data[i][2] = t.getCampus();
			data[i][3] = t.getPatronType();
			data[i][4] = "Archived";
		}

		return data;
	}

	public Object[][] getBookData() {
		List<DAOArchivedBook> records = daoArchives.getBooksArchives();

		return convertToTableDatBook(records);
	}

	public static Object[][] convertToTableDatBook(List<DAOArchivedBook> transactions) {
		Object[][] data = new Object[transactions.size()][4]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {

			DAOArchivedBook t = transactions.get(i);
			data[i][0] = t.getCallNumber();
			data[i][1] = t.getTitle();
			data[i][2] = t.getCollectionCode();
			data[i][3] = "Archived";
		}

		return data;
	}

	public Object[][] getLNFData() {
		List<DAOArchivedLostAndFound> records = daoArchives.getLostAndFoundArchives();

		return convertToTableDatLNF(records);
	}

	public static Object[][] convertToTableDatLNF(List<DAOArchivedLostAndFound> transactions) {
		Object[][] data = new Object[transactions.size()][5]; // 6 columns

		for (int i = 0; i < transactions.size(); i++) {

			DAOArchivedLostAndFound t = transactions.get(i);
			data[i][0] = t.getLostItemNumber();
			data[i][1] = t.getItemLost();
			data[i][2] = t.getNameOfOwner();
			data[i][3] = t.getStatus();
			data[i][4] = t.getClaimedByPatron();
		}

		return data;
	}

	
	public ArchiveController(String tableName, String pk) {
		this.tableName = tableName;
		this.pk = pk;
	}
	
	public boolean setArchived() {
		boolean isSuccessful = daoArchives.setArchived(tableName, pk);
		
		if(!isSuccessful) {
			JOptionPane.showMessageDialog(null, "Error. Record not archived");
		}
		
		return isSuccessful;
	}
	
	public boolean setUnarchived() {
		boolean isSuccessful = daoArchives.setUnarchived(tableName, pk);
		
		if(!isSuccessful) {
			if (tableName.equals("HallReservation")) {
				JOptionPane.showMessageDialog(null, "Cannot unarchive. Theres already an event for that day!");
			} else {
				JOptionPane.showMessageDialog(null, "Error. Record not unarchived");
			}
			
		}
		
		return isSuccessful;
	}
}
