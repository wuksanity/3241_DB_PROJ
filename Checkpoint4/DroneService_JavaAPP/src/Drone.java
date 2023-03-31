import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Drone {

    public static void insert() {
    	boolean again = false;
        do {
            again = false;
            System.out.println(
                    "_________________________________________________________________");
            System.out.println("ADD - DRONE");

            System.out.println("Enter the information as prompted.");
            
            System.out.print("Volume_capacity: ");
            String volume_capacity = App.input.nextLine();

            System.out.print("Distance_autonomy: ");
            String distance_autonomy = App.input.nextLine();
            
            System.out.print("Max_speed: ");
            String max_speed = App.input.nextLine();
            
            System.out.print("Fleet_id: ");
            String fleet_id = App.input.nextLine();
            
            System.out.print("Serial_num: ");
            String serial_num = App.input.nextLine();

            try {
                insertSQL(volume_capacity,distance_autonomy,max_speed,fleet_id,serial_num);
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
            System.out.println("EDIT - DRONE");

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
                                    "[1] Volume_capacity\n[2] Distance_autonomy \n[3] Max_speed\n[4] Fleet_id");
                            System.out.print("Enter Here: ");
                            selection = App.input.nextLine();
                            switch (selection) {
                                case "1":
                                    System.out.print("New Volume_capacity: ");
                                    String volume_capacity = App.input.nextLine();
                                    updateSQL(serial_num, volume_capacity, "Volume_capacity");
                                    System.out.println("Update Successful");
                                    break;
                                case "2":
                                    System.out.print("New Distance_autonomy: ");
                                    String distance_autonomy = App.input.nextLine();
                                    updateSQL(serial_num, distance_autonomy, "Distance_autonomy");
                                    System.out.println("Update Successful");
                                    break;
                                case "3":
                                    System.out.print("New Max_speed: ");
                                    String max_speed = App.input.nextLine();
                                    updateSQL(serial_num, max_speed,"Max_speed");
                                    System.out.println("Update Successful");
                                    break;
                                case "4":
                                	System.out.print("New Fleet_id: ");
                                	String fleed_id = App.input.nextLine();
                                	updateSQL(serial_num,fleed_id,"Fleed_id");
                                	System.out.println("Update Successful");
                                	break;
                                default:
                                    System.out.println("**Invalid Input < "
                                            + selection
                                            + " >, please enter integer between 1-4**");
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
                System.out.println("SEARCH - DRONE");

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

    public static void insertSQL(String volume_capacity,String distance_autonomy,String max_speed,
    		String fleet_id,String serial_num) throws SQLException {
    	String sql = "INSERT into DRONE (Volume_capacity, Distance_autonomy, Max_speed, Fleet_id, Serial_num) values (?, ?, ?, ?, ?);";

        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        stmt.setString(1, volume_capacity);
        stmt.setString(2, distance_autonomy);
        stmt.setString(3, max_speed);
        stmt.setString(4, fleet_id);
        stmt.setString(5, serial_num);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateSQL(String serial_num, String value, String attribute) throws SQLException {
    	String sql = "UPDATE DRONE SET " + attribute
                + " = ? WHERE Serial_num = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, value);
        stmt.setString(2, serial_num);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void deleteSQL(String serial_num) throws SQLException {
    	String sql = "DELETE FROM DRONE WHERE Serial_num = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, serial_num);

        stmt.executeUpdate();
        stmt.close();
    }

    public static boolean selectSQL(String serial_num) throws SQLException {
    	boolean found = true;
        String sql = "SELECT * FROM DRONE WHERE Serial_num = ?;";
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
    	String sql = "SELECT * FROM DRONE;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        Utilities.displayResults(results);
    }

}
