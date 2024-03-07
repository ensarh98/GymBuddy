package com.gymbuddy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymbuddy.db.model.GymRecord;

/**
 * Repository interface for managing Gym instances.
 *
 * @author Ensar Horozović
 */
@Repository
public interface GymRepository extends JpaRepository<GymRecord, Integer> {

}
