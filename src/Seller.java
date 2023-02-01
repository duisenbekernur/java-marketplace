import java.sql.*;

public class Seller extends User {
    public Seller(String username, String password, String role) {
        super(username, password, role);
    }
    PreparedStatement ps = null;
    Connection conn = null;
    ResultSet rs = null;

    public void addProduct(Product product) throws SQLException{
        DBconnection.connection();

        try{
            ps = conn.prepareStatement("INSERT INTO products (name,price,category) VALUES (?,?,?)");
            ps.setString(1,product.getName());
            ps.setDouble(2,product.getPrice());
            ps.setString(3,product.getCategory());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException();
        }

    };

    public void showOwnProducts() throws SQLException{
        DBconnection.connection();
        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM products");
        while(rs.next()){
            System.out.println("Category: " + rs.getString("category") + "ID: " + rs.getInt("id") + ", name: "
                    + rs.getString("name")  + ", price: " + rs.getDouble("price"));
        }
    };
}
