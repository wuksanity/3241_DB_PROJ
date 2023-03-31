import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Maintenance_Records {

    public static void insert() {
        boolean again = false;
        do {
            again = false;
            System.out.println(
                    "_________________________________________________________________");
            System.out.println("ADD - Maintenance_Records");

            System.out.println("Enter the information as prompted.");

            System.out.print("Date_time: ");
            String Date_time = App.input.nextLine();

            System.out.print("Description: ");
            String Description = App.input.nextLine();

            System.out.print("Hours: ");
            String Hours = App.input.nextLine();

            System.out.print("Phone: ");
            String Phone = App.input.nextLine();

            System.out.print("Serial_num: ");
            String Serial_num = App.input.nextLine();

            try {
                insertSQL(Date_time, Description, Hours, Phone, Serial_num);
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
            System.out.println("EDIT - Maintenance_Records");

            System.out.println("Enter the information as prompted.");
            System.out.print("Enter the Date_time, Phone, Serial_num: ");
            String maint_key = App.input.nextLine();
            String[] key = maint_key.split("\\s*,\\s*");
            try {

                if (selectSQL(key)) {
                    System.out.println("**Entry Found**");
                    System.out.println("Select Option Below");
                    System.out.println("[1] Edit\n[2] Delete \n[3] Main Menu");

                    System.out.print("Enter Here: ");

                    String selection = App.input.nextLine();

                    switch (selection) {
                        case "1":
                            System.out.println("Select an option to edit");
                            System.out.println(
                                    "[1] Description \n[2] Hours \n");
                            System.out.print("Enter Here: ");
                            selection = App.input.nextLine();
                            switch (selection) {
                                case "1":
                                    System.out.print("New Descripton: ");
                                    String description = App.input.nextLine();
                                    updateSQL(key, description, "Description");
                                    break;
                                case "2":
                                    System.out.print("New Hours: ");
                                    String hours = App.input.nextLine();
                                    updateSQL(key, hours, "Hours");

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
                                            + key + " > ?");
                            System.out.print("Enter Here [y/n]: ");
                            selection = App.input.nextLine();

                            if (selection.equals("y")) {
                                deleteSQL(key);
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
                System.out.println("SEARCH - MAINTENANCE_RECORDS");

                System.out.println("Select Option Below");
                System.out.println(
                        "[1] Search by Due_date, Phone, and Serial_num \n[2] Display all\n[3] Main Menu\n");
                System.out.print("Enter Here: ");

                String selection = App.input.nextLine();

                switch (selection) {
                    case "1":
                        System.out
                                .println("Enter the information as prompted.");
                        System.out.print("Enter the Due_date, Phone, and Serial_num to search for: ");
                        String maint_key = App.input.nextLine();
                        String[] key = maint_key.split("\\s*,\\s*");

                        if (selectSQL(key)) {
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

    public static void insertSQL(String date, String desc, String hrs, String phone, String serial) throws SQLException {
        String sql = "INSERT into MAINTENANCE_RECORD (Date_time, Description, Hours, Phone, Serial_num) values (?, ?, ?, ?, ?);";

        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        stmt.setString(1, date);
        stmt.setString(2, desc);
        stmt.setString(3, hrs);
        stmt.setString(4, phone);
        stmt.setString(5, serial);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateSQL(String[] Key, String value, String attribute) throws SQLException {
        String sql = "UPDATE MAINTENANCE_RECORD SET " + attribute
                + " = ? WHERE Date_time = ? AND Phone = ? AND Serial_num = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, value);
        stmt.setString(2, Key[0]);
        stmt.setString(3, Key[1]);
        stmt.setString(4, Key[2]);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void deleteSQL(String[] Key) throws SQLException {
        String sql = "DELETE FROM MAINTENANCE_RECORD WHERE Date_time = ? AND Phone = ? AND Serial_num = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, Key[0]);
        stmt.setString(2, Key[1]);
        stmt.setString(3, Key[2]);
        

        stmt.executeUpdate();
        stmt.close();
    }

    public static boolean selectSQL(String[] Key) throws SQLException {
        boolean found = true;
        String sql = "SELECT * FROM MAINTENANCE_RECORD WHERE Date_time = ? AND Phone = ? AND Serial_num = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, Key[0]);
        stmt.setString(2, Key[1]);
        stmt.setString(3, Key[2]);
                

        ResultSet results = stmt.executeQuery();
        if (Utilities.displayResults(results) == 0) {
            found = false;
            System.out.println("No Results Found");
        }

        stmt.close();

        return found;
    }

    public static void displayAll() throws SQLException {
        String sql = "SELECT * FROM MAINTENANCE_RECORD;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        Utilities.displayResults(results);
    }

}
