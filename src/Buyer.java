import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Buyer extends User{
    private static Connection connection = null;
    private static PreparedStatement ps = null;;
    private static ResultSet userDatas = null;
    private static ResultSet productDatas = null;
    static Scanner in = new Scanner(System.in);

    public Buyer(int id, String username, String role) {
        super(id, username, role);
    }

    public static void printListOfProducts() throws SQLException {
        connection = DBconnection.connection();
        Statement st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM products");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + ") " + rs.getString("name") + ". Price: " + rs.getInt("price") + "₸" + rs.getArray("category"));
        }
    }

    public static long getAllPrice(ArrayList<Integer> productIDs) throws SQLException {
        long counter = 0;
        connection = DBconnection.connection();
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

    public static void insertOrder(int sellerID, ArrayList<Integer> productsList) throws SQLException {
        connection = DBconnection.connection();
        Integer[] productsIntegerList = new Integer[productsList.size()];
        for (int i = 0; i < productsList.size(); i++) {
            productsIntegerList[i] = productsList.get(i);
        }
        Array productArray = connection.createArrayOf("INTEGER", productsIntegerList);
        ps = connection.prepareStatement("INSERT INTO orders  (seller_id, buyer_id, products) values (?, ? ,?)");
        ps.setInt(1, sellerID);
        ps.setInt(2, User.getCurrentUser().getId());
        ps.setArray(3, productArray);
        ps.execute();
    }

    public static void buyProduct(ArrayList<Integer> productIDs) throws SQLException {
        connection = DBconnection.connection();
        Statement st = connection.createStatement();
        Statement stProducts = connection.createStatement();

        userDatas = st.executeQuery("SELECT * FROM users");

        while (userDatas.next()) {
            ArrayList<Integer> productArray = new ArrayList<>();
            for (int i = 0; i < productIDs.size(); i++) {
                productDatas = stProducts.executeQuery("SELECT * FROM products");

                while (productDatas.next()) {
                    if(productDatas.getInt("id") == productIDs.get(i)) {
                        if(productDatas.getInt("seller_id") == userDatas.getInt("id")) {
                            productArray.add(productIDs.get(i));
                        }
                    }
                }
            }

            if (productArray.isEmpty()) {
                continue;
            }

            insertOrder(userDatas.getInt("id"), productArray);
        }


        ps.execute();
    }

    public static void buyProductMenu() throws SQLException {
        System.out.println("How many products are you want to buy?");
        ArrayList<Integer> idsOfProducts = new ArrayList<>();
        int numberOfProducts = in.nextInt();
        int cnt = 0;
        while (cnt++ != numberOfProducts) {
            System.out.print(cnt + ") ");
            int id = in.nextInt();
            idsOfProducts.add(id);
        }

        Buyer.buyProduct(idsOfProducts);

        System.out.println("All price: " + Buyer.getAllPrice(idsOfProducts));
        System.out.println("Thank you for your purchase!!! \n\n");
    }
}