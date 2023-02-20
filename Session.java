import java.util.Scanner;

public class Session {

    //wait for classes to be made
    /*
        * HashMap<String, Equipment> Equipment_Record = new HashMap<>();
        * HashMap<String, Members> Members_Record = new HashMap<>();
        * HashMap<String, Warehouse> Warehouse_Record = new HashMap<>();
    */

    public Session() {
    }

     //TODO
    public void createEquipment(Scanner input) {
        //walk user through entering all attributes, create object, add object to Hashmap
        System.out.println("Create New Equipment");
    }

    public void createMembers(Scanner input) {
        //walk user through entering all attributes, create object, add object to Hashmap
        System.out.println("Creating New Member");
        System.out.println("Enter Phone Number:");
        String phone = input.nextLine();
        System.out.println("Enter First Name:");
        String firstName = input.nextLine();
        System.out.println("Enter Last Name:");
        String lastName = input.nextLine();
        System.out.println("Enter Address:");
        String address = input.nextLine();
        System.out.println("Enter Email:");
        String email = input.nextLine();

        //Member newMember = new Member(phone, firstName, lastName, address, email);
        //Members_Record.put(phone, newMember);
        //String.out.println("New Member has been created")
        //displayMember(newMemeber);

    }

     //TODO
    public void createWarehouse(Scanner input) {
        //walk user through entering all attributes, create object, add object to Hashmap
        System.out.println("Create New Warehouse");

    }

    public void displayStart() {
        System.out.println("Please read the following options below:");
        System.out.println(
                "Enter either 'a' to add a record, 'b' to view and edit existing records, or 'q' to quit");

    }

    public void displayAdd(Scanner input) {

        boolean another = true;

        while (another == true) {
            System.out.println(
                    "Enter letter below to select record type to add:");
            System.out.println("a - Equipment");
            System.out.println("b - Members");
            System.out.println("c - Warehouse");
            System.out.println("d - Go Back");
            String selection = input.nextLine();
            switch (selection) {
                case "a":
                    this.createEquipment(input);
                    break;
                case "b":
                    this.createMembers(input);
                    break;
                case "c":
                    this.createWarehouse(input);
                    break;
                case "d":
                    another = false;
                    break;
                default:
                    System.out.println("Please Enter a valid character");

            }

        }

    }

    //TODO
    public void displayEdit() {

    }
    //TODO
    public void displaySearch() {

    }

     //TODO
    public void displayDelete() {

    }

}
