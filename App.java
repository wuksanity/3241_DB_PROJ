import java.util.*;
import java.util.Scanner;


public class App {

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Equipment Rental Program");

        Session session = new Session(); //new session object
        session.displayStart();
        String selection;
        boolean valid = true;

        while (valid) {
            selection = input.nextLine();
            if (selection.equals("a")) { //add 
                session.displayAdd(input);
                session.displayStart();
            } else if (selection.equals("b")) { //view and edit
                session.displaySearch();
                session.displayStart();
            } else if (selection.equals("q")) { //stop program
                valid = false;
            } else {
                System.out.println("Please enter a valid input");
            }
        }

        System.out.println("End Session");
        input.close();

    }

}