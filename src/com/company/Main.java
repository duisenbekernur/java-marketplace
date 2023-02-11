package com.company;

import com.company.auth.Registration;
import com.company.auth.logIn;
import com.company.entities.Buyer;
import com.company.entities.Product;
import com.company.entities.Seller;
import com.company.entities.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws SQLException {
        Menu.loginMethod();
    }
}