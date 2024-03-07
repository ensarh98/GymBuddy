package com.gymbuddy.gym;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents an Gym in the system.
 *
 * @author Ensar Horozović
 */
@Getter
@Setter
public class Gym {

    private Integer id;
    private String name;
    private Integer capacity;
    private Integer members;
}