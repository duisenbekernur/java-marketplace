package com.company.entities;

import com.company.data.DBconnection;
import com.company.entities.interfaces.IBuyer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Buyer extends User implements IBuyer {
    private static Connection connection = null;
    private static PreparedStatement ps = null;;
    private static ResultSet productDatas = null;
    static Scanner in = new Scanner(System.in);


    public Buyer(int id, String username, String role) {
        super(id, username, role);
    }

    public static void printListOfProducts() throws SQLException {
        connection = DBconnection.connection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM products");
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
            for (Integer productID : productIDs) {
                if (productDatas.getInt("id") == productID) {
                    counter += productDatas.getInt("price");
                }
            }
        }

        return counter;
    }

    public static void buyProduct(ArrayList<Integer> productIDs) throws SQLException {
        connection = DBconnection.connection();
        Statement st = connection.createStatement();
        Statement stProducts = connection.createStatement();

        ResultSet userDatas = st.executeQuery("SELECT * FROM users");

        while (userDatas.next()) {
            ArrayList<Integer> productArray = new ArrayList<>();
            for (Integer productID : productIDs) {
                productDatas = stProducts.executeQuery("SELECT * FROM products");

                while (productDatas.next()) {
                    if (productDatas.getInt("id") == productID) {
                        if (productDatas.getInt("seller_id") == userDatas.getInt("id")) {
                            productArray.add(productID);
                        }
                    }
                }
            }

            if (productArray.isEmpty()) {
                continue;
            }

            Order.insertOrder(userDatas.getInt("id"), productArray);
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