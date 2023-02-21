public class Warehouse {
    
    // class variables
    int storageCapacity;
    int droneCapacity;
    String address;
    String phone;
    String manager;
    String city;

    public Warehouse(int storeCapac, int droneCapac, String addr, String phoneNum, String mngr, String cit) {
        storageCapacity = storeCapac;
        droneCapacity = droneCapac;
        address = addr;
        phone = phoneNum;
        manager = mngr;
        city = cit;
    }

    public void updateStorageCapacity(int newCapacity) {
        storageCapacity = newCapacity;
    }

    public void updateDroneCapacity(int newCapacity) {
        droneCapacity = newCapacity;
    }

    public void updatePhoneNum(String newNum) {
        phone = newNum;
    }

    public void updateManager(String newMngr) {
        manager = newMngr;
    }

    public int showStorageCapacity() {
        return storageCapacity;
    }

    public int showDroneCapacity() {
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
