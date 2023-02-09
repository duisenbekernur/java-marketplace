import java.sql.*;

public class Seller extends User {
    public Seller(String username, String password, String role) {
        super(username, password, role);
    }
    PreparedStatement ps = null;
     Connection conn = null;
     ResultSet rs = null;

    public void addProduct(Product product) throws SQLException{
        conn = DBconnection.connection();

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

    @Override
    public void showOwnProducts() {
        try {
            conn = DBconnection.connection();
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM products");
            while(rs.next()){
                if(rs.getInt("id") == User.getCurrentUser().getId()) {
                    System.out.println("Category: " + rs.getString("category") + "ID: " + rs.getInt("id") + ", name: "
                            + rs.getString("name")  + ", price: " + rs.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    };
}
