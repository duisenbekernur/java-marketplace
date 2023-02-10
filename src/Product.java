import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Product {
    private String name;
    private Double price;
    private String category;
    public static final List<String> allCategory = Arrays.asList("computers", "gadgets", "appliances",  "automotive",  "products household goods");
    private static Connection connection = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    static Scanner in = new Scanner(System.in);

    public static void infoAllCategory(){
        for (int i = 0; i<allCategory.size(); i++) {
            System.out.println(i+1 + ") " + allCategory.get(i));
        }
    }
    public Product(String name, Double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public Product() {
    }
    //надо доделать
//    public static String searchCategory(String category){
//        String res = null;
//        for( int i=0; i<allCategoty.size(); i++){
//            if(category.equals(allCategoty.get(i))) res = allCategoty.get(i);
//        }
//        if(res==null){
//            System.out.println("There is no such category");
//        }
//    }

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
//    public static void connection(){
//        try {
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/marketplace", "postgers", "1079");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void addProduct() throws SQLException {
        System.out.println("Write name of the product: ");
        String name = in.next();
        System.out.println("Price: ");
        double price = in.nextDouble();
        System.out.println("Choose the product category: ");
        Product.infoAllCategory();
        int categoryID = in.nextInt();
        Product product = new Product(name, price, Product.allCategory.get(categoryID-1));
        product.insert();
    }

    public void insert() throws SQLException {
        connection = DBconnection.connection();
        ps = connection.prepareStatement("INSERT INTO products(name, price, seller_id, category) VALUES (?, ?, ?, ?)");
        ps.setString(1, getName());
        ps.setDouble(2, getPrice());
        ps.setInt(3, User.getCurrentUser().getId());
        ps.setString(4, getCategory());
        ps.execute();
    }
    public static void infoAllProducts() throws SQLException {
        connection = DBconnection.connection();
        Statement st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM products");
        while (rs.next()){
            System.out.println( rs.getInt("id") + ") "
                    + rs.getString("name") + ", price: " + rs.getDouble("price")
                    + ", category: " + rs.getString("category"));
        }
        System.out.println("\n");
    }
    public static void infoByCategory(String category) throws SQLException {
        connection = DBconnection.connection();
        Statement st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM products WHERE = ?");

        while (rs.next()){
            if(category.equals(rs.getString("category"))){
                System.out.println("ID: " + rs.getInt("id") + ", name: "
                        + rs.getString("name") + ", price: " + rs.getDouble("price")
                        + ", category: " + rs.getString("category"));
            }
        }
    }
}
