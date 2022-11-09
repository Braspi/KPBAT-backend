package tech.wiktor.apis.kpbatapi.controllers.user;

import lombok.Getter;
import lombok.Setter;
import tech.wiktor.apis.kpbatapi.enums.RoleEnum;
import tech.wiktor.apis.kpbatapi.models.User;

@Getter
@Setter
public class UsersListResponse {
    private Long id;
    private String email;
    private RoleEnum role;

    public UsersListResponse(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
