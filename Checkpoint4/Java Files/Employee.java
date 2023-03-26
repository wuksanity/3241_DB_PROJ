public class Employee extends Person {
    
    // class variables
    String startDate;
    int EmpID;
    double wage;

    public Employee(String phoneNum, String firstName, String lastName, String addr, String emailAdd, int ID, double pay, String date){
        super(phoneNum, firstName, lastName, addr, emailAdd);
        EmpID = ID;
        wage = pay;
        startDate = date;
    }

    public void updateWage(double newWage) {
        wage = newWage;
    }

    public String showStartDate() {
        return startDate;
    }

    public double showWage() {
        return wage;
    }

}
