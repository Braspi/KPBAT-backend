package tech.wiktor.apis.kpbatapi.controllers.auth;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AuthRequest {
    @NotEmpty(message = "email field can't be null")
    @Email(message = "email format is invalid!")
    private String email;

    @NotEmpty(message = "password field can't be null")
    private String password;
}