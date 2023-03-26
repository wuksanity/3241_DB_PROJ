import java.util.ArrayList;

public class Community {
    
    // class variables
    String city;
    String joinCode;
    ArrayList<Person> members;
    Warehouse warehouse;

    public Community(String location, String code) {
        city = location;
        joinCode = code;
    }

    public void updateJoinCode(String newCode) {
        joinCode = newCode;
    }

    public String showCity() {
        return city;
    }

    public String showJoinCode() {
        return joinCode;
    }

    public ArrayList<Person> showMembers() {
        return members;
    }

}
