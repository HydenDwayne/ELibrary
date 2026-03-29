package model;

public class Login {
    private String accountName;
	private String password;
	
	public String getAccountName() {
		return accountName;
	}
	public String getPassword() {
		return password;
	}
	
	public Login(String u) {
		this(u,"defaultPassword");
	}
	
	public Login(String u, String p) {
		accountName = u;
		password = p;
	}
}
