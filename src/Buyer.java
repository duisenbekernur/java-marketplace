import java.sql.*;
import java.util.ArrayList;

public class Buyer extends User{
    private static Connection connection = null;
    private static PreparedStatement ps = null;
    private static ResultSet userDatas = null;;
    private static ResultSet productDatas = null;

    public Buyer(int id, String username, String role) {
        super(id, username, role);
    }

    public void printListOfProducts() throws SQLException {
        connection();
        Statement st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM products");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + ") " + rs.getString("name") + ". Price: " + rs.getInt("price") + "₸" + rs.getArray("category"));
        }
    }

    public long getAllPrice(ArrayList<Integer> productIDs) throws SQLException {
        long counter = 0;
        connection();
        Statement st = connection.createStatement();
        productDatas = st.executeQuery("SELECT * from products");
        while (productDatas.next()) {
            for (int i = 0; i < productIDs.size(); i++) {
                if(productDatas.getInt("id") == productIDs.get(i)) {
                    counter += productDatas.getInt("price");
                }
            }
        }

        return counter;
    }

    public void insertOrder(int sellerID, ArrayList<Integer> productsList) throws SQLException {
        connection();
        ps = connection.prepareStatement("INSERT INTO orders (sellerID, buyerID, products) values (?, ? ,?)");
        ps.setInt(1, sellerID);
        ps.setInt(2, User.getCurrentUser().getId());
        ps.setArray(3, (Array) productsList);
        ps.execute();
    }

    public void buyProduct(ArrayList<Integer> productIDs) throws SQLException {
        connection();
        Statement st = connection.createStatement();
        userDatas = st.executeQuery("SELECT * FROM users");
        productDatas = st.executeQuery("SELECT * FROM products");

        while (userDatas.next()) {
            ArrayList<Integer> productArray = new ArrayList<>();

            for (int i = 0; i < productIDs.size(); i++) {
//                int currentProfuctID = productDatas.getInt("id");
                while (productDatas.next()) {
                    if(productDatas.getInt("id") == productIDs.get(i)) {
                        if(productDatas.getInt("sellerID") == userDatas.getInt("id")) {
                            productArray.add(productIDs.get(i));
                        }
                    }
                }

                insertOrder(userDatas.getInt("id"), productArray);
            }
        }

        ps = connection.prepareStatement("INSERT INTO orders (sellerID, buyerID, products) values (,  , products)");
        ps.execute();
    }

    public static void connection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/marketplace", "postgers", "1079");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}