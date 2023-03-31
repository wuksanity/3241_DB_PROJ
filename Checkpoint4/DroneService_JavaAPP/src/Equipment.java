import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Equipment {

    public static void insert() {
    	boolean again = false;
        do {
            again = false;
            System.out.println(
                    "_________________________________________________________________");
            System.out.println("ADD - EQUIPMENT");

            System.out.println("Enter the information as prompted.");
            
            System.out.print("Equipment_type: ");
            String equipment_type = App.input.nextLine();

            System.out.print("Weight: ");
            String weight = App.input.nextLine();
            
            System.out.print("Arrival_date: ");
            String arrival_date = App.input.nextLine();
            
            System.out.print("Size: ");
            String size = App.input.nextLine();
            
            System.out.print("Inventory: ");
            String inventory = App.input.nextLine();
            
            System.out.print("Serial_num: ");
            String serial_num = App.input.nextLine();

            try {
                insertSQL(equipment_type,weight,arrival_date,size,inventory,serial_num);
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
            System.out.println("EDIT - EQUIPMENT");

            System.out.println("Enter the information as prompted.");
            System.out.print("Enter the Serial_num: ");
            String serial_num = App.input.nextLine().trim();
            try {

                if (selectSQL(serial_num)) {
                    System.out.println("**Entry Found**");
                    System.out.println("Select Option Below");
                    System.out.println("[1] Edit\n[2] Delete \n[3] Main Menu");

                    System.out.print("Enter Here: ");

                    String selection = App.input.nextLine();

                    switch (selection) {
                        case "1":
                            System.out.println("Select an option to edit");
                            System.out.println(
                                    "[1] Equipment_type\n[2] Weight \n[3] Arrival_date \n[4] Size \n[5] Inventory");
                            System.out.print("Enter Here: ");
                            selection = App.input.nextLine();
                            switch (selection) {
                                case "1":
                                    System.out.print("New Equipment_type: ");
                                    String equipment_type = App.input.nextLine();
                                    updateSQL(serial_num, equipment_type, "Equipment_type");
                                    System.out.println("Update Successful");
                                    break;
                                case "2":
                                    System.out.print("New Weight: ");
                                    String weight = App.input.nextLine();
                                    updateSQL(serial_num, weight, "Weight");
                                    System.out.println("Update Successful");
                                    break;
                                case "3":
                                    System.out.print("New Arrival_date: ");
                                    String arrival_date = App.input.nextLine();
                                    updateSQL(serial_num, arrival_date, "Arrive_date");
                                    System.out.println("Update Successful");
                                    break;
                                case "4":
                                	System.out.print("New Size:");
                                	String size = App.input.nextLine();
                                	updateSQL(serial_num, size, "Size");
                                	System.out.println("Update Successful");
                                	break;
                                case "5":
                                	System.out.print("New Inventory: ");
                                	String inventory = App.input.nextLine();
                                	updateSQL(serial_num, inventory, "Inventory");
                                	System.out.println("Update Successful");
                                	break;
                                default:
                                    System.out.println("**Invalid Input < "
                                            + selection
                                            + " >, please enter integer between 1-5**");
                                    again = true;
                                    break;
                            }

                            break;
                        case "2":
                            System.out.println(
                                    "Are you sure you want to delete < "
                                            + serial_num + " > ?");
                            System.out.print("Enter Here [y/n]: ");
                            selection = App.input.nextLine();

                            if (selection.equals("y")) {
                                deleteSQL(serial_num);
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
                System.out.println("SEARCH - EQUIPMENT");

                System.out.println("Select Option Below");
                System.out.println(
                        "[1] Search by Serial_num\n[2] Display all\n[3] Main Menu\n");
                System.out.print("Enter Here: ");

                String selection = App.input.nextLine();

                switch (selection) {
                    case "1":
                        System.out
                                .println("Enter the information as prompted.");
                        System.out.print("Enter the Serial_num to search for: ");
                        String serial_num = App.input.nextLine().trim();

                        if (selectSQL(serial_num)) {
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

    public static void insertSQL(String equipment_type,String weight,String arrival_date,String size,
    		String inventory_id,String serial_num) throws SQLException {
    	String sql = "INSERT into EQUIPMENT (Equipment_type, Weight, Arrival_date, Size, Inventory_id, Serial_num) values (?, ?, ?, ?, ?, ?);";

        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        stmt.setString(1, equipment_type);
        stmt.setString(2, weight);
        stmt.setString(3, arrival_date);
        stmt.setString(4, size);
        stmt.setString(5, inventory_id);
        stmt.setString(6, serial_num);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateSQL(String serial_num, String value, String attribute) throws SQLException {
    	String sql = "UPDATE EQUIPMENT SET " + attribute
                + " = ? WHERE Serial_num = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, value);
        stmt.setString(2, serial_num);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void deleteSQL(String serial_num) throws SQLException {
    	String sql = "DELETE FROM EQUIPMENT WHERE Serial_num = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, serial_num);

        stmt.executeUpdate();
        stmt.close();
    }

    public static boolean selectSQL(String serial_num) throws SQLException {
    	boolean found = true;
        String sql = "SELECT * FROM EQUIPMENT WHERE Serial_num = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, serial_num);

        ResultSet results = stmt.executeQuery();
        if (Utilities.displayResults(results) == 0) {
            found = false;
            System.out.println("No Results Found");
        }

        stmt.close();

        return found;
    }

    public static void displayAll() throws SQLException {
    	String sql = "SELECT * FROM EQUIPMENT;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        Utilities.displayResults(results);
    }

}
