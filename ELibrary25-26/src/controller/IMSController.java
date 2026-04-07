package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.DAOs.ActiveRequest.ActiveRequestDAOImp;
import model.DAOs.IMS.IMSDAOImp;
import model.DAOs.Patron.PatronDAOImp;
import view.modal.ims_modal.AddIMS;
import view.modal.ims_modal.AddRequestItem;
import view.modal.ims_modal.ViewBorrowRequest;
import view.modal.ims_modal.ViewIMS;

public class IMSController {
	IMSDAOImp daoIMS = new IMSDAOImp();
	ViewIMS view;
	ViewBorrowRequest viewCard;
	ActiveRequestDAOImp daoCard = new ActiveRequestDAOImp();
	AddIMS add;
	AddRequestItem req;
	PatronDAOImp daoPatron = new PatronDAOImp();
	String serial; 
	
	public IMSController(ViewIMS view) {
		this.view = view;
	}
	
	public void getEquipmentInfo(String serialNumber) {
		String[] equipmentInfo = daoIMS.getEquipmentInfo(serialNumber);
		System.out.println(equipmentInfo[1]);
		view.itemField.setText(equipmentInfo[1]);
		view.equipField.setText(equipmentInfo[0]);
		view.serialField.setText(serialNumber);
	}
	
	public boolean updateEquipmentInfo(String[] equipmentInfo) {
		boolean isSuccessful = daoIMS.updateEquipmentInfo(equipmentInfo);
		
		if(!isSuccessful) {
			JOptionPane.showMessageDialog(null, "Error. Nothing updated");
		}
		return isSuccessful;
	}
	
	public IMSController(ViewBorrowRequest viewCard) {
		this.viewCard = viewCard;
	}
	
	public void getLoanInfo(String loanID) {
		String[] loanDetails = daoCard.getEquipmentLoanDetails(loanID);
        
		viewCard.loanIdField.setText(loanDetails[0]);
		viewCard.serialField.setText(loanDetails[1]);
		viewCard.patronField.setText(loanDetails[2]);
		viewCard.venueField.setText(loanDetails[3]);
		viewCard.dateField.setText(loanDetails[4]);
		
		switch (loanDetails[5]) {
		case "Pending":
			viewCard.statusCombo.setSelectedIndex(0);
			break;
		case "Approved":
			viewCard.statusCombo.setSelectedIndex(1);
			break;
		case "Borrowed":
			viewCard.statusCombo.setSelectedIndex(2);
			break;

		default:
			break;
		}

		
	}

	
	public boolean updateLoanStatus(String loanID, String status) {
		boolean isSuccessful = daoCard.updateStatus(loanID, status);
		
		if(!isSuccessful) {
			JOptionPane.showMessageDialog(null, "Error. Status not updated");
		}
		return isSuccessful;
	}
	
	public IMSController(AddIMS add) {
		this.add = add;
	}
	
	public boolean addNewEquipment(String[] equipmentDetails) {
		boolean serialExists = daoIMS.checkSerialNumber(equipmentDetails[0]);
		boolean isSuccessful = daoIMS.addNewEquipment(equipmentDetails);

		if (serialExists) {
			JOptionPane.showMessageDialog(null, "Error. Serial number already exists");
		} else {
			if (!isSuccessful) {
				JOptionPane.showMessageDialog(null, "Error. Equipment not added");
			}
		}
		return isSuccessful;
	}
	
	public IMSController(AddRequestItem req) {
		this.req = req;
	}
	
	public boolean addNewRequest(String[] requestDetails) {
		boolean isSuccessful = false;
		boolean patronExists = daoPatron.checkPatronExists(requestDetails[1]);
		boolean serialNumberExists = daoCard.checkEquipmentExists(requestDetails[0]);
		boolean isBorrowable = daoCard.checkIfAvailable(requestDetails[0]);
		
		if(!patronExists && !serialNumberExists) {
			JOptionPane.showMessageDialog(null, "Error. Patron ID and Serial does not match any records");
		} else if(!serialNumberExists) {
			JOptionPane.showMessageDialog(null, "Error. Serial Number does not match any records");
		} else if (!patronExists) {
			JOptionPane.showMessageDialog(null, "Error. Patron ID does not match any records");
		} else if (patronExists && serialNumberExists) {
			
			if(!isBorrowable) {
				JOptionPane.showMessageDialog(null, "Error. Equipment currently borrowed");
			} else {
				isSuccessful = daoCard.addRequest(requestDetails);
				if (!isSuccessful) {
					JOptionPane.showMessageDialog(null, "Error. Request not issued");
				}
			}
		}
		return isSuccessful;
	}
	
	public boolean serialExists() {
		boolean serialExists = daoCard.checkEquipmentExists(serial);
		return serialExists;
	}
	
	public IMSController(String serial) {
		this.serial = serial;
	
	}
}
