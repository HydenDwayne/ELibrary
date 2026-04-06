package model.DAOs.Archived;

public class DAOArchivedPatron {

	// patron columns
	private String patronID;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String EmailAddress;
	private String ContactNumber;
	private String HomeAddress;
	private String Campus;
	private String PatronType;

	public String getPatronID() {
		return patronID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public String getContactNumber() {
		return ContactNumber;
	}

	public String getHomeAddress() {
		return HomeAddress;
	}

	public String getCampus() {
		return Campus;
	}

	public String getPatronType() {
		return PatronType;
	}

	public DAOArchivedPatron(String patronID, String firstName, String middleInitial, String lastName, String EmailAddress, String ContactNumber, String HomeAddress,
			String Campus, String PatronType) {
		this.patronID = patronID;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.EmailAddress = EmailAddress;
		this.ContactNumber = ContactNumber;
		this.HomeAddress = HomeAddress;
		this.Campus = Campus;
		this.PatronType = PatronType;
	}
}
