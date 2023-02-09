import java.sql.*;

public class DBconnection {
    private static Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public static final Connection connection() throws SQLException{
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/marketplace","postgres","Ernur2005");
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
