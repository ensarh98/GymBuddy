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
    private String membership_package;
    private String opening_hours;
    private boolean parking_spaces;
    private boolean showers;
    private boolean sauna;
    private boolean tanning_booths;
    private boolean trainers;
    private boolean group_classes;
    private boolean nutrition_counseling;
    private boolean locker_rooms;
}
