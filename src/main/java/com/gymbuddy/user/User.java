package com.gymbuddy.user;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a User in the system.
 *
 * @author Ensar Horozović
 */

@Setter
@Getter
public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isEnabled;
}
