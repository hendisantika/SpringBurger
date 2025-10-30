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
    private String databaseURL;

    // Constructor
    public MyDatabaseConnection(String JAWSDB_MARIA_URL) {

        // Check if running in Docker (Spring datasource URL is set)
        String springDatasourceUrl = System.getenv("SPRING_DATASOURCE_URL");
        String springDatasourceUsername = System.getenv("SPRING_DATASOURCE_USERNAME");
        String springDatasourcePassword = System.getenv("SPRING_DATASOURCE_PASSWORD");

        if (springDatasourceUrl != null && springDatasourceUsername != null && springDatasourcePassword != null) {
            log.info("Connecting using Spring datasource configuration...");
            databaseURL = springDatasourceUrl;
            userName = springDatasourceUsername;
            password = springDatasourcePassword;

            log.info("Database URL: {}, Username: {}", databaseURL, userName);
        }
        // Localhost
        else if (JAWSDB_MARIA_URL == null) {
            log.info("Connecting to localhost...");
            databaseURL = "jdbc:mysql://localhost:3306/burgers?createDatabaseIfNotExist=true";
            userName = "yu71";
            password = "53cret";

            log.info("Database URL: {}, Username: {}", databaseURL, userName);
        }
        // AWS/Heroku
        else {
            log.info("Connecting to cloud database...");
            databaseURL = "jdbc:mysql://" + JAWSDB_MARIA_URL.split("@")[1];
            userName = JAWSDB_MARIA_URL.split(":")[1].split("//")[1];
            password = JAWSDB_MARIA_URL.split(":")[2].split("@")[0];

            log.info("Database URL: {}, Username: {}", databaseURL, userName);
        }

        // Set Variables
        this.databaseURL = databaseURL;
        this.userName = userName;
        this.password = password;
    }

    public String getDatabaseURL() {
        return databaseURL;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
