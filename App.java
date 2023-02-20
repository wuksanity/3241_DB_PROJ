import java.util.*;

public class App {

    public static void main(String[] args) throws Exception {


        Scanner input = new Scanner(System.in);
        System.out.println("Programming running...press (q) at any time to quit.\n");
        Person p = new Person("3304018119", "Walker", "Riley", "293 E 17 Ave", "w@gmail.com");
        Member m = new Member("3304018119", "Walker", "Riley", "293 E 17 Ave", "w@gmail.com", 1, 20.5, "1/1/2023");
        p.updateEmail("pp");
        m.updateEmail("mm");
        System.out.println(m.showEmail());

        input.close();

    }

}