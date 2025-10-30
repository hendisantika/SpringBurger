package com.hendisantika.springburger.controller;

import com.hendisantika.springburger.config.MyDatabaseConnection;
import com.hendisantika.springburger.model.Burger;
import com.hendisantika.springburger.model.Devourer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
@Slf4j
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
                String burgerName = readyBurgers.getString("burger_name");

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
            ResultSet eatenBurgers = findAllEaten.executeQuery("SELECT * FROM burgers, devourers WHERE devourers.burger_id = burgers.id");

            // Loop over the Query ResultSet and Append to HashMap of Edible Burgers
            HashMap<Integer, String[]> consumedBurgers = new HashMap<Integer, String[]>();

            while (eatenBurgers.next()) {

                // Get Fields of Current ResultSet Row
                int burgerId2 = eatenBurgers.getInt("id");
                String burgerName2 = eatenBurgers.getString("burger_name");
                String devourerName2 = eatenBurgers.getString("devourer_name");
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
            log.error(String.valueOf(err));
        }

        // Render the index.html page
        return "index";
    }

    @GetMapping("/devour/{burgerId}")
    public String devour(@PathVariable(value = "burgerId") int burgerId, @RequestParam(value = "burgerEater", required = true) String burgerEater) {

        // Print Fields: Burger Id comes from URL Path Variable and Burger Devourer Name from Request Parameter
        log.info("Yummy!");
        log.info("Burger Id: \"{}\" and Devourer Name: \"{}\"", burgerId, burgerEater);

        // If no name is given, default to Anonymous
        if (burgerEater == "") {
            burgerEater = "Anonymous";
        }


        // Create new Devourer class using Burger Id and Devourer Name (this is more for practice than anything else)
        Devourer newBurgerEater = new Devourer((long) burgerId, burgerEater);


        // Connect to MySQL Database
        try {

            // Create MySQL Connection based on localhost or Heroku deployment
            MyDatabaseConnection myDatabaseCredentials = new MyDatabaseConnection(System.getenv("JAWSDB_MARIA_URL"));
            url = myDatabaseCredentials.getDatabaseURL();
            userName = myDatabaseCredentials.getUsername();
            password = myDatabaseCredentials.getPassword();
            Connection conn = DriverManager.getConnection(url, userName, password);


            // Update selected burger to devoured
            Statement updateDevouredBurger = conn.createStatement();
            updateDevouredBurger.executeUpdate("UPDATE burgers SET devoured=true WHERE id = " + burgerId);


            // Insert a new devourer
            PreparedStatement preparedStmt = conn.prepareStatement("INSERT INTO devourers(devourer_name, burger_id) VALUES (?, ?)");
            preparedStmt.setString(1, newBurgerEater.getDevourerName());
            preparedStmt.setLong(2, newBurgerEater.getBurgerId());
            preparedStmt.execute();


            // Close MySQL Connection
            conn.close();

        } catch (SQLException err) {
            log.error(String.valueOf(err));
        }

        // Re-direct to index route to re-render the page with new devourer
        return "redirect:/";

    }

    @PostMapping("/cook")
    public String devour(@RequestParam(value = "burgerName") String burgerName) {

        // Print Fields: Burger Name comes from Request Parameter
        log.info("Order Up!");
        log.info("Burger Name: \"{}\"", burgerName);

        // If no name is given, give a default name
        if (burgerName == "") {
            burgerName = "Default Burger";
        }

        // Create new Burger class using Burger Name (this is more for practice than anything else)
        Burger newBurger = new Burger(burgerName);


        // Connect to MySQL Database
        try {

            // Create MySQL Connection based on localhost or Heroku deployment
            MyDatabaseConnection myDatabaseCredentials = new MyDatabaseConnection(System.getenv("JAWSDB_MARIA_URL"));
            url = myDatabaseCredentials.getDatabaseURL();
            userName = myDatabaseCredentials.getUsername();
            password = myDatabaseCredentials.getPassword();
            Connection conn = DriverManager.getConnection(url, userName, password);

            // Insert a new burger
            PreparedStatement preparedStmt = conn.prepareStatement("INSERT INTO burgers(burger_name, devoured) VALUES (?, ?)");
            preparedStmt.setString(1, newBurger.getBurgerName());
            preparedStmt.setBoolean(2, newBurger.isDevoured());
            preparedStmt.execute();

            // Close MySQL Connection
            conn.close();

        } catch (SQLException err) {
            log.error(String.valueOf(err));
        }


        // Re-direct to index route to re-render the page with new burger
        return "redirect:/";

    }


}
