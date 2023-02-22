public class Person {

    // class variables
    String phone;
    String fName;
    String lName;
    String address;
    String email;

    public Person(String phone, String fName, String lName, String address, String email) {

        this.phone = phone;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.email = email;

    }

    public void updatePhone(String newPhone) {
        phone = newPhone;
    }

    public void updateAddress(String newAddr) {
        address = newAddr;
    }

    public void updateEmail(String newEmail) {
        email = newEmail;
    }

    public String showPhone() {
        return phone;
    }

    public String showName() {
        return fName + " " + lName;
    }

    public String showAddress() {
        return address;
    }

    public String showEmail() {
        return email;
    }

}
