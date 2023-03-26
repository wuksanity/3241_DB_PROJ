public class Member extends Person {

    // class variables
    int userID;
    double warehouseDist;
    String startDate;
    Boolean activated;

    public Member(String phoneNum, String firstName, String lastName, String addr, String emailAdd, int ID, double distance, String date){
        super(phoneNum, firstName, lastName, addr, emailAdd);
        userID = ID;
        warehouseDist = distance;
        startDate = date;
        activated = true;
    }

    public void updateStatus(Boolean active) {
        activated = active;
    }

    public void updateWarehouseDistance(double distance) {
        warehouseDist = distance;
    }

    public String showStartDate() {
        return startDate;
    }

    public double showWarehouseDistance() {
        return warehouseDist;
    }

    public Boolean isActive() {
        return activated;
    }

}
