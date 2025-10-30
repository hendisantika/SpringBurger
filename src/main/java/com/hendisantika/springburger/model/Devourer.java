package com.hendisantika.springburger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "devourers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Devourer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String devourerName;
    private Long burgerId;

    // Constructor for creating devourer with just name
    public Devourer(String devourerName) {
        this.devourerName = devourerName;
    }

    // Constructor for creating devourer with name and burger ID
    public Devourer(Long burgerId, String devourerName) {
        this.burgerId = burgerId;
        this.devourerName = devourerName;
    }
}
