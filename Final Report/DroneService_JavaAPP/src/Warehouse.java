import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Warehouse {

    public static void insert() {
        boolean again = false;
        do {
            again = false;
            System.out.println(
                    "_________________________________________________________________");
            System.out.println("ADD - WAREHOUSE");

            System.out.println("Enter the information as prompted.");

            System.out.print("Address: ");
            String address = App.input.nextLine();

            System.out.print("City: ");
            String city = App.input.nextLine();

            System.out.print("Phone: ");
            String phone = App.input.nextLine();

            System.out.print("Manager: ");
            String manager = App.input.nextLine();

            System.out.print("Storage Capacity: ");
            String storage = App.input.nextLine();

            try {
                insertSQL(address, city, phone, manager, storage);
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
            System.out.println("EDIT - WAREHOUSE");

            System.out.println("Enter the information as prompted.");
            System.out.print("Enter the Address: ");
            String address = App.input.nextLine().trim();
            try {

                if (selectSQL(address)) {
                    System.out.println("**Entry Found**");
                    System.out.println("Select Option Below");
                    System.out.println("[1] Edit\n[2] Delete \n[3] Main Menu");

                    System.out.print("Enter Here: ");

                    String selection = App.input.nextLine();

                    switch (selection) {
                        case "1":
                            System.out.println("Select an option to edit");
                            System.out.println(
                                    "[1] Phone\n[2] Manager \n[3] Storage Capacity");
                            System.out.print("Enter Here: ");
                            selection = App.input.nextLine();
                            switch (selection) {
                                case "1":
                                    System.out.print("New Phone: ");
                                    String phone = App.input.nextLine();
                                    updateSQL(address, phone, "Phone");
                                    System.out.println("Update Successful");
                                    break;
                                case "2":
                                    System.out.print("New Manager name: ");
                                    String manager = App.input.nextLine();
                                    updateSQL(address, manager, "Manager_name");
                                    System.out.println("Update Successful");
                                    break;
                                case "3":
                                    System.out.print("New Storage Capacity: ");
                                    String storage = App.input.nextLine();
                                    updateSQL(address, storage,
                                            "Storage_capacity");
                                    System.out.println("Update Successful");
                                    break;
                                default:
                                    System.out.println("**Invalid Input < "
                                            + selection
                                            + " >, please enter integer between 1-3**");
                                    again = true;
                                    break;
                            }

                            break;
                        case "2":
                            System.out.println(
                                    "Are you sure you want to delete < "
                                            + address + " > ?");
                            System.out.print("Enter Here [y/n]: ");
                            selection = App.input.nextLine();

                            if (selection.equals("y")) {
                                deleteSQL(address);
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
                System.out.println("SEARCH - WAREHOUSE");

                System.out.println("Select Option Below");
                System.out.println(
                        "[1] Search by Address\n[2] Display all\n[3] Main Menu\n");
                System.out.print("Enter Here: ");

                String selection = App.input.nextLine();

                switch (selection) {
                    case "1":
                        System.out
                                .println("Enter the information as prompted.");
                        System.out.print("Enter the Address to search for: ");
                        String address = App.input.nextLine().trim();

                        if (selectSQL(address)) {
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

    public static void insertSQL(String address, String city, String phone,
            String manager, String storage) throws SQLException {
        String sql = "INSERT into WAREHOUSE (City, Address, Phone, Manager_name, Storage_capacity) values (?, ?, ?, ?, ?);";

        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        stmt.setString(1, city);
        stmt.setString(2, address);
        stmt.setString(3, phone);
        stmt.setString(4, manager);
        stmt.setString(5, storage);

        stmt.executeUpdate();
        stmt.close();

    }

    public static void updateSQL(String address, String value, String attribute)
            throws SQLException {
        String sql = "UPDATE WAREHOUSE SET " + attribute
                + " = ? WHERE Address = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, value);
        stmt.setString(2, address);

        stmt.executeUpdate();
        stmt.close();

    }

    public static void deleteSQL(String address) throws SQLException {
        String sql = "DELETE FROM WAREHOUSE WHERE Address = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, address);

        stmt.executeUpdate();
        stmt.close();

    }

    public static boolean selectSQL(String address) throws SQLException {
        boolean found = true;
        String sql = "SELECT * FROM WAREHOUSE WHERE Address = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, address);

        ResultSet results = stmt.executeQuery();
        if (Utilities.displayResults(results) == 0) {
            found = false;
            System.out.println("No Results Found");
        }

        stmt.close();

        return found;
    }

    public static void displayAll() throws SQLException {
        String sql = "SELECT * FROM WAREHOUSE;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        Utilities.displayResults(results);

    }
}
