package model.DAOs.Reports;

public class DAOLoginFacility {
	private String facilityCode;
	private String logID;
	private String patronTimeIn;
	private String patronTimeOut;
	private String patronID;
	private String cardNo;

	public DAOLoginFacility(String facilityCode, String logID, String patronTimeIn,
                      String patronTimeOut, String patronID, String cardNo) {
        this.facilityCode = facilityCode;
        this.logID = logID;
        this.patronTimeIn = patronTimeIn;
        this.patronTimeOut = patronTimeOut;
        this.patronID = patronID;
        this.cardNo = cardNo;
    }

	
	public String getFacilityCode() {
		return facilityCode;
	}

	public String getLogID() {
		return logID;
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

	public String getCardNo() {
		return cardNo;
	}
}
