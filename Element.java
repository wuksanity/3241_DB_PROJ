public class Element {
    
    // class variables 
    String serialNum;
    String name; 
    String modelNum;
    String manufacturer;
    String warrantyExpiration;
    Boolean activeStatus;
    String year;

    public Element(String serial, String elName, String model, String manufact, String warExpiration, String yr) {
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

    public String showSerialNum() {
        return serialNum;
    }

    public String showName() {
        return name;
    }

    public String showModelNum() {
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
