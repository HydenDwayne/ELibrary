package model.DAOs.IMS;

public class DAOIMS {

	
    private String facilityCode;
    private String serialNumber;
    private String equipmentName;
;

    public String getFacilityCode() {
        return facilityCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public DAOIMS(String facilityCode, String serialNumber, String equipmentName) {
        this.facilityCode = facilityCode;
        this.serialNumber = serialNumber;
        this.equipmentName = equipmentName;

    }
}
