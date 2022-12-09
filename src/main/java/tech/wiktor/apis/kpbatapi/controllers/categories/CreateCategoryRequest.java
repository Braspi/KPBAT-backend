package tech.wiktor.apis.kpbatapi.controllers.categories;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateCategoryRequest {
    @NotEmpty(message = "name field can't be null")
    private String name;

    @NotEmpty(message = "description field can't be null")
    private String description;
}
