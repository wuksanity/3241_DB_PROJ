import java.util.Scanner;

public class App {

    public static Scanner input = new Scanner(System.in);

    public static void exitProgram() {
        DBConnection.close();
        System.out.println("Ending Session...");
        System.exit(0);

    }

    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to the Equipment Rental Program!");
        //implement some sort of sign in?

        DBConnection.getConnection();
        Menus.mainMenu();

        exitProgram();
    }

}
