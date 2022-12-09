package tech.wiktor.apis.kpbatapi.controllers.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import tech.wiktor.apis.kpbatapi.enums.RoleEnum;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
public class CreateUserRequest {

    @NotEmpty(message = "email field can't be null")
    @Email(message = "email format is invalid!")
    private String email;

    @NotEmpty(message = "name field can't be null")
    private String name;

    @NotNull(message = "role field can't be null")
    private RoleEnum role;
}
