import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person {

    public static void insert() {
        boolean again = false;
        do {
            again = false;
            System.out.println(
                    "_________________________________________________________________");
            System.out.println("ADD - PERSON");

            System.out.println("Enter the information as prompted.");

            System.out.print("First_name: ");
            String First_name = App.input.nextLine();

            System.out.print("Last_name: ");
            String Last_name = App.input.nextLine();

            System.out.print("Home_address: ");
            String Home_address = App.input.nextLine();

            System.out.print("Phone: ");
            String Phone = App.input.nextLine();

            System.out.print("Email: ");
            String Email = App.input.nextLine();
            
            System.out.print("Employee_id: ");
            String Employee_id = App.input.nextLine();

            System.out.print("User_id: ");
            String User_id = App.input.nextLine();
            
            System.out.print("Emp_Start_Date: ");
            String Emp_Start_Date = App.input.nextLine();

            System.out.print("Wage: ");
            String Wage = App.input.nextLine();

            System.out.print("is_Emp: ");
            String is_Emp = App.input.nextLine();

            System.out.print("is_Mem: ");
            String is_Mem = App.input.nextLine();

            System.out.print("Warehouse_distance: ");
            String Warehouse_distance = App.input.nextLine();

            System.out.print("Activated: ");
            String Activated = App.input.nextLine();

            System.out.print("Start_Date: ");
            String Start_Date = App.input.nextLine();

            System.out.print("City: ");
            String City = App.input.nextLine();

            System.out.print("Address: ");
            String Address = App.input.nextLine();

            try {
                insertSQL(First_name, Last_name, Home_address, Phone, Email, Employee_id, User_id, Emp_Start_Date, Wage, is_Emp, is_Mem, Warehouse_distance, Activated, Start_Date, City, Address);
                System.out.println("Insertion Successful");
                System.out.println("Would you like to add another?");
                System.out.print("Enter Here [y/n]: ");
                String selection = App.input.nextLine();
                if (selection.equals("y")) {
                    again = true;
                }

            } catch (SQLException e) {
                System.out.println("**INSERTION FAILED**");
                System.out.println("Error Message: " + e);
                again = true;

            }
        } while (again);

        Menus.mainMenu();
    }

    public static void edit() {
        boolean again = false;
        do {
            again = false;
            System.out.println(
                    "_________________________________________________________________");
            System.out.println("EDIT - PERSON");

            System.out.println("Enter the information as prompted.");
            System.out.print("Enter the Phone Number: ");
            String phone = App.input.nextLine().trim();
            try {

                if (selectSQL(phone)) {
                    System.out.println("**Entry Found**");
                    System.out.println("Select Option Below");
                    System.out.println("[1] Edit\n[2] Delete \n[3] Main Menu");

                    System.out.print("Enter Here: ");

                    String selection = App.input.nextLine();

                    switch (selection) {
                        case "1":
                            System.out.println("Select an option to edit");
                            System.out.println(
                                    "[1] Home_Address \n[2] Phone \n[3] Email \n[4] Wage \n[5] is_Emp \n[6] is_Mem \n[7] Warehouse_distance \n[8] Activated \n[9] City \n[10] Address");
                            System.out.print("Enter Here: ");
                            selection = App.input.nextLine();
                            switch (selection) {
                                case "1":
                                    System.out.print("New Home_Address: ");
                                    String address = App.input.nextLine();
                                    updateSQL(phone, address, "Home_address");
                                    break;
                                case "2":
                                    System.out.print("New Phone Number: ");
                                    String phone_num = App.input.nextLine();
                                    updateSQL(phone, phone_num, "Phone");

                                    break;
                                case "3":
                                    System.out.print("New Email: ");
                                    String email = App.input.nextLine();
                                    updateSQL(phone, email,"Email");

                                    break;
                                case "4":
                                    System.out.print("New Wage: ");
                                    String wage = App.input.nextLine();
                                    updateSQL(phone, wage, "Wage");

                                    break;
                                case "5":
                                    System.out.print("New Employee Status: ");
                                    String is_emp = App.input.nextLine();
                                    updateSQL(phone, is_emp, "is_Emp");

                                    break;
                                case "6":
                                    System.out.print("New Membership Status: ");
                                    String is_mem = App.input.nextLine();
                                    updateSQL(phone, is_mem, "is_Mem");

                                    break;
                                case "7":
                                    System.out.print("New Warehouse_distance: ");
                                    String warehouse_distance = App.input.nextLine();
                                    updateSQL(phone, warehouse_distance, "Warehouse_distance");

                                    break;
                                case "8":
                                    System.out.print("New Activation Status: ");
                                    String activated = App.input.nextLine();
                                    updateSQL(phone, activated, "Activated");

                                    break;
                                case "9":
                                    System.out.print("New City: ");
                                    String city = App.input.nextLine();
                                    updateSQL(phone, city, "City");

                                    break;
                                case "10":
                                    System.out.print("New Address: ");
                                    String addr = App.input.nextLine();
                                    updateSQL(phone, addr, "Address");

                                    break;
                                default:
                                    System.out.println("**Invalid Input < "
                                            + selection
                                            + " >, please enter integer between 1-3**");
                                    again = true;
                                    break;
                            }
                            System.out.println("Update Successful");

                            break;
                        case "2":
                            System.out.println(
                                    "Are you sure you want to delete < "
                                            + phone + " > ?");
                            System.out.print("Enter Here [y/n]: ");
                            selection = App.input.nextLine();

                            if (selection.equals("y")) {
                                deleteSQL(phone);
                                System.out.println("Delete Successful");
                            }

                            break;
                        case "3":
                            Menus.mainMenu();

                            break;
                        default:
                            System.out.println("**Invalid Input < " + selection
                                    + " >, please enter integer between 1-3**");
                            again = true;
                            break;

                    }

                    System.out.println("Would you like to update another?");
                    System.out.print("Enter Here [y/n]: ");
                    selection = App.input.nextLine();
                    if (selection.equals("y")) {
                        again = true;
                    }

                } else {
                    System.out.println("ERROR: Entry NOT Found");
                    System.out.println("Would you like to try again?");
                    System.out.print("Enter Here [y/n]: ");
                    String selection = App.input.nextLine();
                    if (selection.equals("y")) {
                        again = true;
                    }
                }
            } catch (SQLException e) {
                System.out.println("**DATABASE UPDATE FAILED**");
                System.out.println("Error Message: " + e);
                again = true;

            }
        } while (again);

        Menus.mainMenu();
    }

    public static void search() {
        boolean again = false;

        do {
            try {
                again = false;
                System.out.println(
                        "_________________________________________________________________");
                System.out.println("SEARCH - PERSON");

                System.out.println("Select Option Below");
                System.out.println(
                        "[1] Search by Phone\n[2] Display all\n[3] Main Menu\n");
                System.out.print("Enter Here: ");

                String selection = App.input.nextLine();

                switch (selection) {
                    case "1":
                        System.out
                                .println("Enter the information as prompted.");
                        System.out.print("Enter the Phone to search for: ");
                        String phone = App.input.nextLine().trim();

                        if (selectSQL(phone)) {
                            System.out.println("**Entry Found**");
                        } else {
                            System.out.println("ERROR: Entry NOT Found");
                        }

                        break;
                    case "2":
                        displayAll();

                        break;
                    case "3":
                        Menus.mainMenu();
                        break;
                    default:
                        System.out.println("**Invalid Input < " + selection
                                + " >, please enter integer between 1-3**");
                        again = true;

                }

                System.out.println("Would you like to Search again?");
                System.out.print("Enter Here [y/n]: ");
                selection = App.input.nextLine();
                if (selection.equals("y")) {
                    again = true;
                }

            } catch (SQLException e) {
                System.out.println("**DATABASE SEARCH FAILED**");
                System.out.println("Error Message: " + e);
                again = true;
            }
        } while (again);
        Menus.mainMenu();
    }

    public static void insertSQL(String first, String last, String home_add, String phone_num, String email, String emp_id, String user_id, String emp_start, String wage, String is_emp, String is_mem, String warehouse_dist, String activated, String start_date, String city, String addr) throws SQLException {
        String sql = "INSERT into PERSON (First_name, Last_name, Home_address, Phone, Email, Employee_id, User_id, Emp_Start_Date, Wage, is_Emp, is_Mem, Warehouse_distance, Activated, Start_Date, City, Address) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        stmt.setString(1, first);
        stmt.setString(2, last);
        stmt.setString(3, home_add);
        stmt.setString(4, phone_num);
        stmt.setString(5, email);
        stmt.setString(6, emp_id);
        stmt.setString(7, user_id);
        stmt.setString(8, emp_start);
        stmt.setString(9, wage);
        stmt.setString(10, is_emp);
        stmt.setString(11, is_mem);
        stmt.setString(12, warehouse_dist);
        stmt.setString(13, activated);
        stmt.setString(14, start_date);
        stmt.setString(15, city);
        stmt.setString(16, addr);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateSQL(String phone, String value, String attribute) throws SQLException {
        String sql = "UPDATE PERSON SET " + attribute
                + " = ? WHERE Phone = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, value);
        stmt.setString(2, phone);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void deleteSQL(String phone) throws SQLException {
        String sql = "DELETE FROM PERSON WHERE Phone = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, phone);

        stmt.executeUpdate();
        stmt.close();
    }

    public static boolean selectSQL(String phone) throws SQLException {
        boolean found = true;
        String sql = "SELECT * FROM PERSON WHERE Phone = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, phone);

        ResultSet results = stmt.executeQuery();
        if (Utilities.displayResults(results) == 0) {
            found = false;
            System.out.println("No Results Found");
        }

        stmt.close();

        return found;
    }

    public static void displayAll() throws SQLException {
        String sql = "SELECT * FROM PERSON;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        Utilities.displayResults(results);
    }

}
