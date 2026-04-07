package controller;

import javax.swing.JOptionPane;

import model.DAOs.LostAndFound.LNFDAOImp;
import model.DAOs.Patron.PatronDAOImp;
import view.modal.lost_and_found_modal.AddLostAndFound;
import view.modal.lost_and_found_modal.MarkAsFound;

public class LNFController {
	LNFDAOImp daoLNF = new LNFDAOImp();
	PatronDAOImp daoPatron = new PatronDAOImp();
	
	public LNFController(MarkAsFound maf, String patronID, String itemNum) {
		
		boolean isValidPatron = daoPatron.checkPatronExists(patronID);
		
		if (!isValidPatron) {
			JOptionPane.showMessageDialog(null, "Patron ID does not match any records");
		} else {
			boolean isSuccessful = daoLNF.markAsClaimed(patronID, itemNum);
			if (!isSuccessful) {
				JOptionPane.showMessageDialog(null, "Error. Nothing was updated");
			}
		}
	}
	
	public LNFController(String[] reportDetails) {
		boolean isSuccessful = daoLNF.addReport(reportDetails);
		if (!isSuccessful) {
			JOptionPane.showMessageDialog(null, "Error. Nothing is updated");
		}
	}
	
	
}
