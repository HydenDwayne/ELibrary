package model.DAOs.Reports;

public class DAOIMS {
	private String serialNumber;
	private String equipmentName;
	private String itemType;

	public DAOIMS(String serialNumber, String equipmentName, String itemType) {
		this.serialNumber = serialNumber;
		this.equipmentName = equipmentName;
		this.itemType = itemType;

	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public String getItemType() {
		return itemType;
	}

}
