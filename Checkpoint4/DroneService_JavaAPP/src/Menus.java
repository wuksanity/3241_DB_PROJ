public class Menus {

    public static void mainMenu() {
        String selection;
        System.out.println(
                "_________________________________________________________________");
        System.out.println("MAIN MENU");
        System.out.println("Enter the corresponding number to select option:");
        System.out.println("*Enter \"exit\" to exit program*");
        System.out.println(
                "[1] Add Entry\n[2] Edit/Delete Entry\n[3] Search \n[4] Reports");

        while (true) {
            System.out.print("Enter Here: ");
            selection = App.input.nextLine();
            selection.trim();
            switch (selection) {

                case "1":
                    addMenu();
                    break;
                case "2":
                    editMenu();
                    break;
                case "3":
                    searchMenu();
                    break;
                case "4":
                    reportMenu();
                    break;
                case "exit":
                    App.exitProgram();
                    break;

                default:
                    System.out.println("**Invalid Input < " + selection
                            + " >, please enter integer between 1-4**");
                    break;
            }
        }

    }

    public static void addMenu() {
        String selection;
        System.out.println(
                "_________________________________________________________________");
        System.out.println("ADD ENTITY");
        System.out.println(
                "Enter the corresponding number to select entity to add or go back to main menu:");
        System.out.println("*Enter \"exit\" to exit program*");
        System.out.println(
                "[1] Community\n[2] Warehouse \n[3] Person \n[4] Drone\n[5] Equipment\n[6] Maintenance Record\n[7] Rental\n[8] Review\n[9] Payment\n[10] Order History");
        System.out.println("[0] Go Back\n");

        boolean again = false;
        do {
            again = false;
            System.out.print("Enter Here: ");
            selection = App.input.nextLine();
            selection.trim();
            switch (selection) {

                case "1":
                    Community.insert();
                    break;
                case "2":
                    Warehouse.insert();
                    break;
                case "3":
                    Person.insert();
                    break;
                case "4":
                    Drone.insert();
                    break;
                case "5":
                    Equipment.insert();
                    break;
                case "6":
                    Maintenance_Records.insert();
                    break;
                case "7":
                    Rentals.insert();
                    break;
                case "8":
                    Reviews.insert();
                    break;
                case "9":
                    Payments.insert();
                    break;
                case "10":
                    Order_History.insert();
                    break;
                case "0":
                    mainMenu();
                    break;
                case "exit":
                    App.exitProgram();
                    break;

                default:
                    again = true;
                    System.out.println("**Invalid Input < " + selection
                            + " >, please enter integer between 0-10**");
                    break;
            }
        } while (again);

    }

    public static void editMenu() {
        String selection;
        System.out.println(
                "_________________________________________________________________");
        System.out.println("EDIT ENTITY");
        System.out.println(
                "Enter the corresponding number to select entity to add or go back to main menu:");
        System.out.println("*Enter \"exit\" to exit program*");
        System.out.println(
                "[1] Community\n[2] Warehouse \n[3] Person \n[4] Drone\n[5] Equipment\n[6] Maintenance Record\n[7] Rental\n[8] Review\n[9] Payment\n[10] Order History");
        System.out.println("[0] Go Back\n");

        boolean again = false;
        do {
            again = false;
            System.out.print("Enter Here: ");
            selection = App.input.nextLine();
            selection.trim();
            switch (selection) {

                case "1":
                    Community.edit();
                    break;
                case "2":
                    Warehouse.edit();
                    break;
                case "3":
                    Person.edit();
                    break;
                case "4":
                    Drone.edit();
                    break;
                case "5":
                    Equipment.edit();
                    break;
                case "6":
                    Maintenance_Records.edit();
                    break;
                case "7":
                    Rentals.edit();
                    break;
                case "8":
                    Reviews.edit();
                    break;
                case "9":
                    Payments.edit();
                    break;
                case "10":
                    Order_History.edit();
                    break;
                case "0":
                    mainMenu();
                    break;
                case "exit":
                    App.exitProgram();
                    break;

                default:
                    again = true;
                    System.out.println("**Invalid Input < " + selection
                            + " >, please enter integer between 0-10**");
                    break;
            }
        } while (again);

    }

    public static void searchMenu() {
        String selection;
        System.out.println(
                "_________________________________________________________________");
        System.out.println("SEARCH ENTITY");
        System.out.println(
                "Enter the corresponding number to select entity to search on or go back to main menu:");
        System.out.println("*Enter \"exit\" to exit program*");
        System.out.println(
                "[1] Community\n[2] Warehouse \n[3] Person \n[4] Drone\n[5] Equipment\n[6] Maintenance Record\n[7] Rental\n[8] Review\n[9] Payment\n[10] Order History");
        System.out.println("[0] Go Back\n");

        boolean again = false;
        do {
            again = false;
            System.out.print("Enter Here: ");
            selection = App.input.nextLine();
            selection.trim();
            switch (selection) {

                case "1":
                    Community.search();
                    break;
                case "2":
                    Warehouse.search();
                    break;
                case "3":
                    Person.search();
                    break;
                case "4":
                    Drone.search();
                    break;
                case "5":
                    Equipment.search();
                    break;
                case "6":
                    Maintenance_Records.search();
                    break;
                case "7":
                    Rentals.search();
                    break;
                case "8":
                    Reviews.search();
                    break;
                case "9":
                    Payments.search();
                    break;
                case "10":
                    Order_History.search();
                    break;
                case "0":
                    mainMenu();
                    break;
                case "exit":
                    App.exitProgram();
                    break;

                default:
                    again = true;
                    System.out.println("**Invalid Input < " + selection
                            + " >, please enter integer between 0-10**");
                    break;
            }
        } while (again);

    }

    public static void reportMenu() {
        String selection;
        System.out.println(
                "_________________________________________________________________");
        System.out.println("REPORTS");
        System.out.println(
                "Enter the corresponding number to select report or go back to main menu:");
        System.out.println("*Enter \"exit\" to exit program*");

        System.out.println(
                "[1] Renting checkouts: Find the total number of equipment items rented by a single member patron");
        System.out.println(
                "[2] Popular item: Find the most popular item in the database");
        System.out.println(
                "[3] Popular Manufacturer: Find the most frequent equipment manufacturer in the database");
        System.out.println(
                "[4] Popular Drone: Find the most used drone in the database");
        System.out.println(
                "[5] Items checked out: Find the member who has rented out the most items and the total number of items they have rented out.");
        System.out.println(
                "[6] Equipment by Type of equipment: Find the description (name) of equipment by type released before YEAR.");
        System.out.println("[0] Main Menu");

        boolean again = false;
        do {
            again = false;
            System.out.print("Enter Here: ");
            selection = App.input.nextLine();
            selection.trim();
            switch (selection) {

                case "1":
                    Reports.Renting_Checkouts();
                    break;
                case "2":
                    Reports.Popular_Item();
                    break;
                case "3":
                    Reports.Popular_Manufacturer();
                    break;
                case "4":
                    Reports.Popular_Drone();
                    break;
                case "5":
                    Reports.Items_Checked_Out();
                    break;
                case "6":
                    Reports.Equipment_Before_Year();
                    break;
                case "0":
                    mainMenu();
                    break;
                case "exit":
                    App.exitProgram();
                    break;

                default:
                    again = true;
                    System.out.println("**Invalid Input < " + selection
                            + " >, please enter integer between 0-6**");
                    break;
            }
        } while (again);

    }

}
