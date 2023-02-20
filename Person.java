public class Person {

    // class variables
    String phone;
    String fName;
    String lName;
    String address;
    String email;

    public Person(String phoneNum, String firstName, String lastName, String addr, String emailAdd) {

        phone = phoneNum;
        firstName = fName;
        lastName = lName;
        address = addr;
        email = emailAdd;

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

    public String showAddress() {
        return address;
    }

    public String showEmail() {
        return email;
    }

}
