import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to the Equipment Rental Program");

        String selection;
        boolean valid = true;

        DBConnection.getConnection();
        Menus.mainMenu();

        try {
            String sql = "INSERT into COMMUNITY (City, Join_code) values (?, ?)";

            PreparedStatement stmt = DBConnection.getConnection()
                    .prepareStatement(sql);
            stmt.setString(1, "Houston");
            stmt.setString(2, "12345");

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        DBConnection.close();
        System.out.println("End Session");

    }

}
