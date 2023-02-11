package com.company;

import com.company.auth.Registration;
import com.company.auth.logIn;
import com.company.entities.Buyer;
import com.company.entities.Product;
import com.company.entities.Seller;
import com.company.entities.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    static Scanner in = new Scanner(System.in);
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
            case 1 -> Buyer.printListOfProducts();
            case 2 -> Buyer.buyProductMenu();
            case 3 -> {
                User.setCurrentUser(null);
                Menu.loginMethod();
            }
        }

        forTheBuyer();
    }
}