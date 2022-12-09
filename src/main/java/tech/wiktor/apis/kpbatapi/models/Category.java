package tech.wiktor.apis.kpbatapi.models;

import lombok.*;
import tech.wiktor.apis.kpbatapi.controllers.categories.CreateCategoryRequest;

import javax.persistence.*;

@Entity(name = "categories")
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String miniature;

    public Category(CreateCategoryRequest createCategoryRequest){
        this.name = createCategoryRequest.getName();
        this.description = createCategoryRequest.getDescription();
    }
    public Category() {}
}