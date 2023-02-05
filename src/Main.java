import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws SQLException {
        Menu.loginMethod();
    }

    static class Menu{
        static Scanner in= new Scanner(System.in);
        public static void loginMethod() throws SQLException {
            System.out.println("""
                    1) Log in
                    2) Registration
                    """);
            int menu = in.nextInt();
            switch (menu){
                case 1 -> logIn.login();
                case 2 -> Registration.reg();
            }
        }
        public static void forTheSeller() throws SQLException {
            System.out.println("""
                    1) Add a product
                    2) List of products
                    3) Exit
                    """);
            int menu = in.nextInt();
            switch (menu){
                case 1 -> Product.addProduct();
                case 2 -> Seller.getCurrentUser().showOwnProducts();
                case 3 -> {
                    User.setCurrentUser(null);
                    Menu.loginMethod();
                }
            }
            System.out.println("\n");
            forTheSeller();
        }
        public static void forTheBuyer() throws SQLException {
            System.out.println("""
                    1) List of products
                    2) Buy products
                    3) Exit
                    """);
            int menu = in.nextInt();
            switch (menu){
                case 1 -> Product.infoAllProducts();
                case 2 -> {
                    ArrayList<Integer> idsOfProducts = new ArrayList<>();
                    while (in.hasNextInt()) {
                        idsOfProducts.add(in.nextInt());
                    }
                    //
                }
                case 3 -> {
                    User.setCurrentUser(null);
                    Menu.loginMethod();
                }
            }

            forTheBuyer();
        }
    }
}