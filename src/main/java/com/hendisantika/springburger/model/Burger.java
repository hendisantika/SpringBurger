package com.hendisantika.springburger.model;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-burger
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/03/18
 * Time: 07.02
 * To change this template use File | Settings | File Templates.
 */
public class Burger {
    private String burgerName;
    private boolean devoured;

    // Constructor
    public Burger(String burgerName) {
        this.burgerName = burgerName;
        this.devoured = false;
    }

    // Getter for Burger Name
    public String getBurgerName() {
        return burgerName;
    }

    // Getter for Burger Status
    public boolean getDevoured() {
        return devoured;
    }
}
