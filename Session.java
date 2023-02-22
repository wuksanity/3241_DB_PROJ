import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Session {

    //wait for classes to be made
    HashMap<String, Element> Element_Record = new HashMap<>();
    HashMap<String, Person> Person_Record = new HashMap<>();
    HashMap<String, Warehouse> Warehouse_Record = new HashMap<>();


    public Session() {
    }

    public void createFakeData(){
        String[] fName = {"Tony", "Emily", "Juan", "Sarah", "Mark"};
        String[] lName = {"Smith", "Andrews", "Keeto", "Jack", "Daniels"};
        String[] phone = {"7202127040", "3037461303", "8639273838", "4158392011", "1234567890"};
        String[] address = {"186 E Northwood", "2790 Carnegie Dr", "4328 Corriente Dr", "420 Cannon Dr", "167 17th St"};
        String[] email = {"Tony@gmail.com", "Emily@gmailcom", "Juan@gmail.com", "Sarah@gmail.com", "Mark@gmail.com"};
        String[] Cities = {"Denver", "Columbus", "Dayton", "Cleveland", "Akron"};
        String[] serial = {"123", "456", "777", "323", "001"};
        String[] elName = {"hammer", "wrench", "leafblower", "mower", "drill"};
        String[] model = {"v1", "v2", "v3", "v2", "v4"};
        String[] manufacturer = {"Company1", "Company2", "Company3", "Company4", "Company5"};
        String[] year = {"2022", "2026", "2023", "2026", "2024"};

        for(int i = 0; i < 5; i++){
            Element_Record.put(serial[i], new Element(serial[i], elName[i], model[i], manufacturer[i], year[i], year[i]));
            Person_Record.put(phone[i], new Person(phone[i], fName[i], lName[i], address[i], email[i]));
            Warehouse_Record.put(address[i], new Warehouse(String.valueOf(i), String.valueOf(i), address[i], phone[i], fName[i] + " " + lName[i], Cities[i]));
        }

    }

    public void createElement(Scanner input) {
        //walk user through entering all attributes, create object, add object to Hashmap
        System.out.println("Create New Element");
        System.out.println("Enter Serial:");
        String serial = input.nextLine();
        System.out.println("Enter Name:");
        String name = input.nextLine();
        System.out.println("Model:");
        String model = input.nextLine();
        System.out.println("manufacturer");
        String manufacturer = input.nextLine();
        System.out.println("Warranty Expiration");
        String warranty = input.nextLine();
        System.out.println("Year Expiration");
        String year = input.nextLine();

        Element newElement = new Element(serial, name, model, manufacturer, warranty, year);
        Element_Record.put(serial, newElement);
        System.out.println("New Element has been created: ");
        System.out.println(newElement.showSerialNum() + " " + newElement.showName() + "\n");
    }

    public void createPerson(Scanner input) {
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

        Person newPerson = new Person(phone, firstName, lastName, address, email);
        Person_Record.put(phone, newPerson);
        System.out.println("New Person has been created: ");
        System.out.println(newPerson.showPhone() + " " + newPerson.showName() + "\n");

    }

    public void createWarehouse(Scanner input){
        //walk user through entering all attributes, create object, add object to Hashmap
        System.out.println("Create New Warehouse");
        //walk user through entering all attributes, create object, add object to Hashmap
        System.out.println("Enter Address:");
        String address = input.nextLine();
        System.out.println("Enter Phone Number:");
        String phone = input.nextLine();
        System.out.println("Enter City:");
        String city = input.nextLine();
        System.out.println("Enter Manager:");
        String manager = input.nextLine();
        System.out.println("Enter storage capacity:");
        String storageCapacity = input.nextLine();
        System.out.println("Enter drone capacity:");
        String droneCapacity = input.nextLine();


        Warehouse newWarehouse = new Warehouse(storageCapacity, droneCapacity, address, phone, manager, city);
        Warehouse_Record.put(address, newWarehouse);
        System.out.println("New Warehouse has been created: ");
        System.out.println(newWarehouse.showLocation() + " " + newWarehouse.showAddress() + " " + newWarehouse.showPhoneNum() + "\n");

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
            System.out.println("a - Elements");
            System.out.println("b - Persons");
            System.out.println("c - Warehouse");
            System.out.println("d - Go Back");
            String selection = input.nextLine();
            switch (selection) {
                case "a":
                    this.createElement(input);
                    break;
                case "b":
                    this.createPerson(input);
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
    public void displaySearch(Scanner input) {
        boolean another = true;

        while (another == true) {
            System.out.println(
                    "Enter letter below to select record type to search:");
            System.out.println("a - Elements");
            System.out.println("b - Persons");
            System.out.println("c - Warehouse");
            System.out.println("d - Go Back");
            String selection = input.nextLine();
            switch (selection) {
                case "a":
                    System.out.println("\n* ELEMENTS *");
                    for (Map.Entry<String, Element> option : this.Element_Record.entrySet()){
                        Element current = option.getValue();
                        String serial = option.getKey();
                        System.out.println("[" + serial + "] " + current.showName());
                    }
                    this.editElement(input);
                    break;
                case "b":
                    System.out.println("\n* PERSONS *");
                    for (Map.Entry<String, Person> option : this.Person_Record.entrySet()){
                        Person current = option.getValue();
                        String phone = option.getKey();
                        System.out.println("[" + phone + "] " + current.showName());
                    }
                    this.editPerson(input);
                    break;
                case "c":
                    System.out.println("\n* WAREHOUSES *");
                    for (Map.Entry<String, Warehouse> option : this.Warehouse_Record.entrySet()){
                        Warehouse current = option.getValue();
                        String address = option.getKey();
                        System.out.println("[" + address + "] " + current.showLocation());
                    }
                    this.editWarehouse(input);
                    break;
                case "d":
                    another = false;
                    break;
                default:
                    System.out.println("Please Enter a valid character");

            }

        }


    }


    public void editWarehouse(Scanner input){
        boolean valid = true;
        do {
            System.out.println("Enter 's' to select a record or 'd' to Go Back");
            String selection = input.nextLine();
            switch (selection) {
                case "s":
                    boolean validWarehouse = false;
                    String address = "";
                    while(!validWarehouse) {
                        System.out.println("Enter the Address of the Warehouse");
                        address = input.nextLine();
                        if(Warehouse_Record.containsKey(address)){
                            validWarehouse = true;
                        } else{
                            System.out.println("INVALID WAREHOUSE ADDRESS: ** TRY AGAIN **");
                        }
                    }

                    boolean validAnswer = true;
                    do{
                        validAnswer = true;
                        System.out.println("Enter 'e' to edit the Warehouse at " + address + " or 'd' to delete the Warehouse at " + address);
                        String answer = input.nextLine();
                        if(answer.equals("e")){
                            Warehouse warehouse = Warehouse_Record.get(address);
                            
                            boolean validAttribute = true;
                            do {
                                validAttribute = true;
                                System.out.println("[1] Phone Number \n[2] Storage Capacity \n[3] Drone Capacity \n[4] Manager ");
                                System.out.println("\n Please Enter an integer 1-4 to select attribute to change");

                                String attribute = input.nextLine();

                                switch(attribute){
                                    case "1":
                                        System.out.println("Enter New Phone Number:");
                                        String phone = input.nextLine();
                                        warehouse.phone = phone;
                                        System.out.println("New Phone number for " + warehouse.address + " is " + warehouse.phone);
                                        break;
                                    case "2":
                                        System.out.println("Enter New Storage Capacity:");
                                        String Storage = input.nextLine();
                                        warehouse.storageCapacity = Storage;
                                        System.out.println("New Storage Capacity for " + warehouse.address + " is " + warehouse.storageCapacity);

                                        break;
                                        case "3":
                                        System.out.println("Enter New Drone Capacity:");
                                        String drone = input.nextLine();
                                        warehouse.droneCapacity = drone;
                                        System.out.println("New Drone Capacity for " + warehouse.address + " is " + warehouse.droneCapacity);


                                        break;
                                    case "4":
                                        System.out.println("Enter New Manager Name:");
                                        String manager = input.nextLine();
                                        warehouse.manager = manager;
                                        System.out.println("New Manager for " + warehouse.address + " is " + warehouse.manager);
                                        break;
                                    default:
                                        System.out.println("INVALID INPUT: PLEASE ENTER INTEGER BETWEEN 1-4");
                                        validAttribute = false;

                                }

                            } while(validAttribute == false);


                        } else if( answer.equals("d")){
                            Warehouse_Record.remove(address);
                            System.out.println("Warehouse at address " + address + " has been removed");
                        } else{
                            System.out.println("Invalid entry, try again");
                            validAnswer = false;
                        }

                    } while (validAnswer == false);
                    break;
                case "d":
                    break;
                default:
                    System.out.println("Please Enter a valid character");
                    valid = false;

            }
        } while(valid == false);

    }

    public void editPerson(Scanner input){
        boolean valid = true;
        do {
            System.out.println("Enter 's' to select a record or 'd' to Go Back");
            String selection = input.nextLine();
            switch (selection) {
                case "s":
                    boolean validPerson = false;
                    String phone = "";
                    while(!validPerson) {
                        System.out.println("Enter the Phone Number of the Person");
                        phone = input.nextLine();
                        if(Person_Record.containsKey(phone)){
                            validPerson = true;
                        } else{
                            System.out.println("INVALID PHONE NUMBER: ** TRY AGAIN **");
                        }
                    }

                    boolean validAnswer = true;
                    do{
                        validAnswer = true;
                        System.out.println("Enter 'e' to edit the Person with number " + phone + " or 'd' to delete the Person with number " + phone);
                        String answer = input.nextLine();
                        if(answer.equals("e")){
                            Person person = Person_Record.get(phone);
                            
                            boolean validAttribute = true;
                            do {
                                validAttribute = true;
                                System.out.println("[1] First Name \n[2] Last Name \n[3] Address \n[4] Email ");
                                System.out.println("\n Please Enter an integer 1-4 to select attribute to change");

                                String attribute = input.nextLine();

                                switch(attribute){
                                    case "1":
                                        System.out.println("Enter New First Name:");
                                        String fName = input.nextLine();
                                        person.fName = fName;
                                        System.out.println("New First Name for " + person.phone + " is " + person.fName);
                                        break;
                                    case "2":
                                        System.out.println("Enter New Last Name:");
                                        String lName = input.nextLine();
                                        person.lName = lName;
                                        System.out.println("New First Name for " + person.phone + " is " + person.lName);
                                        break;
                                    case "3":
                                        System.out.println("Enter New Address:");
                                        String address = input.nextLine();
                                        person.address = address;
                                        System.out.println("New Address for " + person.phone + " is " + person.address);
                        
                                        break;
                                    case "4":
                                        System.out.println("Enter New Email:");
                                        String email = input.nextLine();
                                        person.email = email;
                                        System.out.println("New Email for " + person.phone + " is " + person.email);
                        
                                        break;
                                    default:
                                        System.out.println("INVALID INPUT: PLEASE ENTER INTEGER BETWEEN 1-4");
                                        validAttribute = false;

                                }

                            } while(validAttribute == false);


                        } else if( answer.equals("d")){
                            Warehouse_Record.remove(phone);
                            System.out.println("Person with Number " + phone + " has been removed");
                        } else{
                            System.out.println("Invalid entry, try again");
                            validAnswer = false;
                        }

                    } while (validAnswer == false);
                    break;
                case "d":
                    break;
                default:
                    System.out.println("Please Enter a valid character");
                    valid = false;

            }
        } while(valid == false);

    }

    public void editElement(Scanner input){
        boolean valid = true;
        do {
            System.out.println("Enter 's' to select a record or 'd' to Go Back");
            String selection = input.nextLine();
            switch (selection) {
                case "s":
                    boolean validElement = false;
                    String serial = "";
                    while(!validElement) {
                        System.out.println("Enter the Serial Number of the Element");
                        serial = input.nextLine();
                        if(Element_Record.containsKey(serial)){
                            validElement = true;
                        } else{
                            System.out.println("INVALID SERIAL NUMBER: ** TRY AGAIN **");
                        }
                    }

                    boolean validAnswer = true;
                    do{
                        validAnswer = true;
                        System.out.println("Enter 'e' to edit the Element with serial " + serial + " or 'd' to delete the Element with serial " + serial);
                        String answer = input.nextLine();
                        if(answer.equals("e")){
                            Element element = Element_Record.get(serial);
                            
                            boolean validAttribute = true;
                            do {
                                validAttribute = true;
                                System.out.println("[1] Element Name \n[2] Model \n[3] Warranty Expiration ");
                                System.out.println("\n Please Enter an integer 1-4 to select attribute to change");

                                String attribute = input.nextLine();

                                switch(attribute){
                                    case "1":
                                        System.out.println("Enter New Element Name:");
                                        String name = input.nextLine();
                                        element.name = name;
                                        System.out.println("New Element Name for " + element.serialNum + " is " + element.name);
                                        break;
                                    case "2":
                                        System.out.println("Enter New Model Number:");
                                        String model = input.nextLine();
                                        element.modelNum = model;
                                        System.out.println("New Model Number for " + element.serialNum + " is " + element.modelNum);
                                        break;
                                    case "3":
                                        System.out.println("Enter New Warranty Expiration:");
                                        String warranty = input.nextLine();
                                        element.warrantyExpiration = warranty;
                                        System.out.println("New Warranty Expiration for " + element.serialNum + " is " + element.warrantyExpiration);
                                        break;
                                    default:
                                        System.out.println("INVALID INPUT: PLEASE ENTER INTEGER BETWEEN 1-4");
                                        validAttribute = false;

                                }

                            } while(validAttribute == false);


                        } else if( answer.equals("d")){
                            Warehouse_Record.remove(serial);
                            System.out.println("Element with Serial Number " + serial + " has been removed");
                        } else{
                            System.out.println("Invalid entry, try again");
                            validAnswer = false;
                        }

                    } while (validAnswer == false);
                    break;
                case "d":
                    break;
                default:
                    System.out.println("Please Enter a valid character");
                    valid = false;

            }
        } while(valid == false);

    }


}
