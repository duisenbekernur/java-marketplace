import java.sql.*;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    static Connection connection = null;
    PreparedStatement ps =null;
    static ResultSet rs = null;
    private static User currentUser = null;
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
       currentUser = user;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//    public User() {
//    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Username: " + getUsername() + ", role: " + getRole();
    }
    public static void connection(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/marketplace", "postgers", "1079");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insert() throws SQLException {
        connection();
        ps = connection.prepareStatement("INSERT INTO users (username, password, role) values (?, ? ,?)");
        ps.setString(1, getUsername());
        ps.setString(2, getPassword());
        ps.setString(3, getRole());
        ps.execute();
    }
    public static int getIdCurrencyUser() throws SQLException {
        connection();
        int id = 0;
        Statement st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM users");
        while (rs.next()){
            if(getCurrentUser().getUsername().equals(rs.getString("username")) && getCurrentUser().getPassword().equals(rs.getString("password")) && getCurrentUser().getRole().equals(rs.getString("role"))){
                id = rs.getInt("id");
            }
        }
        return id;
    }
}
