package com.gymbuddy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gymbuddy.db.model.UserRecord;

/**
 * Repository interface for managing User instances.
 *
 * @author Ensar Horozović
 */
@Repository
public interface UserRepository extends JpaRepository<UserRecord, Integer> {

        @Query("""
                        select u from UserRecord as u where u.email = :email
                        """)
        UserRecord findByUserEmail(@Param("email") String email);

        @Query("""
                        select u from UserRecord as u where u.id = :id
                        """)
        UserRecord findUserById(@Param("id") Integer id);
}
