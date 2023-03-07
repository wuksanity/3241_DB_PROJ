public class Warehouse {
    
    // class variables
    String storageCapacity;
    String droneCapacity;
    String address;
    String phone;
    String manager;
    String city;

    public Warehouse(String storeCapac, String droneCapac, String addr, String phoneNum, String mngr, String cit) {
        storageCapacity = storeCapac;
        droneCapacity = droneCapac;
        address = addr;
        phone = phoneNum;
        manager = mngr;
        city = cit;
    }

    public void updateStorageCapacity(String newCapacity) {
        storageCapacity = newCapacity;
    }

    public void updateDroneCapacity(String newCapacity) {
        droneCapacity = newCapacity;
    }

    public void updatePhoneNum(String newNum) {
        phone = newNum;
    }

    public void updateManager(String newMngr) {
        manager = newMngr;
    }

    public String showStorageCapacity() {
        return storageCapacity;
    }

    public String showDroneCapacity() {
        return droneCapacity;
    }

    public String showAddress() {
        return address;
    }

    public String showPhoneNum() {
        return phone;
    }

    public String showManager() {
        return manager;
    }

    public String showLocation() {
        return city;
    }

}
