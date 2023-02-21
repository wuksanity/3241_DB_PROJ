public class Element {
    
    // class variables 
    int serialNum;
    String name; 
    int modelNum;
    String manufacturer;
    String warrantyExpiration;
    Boolean activeStatus;
    String year;

    public Element(int serial, String elName, int model, String manufact, String warExpiration, String yr) {
        serialNum = serial;
        name = elName;
        modelNum = model;
        manufacturer = manufact;
        warrantyExpiration = warExpiration;
        activeStatus = false;
        year = yr;
    }

    public void updateStatus(Boolean isActive) {
        activeStatus = isActive;
    }

    public int showSerialNum() {
        return serialNum;
    }

    public String showName() {
        return name;
    }

    public int showModelNum() {
        return modelNum;
    }

    public String showManufacturer() {
        return manufacturer;
    }

    public String showWarrantyExpiration() {
        return warrantyExpiration;
    }

    public Boolean isActive() {
        return activeStatus;
    }

    public String showYear() {
        return year;
    }

}
