package com.gymbuddy.token;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "core", name = "tokens")
@Setter
@Getter
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;

    @Column(name = "is_logged_out")
    private boolean isLoggedOut;

    @Column(name = "user_id")
    private Integer user_id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLoggedOut() {
        return isLoggedOut;
    }

    public void setLoggedOut(boolean loggedOut) {
        this.isLoggedOut = loggedOut;
    }

    public void setUserId(Integer id2) {
        this.user_id = id2;
    }

    public Integer getUserId() {
        return user_id;
    }
}
