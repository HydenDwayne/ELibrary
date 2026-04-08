package model.DAOs.FacilityLogin;

public class DAOFacilityLogin {

	

	
	public String getFacilityCode() {
		return facilityCode;
	}
	public String getPatronTimeIn() {
		return patronTimeIn;
	}
	public String getPatronTimeOut() {
		return patronTimeOut;
	}
	public String getPatronID() {
		return patronID;
	}
	public int getCardNo() {
		return cardNo;
	}
	
	private String facilityCode;
	private String patronTimeIn;
	private String patronTimeOut;
	private String patronID;
	private int cardNo;

	public DAOFacilityLogin(String facilityCode, String patronTimeIn, String patronTimeOut, String patronID, int cardNo) {
		this.facilityCode = facilityCode;
		this.patronTimeIn = patronTimeIn;
		this.patronTimeOut = patronTimeOut;
		this.patronID = patronID;
		this.cardNo = cardNo;
	}
}
