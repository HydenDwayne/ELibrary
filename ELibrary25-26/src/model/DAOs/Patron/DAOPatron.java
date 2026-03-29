package model.DAOs.Patron;

public class DAOPatron {

	// patron columns
    private String patronID;
    private String FullName;
    private String EmailAddress;
    private String ContactNumber;
    private String HomeAddress;
    private String Campus;
    private String PatronType;

    public String getPatronID() {
        return patronID;
    }

    public String getFullName() {
        return FullName;
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

    public DAOPatron(String patronID, String FullName, String EmailAddress, String ContactNumber, String HomeAddress, String Campus, String PatronType) {
        this.patronID = patronID;
        this.FullName = FullName;
        this.EmailAddress = EmailAddress;
        this.ContactNumber = ContactNumber;
        this.HomeAddress = HomeAddress;
        this.Campus = Campus;
        this.PatronType = PatronType;
    }
}
