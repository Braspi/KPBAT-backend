package tech.wiktor.apis.kpbatapi.controllers.auth;

import lombok.Getter;

@Getter
public class AuthResponse {
    private String email;
    private String accessToken;

    public AuthResponse(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }
}