package com.hendisantika.springburger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Project : SpringBurger
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 30/10/25
 * Time: 06.20
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class IndexController {

    // Instance Variables (ivars)
    private static String url;
    private static String userName;
    private static String password;

    @GetMapping("/")
    public String index(Model model) {
        // Connect to MySQL Database
        try {

            // Create MySQL Connection based on localhost or Heroku deployment
            MyDatabaseConnection myDatabaseCredentials = new MyDatabaseConnection(System.getenv("JAWSDB_MARIA_URL"));
            url = myDatabaseCredentials.getDatabaseURL();
            userName = myDatabaseCredentials.getUsername();
            password = myDatabaseCredentials.getPassword();
            Connection conn = DriverManager.getConnection(url, userName, password);


            // Execute SQL Query for Available Burgers
            Statement findAllReady = conn.createStatement();
            ResultSet readyBurgers = findAllReady.executeQuery("SELECT * FROM burgers WHERE devoured = false");

            // Loop over the Query ResultSet and Append to HashMap of Edible Burgers
            HashMap<Integer, String> edibleBurgers = new HashMap<Integer, String>();

            while (readyBurgers.next()) {

                // Get Fields of Current ResultSet Row
                int burgerId = readyBurgers.getInt("id");
                String burgerName = readyBurgers.getString("burgerName");

                // Print Fields (for de-bugging)
                // String p = burgerId + " | " + burgerName;
                // System.out.println(p);

                // Append Burger to Edible HashMap
                edibleBurgers.put(burgerId, burgerName);

            }

            // Append Edible Burgers to model
            model.addAttribute("edibleBurgers", edibleBurgers);


            // Execute SQL Query for Eaten Burgers
            Statement findAllEaten = conn.createStatement();
            ResultSet eatenBurgers = findAllEaten.executeQuery("SELECT * FROM burgers, devourers WHERE devourers.burgerid = burgers.id");

            // Loop over the Query ResultSet and Append to HashMap of Edible Burgers
            HashMap<Integer, String[]> consumedBurgers = new HashMap<Integer, String[]>();

            while (eatenBurgers.next()) {

                // Get Fields of Current ResultSet Row
                int burgerId2 = eatenBurgers.getInt("id");
                String burgerName2 = eatenBurgers.getString("burgerName");
                String devourerName2 = eatenBurgers.getString("devourerName");
                String[] burgerNameAndDevourerName = {burgerName2, devourerName2};

                // Print Fields (for de-bugging)
                // String p2 = burgerId2 + " | " + burgerName2 + " | " + devourerName2;
                // System.out.println(p2);

                // Append Burger to Edible HashMap
                consumedBurgers.put(burgerId2, burgerNameAndDevourerName);

            }

            // Append Edible Burgers to model
            model.addAttribute("consumedBurgers", consumedBurgers);


            // Close MySQL Connection
            conn.close();

        } catch (SQLException err) {
            System.out.println(err);
        }

        // Render the index.html page
        return "index";
    }

}
