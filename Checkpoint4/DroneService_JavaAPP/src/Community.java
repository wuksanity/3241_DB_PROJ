import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Community {

    public static void insert() {
        boolean again = false;
        do {
            again = false;
            System.out.println(
                    "_________________________________________________________________");
            System.out.println("ADD - COMMUNITY");

            System.out.println("Enter the information as prompted.");
            System.out.print("City: ");
            String city = App.input.nextLine();

            System.out.print("Join_Code: ");
            String code = App.input.nextLine();

            try {
                insertSQL(city, code);
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
            System.out.println("EDIT - COMMUNITY");

            System.out.println("Enter the information as prompted.");
            System.out.print("Enter the City: ");
            String city = App.input.nextLine().trim();
            try {

                if (selectSQL(city)) {
                    System.out.println("**Entry Found**");
                    System.out.println("Select Option Below");
                    System.out.println("[1] Edit\n[2] Delete \n[3] Main Menu");

                    System.out.print("Enter Here: ");

                    String selection = App.input.nextLine();

                    switch (selection) {
                        case "1":
                            System.out.print("New Join Code: ");
                            String code = App.input.nextLine();

                            updateSQL(city, code);
                            System.out.println("Update Successful");
                            System.out.println(
                                    "Would you like to update another?");
                            System.out.print("Enter Here [y/n]: ");
                            selection = App.input.nextLine();
                            if (selection.equals("y")) {
                                again = true;
                            }

                            break;
                        case "2":
                            System.out.println(
                                    "Are you sure you want to delete < " + city
                                            + " > ?");
                            System.out.print("Enter Here [y/n]: ");
                            selection = App.input.nextLine();

                            if (selection.equals("y")) {
                                deleteSQL(city);
                                System.out.println("Delete Successful");
                            }

                            System.out.println(
                                    "Would you like to update another?");
                            System.out.print("Enter Here [y/n]: ");
                            selection = App.input.nextLine();
                            if (selection.equals("y")) {
                                again = true;
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
                System.out.println("SEARCH - COMMUNITY");

                System.out.println("Select Option Below");
                System.out.println(
                        "[1] Search by name\n[2] Display all\n[3] Main Menu\n");
                System.out.print("Enter Here: ");

                String selection = App.input.nextLine();

                switch (selection) {
                    case "1":
                        System.out
                                .println("Enter the information as prompted.");
                        System.out.print("Enter the City to search for: ");
                        String city = App.input.nextLine().trim();

                        if (selectSQL(city)) {
                            System.out.println("**Entry Found**");
                        } else {
                            System.out.println("ERROR: Entry NOT Found");
                        }
                        System.out.println("Would you like to Search again?");
                        System.out.print("Enter Here [y/n]: ");
                        selection = App.input.nextLine();
                        if (selection.equals("y")) {
                            again = true;
                        }

                        break;
                    case "2":
                        displayAll();
                        System.out.println("Would you like to search again?");
                        System.out.print("Enter Here [y/n]: ");
                        selection = App.input.nextLine();
                        if (selection.equals("y")) {
                            again = true;
                        }

                        break;
                    case "3":
                        Menus.mainMenu();
                        break;
                    default:
                        System.out.println("**Invalid Input < " + selection
                                + " >, please enter integer between 1-3**");
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

    public static void insertSQL(String city, String code) throws SQLException {
        String sql = "INSERT into COMMUNITY (City, Join_code) values (?, ?);";

        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        stmt.setString(1, city);
        stmt.setString(2, code);

        stmt.executeUpdate();
        stmt.close();

    }

    public static void updateSQL(String city, String code) throws SQLException {
        String sql = "UPDATE COMMUNITY SET Join_code = ? WHERE City = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, code);
        stmt.setString(2, city);

        stmt.executeUpdate();
        stmt.close();

    }

    public static void deleteSQL(String city) throws SQLException {
        String sql = "DELETE FROM COMMUNITY WHERE City = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, city);

        stmt.executeUpdate();
        stmt.close();

    }

    public static boolean selectSQL(String city) throws SQLException {
        boolean found = true;
        String sql = "SELECT * FROM COMMUNITY WHERE City = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, city);

        ResultSet results = stmt.executeQuery();
        if (Utilities.displayResults(results) == 0) {
            found = false;
            System.out.println("No Results Found");
        }

        stmt.close();

        return found;
    }

    public static void displayAll() throws SQLException {
        String sql = "SELECT * FROM COMMUNITY;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        Utilities.displayResults(results);

    }

}
