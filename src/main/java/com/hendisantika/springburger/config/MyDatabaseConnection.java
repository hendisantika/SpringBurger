package com.hendisantika.springburger.config;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 * Project : SpringBurger
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 30/10/25
 * Time: 06.24
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class MyDatabaseConnection {
    private String userName;
    private String password;
    private String dataBaseURL;

    // Constructor
    public MyDatabaseConnection(String JAWSDB_MARIA_URL) {

        // Localhost
        if (JAWSDB_MARIA_URL == null) {
            log.info("Connecting to localhost...");
            dataBaseURL = "jdbc:mysql://localhost:3306/burgers_db?createDatabaseIfNotExist=true";
            userName = "root";
            password = "root"; // "root" on PC or "" on Mac

            log.info("{}\n{}\n{}", dataBaseURL, userName, password);
        }
        // Heroku
        else {
            log.info("Connecting to AWS...");
            dataBaseURL = "jdbc:mysql://" + JAWSDB_MARIA_URL.split("@")[1];
            userName = JAWSDB_MARIA_URL.split(":")[1].split("//")[1];
            password = JAWSDB_MARIA_URL.split(":")[2].split("@")[0];

            log.info("{}\n{}\n{}", dataBaseURL, userName, password);
        }

        // Set Variables
        this.dataBaseURL = dataBaseURL;
        this.userName = userName;
        this.password = password;

    }

    // Getter for database URL
    public String getDatabaseURL() {
        return dataBaseURL;
    }

    // Getter for username
    public String getUsername() {
        return userName;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

}
