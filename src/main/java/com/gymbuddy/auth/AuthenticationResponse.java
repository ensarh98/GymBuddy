package com.gymbuddy.auth;

import com.gymbuddy.db.model.UserRecord;

public class AuthenticationResponse {

    private UserRecord user;
    private String token;
    private String message;

    public AuthenticationResponse(UserRecord user, String token, String message) {
        this.user = user;
        this.token = token;
        this.message = message;
    }

    public UserRecord getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

}
