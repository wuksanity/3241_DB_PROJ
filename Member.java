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

    

}
