package com.hendisantika.springburger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

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

@Entity
public class Devourer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long burgerId;
    private String devourerName;

    public Devourer(String devourerName) {
        this.devourerName = devourerName;
    }

    public Devourer(String devourerName, Long burgerId) {
        this.devourerName = devourerName;
        this.burgerId = burgerId;
    }

    public String getDevourerName() {
        return devourerName;
    }

    public Devourer() {
    }

    public Long getBurgerId() {
        return burgerId;
    }

    public void setBurgerId(Long burgerId) {
        this.burgerId = burgerId;
    }

    public void setDevourerName(String devourerName) {
        this.devourerName = devourerName;
    }
}
