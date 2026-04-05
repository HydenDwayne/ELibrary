package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.DAOs.ActiveRequest.ActiveRequestDAOImp;
import model.DAOs.IMS.IMSDAOImp;
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
	
	public IMSController(ViewIMS view) {
		this.view = view;
	}
	
	public void getEquipmentInfo(String serialNumber) {
		String[] equipmentInfo = daoIMS.getEquipmentInfo(serialNumber);
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
		boolean isSuccessful = daoCard.addRequest(requestDetails);
		
		if (isSuccessful) {
			JOptionPane.showMessageDialog(null, "Error. Equipment not added");
		}
		
		return isSuccessful;
	}
}
