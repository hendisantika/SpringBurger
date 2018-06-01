package com.hendisantika.springburger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String burgerName;
    private boolean devoured;

    // Constructor
    public Burger(String burgerName) {
        this.burgerName = burgerName;
        this.devoured = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBurgerName() {
        return burgerName;
    }

    public void setBurgerName(String burgerName) {
        this.burgerName = burgerName;
    }

    public boolean isDevoured() {
        return devoured;
    }

    public void setDevoured(boolean devoured) {
        this.devoured = devoured;
    }
}
