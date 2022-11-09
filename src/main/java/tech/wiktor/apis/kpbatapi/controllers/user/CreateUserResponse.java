package tech.wiktor.apis.kpbatapi.controllers.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserResponse {
    private String email;
    private String password;
}
