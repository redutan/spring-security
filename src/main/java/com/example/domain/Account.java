package com.example.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author redutan
 * @since 2015. 11. 9.
 */
@Entity
@Data
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String role;

    private String nick;
}
