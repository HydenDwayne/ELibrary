package controller;

import model.DAOs.LoginDAO.LoginDAOImp;

public class LoginController {
	
	private boolean isLoggedIn = false;
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public LoginController(String username, String password) {
		LoginDAOImp daoLogin = new LoginDAOImp();
		isLoggedIn = daoLogin.checkCredentials(username, password);
	}
}
