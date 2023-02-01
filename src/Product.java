import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Product {
    private String name;
    private Double price;
    private String category;
    private static final List<String> allCategoty = Arrays.asList("computers", "gadgets", "appliances",  "automotive",  "products household goods");
    private static Connection connection = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void infoAllCategoty(){
        allCategoty.forEach(System.out::println);
    }
    public Product(String name, Double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public Product() {
    }
    public static String searchCategory(String category){
        String res = null;
        for( int i=0; i<allCategoty.size(); i++){
            if(category.equals(allCategoty.get(i))) res = allCategoty.get(i);
        }
        if(res==null){
            System.out.println("There is no such category");
        }
        return category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public static void connection(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/marketplace", "postgers", "1079");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void infoByCategory(String category) throws SQLException {
        connection();
        Statement st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM products");
        while (rs.next()){
            if(category.equals(rs.getString("category"))){
                System.out.println("ID: " + rs.getInt("id") + ", name: "
                        + rs.getString("name") + ", price: " + rs.getDouble("price")
                        + ", category: " + rs.getString("category"));
            }
        }
    }
}
