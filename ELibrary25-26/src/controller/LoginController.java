package controller;

import javax.swing.JOptionPane;

import model.DAOs.LoginDAO.LoginDAOImp;

public class LoginController {
	
	private boolean isLoggedIn = false;
	private String username;
	
	LoginDAOImp daoLogin = new LoginDAOImp();
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public LoginController(String username, String password) {
		if (password == null) {
			
			this.username = username;
			
		} else {
			isLoggedIn = daoLogin.checkCredentials(username, password);
			if (isLoggedIn) {
				this.username = username;
			}
		}
	}
	
	public String[] getLibrarianDetails() {
		return daoLogin.getLibrarianDetails(username);
	}
	
	public boolean updateLibrarianDetails(String[] details) {
		boolean isSuccessful = daoLogin.updateLibrarianDetails(details);
		
		if (!isSuccessful) {
			JOptionPane.showMessageDialog(null, "Error. No details updated");
		}
		
		return isSuccessful;
	}
}
