package com.gymbuddy.db.repository;

import com.gymbuddy.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

        @Query("""
                        select t from Token t
                        inner join UserRecord u on t.user_id = u.id
                        where u.id = :userId and t.isLoggedOut = false
                        """)
        List<Token> findAllTokensByUser(Integer userId);

        @Query("""
                        select t from Token t where t.token = :token
                        """)
        Token findToken(String token);

        @Query("""
                        select t.user_id from Token t where t.token = :token
                        """)
        Integer findUserIdByToken(String token);

        Optional<Token> findByToken(String token);
}
