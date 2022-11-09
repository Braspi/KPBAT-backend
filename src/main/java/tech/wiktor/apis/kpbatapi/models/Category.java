package tech.wiktor.apis.kpbatapi.models;

import javax.persistence.*;

@Entity(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String miniature;
}