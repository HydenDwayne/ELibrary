package model.DAOs.LostAndFound;

public class DAOLNF {

	// LNF columns
    private String itemLost;
    public String getItemLost() {
		return itemLost;
	}

	public String getNameOfOwner() {
		return nameOfOwner;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public String getLostOnFloor() {
		return lostOnFloor;
	}

	public String getStatus() {
		return status;
	}

	public String getLastSeen() {
		return lastSeen;
	}
	
	public String getItemNum() {
		return itemNum;
	}

	private String nameOfOwner;
    private String itemDescription;
    private String lostOnFloor;
    private String status;
    private String lastSeen;
    private String itemNum;

    public DAOLNF(String itemLost, String nameOfOwner, String itemDescription, String lostOnFloor, String status, String lastSeen, String itemNum) {
    	this.itemLost = itemLost;
    	this.nameOfOwner = nameOfOwner;
    	this.itemDescription = itemDescription;
    	this.lostOnFloor = lostOnFloor;
    	this.status = status;
    	this.lastSeen = lastSeen;
    	this.itemNum = itemNum;
    }
}
