import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class logIn extends User{
    private static Statement statement= null;
    static Scanner in = new Scanner(System.in);
    public logIn(String username, String password, String role) {
        super(username, password, role);
    }
    public static void login() throws SQLException {
        System.out.println("Write username: ");
        String username = in.next();
        System.out.println("Write password: ");
        String pass = in.next();
        searchUser(username, pass);
    }
    public static void searchUser(String name, String pass) throws SQLException {
        DBconnection.connection();
        String role = null;
        rs = statement.executeQuery("SELECT * FROM users");
        while (rs.next()){
            if ((name.equals(rs.getString("username"))) && pass.equals(rs.getString("password"))){
                role = rs.getString("role");
                User.setCurrentUser( new User(rs.getInt("id"), rs.getString("username"), rs.getString("role")));
            }
        }
        if(role==null){
            System.out.println("There is no such user or the wrong password!");
            Main.Menu.loginMethod();
        }
        successfulLogin(role);
    }
    public static void successfulLogin(String role) throws SQLException {
        if (role.equals("seller")) {
            Main.Menu.forTheSeller();
        } else if (role.equals("buyer")) {
            Main.Menu.forTheBuyer();
        }
    }
}
