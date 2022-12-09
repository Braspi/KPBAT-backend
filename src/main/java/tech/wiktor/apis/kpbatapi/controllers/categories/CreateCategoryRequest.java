package tech.wiktor.apis.kpbatapi.controllers.categories;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateCategoryRequest {
    @NotEmpty(message = "name field can't be null")
    @Min(value = 4, message = "name min length is 4!")
    private String name;

    @NotEmpty(message = "description field can't be null")
    private String description;
}
