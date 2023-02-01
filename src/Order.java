import java.sql.*;
import java.util.ArrayList;

public class Order {
    private int id;
    private int buyerId;
    private int sellerId;
    private int[] productIds;
    private static Connection connection = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;


    public Array getListOfProducts(int sellerId, int buyerId) throws SQLException {
        connection();
        Statement st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM orders");
        while (rs.next()) {
            if (rs.getInt("sellerID") == sellerId && rs.getInt("buyerID") == buyerId) {
                return rs.getArray("products");
            }
        }

        return null;
    }

    public static void connection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/marketplace", "postgers", "1079");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
