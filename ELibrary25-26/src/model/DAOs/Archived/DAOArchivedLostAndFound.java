package model.DAOs.Archived;

public class DAOArchivedLostAndFound {
	// lost and found columns
	private String lostItemNumber;
	private String itemLost;
	private String nameOfOwner;
	private String status;
	private String claimedByPatron;

	public String getLostItemNumber() {
		return lostItemNumber;
	}

	public String getItemLost() {
		return itemLost;
	}

	public String getNameOfOwner() {
		return nameOfOwner;
	}

	public String getStatus() {
		return status;
	}

	public String getClaimedByPatron() {
		return claimedByPatron;
	}

	public DAOArchivedLostAndFound(String lostItemNumber, String itemLost, String nameOfOwner, String status,
			String claimedByPatron) {
		this.lostItemNumber = lostItemNumber;
		this.itemLost = itemLost;
		this.nameOfOwner = nameOfOwner;
		this.status = status;
		this.claimedByPatron = claimedByPatron;
	}
}
