import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Menu.loginMethod();
    }

    class Menu{
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
        public static void forTheSeller(){
            System.out.println("""
                    1) Add a product
                    2) List of products
                    """);
            int menu = in.nextInt();
            switch (menu){
//                case 1 ->
                case 2 -> Product.infoAllCategoty();
            }
        }
        public static void forTheBuyer() throws SQLException {
            System.out.println("""
                    1) List of products
                    2) Buy products
                    """);
            int menu = in.nextInt();
            switch (menu){
    //            case 1 -> Product.infoAllProducts();
//                case 2 ->
            }
        }
    }
}