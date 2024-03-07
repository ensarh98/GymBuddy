package com.gymbuddy.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an gym in the system.
 * This entity is mapped to the "gyms" table in the database.
 *
 * @author Ensar Horozović
 */
@Getter
@Setter
@Entity
@Table(schema = "core", name = "gyms")
public class GymRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer capacity;
    private Integer members;
}
