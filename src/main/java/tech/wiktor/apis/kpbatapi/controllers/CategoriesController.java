package tech.wiktor.apis.kpbatapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wiktor.apis.kpbatapi.controllers.categories.CreateCategoryRequest;
import tech.wiktor.apis.kpbatapi.models.Category;
import tech.wiktor.apis.kpbatapi.repositories.CategoryRepository;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/categories")
public class CategoriesController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(this.categoryRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category category = new Category(createCategoryRequest);
        this.categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}
