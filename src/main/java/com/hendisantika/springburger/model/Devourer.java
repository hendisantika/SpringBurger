package com.hendisantika.springburger.model;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-burger
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/03/18
 * Time: 07.03
 * To change this template use File | Settings | File Templates.
 */
public class Devourer {

    private String devourerName;
    private int burgerId;

    // Constructor
    public Devourer(String devourerName, int burgerId) {
        this.devourerName = devourerName;
        this.burgerId = burgerId;
    }

    // Getter for Devourer Name
    public String getDevourerName() {
        return devourerName;
    }

    // Getter for Devoured Burger Id
    public int getBurgerId() {
        return burgerId;
    }
}
